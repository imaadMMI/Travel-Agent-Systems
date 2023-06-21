package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlyingPlannerMainPartBC 
{
	
	//Object to take input from the user
	private static Scanner scan = new Scanner(System.in);
	
	//Function to print the flight details
	private static void printFlightDetails(Flight fly)
	{
		//Printing the flight details
		System.out.printf("%-18s","| "+fly.getFrom().getName()+" ("+fly.getFrom().getCode()+")");
		System.out.printf("%-8s","| "+fly.getFromGMTime().substring(0, 2)+":"+fly.getFromGMTime().substring(2, 4));
		System.out.printf("%-10s","| "+fly.getFlightCode());
		System.out.printf("%-18s","| "+fly.getTo().getName()+" ("+fly.getTo().getCode()+")");
		System.out.printf("%-8s","| "+fly.getToGMTime().substring(0, 2)+":"+fly.getToGMTime().substring(2, 4));
		System.out.print("|");
		System.out.println();
		
	}
	
	//Function to print the journey details which includes the flight details
	private static void printJourneyDetails(List<String> f,FlyingPlanner flyPlan)
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.printf("%-7s","| Leg");
		System.out.printf("%-18s","| Leave");
		System.out.printf("%-8s","| At");
		System.out.printf("%-10s","| On");
		System.out.printf("%-18s","| Arrive");
		System.out.printf("%-8s","| At");
		System.out.print("|");
		System.out.println();
		System.out.println("----------------------------------------------------------------------");
		int i=1;
		//Running loop to call the function and print it 
		for(String c : f)
		{
			System.out.printf("%-7s","| "+i);
			i+=1;
			printFlightDetails(flyPlan.flight(c));
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println();
	}
	
	//Function to print the whole journey
	private static void printJourney(Journey j,FlyingPlanner fp)
	{
		printJourneyDetails(j.getFlights(),fp);
		System.out.println();
		System.out.println("|=============================================================================|");
		System.out.println("| 	Total Time of the journey        |   "+j.totalTime()+" minutes."+"        |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |");
		System.out.println("| 	Total Connecting Time            |   "+j.connectingTime()+" minutes."+"   |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |");
		System.out.println("| 	Total Air Time of the journey    |   "+j.airTime()+" minutes."+"          |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |");
		System.out.println("| 	Total Cost of the journey        | £ "+j.totalCost()+"                    |");
		System.out.println("|=============================================================================|");
	}
	
	//Function to print the full journey according to the choices
	private static void fullJourney(FlyingPlanner flyPlan,int opt,int excAirport,String depCode,String arrCode,List<String> exc)
	{
		//Printing the journey details 
		System.out.println("|=================================================|");
		System.out.println("|     Journey from "+depCode+" and "+arrCode+" is |");
		System.out.println("|=================================================|");
		System.out.println();
		
		try 
		{
			//exclude airport == true
			if(excAirport==2)
			{
				//least_cost == true 
				if(opt==1)
				{
					
					//print the journey with least stops
					printJourney(flyPlan.leastHop(depCode, arrCode,exc),flyPlan);
				}
				else
				{
					//print the journey with least cost
					printJourney(flyPlan.leastCost(depCode, arrCode,exc),flyPlan);
				}
				
			}
			//exclude airport == false
			else
			{
		
				//least_cost == true 
				if(opt==2)
				{
					//print the journey with least stops
					printJourney(flyPlan.leastHop(depCode, arrCode),flyPlan);
				}
				
				else
				{
					//print the journey with least cost 
					printJourney(flyPlan.leastCost(depCode, arrCode),flyPlan);
				}
			}
		} 
		catch (FlyingPlannerException e) 
		{
			e.printStackTrace();
		}
	}
	
	//prints an error message 
	private static void errorMessage() {
		
		System.err.println("| ===========================|");
		System.err.println("|      Input is invalid      |");
		System.err.println("| ===========================|");
		
	}
	
	//Menu selection class 
	private static void menu(FlyingPlanner flyPlan)
	{
		System.out.println("| ===================================================|");
		System.out.println("|   Menu Based Selection For A Travel Agent System   |");
		System.out.println("| ===================================================|\n");
		System.out.println();
		System.out.println("| ==================================================|");
		System.out.println("|          Enter the code for Departure Airport     |");
		System.out.println("| ==================================================|");
		
		String depCode = scan.nextLine().toUpperCase();
		System.out.println();
		System.out.println("| ==================================================|");
		System.out.println("|        Enter the code for Destination Airport     |");
		System.out.println("| ==================================================|");

		String desCode = scan.nextLine().toUpperCase();
		if((flyPlan.airport(depCode)==null) && (flyPlan.airport(depCode)==null))
		{
			errorMessage();
		}
		//menu providing options of least cost or least stops
		System.out.println("|===================================================|");
		System.out.println("|             Please select option 1 or 2           |");
		System.out.println("|===================================================|");
		System.out.println("|     1    |           Least Cost                   |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - |");
		System.out.println("|     2    |           Least Stops                  |");
		System.out.println("|===================================================|");
	
		int cOrS = scan.nextInt();
		
		//error checking 
		if(cOrS!=1 && cOrS!=2)
		{
			errorMessage();
		}
	
		System.out.println("|==========================================|");
		System.out.println("|   Would you like to exclude any aiport?  |");
		System.out.println("|==========================================|");
		System.out.println("|     1   |              Yes               |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - -|");
		System.out.println("|     2   |              No                |");
		System.out.println("|==========================================|");
		int excOpt = scan.nextInt();

		//excluding the airports == true  
		if(excOpt == 1)
		{
			
			System.out.println("|=============================================================|");
			System.out.println("|  Please enter the codes of the airports you want to exclude |");
			System.out.println("|      				"+" [ enter * to stop ] "+"               |");
			System.out.println("|=============================================================|");
			String airCode ;

			List<String> excAirports= new ArrayList<String>();
	
			for(;;)
			{
				System.out.print("> ");
				//Taking the input
				airCode = scan.next().toUpperCase();
				//If the user wants to stop the code then breaking the loop
				if(airCode.equalsIgnoreCase("*"))
				{
					break;
				}
				//If the code entered by the user is not valid then not adding it in the list
				if(!(flyPlan.airport(airCode)==null))
				{
					//If the code is equal to the start code it end code then not adding it in the list
					if(!(airCode.equals(depCode)) && !(airCode.equals(desCode)))
					{
						excAirports.add(airCode);	
					}
					else
					{errorMessage();}
				}
				else
				{errorMessage();}
			}
			
			System.out.println("|=========================================|");
			System.out.println("|     Thank you for entering the codes !  |");
			System.out.println("|=========================================|");
			System.out.println();
			//Calling the function to print the whole journey
			fullJourney(flyPlan,cOrS,1,depCode,desCode,excAirports);
		}
		//if the user doesn't want to exclude airports 
		else if(excOpt == 2)
		{
			//Calling the function with different mode
			fullJourney(flyPlan,cOrS,0,depCode,desCode,null);
		}
		//If the input is not valid then exiting the code
		else
		{errorMessage();}
		
	}
	
	public static void main(String[] args) 
	{
		//Creating the object of class
		FlyingPlanner fi;
		fi = new FlyingPlanner();
		try 
		{
			//Calling the populate function to populate the graph 
			fi.populate(new FlightsReader());
			//Calling the main function
			menu(fi);
		} 
		catch (FileNotFoundException | FlyingPlannerException e) 
		{
			e.printStackTrace();
		}
	}
}