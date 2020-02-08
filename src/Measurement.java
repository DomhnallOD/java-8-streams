//Create Measurement class
public class Measurement { 
	//Declare member variables
	private int time;
	private double temperature;
	//Create overloaded constructor
	public Measurement(int time, double temperature) {
		this.time = time;
		this.temperature = temperature;
	}

	//Getter for time
	public int getTime() {
		return time;
	}

	//Getter for temperature
	public double getTemperature() {
		return temperature;
	}	
} //end Measurement class

