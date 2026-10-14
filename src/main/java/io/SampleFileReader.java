package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SampleFileReader {

    public List<Double> readSamples(String fileName) throws IOException {

        List<Double> samples = new ArrayList<>();

        List<String> lines = Files.readAllLines(Path.of(fileName));

        for (String line : lines) {
            if (!line.isBlank()) {
                samples.add(Double.parseDouble(line.trim()));
            }
        }

        return samples;
    }
}