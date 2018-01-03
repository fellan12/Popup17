import java.util.Arrays;

public class RussianDolls {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		StringBuilder sb = new StringBuilder();
		int dollPerSet = io.getInt();
		while(dollPerSet != 0) {
			Doll[] dolls = new Doll[dollPerSet*2];
			for (int j = 0; j < dolls.length; j++) {
				int h = io.getInt();
				int d = io.getInt();
				int w = io.getInt();
				dolls[j] = new Doll(h,d,w);
			}
			Doll[][] results = solve(dolls);
			for (int j = 0; j < results.length; j++) {
				for (int j2 = 0; j2 < results[0].length; j2++) {
					Doll doll = results[j][j2];
					sb.append(doll.height).append(" ").append(doll.diameter).append(" ").append(doll.width).append("\n");
				}
				if (j == 0)
					sb.append("-\n");
			}
			sb.append("\n\n");
			dollPerSet = io.getInt();
		}
		io.print(sb.toString());
		io.close();
	}

	private static Doll[][] solve(Doll[] dolls) {
		Doll[][] results = new Doll[2][dolls.length/2];
		Arrays.sort(dolls);
		int idx1 = 0;
		int idx2 = 0;
		for (int i = 0; i < dolls.length; i++) {
			
			Doll d = dolls[i];
			
			if(idx1 == 0){
				results[0][idx1] = d;
				idx1++;
				continue;
			}
			
			if(idx1 < dolls.length/2 && results[0][idx1-1].fit(d)){
				if(idx2 > 0 && idx2 < dolls.length/2 && results[1][idx2-1].fit(d)){
					int doll1HeightDiff = results[0][idx1-1].innerHeight() - d.height;
					int doll1DiameterDiff = results[0][idx1-1].innerDiameter() - d.diameter;
					
					int doll2HeightDiff = results[1][idx2-1].innerHeight() - d.height;
					int doll2DiameterDiff = results[1][idx2-1].innerDiameter() - d.diameter;
					
					if(doll1DiameterDiff < doll2DiameterDiff && doll1HeightDiff < doll2HeightDiff){
						results[0][idx1] = d;
						idx1++;

					} else {
						results[1][idx2] = d;
						idx2++;

					}
				} else {
					results[0][idx1] = d;
					idx1++;

				}
			} else {
				results[1][idx2] = d;
				idx2++;

			}
		
		}
		
		return results;
	}
	
	private static class Doll implements Comparable<Doll>{
		int height;
		int diameter;
		int width;
		
		public Doll(int height, int diameter, int width) {
			this.height = height;
			this.diameter = diameter;
			this.width = width;
		}
		
		public int innerDiameter() {
			return diameter-2*width;
		}
		
		public int innerHeight() {
			return height-2*width;
		}
		
		public boolean fit(Doll other) {
			return (this.innerDiameter() >= other.diameter) && (this.innerHeight() >= other.innerHeight());
		}

		@Override
		public int compareTo(Doll o) {
			return Integer.compare(o.innerDiameter()*this.innerHeight(), this.innerDiameter()*this.innerHeight());
		}
		
		@Override
		public String toString() {
			return height+"";
		}
	}
}
