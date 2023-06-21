package F28DA_CW2;

import java.util.List;

public class totalStops {
		
		private String aC;
		private int nS;
		private int tC;
		
		public totalStops(String code, int stops, int cost) {
			this.aC = code;
			this.nS = stops;
			this.tC = cost;
		}
		
		//returns the value of the "aC" string variable
		public String getCode() {
			return this.aC;
		}
		
		//returns the value of nS if mode is 's', and tC otherwise
		public int getAmount(char mode) {
			if(mode == 's')
				return this.nS;
			else
				return this.tC;
		}
		
		public void sortClass(List<totalStops> list) {
			
		}

		
	}
