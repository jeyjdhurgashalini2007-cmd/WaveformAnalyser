package model;

public class AnalysisResult {

    private final int chunkId;
    private final double rms;
    private final double peak;
    private final double zeroCrossingRate;

    public AnalysisResult(
            int chunkId,
            double rms,
            double peak,
            double zeroCrossingRate) {

        this.chunkId = chunkId;
        this.rms = rms;
        this.peak = peak;
        this.zeroCrossingRate = zeroCrossingRate;
    }

    public int getChunkId() {
        return chunkId;
    }

    public double getRms() {
        return rms;
    }

    public double getPeak() {
        return peak;
    }

    public double getZeroCrossingRate() {
        return zeroCrossingRate;
    }

    @Override
    public String toString() {
        return "Chunk " + chunkId
                + " | RMS = " + rms
                + " | Peak = " + peak
                + " | ZCR = " + zeroCrossingRate;
    }
}