import java.text.DecimalFormat;

public class TripSolver {
	
	private static Kattio io = new Kattio(System.in);

	public static void main(String[] args) {
		int studentAmount = 0;
		DecimalFormat df = new DecimalFormat("####0.00");
		while ((studentAmount = io.getInt()) != 0) {
			double toPay = testCase(studentAmount);
			io.print("$");
			io.println(df.format(toPay));
			//io.flush();
		}
		io.close();
	}
	
	private static double testCase(int studentAmount) {
		long total = 0;
		long[] students = new long[studentAmount];
		for (int i = 0; i < students.length; i++) {
			students[i] = nextLong();
			total += students[i];
		}
		double av = (double) total / (double) studentAmount;
		long average = total / studentAmount;
		if (av-average >= 0.5)
			average++;
		long toGetBack = 0;
		long toPay = 0;
		for (int i = 0; i < students.length; i++) {
			if (students[i] > average) {
				toGetBack += students[i] - average;
			} else if (students[i] < average) {
				toPay += average - students[i];
			}
		}
		long ret = Math.min(toGetBack, toPay);
		return (double) ret / 100;
	}

	private static long nextLong() {
		String[] s = io.getWord().split("\\.");
		String number = s[0]+s[1];
		return Long.parseLong(number);
	}
}
