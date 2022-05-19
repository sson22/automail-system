package exceptions;

/*
 * An exception to be thrown if system attempts to load food item into a tube when there
 * are already 3 in tube.
 */
public class TooManyFoodItemsException extends Throwable {
	public TooManyFoodItemsException() {
		super("Cannot load item to tube. Tube has 3 food items.");
	}
}
