import java.util.ArrayList;
import java.util.HashSet;

public class Cache {

	private ArrayList<CacheObject> cache;
	private HashSet<Integer> containSet;

	public Cache(int size) {
		initCache(size);
		this.containSet = new HashSet<>();
	}
	
	private void initCache(int size) {
		this.cache = new ArrayList<>(size);
		CacheObject obj = new CacheObject(-1, Integer.MAX_VALUE);
		for (int i = 0; i < size; i++) {
			cache.add(obj);
		}
	}

	/**
	 * Replaces an old entry in the cache with a new one.
	 */
	public void add(int obj, int nextRef) {
		CacheObject oldObj = cache.remove(0);
		containSet.remove(oldObj.id);
		containSet.add(obj);
		insertObjectIntoCache(new CacheObject(obj, nextRef));
	}
	
	private void insertObjectIntoCache(CacheObject obj) {
		if (cache.size() == 0) {
			cache.add(obj);
			return;
		} else if (obj.nextRef == Integer.MAX_VALUE-1) {
			cache.add(0, obj);
			return;
		}
		int lo = 0;
		int hi = cache.size()-1;
		int mid = -1;
		int ref = -1;
		while (lo <= hi) {
			mid = lo + (hi - lo) / 2;
			CacheObject compare = cache.get(mid);
			ref = compare.nextRef;
			if (obj.nextRef > compare.nextRef)
				hi = mid-1;
			else if (obj.nextRef < compare.nextRef)
				lo = mid+1;
			else
				throw new RuntimeException("Trying to add an object already inside the cache!");
		}
		int index = mid + (ref < obj.nextRef ? 0 : 1);
		cache.add(index, obj);
	}
	
	/**
	 * Returns whether the cache contains the argument
	 */
	public boolean contains(int obj) {
		return containSet.contains(obj);
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
}
