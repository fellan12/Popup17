import java.text.DecimalFormat;

public class TheTrip {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int amount = 0;
		while ((amount = io.getInt()) != 0) {
			long total = 0;
			long[] students = new long[amount];
			for (int i = 0; i < students.length; i++) {
				students[i] = getLong(io);
				total += students[i];
			}

			double av = (double) total / (double) amount;
			long average = total / amount;
			if (av-average >= 0.5){
				average++;
			}
			long toGetBack = 0;
			long toPay = 0;
			for (int i = 0; i < students.length; i++) {
				if (students[i] > average) {
					toGetBack += students[i] - average;
				} else if (students[i] < average) {
					toPay += average - students[i];
				}
			}

			io.println("$" + new DecimalFormat("####0.00").format((double) Math.min(toGetBack, toPay) / 100));
		}
		io.close();
	}


	private static long getLong(Kattio io) {
		String[] s = io.getWord().split("\\.");
		String number = s[0]+s[1];
		return Long.parseLong(number);
	}
}
