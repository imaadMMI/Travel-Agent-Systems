package F28DA_CW2;

import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.jgrapht.*;
public class Journey implements IJourneyPartB<Airport, Flight>, IJourneyPartC<Airport, Flight> {

		private List<Airport> aJ;
		private List<Flight> fJ;
		
		public Journey(List<Airport> airports , List<Flight> flights) {
			this.aJ = airports;
			this.fJ = flights;
		}
		
		public Journey(GraphPath<Airport, Flight> path) {
			this.aJ = path.getVertexList();
			this.fJ = path.getEdgeList();
		}
		
	@Override
	//Returns a list of airport codes from a given list of airports
	public List<String> getStops() {
	List<String> aL = new ArrayList<>();
		
		for(Airport airport : aJ)
			aL.add(airport.getCode());
		
		return aL;
	}

	@Override
	//Returns a list of flight codes extracted from a given list of flights
	public List<String> getFlights() {
    List<String> fL = new ArrayList<>();
		
		for(Flight flight : fJ)
			fL.add(flight.getFlightCode());
		
		return fL;
	}

	@Override
	//returns the size of the list
	public int totalHop() {
		// TODO Auto-generated method stub
		return fJ.size();
	}

	@Override
	// calculates the total cost of all flights in the given list
	public int totalCost() {
        int total = 0;
		
		for(Flight flight : fJ)
			total += flight.getCost();
		
		return total;
	}

	@Override
	// calculates the total air time for a list of flights
	public int airTime() {
		int tAT = 0;
		
		for(Flight f : this.fJ) {
			String l = f.getFromGMTime();
			String a = f.getToGMTime();
			
			int aT = FlyingPlanner.timeDuration(l, a);
			tAT += aT;
		}
		return tAT;
	}

	@Override
	// calculates the total connecting time between flights in a given Flight Journey
	public int connectingTime() {
        int tCT = 0;
		
		for(int i =0 ; i < fJ.size()-1 ; i++) {
			Flight f1 = fJ.get(i);
			Flight f2 = fJ.get(i+1);
			
			String f1A = f1.getToGMTime();
			String f2D = f2.getFromGMTime();
			
			int duration = FlyingPlanner.timeDuration(f1A, f2D);
			tCT += duration;
		}
		
		return tCT;
	}

	@Override
	// Method returns the sum of air time and connecting time
	public int totalTime() {
		int aT = airTime();
		int cT = connectingTime();
		return aT + cT;
	}

	@Override
	// calculates and returns the total number of airmiles earned based on the airtime and total cost of a flight
	public int totalAirmiles() {
		double multiply = this.totalCost() * 0.03;
		return (int) (this.airTime() * multiply);
	}
	

		
	
	

}
