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

public class DataChartUnemployment {

    public static void main(String[] args) {
        DataChartUnemployment unemployment = new DataChartUnemployment();
        unemployment.drawChart();
    }

       public void drawChart() {
           // Step 1: Create a chart
           XYChart chart = new XYChartBuilder().width(800).height(600).title("Date Chart").xAxisTitle("Date").yAxisTitle("Value").build();

           try {
               // Step 2: Get CSV data
               List<List> allSeries = getAllSeries(chart, new File("resources/UnemploymentDataUS.csv"));
               List<Date> dates = allSeries.get(0);
               List<Integer> values = allSeries.get(1);
               chart.addSeries("Unemployment US Series", dates, values);

               // Step 3: Customize the chart
               chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
               chart.getStyler().setXAxisTickMarkSpacingHint(100); // Adjust the tick mark spacing for better readability
               chart.getStyler().setDatePattern("MM-yyyy");
               // Step 4: Display the chart
               chart.setYAxisTitle("Unemployment Rate");
               chart.setXAxisTitle("Month Year");
               chart.setTitle("Unemployment US 1948-2020");
               new SwingWrapper<>(chart).displayChart();

           } catch (IOException | ParseException e) {
               e.printStackTrace();
           }
       }

     public List<List> getAllSeries(XYChart chart, File file) throws IOException, ParseException {
            List<Date> dates = new ArrayList<>();
            List<Double> values = new ArrayList<>();

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
                    Double value = Double.parseDouble(parts[1].trim());
                    dates.add(date);
                    values.add(value);
                }
            }
            List<List> allSeries = new ArrayList<List>();;
            allSeries.add(dates);
            allSeries.add(values);
            return allSeries;
        }

        private Date parseDate(String dateString) throws ParseException {
            // Manually parse the date based on your date format
            return new SimpleDateFormat("MM/dd/YYYY").parse(dateString);
        }
    }

