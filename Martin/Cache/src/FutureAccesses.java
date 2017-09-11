import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FutureAccesses {

	int[] accesses;
	HashMap<Integer, ArrayList<Integer>> map;
	int index;

	public FutureAccesses(int[] accesses) {
		this.accesses = accesses;
		initiateMap();
		index = 0;
	}

	/**
	 * Initiates the map. Adds the indexes of each object in the access list.
	 */
	private void initiateMap() {
		map = new HashMap<>();
		for (int i = 0; i < accesses.length; i++) {
			int object = accesses[i];
			if (map.get(object) == null) {
				map.put(object, new ArrayList<>());
			}
			map.get(object).add(i);
		}
	}

	public int peekFirst() {
		return accesses[index];
	}

	public int removeFirst() {
		int ret = accesses[index];
		index++;
		return ret;
	}

	/**
	 * Returns the next access of an object.
	 * Returns Integer.MAX_VALUE if there is no more access of the object.
	 */
	public int nextAccess(int object) {
		try {
			List<Integer> list = map.get(object);
			list.remove(0);	// Remove current access
			return list.get(0);
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}

	public boolean isEmpty() {
		return index == accesses.length;
	}
}
