package automail;

import java.util.Stack;
public class FoodTube extends Tube{
	
	public static final int MAX_ITEMS = 3;
	
	Stack<FoodItem> foodItems;
	private Heater heater;
	
<<<<<<< Updated upstream
	/*
	 * @Override
	 */
	public FoodTube(String id) {
		super(id);
		this.prodType = ProductType.food;
		// Provide heater the same id because it will be associated with this tube
		this.heater = new Heater(this.getTubeID());
=======
	public FoodTube() {
		super();
		foodItems = new Stack<FoodItem>();
		this.heater = new Heater();
		
	}
	
	
	public boolean addItem(FoodItem item) {
		if(foodItems.size() == MAX_ITEMS) {
			return false;
		}else {
			foodItems.push(item);
			return true;
		}
	}
	
	public FoodItem getNext() {
		if(foodItems.size()>0) {
			return foodItems.pop();
		} else {
			return null;	
		}
	}
	
	public boolean isFull() {
		return foodItems.size()== MAX_ITEMS;
	}
	public Heater getHeater() {
		return heater;
>>>>>>> Stashed changes
	}
}
