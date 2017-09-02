
public class Solver {
	
	private Kattio kattio;
	private Cache cache;
	private FutureReferences references;

	public Solver() {
		this.kattio = new Kattio(System.in);
		InputParser parser = new InputParser();
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
			int object = references.removeFirst();
			if (!cache.contains(object)) {
				accesses++;
				addObjectToCache(object);
			}
		}
		return accesses;
	}
	
	/**
	 * Adds an object to the cache, and removes an object if necessary.
	 */
	private void addObjectToCache(int o) {
		if (!cache.isFull()) {
			cache.add(o);
			return;
		}
		int objectToRemove = findBestObjectToRemove();
		cache.replace(o, objectToRemove);
	}
	
	/**
	 * Finds the object in the cache that is referenced latest.
	 */
	private int findBestObjectToRemove() {
		int bestObject = -1;
		int bestReference = -1;
		for (int cacheObject : cache.getObjectsInCache()) {
			int nextRef = references.nextReference(cacheObject);
			if (nextRef == -1) {
				bestObject = cacheObject;
				break;
			}
			if (nextRef > bestReference) {
				bestReference = nextRef;
				bestObject = cacheObject;
			}
		}
		return bestObject;
	}
	
	public static void main(String[] args) {
		new Solver();
	}
}
