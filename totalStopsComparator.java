package F28DA_CW2;

import java.util.*;

public class totalStopsComparator implements Comparator<totalStops> {
	
	@Override
	//compares objects' 'h' and 'c' attributes and returns the result based on their values
	public int compare(totalStops first, totalStops second) {
		int hC = 0;
		int h1 = first.getAmount('h');
		int h2 = second.getAmount('h');
		if (h1 > h2) {
			hC = 1;
		}
		else if (h1 == h2) {
			hC = 0;
		}
		else {
			hC = -1;
		}
		
		
		
		int cost = 0;
		int c1 = first.getAmount('c');
		int c2 = second.getAmount('c');
		if(c1 == c2)
			cost = 0;
		else if(c1 > c2)
			cost = 1;
		else
			cost = -1;
		if (hC==0) {
			return cost;
		}
		return hC;
	}

}

