import java.util.PriorityQueue;

public class Cache {

	private PriorityQueue<AccessObject> cache;
	private int maxSize;

	public Cache(int size) {
		this.cache = new PriorityQueue<>(size);
		this.maxSize = size;
	}

	/**
	 * Replaces an old entry in the cache with a new one.
	 */
	public void add(AccessObject obj) {
		if (cache.size() == maxSize) {
			AccessObject remove = cache.poll();
			remove.removeFromCache();
			remove.removeNextAccess();
		}
		cache.offer(obj);
		obj.setInCache();
	}
	
	/**
	 * Updates the object in the cache to the new nextRef
	 */
	public void update(AccessObject obj) {
		cache.remove(obj);
		obj.removeNextAccess();
		cache.add(obj);
	}
	
	@Override
	public String toString() {
		return cache.toString();
	}
}
