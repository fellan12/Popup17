import java.util.HashSet;

public class Cache {
	private HashSet<Integer> cache;
	private int size;
	
	public Cache(int size) {
		this.size = size;
		this.cache = new HashSet<>();
	}
	
	/**
	 * Adds an int to the cache. If cache is already filled up, throws RunTimeException.
	 */
	public void add(int n) {
		if (cache.size() == size) {
			throw new RuntimeException("Cannot use method 'add' when cache is filled up. Use replace instead.");
		}
		cache.add(n);
	}
	
	/**
	 * Replaces an old entry in the cache with a new one. Throws RunTimeException if old one isn't in the cache.
	 */
	public void replace(int nev, int old) {
		if (!cache.contains(old)) {
			throw new RuntimeException("Trying to replace old "+old+", but it isn't inside the cache.");
		}
		cache.remove(old);
		cache.add(nev);
	}
	
	/**
	 * Returns whether the cache contains the argument
	 */
	public boolean contains(int n) {
		return cache.contains(n);
	}
	
	/**
	 * Returns whether the cache is full or not.
	 */
	public boolean isFull() {
		return cache.size() == size;
	}
}
