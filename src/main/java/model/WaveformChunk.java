package model;

import java.util.List;

public class WaveformChunk {

    private final int chunkId;
    private final List<Double> samples;

    public WaveformChunk(int chunkId, List<Double> samples) {
        this.chunkId = chunkId;
        this.samples = samples;
    }

    public int getChunkId() {
        return chunkId;
    }

    public List<Double> getSamples() {
        return samples;
    }
}