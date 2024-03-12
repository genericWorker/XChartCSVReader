import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataChartPopulation {

    public static void main(String[] args) {
        DataChartPopulation pop = new DataChartPopulation();
        pop.drawPopulationChart();
    }

    private void drawPopulationChart() {
        // Step 1: Create a chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Date Chart").xAxisTitle("Date").yAxisTitle("Value").build();

        try {
            // Step 2: Use CSVImporter to import data from a CSV file
            CSVImporter importer = new CSVImporter();
            importer.importCSV(chart, new File("resources/WorldPopulation.csv"));

            // Step 3: Customize the chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setXAxisTickMarkSpacingHint(100); // Adjust the tick mark spacing for better readability
            chart.getStyler().setDatePattern("yyyy");
            // Step 4: Display the chart
            chart.setYAxisTitle("World Population (Millions)");
            chart.setXAxisTitle("Year");
            chart.setTitle("World Population 1950-2020");
            new SwingWrapper<>(chart).displayChart();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

class CSVImporter {
    // Simulated method for importing CSV data
    void importCSV(XYChart chart, File file) throws IOException, ParseException {
        // Replace this with your CSV reading logic
        // Simulated data for demonstration purposes
        List<Date> dates = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        try (FileReader reader = new FileReader(file);
             java.io.BufferedReader br = new java.io.BufferedReader(reader)) {

            // Skip the header
            br.readLine();

            // Read and parse the rest of the lines
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming the first column is date and the second column is the value
                Date date = parseDate(parts[0].trim());
                Integer value = Integer.parseInt(parts[1].trim().replaceAll(" ", ""));
                dates.add(date);
                values.add(value / 1000);
                values.add(value);
            }
        }

        // Add series to the chart
        chart.addSeries("World Population", dates, values);
    }

    private Date parseDate(String dateString) throws ParseException {
        // Manually parse the date based on your date format
        return new SimpleDateFormat("YYYY").parse(dateString);
    }
}
