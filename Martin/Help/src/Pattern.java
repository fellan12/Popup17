import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Pattern {

	private HashMap<Element, List<Integer>> map;
	private int index;
	
	public Pattern() {
		this.index = 0;
		this.map = new HashMap<>();
	}
	
	/**
	 * Adds an element to the pattern
	 */
	public void add(Element e) {
		List<Integer> list;
		if (!map.containsKey(e)) {
			list = new LinkedList<>();
			map.put(e, list);
		} else {
			list = map.get(e);
		}
		list.add(index);
		index++;
	}
	
	/**
	 * Returns the elements in the pattern as an array
	 */
	public Element[] getElements() {
		Element[] elems = new Element[index];
		for (Element e : map.keySet()) {
			for (int i : map.get(e)) {
				elems[i] = e;
			}
		}
		return elems;
	}
	
	public int getUniqueAmount() {
		return map.size();
	}
	
	/**
	 * returns the amount of words + placeholders 
	 */
	public int getSize() {
		return index;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Element e : getElements()) {
			sb.append(e.getValue()).append(" ");
		}
		return sb.toString();
	}

}
