package automail;

import java.time.Instant;

public class Heater {
<<<<<<< Updated upstream

	private String heaterID;
	private long timeActive;
	private long start;
	private Instant instant;
	
	public Heater(String id) {
		this.heaterID = id;
		this.instant = Instant.now();
		this.start = instant.getEpochSecond();
		this.timeActive = instant.getEpochSecond()-start;
	}
=======
	static final private int NEW_HEATER = 0;

	private int timeActive;
	
	public Heater() {
>>>>>>> Stashed changes

		this.timeActive = NEW_HEATER;
	}

	public int getTimeActive() {
<<<<<<< Updated upstream
		return (int) this.timeActive;
	}

	public void setTimeActive(int timeActive) {
		this.timeActive = this.instant.getEpochSecond() - start;
	}
	
	private void resetStart() {
		this.start = this.instant.getEpochSecond();
	}
=======
		
		return timeActive;
	}

	public void setTimeActive(int heaterCount) {
		timeActive += heaterCount;
		
	}


	
>>>>>>> Stashed changes
}
