import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FutureReferences {
	
	int[] references;
	HashMap<Integer, List<Integer>> map;
	int index;
	
	public FutureReferences(int[] references) {
		this.references = references;
		map = initiateMap();
		index = 0;
	}
	
	/**
	 * Initiates the map. Adds the indexes of each object in the reference list.
	 */
	private HashMap<Integer, List<Integer>> initiateMap() {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < references.length; i++) {
			int object = references[i];
			if (map.get(object) == null) {
				map.put(object, new ArrayList<>());
			}
			map.get(object).add(i);
		}
		return map;
	}
	
	public int peekFirst() {
		return references[index];
	}
	
	public int removeFirst() {
		int ret = references[index];
		index++;
		return ret;
	}
	
	/**
	 * Returns the next reference of an object.
	 * Returns -1 if there is no more reference of the object.
	 */
	public int nextReference(int object) {
		Iterator<Integer> it = map.get(object).iterator();
		while (it.hasNext()) {
			int i = it.next();
			if (i < index) {
				it.remove();
			} else {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return index == references.length;
	}
}
