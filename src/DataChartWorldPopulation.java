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

public class DataChartWorldPopulation {

    public static void main(String[] args) {

        DataChartWorldPopulation worldPop = new DataChartWorldPopulation();
        worldPop.drawChart();
    }

    public void drawChart() {
        // size of display
        XYChart chart = new XYChartBuilder().width(800).height(600).title("World Population").xAxisTitle("Date").yAxisTitle("Value").build();

        try {
            // Step 2:  Read the CSV File, then add the series to the chart
            List<List> allSeries = getAllSeries(new File("resources/WorldPopulation.csv"));
            chart.addSeries("World Population", allSeries.get(0), allSeries.get(1));
            // Step 3: Customize the chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setXAxisTickMarkSpacingHint(100); // Adjust the tick mark spacing for better readability
            chart.getStyler().setDatePattern("yyyy");
            // Step 4: Display the chart
            new SwingWrapper<>(chart).displayChart();
            chart.setYAxisTitle("World Population (Millions)");
            chart.setXAxisTitle("Year");
            chart.setTitle("World Population 1950-2020");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        // Parse the date based on your date format in csv file
        return new SimpleDateFormat("YYYY").parse(dateString);
    }

    public List<List> getAllSeries(File file) throws IOException, ParseException {
        List<List> allSeries = new ArrayList<>();
        List<Date> xData = new ArrayList<>();
        List<Integer> yData  = new ArrayList<>();

        FileReader reader = new FileReader(file);
        java.io.BufferedReader br = new java.io.BufferedReader(reader);

        // Skip the header
       String line = br.readLine();

        // Read and parse the rest of the lines

        while ((line = br.readLine()) != null) {
           // Add your code here
        }
        allSeries.add(xData);
        allSeries.add(yData);
        return allSeries;
    }

}
