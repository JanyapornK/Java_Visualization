/**
 * This abstract class represents a stock with various attributes such as date, opening price, closing price, etc.
 * It provides getter methods to access these attributes.
 */
public abstract class Stock {
	
	/**
     * The date of the stock.
     */
	String date;
	/**
     * The opening price of the stock.
     */
	float open;
	/**
     * The highest price of the stock during a given period.
     */
	float high;
	/**
     * The lowest price of the stock during a given period.
     */
	float low;
	/**
     * The closing price of the stock.
     */
	float close;
	/**
     * The trading volume of the stock.
     */
	double volume;
	
	/**
     * Returns the trading volume of the stock.
     * @return The trading volume.
     */
	public double getVolume() {
		return volume;
	}
	/**
     * Returns the date of the stock.
     * @return The date.
     */
	public String getDate() {
	    return date;
	}
	/**
     * Returns the closing price of the stock.
     * @return The closing price.
     */
	public float getClose() {
		return close;
	}
	/**
     * Returns the opening price of the stock.
     * @return The opening price.
     */
	public float getOpen() {
		return open;
	}
	/**
     * Returns the highest price of the stock during a given period.
     * @return The highest price.
     */
	public float getHigh() {
		return high;
	}
	/**
     * Returns the lowest price of the stock during a given period.
     * @return The lowest price.
     */
	public float getLow() {
		return low;
	}
}
