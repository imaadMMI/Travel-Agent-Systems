# Travel-Agent-Systems
Implemented using Java and Object Oriented Programming techniques, a system for a travel agent was created with the added help of graph libraries to represent airline data.

DESCRIPTION:
The task is implement a system for a travel agency which uses a graph library to represent airline data,
and supports searching based on criteria the travel agent sets. For this reason, the system should
allow inputs from the user and an output in an appropriate format.
Some code is provided to help you in getting started with JUnits for checking your code. We also
provide you with a Dataset that you should use for your system. Your code should work with any
dataset, and we will use a different dataset during assessment.
The coursework aims to reinforce your understanding of course material, specifically the following learning
objectives:
• Gain an understanding of a range of graph classes and their use to represent realistic data.
• Gain further experience in object-oriented software engineering with a non-trivial class hierarchy:
specifically selecting an appropriate class; reusing existing classes; extending existing classes.
• Using generic code: reusing generic graph classes, and parameterising a class with different types.
• You will also gain general software engineering experience, specifically downloading and using Open
Source software, using a general method for a specific purpose, and issues with reusing existing
code.
• Gain further experience with Java programming.

PART A: Representing direct flights and Cheapest connections:
Write a program FlyingPlannerMainPartA (containing a single main method) to represent the
following direct flights with associated costs as a graph. For the purpose of this exercise assume
that flights operate in both directions with the same cost, e.g. Edinburgh to Heathrow denotes a
pair of flights, one from Edinburgh to Heathrow, and another from Heathrow to Edinburgh.
Hint: Flights are directed, i.e. from one airport to another, and weighted by the ticket cost,
hence use the JGraphT SimpleDirectedWeightedGraph class. You should display the contents
of the graph (and may omit the weights).

Extend your program to search the flights graph to find the cheapest journey between two cities
consisting of one or more direct flights.


PART B: Use provided flights dataset, add flight information Week 9–10
You should now write a program FlyingPlannerPartBC (containing a single main method) which
will make use of your class FlyingPlanner (this is the central class of your program although it
does not have to have a main method).
Add flight information
Your program should be operating on a flight graph that will now include the following
information about each flight. The flight number, e.g. BA345; the departure time; the arrival time;
the flight duration; and the ticket price, e.g. 100. All times should be recorded in 24 hour hhmm
format, e.g. 1830. Individual flight durations are under 24h.
Use the additional flight information to print the cheapest journey in a format similar to the
following example. The key aspects are:
1. A sequence of connecting flights (with cheapest),
2. A total cost for the journey.

Use provided flights dataset
Build your graph of flights using the provided flights dataset and its reader (FlightsReader). The
dataset is composed of a list of airports (indexed by a three character code), and a list of flights
(indexed by a flight code). The list of airports and flights originated from the Open Flights
https://openflights.org/ open source project. In addition to these initial lists the following
information were automatically and randomly generated: the flight numbers, departure and
arrival times, cost.

Interfaces to implement
For the purpose of printing such journey, and to complete this part,
• your FlyingPlanner class should implement the IFlyingPlannerPartB<Airport,Flight> interface;
• your Journey class should implement the IJourneyPartB<Airport,Flight> interface;
• your Airport class should implement the IAirportPartB interface,
• your Flight class should implement the IFlight interface.


PART C: Advanced features

Journey duration
Extend your program to calculate the total time in the air, i.e. the sum of the durations of all
flights in the journey and the total trip time.
Hint: you will need to write functions to perform arithmetic on 24 hour clock times.
Least hops
Extend your program to locate journeys with the fewest number of changeovers. Extend your
program to offer the possibility to exclude one or more airports from the journey search.
Directly connected order
Extend your program to calculate for an airport the number of directly connected airports. Two
airports are directly connected if there exist two flights connecting them in a single hop in both
directions. Extend your program to calculate the set of airports reachable from a given airport
that have strictly more direct connections.
Hint: use a directed acyclic graph, available in JGraphT.
Meet-Up search
Extend your program to offer the possibility to search for a least-hop/least-price meet-up
place for two people located at two different airports. The meet-up should be different than
the two starting airports. Extend your program to offer the possibility to search for a least time
meet-up place for two people located at two different airports (considering a given starting
time).
AirMiles scheme
The travel agency also wants to reward customers giving them airmiles they can later spend in
their online shop (shop not part of this coursework). These airmiles are calculated by
multiplying the flight time (time on air only) by 3% 30% of the total cost of a journey. Airmiles
can only be whole numbers. The system should ignore any decimal points. Implement a
function to display the air mile points awarded per chosen flight.
Interfaces to implement
To complete this part,
• your FlyingPlanner class should implement the IFlyingPlannerPartC<Airport,Flight> interface;
• your Journey class should implement the IJourneyPartC<Airport,Flight> interface;
• your Airport class should implement the IAirportPartC interface,
• your Flight class should implement the IFlight interface,
