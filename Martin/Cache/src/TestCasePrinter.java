
import java.util.Random;

public class TestCasePrinter {
	public static String generateRandom() {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int c = 1 + r.nextInt(5);
		int o = c + r.nextInt(10);
		int a = o + r.nextInt(10);
		sb.append(c).append(" ").append(o).append(" ").append(a);
		for (int i = 0; i < a; i++) {
			sb.append("\n");
			sb.append(r.nextInt(o));
		}
		return sb.toString();
	}
	
	public static String generateLarge() {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int c = 5000;
		int o = 25000;
		int a = 50000;
		sb.append(c).append(" ").append(o).append(" ").append(a);
		for (int i = 0; i < a; i++) {
			sb.append("\n");
			sb.append(r.nextInt(o));
		}
		return sb.toString();
	}
}
