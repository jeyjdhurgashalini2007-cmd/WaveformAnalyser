package analysis;

import model.AnalysisResult;
import model.WaveformChunk;

import java.util.List;

public class WaveformAnalyzer {

    public AnalysisResult analyze(WaveformChunk chunk) {

        List<Double> samples = chunk.getSamples();

        double sumSquares = 0;
        double peak = 0;
        int zeroCrossings = 0;

        for (int i = 0; i < samples.size(); i++) {

            double current = samples.get(i);

            // RMS calculation
            sumSquares += current * current;

            // Peak calculation
            peak = Math.max(peak, Math.abs(current));

            // Zero-crossing calculation
            if (i > 0) {

                double previous = samples.get(i - 1);

                if ((previous >= 0 && current < 0) ||
                    (previous < 0 && current >= 0)) {

                    zeroCrossings++;
                }
            }
        }

        double rms =
                Math.sqrt(sumSquares / samples.size());

        double zeroCrossingRate =
                (double) zeroCrossings / samples.size();

        return new AnalysisResult(
                chunk.getChunkId(),
                rms,
                peak,
                zeroCrossingRate
        );
    }
}