package automail;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import exceptions.ItemTooHeavyException;
import exceptions.TooManyFoodItemsException;
import simulation.PriorityMailItem;

/**
 * addToPool is called when there are mail items newly arrived at the building to add to the MailPool or
 * if a robot returns with some undelivered items - these are added back to the MailPool.
 * The data structure and algorithms used in the MailPool is your choice.
 * 
 */
public class MailPool {

	private class Item {
		int priority;
		int destination;
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions
		
		public Item(MailItem mailItem) {
			priority = (mailItem instanceof PriorityMailItem) ? ((PriorityMailItem) mailItem).getPriorityLevel() : 1;
			destination = mailItem.getDestFloor();
			this.mailItem = mailItem;
		}
	}
	
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.priority < i2.priority) {
				order = 1;
			} else if (i1.priority > i2.priority) {
				order = -1;
			} else if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}
	
	private LinkedList<Item> pool;
	private LinkedList<Robot> robots;
<<<<<<< Updated upstream
	private LinkedList<Tube> mailTubes;
	private LinkedList<Tube> foodTubes;
	public ArrayList<Floor> floors;

	public MailPool(int nrobots){
		// Start empty
		foodPool = new LinkedList<Item>();
		mailPool = new LinkedList<Item>();
		mailTubes = new LinkedList<Tube>();
		foodTubes = new LinkedList<Tube>();
		robots = new LinkedList<Robot>();
		floors = new ArrayList<Floor>();
	}
	
=======
	public static Map<Integer, String> floors;
	static public final int NUM_FLOORS = 12;
	

	public MailPool(int nrobots){
		// Start empty
		pool = new LinkedList<Item>();
		robots = new LinkedList<Robot>();
		floors = new HashMap<Integer, String>();
	}

>>>>>>> Stashed changes
	/**
     * Adds an item to the mail pool
     * @param mailItem the mail item being added.
     */
	public void addToPool(MailItem mailItem) {
		Item item = new Item(mailItem);
		pool.add(item);
		pool.sort(new ItemComparator());
	}
	public void setFloors() {
		for(int i = 0; i < NUM_FLOORS; i++) {
			floors.put(i, "Available");
		}
	}

	public boolean floorAvailable(int destFloor) {
		if(floors.get(destFloor) == "Available") {
			return true;	
		}
		return false;
	}
	/**
     * load up any waiting robots with mailItems, if any.
	 * @throws TooManyFoodItemsException 
     */
	public void loadItemsToRobot() throws ItemTooHeavyException, TooManyFoodItemsException {
		//List available robots
		ListIterator<Robot> i = robots.listIterator();
		while (i.hasNext()) loadItem(i);
	}
	
	//load items to the robot
	private void loadItem(ListIterator<Robot> i) throws ItemTooHeavyException, TooManyFoodItemsException {
		Robot robot = i.next();
		assert(robot.isEmpty());
		// System.out.printf("P: %3d%n", pool.size());
		ListIterator<Item> j = pool.listIterator();
		if (pool.size() > 0) {
			MailItem nextItem = j.next().mailItem;
			try {
			if(nextItem instanceof FoodItem) {
				robot.addToFoodTube((FoodItem)nextItem);
				//5.food tube should be full before delivery 
				//unless there are no more food items awaiting in the mail pool at that time point
				if(robot.getFoodTube().isFull() || pool.size()==0) {
					robot.dispatch(); // send the robot off if it has any items to deliver
					i.remove();       // remove from mailPool queue
				}
			} else {
				robot.addToHand(nextItem); // hand first as we want higher priority delivered first
				j.remove();
				if (pool.size() > 0) {
					robot.addToTube(nextItem);
					j.remove();
				}
				robot.dispatch(); // send the robot off if it has any items to deliver
				i.remove();       // remove from mailPool queue
			}
			
			
			} catch (Exception e) { 
	            throw e; 
	        } 
		}
	}
<<<<<<< Updated upstream
	
	/*
	 * Add tube back to appropriate list when use is complete
	 */
	public void addMailTubeToPool(Tube tube) {
			this.mailTubes.add(tube);
	}
	
	public void addFoodTubeToPool(Tube foodTube) {
		this.foodTubes.add(foodTube);
	}
	
=======

>>>>>>> Stashed changes
	/**
     * @param robot refers to a robot which has arrived back ready for more mailItems to deliver
     */	
	public void registerWaiting(Robot robot) { // assumes won't be there already
		robots.add(robot);
	}
<<<<<<< Updated upstream
	
	public void generateFoodTubes(int numFoodTubes) {
		for (int i = 0; i < numFoodTubes; i++) {
			FoodTube foodTube = new FoodTube("FT"+i);
			this.foodTubes.add(foodTube);
		}
	}
	
	public void generateMailTubes(int numMailTubes) {
		for (int i = 0; i < numMailTubes; i++) {
			Tube mailTube = new Tube("MT"+i);
			this.mailTubes.add(mailTube);
		}
	}
	
	public void makeFloors(int numFloors) {
		for (int i = 1; i <= numFloors; i++) {
			Floor floor = new Floor(i);
			floors.add(floor);
		}
	}
}
=======




}
>>>>>>> Stashed changes
