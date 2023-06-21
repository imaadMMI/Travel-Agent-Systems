package F28DA_CW2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;

public class FlyingPlanner implements IFlyingPlannerPartB<Airport,Flight>, IFlyingPlannerPartC<Airport,Flight> {
   
	//Graphs being declared
		private Graph<Airport, Flight> g = new SimpleDirectedWeightedGraph<>(Flight.class); //Edges weighted with cost for leastCost()
		private Graph<Airport, Flight> dG = new DirectedAcyclicGraph<>(Flight.class);
	
		//method that reutrns the graph
		public Graph<Airport, Flight> getGraph() {
			return g;
		}
		
		//method that returns the duration of the flight
		public static int timeDuration(String start, String end) {

			int hour = 0, min = 0;
			int finalHour = 0, finalMin = 0;
			
			hour = Integer.parseInt(start.substring(0, 2));
			min = Integer.parseInt(start.substring(2));
			
			finalHour = Integer.parseInt(end.substring(0, 2));
			finalMin = Integer.parseInt(end.substring(2));
			
			LocalTime timeBegin = LocalTime.of(hour, min);
			LocalTime timeFinish = LocalTime.of(finalHour, finalMin);
			
			Duration diff = Duration.between(timeBegin, timeFinish);
			
			if(diff.isNegative())
				diff = diff.plusDays(1);
			
			return (int) diff.toMinutes();
		}
		
		

	@Override
	//populates Hashsets for airports and flights
	public boolean populate(FlightsReader fr) {
		HashSet<String[]> a = fr.getAirports(); 
		HashSet<String[]> f = fr.getFlights(); 
		return populate(a, f);
	}

	@Override
	//Iterate through HashSet of airports and add Airport instances to graph
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) {
		
		for(String[] a : airports) {
			g.addVertex(new Airport(a));
		}
		
		//Iterate through HashSet of flights and set edges between two vertices(airports) and set weight of edge as cost of flight
		for(String[] f : flights) {
			Airport dep = airport(f[1]);
			Airport arr = airport(f[3]);
			g.addEdge(dep, arr, new Flight(dep, arr, f));
		}
		return true;
		
		
	}

	@Override
	//Returns an Airport object with the given code or null if not found in the vertex set
	public Airport airport(String code) {
		for(Airport a : g.vertexSet()) {
			if(a.getCode().equals(code)) 
				return a;
		}
		return null;
	}

	@Override
	
	//  Searches for flight object in a graph by its flight code and returns it if found, otherwise returns null
	public Flight flight(String code) {
		for(Flight f: g.edgeSet()) {
			if(f.getFlightCode().equals(code))
				return f;
		}
		return null;
	}

	@Override
	// Returns the least cost journey between two points using the leastCost method
	public Journey leastCost(String from, String to) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return leastCost(from, to, null); 
	}

	@Override
	// // Returns the least hop journey between two points using the leastHop method
	public Journey leastHop(String from, String to) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return leastHop(from, to, null);
	}

	@Override
	// Returns a Journey object by using Dijkstra's Algorithm to find the cheapest path between two airports, while allowing exclusions
	public Journey leastCost(String from, String to, List<String> excluding)
			throws FlyingPlannerException {
		
				Airport fromAirport = airport(from);
				Airport toAirport = airport(to);
				
				// Removes the airport if it is excluded
				if(excluding != null) { 
					for(String exc : excluding) { 
						Airport air = airport(exc);
						g.removeVertex(air);
					}
				}
				
				//Sets the edge weights of a graph 'g' to their respective costs
				for(Flight edge : g.edgeSet()) {
					g.setEdgeWeight(edge, edge.getCost());
				}
				
				//
				GraphPath<Airport, Flight> p = DijkstraShortestPath.findPathBetween(g, fromAirport, toAirport);
				
				//THROW EXCEPTION
				if(p == null) {
					throw new FlyingPlannerException("No paths available");
				}
				
				Journey j = new Journey(p);
				return j;

	}

	@Override
	// Finds the cheapest journey between two airports while excluding specified airports
	public Journey leastHop(String from, String to, List<String> excluding)
			throws FlyingPlannerException {
		//VARIABLES
				Airport fromAirport = airport(from);
				Airport toAirport = airport(to);
				
				//Removes specified vertices from graph if excluded
				if(excluding != null) { 
					for(String exc : excluding) { 
						Airport a = airport(exc);
						g.removeVertex(a);
					}
				}
				
				for(Flight f : g.edgeSet()) {
					g.setEdgeWeight(f, 1d);
				}
				
				//finds the cheapest path with help of Dijkstra's Algorithm
				GraphPath<Airport, Flight> gp = DijkstraShortestPath.findPathBetween(g, fromAirport, toAirport);
				
				//Exception thrown
				if(gp == null) {
					throw new FlyingPlannerException("No paths available");
				}
				
				Journey j = new Journey(gp);
				return j;
	}


	@Override
	//Returns a set of directly connected airports to a given airport
	public Set<Airport> directlyConnected(Airport airport) {
        Set<Airport> cA = new HashSet<>();
		
		for(Airport air : g.vertexSet()) {
			if( g.containsEdge(air, airport) && g.containsEdge(airport, air) ) {
				cA.add(air);
			}
		}
		
		return cA;
	}

	@Override
	//Calculates and returns the sum of directly connected airports in a graph
	public int setDirectlyConnected() {
	    int s = 0;
		
		for(Airport aP : g.vertexSet()) {
			aP.setDicrectlyConnected(directlyConnected(aP));
			s += directlyConnected(aP).size();
		}
		
		return s;
	}

	@Override
	//Sets the order of directly connected flights between airports in a graph
	public int setDirectlyConnectedOrder() {
		for(Flight fly : g.edgeSet()) {
			Airport a1 = g.getEdgeSource(fly);
			Airport a2 = g.getEdgeTarget(fly);
			
			if(a1.getDicrectlyConnected().size() < a2.getDicrectlyConnected().size()) {
				dG.addVertex(a1);
				dG.addVertex(a2);
				dG.addEdge(a1, a2, fly);
			}
		}
		
		return dG.edgeSet().size();
	}

	@Override
	//Returns a set of airports that are better connected than the input airport in increasing order of distance
	public Set<Airport> getBetterConnectedInOrder(Airport airport) {
	Set<Airport> bC = new HashSet<>();
		
		for(Airport des : dG.vertexSet()) {
			if(DijkstraShortestPath.findPathBetween(dG, airport, des) != null) {
				bC.add(des);
			}
		}
		bC.remove(airport);
		
		return bC;
	}

	@Override
	//finds the least cost meet-up point between two locations based on their respective journey costs
	public String leastCostMeetUp(String at1, String at2) throws FlyingPlannerException {
		List<String> code = codeList(at1, at2);
			
			List<Integer> costL = new LinkedList<>();
			for(String c : code) {
				Journey j = leastCost(at1, c);
				Journey journey = leastCost(at2, c);
				int cost = j.totalCost() + journey.totalCost();
				costL.add(cost);
			}
			
			int min = Collections.min(costL);
			int i = costL.indexOf(min);
			String mU = code.get(i);
			
			return mU;
		}

	@Override
	// Calculates the airport with the least cost and hops for two given airports
	public String leastHopMeetUp(String at1, String at2) throws FlyingPlannerException {
        List<String> code = codeList(at1, at2);
		
		List<totalStops> costSL = new LinkedList<>();
		for(String c : code) {
			Journey j = leastHop(at1, c);
			Journey journey = leastHop(at2, c);
			int cost = j.totalCost() + journey.totalCost();
			int hop = j.totalHop() + journey.totalHop();
			costSL.add(new totalStops(c, hop, cost));
		}
		
		costSL.sort(new totalStopsComparator());
		String mU = costSL.get(0).getCode();
		
		return mU;
	}

	@Override
	//Finds the airport code with the least time required for two people to meet up given their departure airports and a start time
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException {	
		List<String> code = codeList(at1, at2);
		
		int min = Integer.MAX_VALUE;
		String mC = null;
		
		for(String c : code) {
			
			Journey j = leastTime(at1, c);
			Flight fF1 = flight(j.getFlights().get(0));
			int wT1 = timeDuration(startTime, fF1.getFromGMTime());
			int j1T = j.totalTime();
			int t1 = wT1 + j1T;
				
			Journey journey = leastTime(at2, c);
			Flight fF2 = flight(journey.getFlights().get(0));
			int wT2 = timeDuration(startTime, fF2.getFromGMTime());
			int j2T = journey.totalTime();
			int t2 = wT2 + j2T;
			
			if(t1 < t2) {
				if(t1 < min) {
					min = t1;
					mC = c;
				} else {
					continue;
				}
			}
			
			else if(t2 < t1) {
				if(t2 < min) {
					min = t2;
					mC = c;
				} else {
					continue;
				}
			}
			
			else {
				if(t1 < min) {
					min = t1;
					mC = c;
				} else {
					continue;
				}
			}
			
		}
		
		return mC;
	}
	
	//Generates all possible paths between two nodes, from and to, with a maximum of 30 paths and a maximum of 5 hops
	private List<String> codeList(String from, String to){
		List<String> code = new LinkedList<>();
		int max = 0;
		int hop = 0;
		
		do {
			code = allPaths(from, to, hop);
			max = code.size();
			hop++;
		} while(max<30 && hop<6);
		
		return code;
	}
	
	// method finds intermediate airport codes between two airports, limited by maxNum
	private List<String> allPaths(String from, String to, int maxNum){
		Airport fromAirport = airport(from);
		Airport toAirport = airport(to);
		
		AllDirectedPaths<Airport, Flight> aDP = new AllDirectedPaths<>(g);
		List<GraphPath<Airport, Flight>> path = aDP.getAllPaths(fromAirport, toAirport, true, maxNum);
		
		List<String> aCL = new LinkedList<>();
		
		for(GraphPath<Airport, Flight> p : path) {
			if(p.getEdgeList().size() != 1) {
				List<Airport> aL = p.getVertexList();
				for(Airport a : aL) {
					String aC = a.getCode();
					if(!aC.equalsIgnoreCase(from) && !aC.equalsIgnoreCase(to) && !aCL.contains(aC))
						aCL.add(aC);
				}
			}
		}
		
		return aCL;
	}

	// Finds the least time journey using Dijkstra's Algorithm and throws an exception if no path is available
	public Journey leastTime(String from, String to) throws FlyingPlannerException {
		Airport fromAirport = airport(from);
		Airport toAirport = airport(to);
		
		for(Flight e : g.edgeSet()) {
			g.setEdgeWeight(e, e.getFlightDuration());
		}
				
		//FINDING SHORTEST PATH - using Dijkstra's Algorithm
		GraphPath<Airport, Flight> p = DijkstraShortestPath.findPathBetween(g, fromAirport, toAirport);
		
		//THROW EXCEPTION
		if(p == null) {
			throw new FlyingPlannerException("No paths available");
		}
				
		Journey j = new Journey(p);
		return j;
	}


}
