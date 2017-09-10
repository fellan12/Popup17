import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FutureAccesses {

	int[] accesses;
	HashMap<Integer, ArrayList<Integer>> map;
	int index;

	public FutureAccesses(int[] references) {
		this.accesses = references;
		initiateMap();
		index = 0;
	}

	/**
	 * Initiates the map. Adds the indexes of each object in the reference list.
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
	 * Returns Integer.MAX_VALUE-1 if there is no more reference of the object.
	 */
	public int nextAccess(int object) {
		try {
			/*ListNode e = map.get(object);
			ListNode next = e.getNext();
			int ret = next.getIndex();
			map.put(object, next);
			return ret;*/
			List<Integer> list = map.get(object);
			list.remove(0);
			return list.get(0);
		} catch (Exception e) {
			return Integer.MAX_VALUE-1;
		}
	}

	public boolean isEmpty() {
		return index == accesses.length;
	}

	/**
	 * TODO
	 */
	private class ListNode {
		private ListNode next;
		private int index;
		private ListNode last;

		public ListNode(int value) {
			this.index = value;
			this.last = this;
		}

		public int getIndex() {
			return index;
		}

		private void setNext(ListNode e) {
			this.next = e;
		}

		public ListNode getNext() {
			return next;
		}

		public void pushBack(ListNode e) {
			last.setNext(e);
			last = e;
		}
	}
}
