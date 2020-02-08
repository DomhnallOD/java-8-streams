import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.stream.Stream;
import java.util.function.Predicate;



//Create public WeatherStation class
public class WeatherStation { 
	//Declare member variables for city, measurement (ArrayList) and WeatherStation (ArrayList)
	private String city;
	private ArrayList <Measurement> mes = new ArrayList<>(); 
	public static ArrayList<WeatherStation> stn = new ArrayList<>();
	//Create overloaded constructor passing in City
	public WeatherStation(String city) {
		this.city = city;
	}

	//Getter for city
	public String getCity() {
		return city;
	}

	//Getter for measurements stream ArrayList 
	public Stream<Measurement> getTemperStream(){
		return this.mes.stream();
	}

	//Calculate average temp for weather station
	public double avgTemperature(int startTime, int endTime) {
		//Temperature measurements ranging from start to end time will be added to measurement ArrayList
		Predicate <Measurement> measures = me -> me.getTime() >= startTime && me.getTime() <= endTime;
		OptionalDouble averageRecordedTemperature = mes

				//Replace elements of the returned stream with those in mapped stream
				.parallelStream()


				//Convert stream to measures list
				.filter(measures)

				//Generate new stream for previous aggregate op
				.mapToDouble(me -> me.getTemperature()) 

				//Calculate average temperature for the station and convert to Double
				.average(); 
				return averageRecordedTemperature.getAsDouble();
	}

	//Calculate the avg. temp. recorded across all stations
	public static double avgTempratureAcrossAllStations(int startTime, int endTime) {

		//Temperature measurements ranging from start to end time will be added to measurement ArrayList
		Predicate <Measurement> measures = me -> me.getTime() >= startTime && me.getTime() <= endTime;
		OptionalDouble averageRecordedTemperature = stn 
				//Process streams in parallel
				.parallelStream() 

				//Replace elements of the returned stream with those in mapped stream
				.flatMap(i -> i.getTemperStream()) 
				//Convert the stream to Measures list 
				.filter(measures) 

				//Generate new stream for previous aggregate op
				.mapToDouble(t -> t.getTemperature())

				//Calculate the avg temp across all weather stations and return as double
				.average();
				return averageRecordedTemperature.getAsDouble();

	}

	public static void main(String[] args) {

		//Make three stations, passing name in to the constructor
		WeatherStation stat1 = new WeatherStation("Tulla");
		WeatherStation stat2 = new WeatherStation("Ennis");
		WeatherStation stat3 = new WeatherStation("Kilkee");



		//Station 1 measurements
		Measurement measure1 = new Measurement(1220, 12);
		Measurement measure2 = new Measurement(640, 9);

		//Add station 1 to list
		stat1.mes.add(measure1);  
		stat1.mes.add(measure2);

		//Station 2 measurements
		Measurement measure3 = new Measurement(1640, 9);
		Measurement measure4 = new Measurement(1720, 12);


		stat2.mes.add(measure3);
		stat2.mes.add(measure4);

		//Station 3 measurements
		Measurement measure5 = new Measurement(350, 4);
		Measurement measure6 = new Measurement(410, 14);


		stat3.mes.add(measure5);
		stat3.mes.add(measure6);

		//Add to stations list
		stn.add(stat1);
		stn.add(stat2);
		stn.add(stat3);
		//Use for loop to print out station names as well as avg temps for each
		for(int n = 0; n < stn.size(); n++) { 
			System.out.println("Station: " + stn.get(n).getCity() + "	Avg. temp. is: " + stn.get(n).avgTemperature(0, 2300));
		}

		//Output avg temp for entire county
		System.out.println("\nThe average temperature across county Clare today is "  + avgTempratureAcrossAllStations(0, 2300));
	}
} //end class
