package F28DA_CW2;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FlyingPlannerMainPartA {

	public static void main(String[] args) {
		
		// The following code is from HelloJGraphT.java of the org.jgrapth.demo package
		
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        
        
		Graph<String, DefaultEdge> g = new SimpleDirectedWeightedGraph<>(DefaultEdge.class);
		
		DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<String, DefaultEdge>(g);
        

        String v1 = "Edinburgh";
        String v2 = "Heathrow";
        String v3 = "Dubai";
        String v4 = "Sydney";
        String v5 = "Kuala Lumpur";
        
        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v2, v4);
        g.addEdge(v1, v3);
        g.addEdge(v3, v5);
        g.addEdge(v5, v4);
        
        g.addEdge(v2, v1);
        g.addEdge(v3, v2);
        g.addEdge(v4, v2);
        g.addEdge(v3, v1);
        g.addEdge(v5, v3);
        g.addEdge(v4, v5);
        
        //add weight to the edges
        g.setEdgeWeight(v1, v2, 80);
        g.setEdgeWeight(v2, v1, 80);
        
        g.setEdgeWeight(v2, v3, 130);
        g.setEdgeWeight(v3, v2, 130);
        
        g.setEdgeWeight(v2, v4, 570);
        g.setEdgeWeight(v4, v2, 570);
        
        g.setEdgeWeight(v1, v3, 150);
        g.setEdgeWeight(v3, v1, 150);
        
        g.setEdgeWeight(v3, v5, 170);
        g.setEdgeWeight(v5, v3, 170);
        
        g.setEdgeWeight(v5, v4, 150);
        g.setEdgeWeight(v4, v5, 150);
        
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Flight Planner!");
        System.out.println("Here are the following airports that are used: ");
        listVertices(g);
        String response;
        
        do {
	    	System.out.println("Please enter departure city: ");
		    String dep = scan.next();
		    System.out.println("Please enter destination city: ");
		    String des = scan.next();
		    System.out.println("Here is the shortest path for the chosen route: ");
		    listPaths(dijkstra.getPath(dep, des)); 
		    System.out.println("Number of flight changes: ");
		    System.out.println((dijkstra.getPath(dep, des).getLength())-1);
		    System.out.println("Here is the cost of the cheapest path for the chosen route: ");
		    System.out.println("£"+dijkstra.getPathWeight(dep, des));
		    System.out.println("Would you like to find another route?(yes/no)");
		    response = scan.next();
        }
        while (response.equalsIgnoreCase("Yes"));
	    
	    System.out.println("Thank you for using Flight Planner!");
	    
	    
        }
	    
        
               
	
	private static void listVertices (Graph<String, DefaultEdge> g) {
		Set<String> v = g.vertexSet();
		
		for(String vertex: v) {
			System.out.println(vertex);
		}
	}
	
	private static void listPaths (GraphPath<String, DefaultEdge> gp) {
		List<String> l = gp.getVertexList();
		
	for(int i= 0; i<l.size()-1; i++) {
			System.out.println((i+1) + ") " + l.get(i) + " -> " + l.get(i+1));
	}
		
	}
	
	

}
