import java.util.List;

public class FractionSolver {
	
	private static Kattio io = new Kattio(System.in);

	public static void main(String[] args) {
		int[] q1 = new int[io.getInt()];
		int[] q2 = new int[io.getInt()];
		for (int i = 0; i < q1.length; i++) {
			q1[i] = io.getInt();
		}
		for (int i = 0; i < q2.length; i++) {
			q2[i] = io.getInt();
		}
		
		Rational r1 = new ContinuedFraction(q1).getRational();
		Rational r2 = new ContinuedFraction(q2).getRational();
		
		ContinuedFraction add = new ContinuedFraction(r1.add(r2));
		ContinuedFraction sub = new ContinuedFraction(r1.sub(r2));
		ContinuedFraction mult = new ContinuedFraction(r1.mul(r2));
		ContinuedFraction div = new ContinuedFraction(r1.div(r2));
		
		print(add.toQuotients());
		print(sub.toQuotients());
		print(mult.toQuotients());
		print(div.toQuotients());
		io.close();
	}
	
	public static void print(List<Long> printList) {
		for (long l : printList) {
			io.print(l);
			io.print(" ");
		}
		io.println();
		io.flush();
	}
}
