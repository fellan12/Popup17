import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Cache {

	//private ArrayList<CacheObject> cache;
	private HashSet<Integer> containSet;
	private PriorityQueue<CacheObject> cache;
	private int maxSize;

	public Cache(int size) {
		this.cache = new PriorityQueue<>(size, new ObjectComparator());
		this.containSet = new HashSet<>();
		this.maxSize = size;
	}

	/**
	 * Replaces an old entry in the cache with a new one.
	 */
	public void add(int obj, int nextRef) {
		if (cache.size() == maxSize) {
			containSet.remove(cache.poll().id);
		}
		cache.offer(new CacheObject(obj, nextRef));
		containSet.add(obj);
	}
	
	/**
	 * Returns whether the cache contains the argument
	 */
	public boolean contains(int obj) {
		return containSet.contains(obj);
	}
	
	@Override
	public String toString() {
		return cache.toString();
	}
	
	private class CacheObject {
		int id;
		int nextRef;
		
		public CacheObject(int id, int nextRef) {
			this.id = id;
			this.nextRef = nextRef;
		}
		
		@Override
		public String toString() {
			return "["+id+", "+nextRef+"]";
		}
	}
	
	private class ObjectComparator implements Comparator<CacheObject> {

		@Override
		public int compare(CacheObject o1, CacheObject o2) {
			return Integer.compare(o2.nextRef, o1.nextRef);
		}
		
	}
}
