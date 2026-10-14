
package analysis;

import model.AnalysisResult;
import model.WaveformChunk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelAnalyzer {

    public List<AnalysisResult> analyzeInParallel(
            List<WaveformChunk> chunks,
            int numberOfThreads) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfThreads);

        List<Future<AnalysisResult>> futures =
                new ArrayList<>();

        WaveformAnalyzer analyzer =
                new WaveformAnalyzer();

        for (WaveformChunk chunk : chunks) {

            Future<AnalysisResult> future =
                    executor.submit(() -> {

                        System.out.println(
                                "Analysing Chunk "
                                        + chunk.getChunkId()
                                        + " on "
                                        + Thread.currentThread().getName()
                        );

                        return analyzer.analyze(chunk);
                    });

            futures.add(future);
        }

        List<AnalysisResult> results =
                new ArrayList<>();

        for (Future<AnalysisResult> future : futures) {
            results.add(future.get());
        }

        executor.shutdown();

        return results;
    }
}