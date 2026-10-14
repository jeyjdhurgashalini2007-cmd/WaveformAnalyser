package util;

import model.WaveformChunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkSplitter {

    public List<WaveformChunk> split(List<Double> samples, int chunkSize) {

        List<WaveformChunk> chunks = new ArrayList<>();

        int chunkId = 1;

        for (int start = 0; start < samples.size(); start += chunkSize) {

            int end = Math.min(start + chunkSize, samples.size());

            List<Double> chunkSamples =
                    new ArrayList<>(samples.subList(start, end));

            chunks.add(new WaveformChunk(chunkId, chunkSamples));

            chunkId++;
        }

        return chunks;
    }
}