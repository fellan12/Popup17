import java.io.InputStream;
import java.io.PrintWriter;

public class Solver {
	
	private PrintWriter kattio;
	private Cache cache;
	private FutureAccesses accesses;

	public Solver(PrintWriter kattio, InputStream in) {
		this.kattio = kattio;
		InputParser parser = new InputParser(in);
		cache = parser.getCache();
		accesses = parser.getAccesses();
	}
	
	public void solve() {
		int accesses = calcAccesses();
		kattio.println(accesses);
		kattio.flush();
		kattio.close();
	}
	
	/**
	 * Calculates the least amount of accesses needed and returns it.
	 */
	private int calcAccesses() {
		int acc = 0;
		while (!accesses.isEmpty()) {
			int obj = accesses.removeFirst();
			if (!cache.contains(obj)) {
				acc++;
				cache.add(obj, accesses.nextAccess(obj));
			}
		}
		return acc;
	}
	
	public static void main(String[] args) {
		Solver solver = new Solver(new Kattio(System.in), System.in);
		solver.solve();
	}
}
