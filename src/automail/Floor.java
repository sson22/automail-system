package automail;

public class Floor {

	public int floor_num;
	public boolean has_robot;
	
	public Floor(int floor_num) {
		this.floor_num = floor_num;
		//At the beginning assume that the floor does not have a robot
		this.has_robot = false;
	}
	
	public int getFloorNum() {
		return this.floor_num;
	}
	
	public boolean getHasRobot() {
		return this.has_robot;
	}
	
	private void setHasRobot(boolean hasRobot) {
		this.has_robot = hasRobot;
	}
	
}
