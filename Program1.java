import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
/**
 * This class represents a program for stock data processing.
 * It provides methods for reading stock data from CSV files, performing calculations,
 * and serializing/deserializing stock data.
 */
public class Program1 {

	ArrayList<Stock> appleStocks = new ArrayList<>();
    ArrayList<Stock> hsbcStocks = new ArrayList<>();
    ArrayList<Stock> nikeStocks = new ArrayList<>();
    ArrayList<Stock> oracleStocks = new ArrayList<>();
    
    /**
     * Processes the stock data by reading the user input, reading stock data from CSV files,
     * and performing various calculations and operations on the stock data.
     */
	private void StockDataProcessing(){
		
		System.out.println("java StockDataProcessing");
		System.out.print("Enter the stock files to be processed: ");
		String stock = readInput();
		String[] files = stock.split(" ");
		
		for (String file : files){
			if (file.contains("Apple")) {
				String path = "src\\Apple.csv";
				readStockData("Apple", path);
				
			}else if (file.contains("HSBC")) {
				String path = "src\\HSBC.csv";
				readStockData("HSBC", path);
			}else if (file.contains("Nike")) {
				String path = "src\\Nike.csv";
				readStockData("Nike", path);
			}else if (file.contains("Oracle")) {
				String path = "src\\Oracle.csv";
				readStockData("Oracle", path);
			}
		}
		
		System.out.println("--------------------Top 3 Volume----------------");
		for (String file : files) {
			if (file.contains("Apple")) {
				printTopThreeVolumes(appleStocks, "Apple");
			}else if (file.contains("HSBC")) {
				printTopThreeVolumes(hsbcStocks, "HSBC");
			}else if (file.contains("Nike")) {
				printTopThreeVolumes(nikeStocks, "Nike");
			}else if (file.contains("Oracle")) {
				printTopThreeVolumes(oracleStocks, "Oracle");
			}
		}
		
        System.out.println("-------------------Average Close Price----------------");
        for (String file : files) {
			if (file.contains("Apple")) {
				AverageClosePrice(appleStocks, "Apple");
			}else if (file.contains("HSBC")) {
				AverageClosePrice(hsbcStocks, "HSBC");
			}else if (file.contains("Nike")) {
				AverageClosePrice(nikeStocks, "Nike");
			}else if (file.contains("Oracle")) {
				AverageClosePrice(oracleStocks, "Oracle");
			}
		}
        
        System.out.println("--------------------Serialization----------------");
        for (String file : files) {
			if (file.contains("Apple")) {
				
				Serialization(appleStocks, "Apple");
				printFirstThreeStocks(appleStocks, "Apple");
				System.out.println("--------------------Save Apple Stock----------------");
				Deserialization("Apple");
				System.out.println("--------------------Restore Apple Stock----------------");
				printFirstThreeStocks(appleStocks, "Apple");
				System.out.println();
				
			}else if (file.contains("HSBC")) {
				
				Serialization(hsbcStocks, "HSBC");
				printFirstThreeStocks(hsbcStocks, "HSBC");
				System.out.println("--------------------Save HSBC Stock----------------");
				Deserialization("HSBC");
				System.out.println("--------------------Restore HSBC Stock----------------");
				printFirstThreeStocks(hsbcStocks, "HSBC");
				System.out.println();
				
			}else if (file.contains("Nike")) {
				
				Serialization(nikeStocks, "Nike");
				printFirstThreeStocks(nikeStocks, "Nike");
				System.out.println("--------------------Save Nike Stock----------------");
				Deserialization("Nike");
				System.out.println("--------------------Restore Nike Stock----------------");
				printFirstThreeStocks(nikeStocks, "Nike");
				System.out.println();
				
			}else if (file.contains("Oracle")) {
				
				Serialization(oracleStocks, "Oracle");
				printFirstThreeStocks(oracleStocks, "Oracle");
				System.out.println("--------------------Save Oracle Stock----------------");
				Deserialization("Oracle");
				System.out.println("--------------------Restore Oracle Stock----------------");
				printFirstThreeStocks(oracleStocks, "Oracle");
				System.out.println();
			}
		}
        
	}
	/**
     * Prints the details of the first three stocks in the given list.
     *
     * @param stocks     The list of stocks.
     * @param stockName  The name of the stock.
     */
	private void printFirstThreeStocks(ArrayList<Stock> stocks, String stockName) {
        for (int i = 0; i < Math.min(3, stocks.size()); i++) {
            Stock stock = stocks.get(i);
            System.out.println("Stock name: " + stockName + ", open: " + stock.getOpen() +
                               ", high: " + stock.getHigh() + ", low: " + stock.getLow() +
                               ", close: " + stock.getClose() + ", volume: " + stock.getVolume());
        }
    }
	/**
     * Serializes the given list of stocks and saves it to a file.
     *
     * @param stocks     The list of stocks.
     * @param stockName  The name of the stock.
     */
	private void Serialization(ArrayList<Stock> stocks, String stockName) {
		try {
            FileOutputStream fos = new FileOutputStream(stockName + "Stock.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stocks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
     * Deserializes the stock data from a file and restores it to the corresponding stock list.
     *
     * @param stockName  The name of the stock.
     */
	@SuppressWarnings({ "unchecked", "unused" })
	private void Deserialization(String stockName) {
		ArrayList<Stock> restoredStocks = null;
		try {
		    FileInputStream fis = new FileInputStream(stockName + "Stock.ser");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    restoredStocks = (ArrayList<Stock>) ois.readObject();
		    ois.close();
		    fis.close();
		} catch (IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		}
	}
	/**
     * Calculates and prints the average close price of the given list of stocks.
     *
     * @param stocks     The list of stocks.
     * @param stockName  The name of the stock.
     */
	private void AverageClosePrice(ArrayList<Stock> stocks, String stockName) {
		
		double totalClosePrice = 0;
	    for (Stock stock : stocks) {
	        totalClosePrice += stock.getClose();
	    }
	    double averageClosePrice = totalClosePrice / stocks.size();
	    System.out.println("Stock name: " + stockName + ", average close price: " + averageClosePrice);
	}
	/**
     * Prints the top three volumes of the given list of stocks.
     *
     * @param stocks     The list of stocks.
     * @param stockName  The name of the stock.
     */
	private void printTopThreeVolumes(ArrayList<Stock> stocks, String stockName) {
		ArrayList<Stock> sortedStocks = new ArrayList<>(stocks);
        // Sort the stocks list based on volume in descending order
		sortedStocks.sort(Comparator.comparing(Stock::getVolume).reversed());

        // Print the stock name and the top three volumes
        for (int i = 0; i < Math.min(3, sortedStocks.size()); i++) {
            Stock stock = sortedStocks.get(i);
            System.out.println("Stock name: " + stockName + ", date: " + stock.getDate() + ", volume: " + stock.getVolume());
        }
    }
	/**
     * Reads the stock data from a CSV file and populates the corresponding stock list.
     *
     * @param name   The name of the stock.
     * @param path   The path to the CSV file.
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
    			String date = formatDateString(originalDate, name);
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
     * Formats the date string from the original format to the target format.
     *
     * @param originalDate  The original date string.
     * @param name          The name of the stock.
     * @return The formatted date string.
     */
	public String formatDateString(String originalDate, String name) {
		if (name.equals("Apple")) {
			return "";
		}
		try {
			SimpleDateFormat originalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = originalDateFormat.parse(originalDate);
			return targetDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return ""; // Return empty string if date parsing fails
		}
	}
	
	public static void main(String[] args) {
		
		Program1 p = new Program1();
		p.StockDataProcessing();

	}
	/**
	 * Read the user input from the console
	 * @return the user input as a String
	 */	
	public String readInput() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = null;
		try {
			inputLine = input.readLine();
			//System.out.println(inputLine);
			return inputLine;
		} catch (IOException e) {
			System.err.println("Input ERROR.");
		}
		// return empty string if error occurs
		return inputLine;
	}
}
