import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class CacheSolver {
	
	private PrintWriter kattio;
	private Cache cache;
	private HashMap<Integer, AccessObject> objects;
	private int[] accesses;

	public CacheSolver(PrintWriter kattio, InputStream in) {
		this.kattio = kattio;
		InputParser parser = new InputParser(in);
		this.cache = parser.getCache();
		this.objects = parser.getObjects();
		this.accesses = parser.getAccesses();
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
		for (int i = 0; i < accesses.length; i++) {
			AccessObject obj = objects.get(accesses[i]);
			if (!obj.isInCache()) {
				acc++;
				cache.add(obj);
			} else {
				cache.update(obj);
			}
		}
		return acc;
	}
	
	public static void main(String[] args) {
		CacheSolver solver = new CacheSolver(new Kattio(System.in), System.in);
		solver.solve();
	}
}
