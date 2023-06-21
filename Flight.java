package F28DA_CW2;

public class Flight implements IFlight {

	//DECLARATIONS
		private String fC;
		private Airport f;
		private String fromGMT;
		private Airport t;
		private String toGMT;
		private int c;
		
		//constructor
		public Flight(Airport departure, Airport arrival, String[] flight) {
			this.fC = flight[0];
			this.f = departure;
			this.fromGMT = formatTimeString(flight[2]);
			this.t = arrival;
			this.toGMT = formatTimeString(flight[4]);
			this.c = Integer.parseInt(flight[5]);
		}
		
		//formats a given time string by padding it with leading zeros
		public static String formatTimeString(String time) {
			if(time.length() == 1) {
				time = ("000" + time);
				return time;
			}
			else if(time.length() == 2) {
				time = ("00" + time);
				return time;
			}
			else if(time.length() == 3) {
				time = ("0" + time);
				return time;
			}
			else {
				return time;
			}
		}
		
	@Override
	//Return flight code
	public String getFlightCode() {
		// TODO Auto-generated method stub
		return this.fC;
	}

	@Override
	//Return Destination airport
	public Airport getTo() {
		// TODO Auto-generated method stub
		return this.t;
	}

	@Override
	//Return Departure airport
	public Airport getFrom() {
		// TODO Auto-generated method stub
		return this.f;
	}

	@Override
	//Return Departure time
	public String getFromGMTime() {
		// TODO Auto-generated method stub
		return this.fromGMT;
	}

	@Override
	//Return Destination time
	public String getToGMTime() {
		// TODO Auto-generated method stub
		return this.toGMT;
	}

	@Override
	//Returns Cost
	public int getCost() {
		// TODO Auto-generated method stub
		return this.c;
	}
	
	//Calculates flight duration
	public int getFlightDuration() {
		int dur = FlyingPlanner.timeDuration(this.fromGMT, this.toGMT);
		return dur;
	}


}
