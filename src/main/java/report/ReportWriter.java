package report;

import model.AnalysisResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.time.LocalDateTime;
public class ReportWriter {

    public void writeReport(
            List<AnalysisResult> results,
            String fileName) throws IOException {

        StringBuilder report = new StringBuilder();

       report.append("PARALLEL WAVEFORM ANALYSIS REPORT\n");
report.append("=================================\n");

report.append("Generated: ")
      .append(LocalDateTime.now())
      .append("\n\n");

        report.append(String.format(
                "%-10s %-12s %-12s %-15s%n",
                "Chunk", "RMS", "Peak", "Zero-Crossing"
        ));

        report.append(
                "--------------------------------------------------\n"
        );

        for (AnalysisResult result : results) {

            report.append(String.format(
                    "%-10d %-12.4f %-12.4f %-15.4f%n",
                    result.getChunkId(),
                    result.getRms(),
                    result.getPeak(),
                    result.getZeroCrossingRate()
            ));
        }

        report.append("\nTotal Chunks: ")
                .append(results.size())
                .append("\n");

        Files.writeString(
                Path.of(fileName),
                report.toString()
        );
    }
}