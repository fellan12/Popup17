import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Scanner;

public class Cantor {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String s;
		while (!(s = in.nextLine()).equals("END")) {
			double d = Double.parseDouble(s);
			if (isCantor(d))
				sb.append("MEMBER\n");
			else
				sb.append("NON-MEMBER\n");
		}
		System.out.println(sb.toString());
		in.close();
	}
	
	public static boolean isCantor1(double d) {
		double low = 0;
		double lmid = 1/3;
		double hmid = 2/3;
		double high = 1;
		while (true) {
			if (d == low || d == lmid || d == hmid || d == high) {
				return true;
			} else if (lmid < d && d < hmid) {
				return false;
			} else if (low < d && d < lmid) {
				lmid = low+(lmid-low)/3;
				hmid = low+2*((lmid-low)/3);
				high = lmid;
			} else {
				low = hmid;
				lmid = hmid+(high-hmid)/3;
				hmid = hmid+2*((high-hmid)/3);
			}
		}
	}
	
	public static boolean isCantor(double d) {
		HashSet<Double> visited = new HashSet<>();
		DecimalFormat df = new DecimalFormat("0.000000");
		while (true) {
			d = Double.parseDouble(df.format(d));
			if (visited.contains(d))
				break;
			visited.add(d);
			d = 3*d;
			int quotient = (int) d;
			if (quotient == 1)
				return false;
			d = d - quotient;
		}
		return true;
	}
}
