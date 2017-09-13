import java.util.ArrayList;
import java.util.List;

public class UnionSet {
	private int id;
	private List<Integer> values;
	
	public UnionSet(int id) {
		this.id = id;
		this.values = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public List<Integer> getValues() {
		return values;
	}
	
	public void add(int i) {
		values.add(i);
	}
	
	public void remove(int i) {
		values.remove(new Integer(i));
	}
	
	public void swallow(UnionSet set) {
		this.values.addAll(set.values);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(id).append("]{");
		if (values.size() > 0)
			sb.append(values.get(0));
		for (int i = 1; i < values.size(); i++) {
			sb.append(", ").append(values.get(i));
		}
		sb.append("}");
		return sb.toString();
	}
}
