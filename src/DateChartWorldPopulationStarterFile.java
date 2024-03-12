import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateChartWorldPopulationStarterFile {

    public static void main(String[] args) {

        DateChartWorldPopulationStarterFile worldPop = new DateChartWorldPopulationStarterFile();
        // size of display
        XYChart chart = new XYChartBuilder().width(800).height(600).title("World Population").xAxisTitle("Date").yAxisTitle("Value").build();

        try {
            // Step 2:  Read the CSV File, then add the series to the chart
            List<List> allSeries = worldPop.importCSV(new File("resources/WorldPopulation.csv"));
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

    public List<List> importCSV (File file) throws IOException, ParseException {
        List<List> allSeries = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
//      Set up your reader and BufferedReader stream.
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        // Skip the header in the CSV file,  we don't need to process it
        br.readLine();

        // Read and parse the rest of the lines
        String line;
        while ((line = br.readLine()) != null) {
            //  Add your code here:  pseudocode follows:
            //  Split the line by comma and assign to String[] parts like this:
            // String[] parts = line.split(",");
            /*
              Assign the datas and values to variables date and value
             */
            //  Assign parts[0] to  date, calling parseDate like this:    Date date = parseDate(parts[0].trim());
            //  It is a good idea to trim any spaces just in case
            //  Assign parts[1] to  value, calling parseInt like this.  See the csv file, there are spaces between the nubmers where there
            //  were commas before
            //  Integer value = Integer.parseInt(parts[1].replaceAll(" ", "").trim());
            // Assuming the first column is date and the second column is the value

   //         Date date = parseDate(parts[0].trim());
     //       Integer value = Integer.parseInt(parts[1].replaceAll(" ", "").trim());
      /*
              Add the date and value to the dates and values ArrayLists
       */

            //      dates.add(date);
            //  Reduce numbers of zeros in population.  Divide the populations by 1000 for better display
           //  values.add(value / 1000);
        }
        // add the dates and values ArrayLists to the list of lists List<List>
        //allSeries.add(dates);
        //allSeries.add(values);
        // return allSeries;
        return allSeries;
    }

}
