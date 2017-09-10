import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Pattern {

	private Element[] elements;
	private HashMap<Element, List<Integer>> map;
	private int index;
	private boolean updated;
	
	public Pattern() {
		this.index = 0;
		this.map = new HashMap<>();
		this.updated = true;
	}
	
	/**
	 * Adds an element to the pattern
	 */
	public void add(Element e) {
		this.updated = false;
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
	
	public Element[] getElements() {
		return elements;
	}
	
	/**
	 * Updates the pattern. Must be called before calling get
	 */
	private void update() {
		elements = new Element[index];
		for (Element e : map.keySet()) {
			for (int i : map.get(e)) {
				elements[i] = e;
			}
		}
		this.updated = true;
	}
	
	public Element get(int i) {
		if (!updated)
			update();
		return elements[i];
	}
	
	public void replace(Element from, Element to) {
		if (!map.containsKey(from))
			return;
		this.updated = false;
		List<Integer> list = map.remove(from);
		if (map.containsKey(to)) {
			list.addAll(map.get(to));
		}
		map.put(to, list);
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
		update();
		for (Element e : elements) {
			sb.append(e.getValue()).append(" ");
		}
		return sb.toString();
	}

}
