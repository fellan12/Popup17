import java.io.InputStream;
import java.io.PrintWriter;

public class Solver {
	
	private PrintWriter kattio;
	private Cache cache;
	private FutureReferences references;

	public Solver(PrintWriter kattio, InputStream in) {
		this.kattio = kattio;
		InputParser parser = new InputParser(in);
		cache = parser.getCache();
		references = parser.getReferences();
		solve();
	}
	
	private void solve() {
		int accesses = calcAccesses();
		kattio.println(accesses);
		kattio.flush();
		kattio.close();
	}
	
	/**
	 * Calculates the least amount of accesses needed and returns it.
	 */
	private int calcAccesses() {
		int accesses = 0;
		while (!references.isEmpty()) {
			int obj = references.removeFirst();
			if (!cache.contains(obj)) {
				accesses++;
				cache.add(obj, references.nextReference(obj));
			}
		}
		return accesses;
	}
	
	public static void main(String[] args) {
		new Solver(new Kattio(System.in), System.in);
	}
}
