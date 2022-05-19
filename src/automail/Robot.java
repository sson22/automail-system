package automail;

import exceptions.ExcessiveDeliveryException;
import exceptions.ItemTooHeavyException;
import exceptions.TooManyFoodItemsException;
import simulation.Building;
import simulation.Clock;
import simulation.IMailDelivery;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The robot delivers mail!
 */
public class Robot {
	
    static public final int INDIVIDUAL_MAX_WEIGHT = 2000;
    
    private int dummy_var;

    IMailDelivery delivery;
    protected final String id;
    /** Possible states the robot can be in */
    public enum RobotState { DELIVERING, WAITING, RETURNING }
    public RobotState current_state;
    private int current_floor;
    private int destination_floor;
    private MailPool mailPool;
    private boolean receivedDispatch;
    
    private MailItem deliveryItem = null;
    private MailItem tube = null;
    private FoodTube foodTube = null;
    private boolean arePartsAttached = true;
    
    private int deliveryCounter;
<<<<<<< Updated upstream
=======
    
    public FoodTube getFoodTube() {
    	return foodTube;
    }
    
>>>>>>> Stashed changes

    /**
     * Initiates the robot's location at the start to be at the mailroom
     * also set it to be waiting for mail.
     * @param behaviour governs selection of mail items for delivery and behaviour on priority arrivals
     * @param delivery governs the final delivery
     * @param mailPool is the source of mail items
     */
    public Robot(IMailDelivery delivery, MailPool mailPool, int number){
    	this.id = "R" + number;
        // current_state = RobotState.WAITING;
    	current_state = RobotState.RETURNING;
        current_floor = Building.MAILROOM_LOCATION;
        this.delivery = delivery;
        this.mailPool = mailPool;
        this.receivedDispatch = false;
        this.deliveryCounter = 0;
    }
    
    /**
     * This is called when a robot is assigned the mail items and ready to dispatch for the delivery 
     */
    public void dispatch() {
    	receivedDispatch = true;
    }
<<<<<<< Updated upstream
    
    //TODO When robot arriving back to mailroom needs to remove its tube and hands before being re-registered
    //TODO This is the most fiddly robot method and will need a lot of work before the project can move forward
=======

>>>>>>> Stashed changes
    /**
     * This is called on every time step
     * @throws ExcessiveDeliveryException if robot delivers more than the capacity of the tube without refilling
     */
    public void operate() throws ExcessiveDeliveryException {   
    	switch(current_state) {
    		/** This state is triggered when the robot is returning to the mailroom after a delivery */
    		case RETURNING:
    			/** If its current position is at the mailroom, then the robot should change state */
                if(current_floor == Building.MAILROOM_LOCATION){
                	if (tube != null) {
                		if (tube.getProdType().equals(Tube.ProductType.mail) ) {
                			mailPool.addMailTubeToPool(tube);
                		} else if (tube.getProdType().equals(Tube.ProductType.food)){
                			mailPool.addFoodTubeToPool(tube);
                		}
                        System.out.printf("T: %3d > old addToPool [%s]%n", Clock.Time(), tube.getTubeID().toString());
                        tube = null;
                	}
                	else if(foodTube!=null) {
                		while(true) {
                			FoodItem foodItem = foodTube.getNext();
                			if(foodItem==null) {
                				break;
                			}
                			mailPool.addToPool(foodItem);
                			System.out.printf("T: %3d > old addToPool [%s]%n", Clock.Time(), foodItem.toString());
                		}
                		foodTube = null;
                		arePartsAttached = true;
                	}
                		
        			/** Tell the sorter the robot is ready */
        			mailPool.registerWaiting(this);
                	changeState(RobotState.WAITING);
                } else {
                	/** If the robot is not at the mailroom floor yet, then move towards it! */
                    moveTowards(Building.MAILROOM_LOCATION);
                	break;
                }
                case WAITING:
                /** If the StorageTube is ready and the Robot is waiting in the mailroom then start the delivery */
                if(!isEmpty() && receivedDispatch){
                	receivedDispatch = false;
                	deliveryCounter = 0; // reset delivery counter
                	setDestination();
                	changeState(RobotState.DELIVERING);
                }
                break;
    		case DELIVERING:
<<<<<<< Updated upstream
    			//TODO Keep a firm eye on whether this works during testing
=======
    			// 5. food tube needs 5 units of time to boot a heating system. 
    			if(foodTube != null && foodTube.getHeater().getTimeActive() >= 5) {
    				// lock the floor
    				MailPool.floors.replace(destination_floor, "Not Available");
    				
    			}else {
    				// wait for 5 unit times.
    				int heaterCount = 0;
    				heaterCount++;
    				foodTube.getHeater().setTimeActive(heaterCount);
    				
    			}
    			
>>>>>>> Stashed changes
    			if(current_floor == destination_floor){ // If already here drop off either way
    				//First ensure that there isn't already a robot delivering food to the floor
    				if (this.tube.getProdType().equals(Tube.ProductType.food) && mailPool.floors.get(current_floor-1).has_robot) {
    					// Change back to waiting and then during next operation check floor again
    					this.current_state = RobotState.WAITING;
    				}
                    /** Delivery complete, report this to the simulator! */
                    delivery.deliver(deliveryItem);
                    deliveryItem = null;
                    deliveryCounter++;
                    if(deliveryCounter > 2){  // Implies a simulation bug
                    	throw new ExcessiveDeliveryException();
                    }
                    /** Check if want to return, i.e. if there is no item in the tube*/
                    if(tube == null || tube.tubeIsEmpty()){
                    	changeState(RobotState.RETURNING);
                    }
                    else{
                        /** If there is another item, set the robot's route to the location to deliver the item */
<<<<<<< Updated upstream
                        deliveryItem = tube.listItems.getFirst();
                        tube = null;
                        setDestination();
                        changeState(RobotState.DELIVERING);
=======
                        // Check the destination floor is not locked 
                        if(mailPool.floorAvailable(deliveryItem.getDestFloor())) {
                        	 deliveryItem = tube;
                             tube = null;
                        	 setDestination();	
                        	 changeState(RobotState.DELIVERING);
                        }else {
                        	break;
                        }
                        
>>>>>>> Stashed changes
                    }
    			} else {
	        		/** The robot is not at the destination yet, move towards it! */
	                moveTowards(destination_floor);
    			}
                break;
    	}
    }

    /**
     * Sets the route for the robot
     */
    
    
    private void setDestination() {
        /** Set the destination floor */
    	destination_floor = deliveryItem.getDestFloor(); 
 
    }

    /**
     * Generic function that moves the robot towards the destination
     * @param destination the floor towards which the robot is moving
     */
    private void moveTowards(int destination) {
        if(current_floor < destination){
            current_floor++;
        } else {
            current_floor--;
        }
    }
    
    private String getIdTube() {
    	return String.format("%s(%1d)", this.id, (tube == null ? 0 : 1));
    }
    
    /**
     * Prints out the change in state
     * @param nextState the state to which the robot is transitioning
     */
    private void changeState(RobotState nextState){
    	assert(!(deliveryItem == null && tube != null));
    	if (current_state != nextState) {
            System.out.printf("T: %3d > %7s changed from %s to %s%n", Clock.Time(), getIdTube(), current_state, nextState);
    	}
    	current_state = nextState;
    	if(nextState == RobotState.DELIVERING){
            System.out.printf("T: %3d > %7s-> [%s]%n", Clock.Time(), getIdTube(), deliveryItem.toString());
    	}
    }

	public MailItem getTube() {
		return tube;
	}
    
	static private int count = 0;
	static private Map<Integer, Integer> hashMap = new TreeMap<Integer, Integer>();

//	@Override
//	public int hashCode() {
//		Integer hash0 = super.hashCode();
//		Integer hash = hashMap.get(hash0);
//		if (hash == null) { hash = count++; hashMap.put(hash0, hash); }
//		return hash;
//	}

	public boolean isEmpty() {
		return (deliveryItem == null && tube == null);
	}

	public void addToHand(MailItem mailItem) throws ItemTooHeavyException {
		assert(deliveryItem == null && arePartsAttached == true);
		deliveryItem = mailItem;
		if (deliveryItem.weight > INDIVIDUAL_MAX_WEIGHT) throw new ItemTooHeavyException();
	}

	public void addToTube(MailItem mailItem) throws ItemTooHeavyException {
		assert(tube == null && arePartsAttached == true);
		tube = mailItem;
		if (tube.weight > INDIVIDUAL_MAX_WEIGHT) throw new ItemTooHeavyException();
	}
	public void addToFoodTube(FoodItem foodItem) throws TooManyFoodItemsException {
		deliveryItem = null;
		tube = null;
		arePartsAttached = false;
		if(foodTube == null) {
			 foodTube = new FoodTube();
		}
		foodTube.addItem(foodItem);
		//Exception needed for too many items.
		if (this.foodTube.foodItems.size() == 3) throw new TooManyFoodItemsException();
	}
	
}
