package ui;

import analysis.ParallelAnalyzer;
import io.SampleFileReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.AnalysisResult;
import model.WaveformChunk;
import util.ChunkSplitter;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.List;
import report.ReportWriter;

public class WaveformApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // 1. Read sample file
        SampleFileReader reader = new SampleFileReader();

        List<Double> samples =
                reader.readSamples("data/sample.csv");

        // 2. Split samples into chunks
        ChunkSplitter splitter = new ChunkSplitter();

        List<WaveformChunk> chunks =
                splitter.split(samples, 8);

        // 3. Analyse chunks using thread pool
        ParallelAnalyzer analyzer =
                new ParallelAnalyzer();

        List<AnalysisResult> results =
                analyzer.analyzeInParallel(chunks, 4);

        // 4. Create chart axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Chunk");
       yAxis.setLabel("Analysis Value");

        // 5. Create line chart
        LineChart<Number, Number> chart =
                new LineChart<>(xAxis, yAxis);

        chart.setTitle("Parallel Waveform Analyser - Chunk Analysis");;

        // 6. Create RMS series
        XYChart.Series<Number, Number> rmsSeries =
                new XYChart.Series<>();

        rmsSeries.setName("RMS");
        XYChart.Series<Number, Number> peakSeries =
        new XYChart.Series<>();

peakSeries.setName("Peak");

XYChart.Series<Number, Number> zcrSeries =
        new XYChart.Series<>();

zcrSeries.setName("Zero-Crossing Rate");

       for (AnalysisResult result : results) {

    rmsSeries.getData().add(
            new XYChart.Data<>(
                    result.getChunkId(),
                    result.getRms()
            )
    );

    peakSeries.getData().add(
            new XYChart.Data<>(
                    result.getChunkId(),
                    result.getPeak()
            )
    );

    zcrSeries.getData().add(
            new XYChart.Data<>(
                    result.getChunkId(),
                    result.getZeroCrossingRate()
            )
    );
}

       chart.getData().addAll(
        rmsSeries,
        peakSeries,
        zcrSeries
);
Button reportButton = new Button("Generate Report");
reportButton.setOnAction(event -> {

    try {

        ReportWriter reportWriter =
                new ReportWriter();

        reportWriter.writeReport(
                results,
                "data/analysis_report.txt"
        );

        System.out.println(
                "Report generated successfully!"
        );

    } catch (Exception e) {

        e.printStackTrace();
    }
});

        // 8. Display chart
       VBox layout = new VBox(10);

layout.getChildren().addAll(
        chart,
        reportButton
);

Scene scene = new Scene(layout, 800, 550);
        stage.setTitle("Parallel Waveform Analyser");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}