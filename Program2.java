import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This class represents a program for visualizing stock data using JavaFX.
 * It extends the Application class and provides methods for reading stock data
 * from CSV files, processing the data, and displaying it in a line chart.
 */
public class Program2 extends Application {
	
	private ArrayList<Stock> appleStocks = new ArrayList<>();
    private ArrayList<Stock> hsbcStocks = new ArrayList<>();
    private ArrayList<Stock> nikeStocks = new ArrayList<>();
    private ArrayList<Stock> oracleStocks = new ArrayList<>();
    private Program1 p = new Program1();
    
    /**
     * Reads stock data from a CSV file and populates the corresponding stock list.
     *
     * @param name The name of the stock.
     * @param path The path to the CSV file.
     */
    private void readStockData(String name, String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            boolean firstLineSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip the first line
                }
                String[] data = line.split(",");
                String originalDate = data[0];
                String date = p.formatDateString(originalDate, name);
                float open = Float.parseFloat(data[1]);
                float high = Float.parseFloat(data[2]);
                float low = Float.parseFloat(data[3]);
                float close = Float.parseFloat(data[4]);
                double volume = Double.parseDouble(data[5]);
                if (name.equals("Apple")) {
                    appleStocks.add(new Apple(originalDate, open, high, low, close, volume));
                } else if (name.equals("HSBC")) {
                    hsbcStocks.add(new HSBC(date, open, high, low, close, volume));
                } else if (name.equals("Nike")) {
                    nikeStocks.add(new Nike(date, open, high, low, close, volume));
                } else if (name.equals("Oracle")) {
                    oracleStocks.add(new Oracle(date, open, high, low, close, volume));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves a stock object from the given list based on the specified date.
     *
     * @param stocks The list of stocks.
     * @param date   The date to search for.
     * @return The stock object with the specified date, or null if not found.
     */
    private Stock getStockByDate(ArrayList<Stock> stocks, String date) {
        for (Stock stock : stocks) {
            if (stock.getDate().equals(date)) {
                return stock;
            }
        }
        return null;
    }
    

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void start(Stage stage) {
        System.out.println("java StockDataVisualization");
        System.out.print("Enter the stock files to be processed: ");
        String stock = p.readInput();
        String[] files = stock.split(" ");

        for (String file : files) {
            if (file.contains("Apple")) {
                String path = "src/Apple.csv";
                readStockData("Apple", path);
            } else if (file.contains("HSBC")) {
                String path = "src/HSBC.csv";
                readStockData("HSBC", path);
            } else if (file.contains("Nike")) {
                String path = "src/Nike.csv";
                readStockData("Nike", path);
            } else if (file.contains("Oracle")) {
                String path = "src/Oracle.csv";
                readStockData("Oracle", path);
            }
        }

        // Sort the data points based on date for each series
        Collections.sort(appleStocks, (s1, s2) -> s1.getDate().compareTo(s2.getDate()));
        Collections.sort(hsbcStocks, (s1, s2) -> s1.getDate().compareTo(s2.getDate()));
        Collections.sort(nikeStocks, (s1, s2) -> s1.getDate().compareTo(s2.getDate()));
        Collections.sort(oracleStocks, (s1, s2) -> s1.getDate().compareTo(s2.getDate()));

        // Create a list of all unique dates across all series
        ArrayList<String> dates = new ArrayList<>();
        for (Stock stock1 : appleStocks) {
            String date = stock1.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        for (Stock stock1 : hsbcStocks) {
            String date = stock1.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        for (Stock stock1 : nikeStocks) {
            String date = stock1.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        for (Stock stock1 : oracleStocks) {
            String date = stock1.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
     // Sort the dates in chronological order
        Collections.sort(dates);

        // Add data points to each series for each date
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Apple");
        int interval = 10; // Display a node for every 10 dates
        int count = 0;
        for (String date : dates) {
        	if (count % interval == 0) {
        		Stock stock1 = getStockByDate(appleStocks, date);
        		if (stock1 != null) {
        			series1.getData().add(new XYChart.Data<>(date, stock1.getClose()));
        		}
            }
        	count++;
        }

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("HSBC");
        int interval1 = 10; // Display a node for every 10 dates
        int count1 = 0;
        for (String date : dates) {
        	if (count1 % interval1 == 0) {
        		Stock stock1 = getStockByDate(hsbcStocks, date);
        		if (stock1 != null) {
        			series2.getData().add(new XYChart.Data<>(date, stock1.getClose()));
        		}
            }
        	count1++;
        }

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Nike");
        int interval2 = 10; // Display a node for every 10 dates
        int count2 = 0;
        for (String date : dates) {
        	if (count2 % interval2 == 0) {
        		Stock stock1 = getStockByDate(nikeStocks, date);
        		if (stock1 != null) {
        			series3.getData().add(new XYChart.Data<>(date, stock1.getClose()));
        		}
            }
        	count2++;
        }

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Oracle");
        int interval3 = 10; // Display a node for every 10 dates
        int count3 = 0;
        for (String date : dates) {
        	if (count3 % interval3 == 0) {
                Stock stock1 = getStockByDate(oracleStocks, date);
                if (stock1 != null) {
                    series4.getData().add(new XYChart.Data<>(date, stock1.getClose()));
                }
            }
            count3++;
        }
        
        final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Date");
	    yAxis.setLabel("Close");
	    final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	    lineChart.setTitle("Stock Line Chart");

	    Scene scene = new Scene(lineChart, 800, 600);
	    lineChart.getData().addAll(series1, series2, series3, series4);

	    stage.setScene(scene);
	    stage.show();
    }
}
