import java.util.HashMap;

public class FutureReferences {
	
	int[] references;
	HashMap<Integer, Elem> map;
	int index;
	
	public FutureReferences(int[] references) {
		this.references = references;
		map = initiateMap();
		index = 0;
	}
	
	/**
	 * Initiates the map. Adds the indexes of each object in the reference list.
	 */
	private HashMap<Integer, Elem> initiateMap() {
		HashMap<Integer, Elem> map = new HashMap<>();
		for (int i = 0; i < references.length; i++) {
			int object = references[i];
			if (map.get(object) == null) {
				map.put(object, new Elem(i));
			} else {
				map.get(object).pushBack(new Elem(i));
			}
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
	 * Returns Integer.MAX_VALUE-1 if there is no more reference of the object.
	 */
	public int nextReference(int object) {
		if (map.get(object) == null)
			return Integer.MAX_VALUE-1;
		Elem next = map.get(object).getNext();
		map.put(object, next);
		int ret = next == null ? Integer.MAX_VALUE-1 : next.getIndex();
		return ret;
	}
	
	public boolean isEmpty() {
		return index == references.length;
	}
	
	/**
	 * TODO
	 */
	private class Elem {
		private Elem next;
		private int index;
		private Elem last;
		
		public Elem(int value) {
			this.index = value;
			this.last = this;
		}
		
		public int getIndex() {
			return index;
		}
		
		private void setNext(Elem e) {
			this.next = e;
		}
		
		public Elem getNext() {
			return next;
		}
		
		public void pushBack(Elem e) {
			last.setNext(e);
			last = e;
		}
	}
}
