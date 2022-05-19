package automail;

import java.util.*;  

public class Tube {

	static private final int TUBE_EMPTY = 0;
	
	
	private String tubeID;
	private int numItems;
	public LinkedList<MailItem> listItems;
	/*
	 * Constructor method for Tube class
	 */
<<<<<<< Updated upstream
	public Tube(String id) {
=======
	public Tube() {
>>>>>>> Stashed changes
	/*
	 * Default behaviour for non-food tube is to be a mail tube and to be empty on
	 * instantiation. 
	 */
<<<<<<< Updated upstream
	this.tubeID = id;
=======
>>>>>>> Stashed changes
	this.numItems = TUBE_EMPTY;
	this.listItems = new LinkedList<MailItem>();
	}

	public String getTubeID() {
		return tubeID;
	}

	public int getNumItems() {
		return numItems;
	}

	private void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	public boolean tubeIsEmpty() {
		return (listItems == null);
	}
}
