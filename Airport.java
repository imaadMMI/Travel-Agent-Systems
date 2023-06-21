package F28DA_CW2;

import java.util.Set;

public class Airport implements IAirportPartB, IAirportPartC {
	
	private String cd, ct, n;
	private Set<Airport> dCS;
	private int o;
	
	//Constructor for Airport object
	public Airport(String[] airports) {
		this.cd = airports[0];
		this.ct = airports[1];
		this.n = airports[2];
	}
	
	@Override
	// returns code
	public String getCode() {
		return cd;
	}

	@Override
	// returns name
	public String getName() {
		return n;
	}
	
	// returns city
	public String getCity() {
		return ct;
	}

	@Override
	// returns the set
	public void setDicrectlyConnected(Set<Airport> dicrectlyConnected) {
		this.dCS = dicrectlyConnected;

	}

	@Override
	//returns the set
	public Set<Airport> getDicrectlyConnected() {
		return dCS;
	}


	@Override
	// returns the order
	public void setDicrectlyConnectedOrder(int order) {
		this.o = order;

	}

	@Override
	// returns the order
	public int getDirectlyConnectedOrder() {
		return this.o;
	}

}
