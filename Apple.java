import java.io.Serializable;
/**
 * This class represents an Apple stock, extending the Stock class and implementing the Serializable interface.
 * It provides a constructor to initialize the Apple stock attributes.
 */
public class Apple extends Stock implements Serializable {

	/**
     * A unique identifier for serialization purposes.
     */
	private static final long serialVersionUID = 1L;
	/**
     * Constructs an Apple stock object with the specified attributes.
     *
     * @param date   The date of the stock.
     * @param open   The opening price of the stock.
     * @param high   The highest price of the stock.
     * @param low    The lowest price of the stock.
     * @param close  The closing price of the stock.
     * @param volume The trading volume of the stock.
     */
	Apple(String date, float open, float high, float low, float close, double volume){

		this.date =date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
}
