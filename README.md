**Overview**

This project contains two Java programs that process and visualize stock market data:

StockDataProcessing: Processes CSV files, calculates statistics, and serializes data

StockDataVisualization: Deserializes data and displays interactive line charts

**Program 1: StockDataProcessing**
Functionality:

Processes CSV stock data files

Calculates:

Top 3 trading volumes per stock

Average closing price per stock

Serializes data to .ser files

Verifies serialization by deserializing samples

How to Run:

Compile: javac *.java

Execute: java StockDataProcessing

At prompt:
Enter the stock files to be processed:
Input space-separated stock names (e.g., Apple HSBC Nike Oracle)

Output Files:

Generates serialized files:
AppleStock.ser, HSBCStock.ser, etc.

**Program 2: StockDataVisualization**
Functionality:

Loads serialized stock data

Displays interactive line charts using JavaFX

Shows closing prices over time (1 data point per 10 records)

How to Run:

Compile: javac *.java

Execute: java StockDataVisualization

At prompt:
Enter the stock files to be processed:
Input same stock names used in Program 1

Visualization:

X-axis: Date (record index)

Y-axis: Closing price

Color-coded lines for each stock

Interactive tooltips on hover
