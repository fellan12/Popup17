import java.util.HashMap;
import java.util.List;

public class UnionSolver {
	
	private HashMap<Integer, UnionSet> valueToSet;
	private List<Command> commands;
	private StringBuilder sb;

	public UnionSolver() {
		InputParser parser = new InputParser();
		valueToSet = parser.getValueToSet();
		commands = parser.getCommands();
		sb = new StringBuilder();
	}
	
	public void solve() {
		for (Command command : commands) {
			switch (command.getType()) {
			case UNION:
				union(command.getP(), command.getQ());
				break;
			case MOVE:
				move(command.getP(), command.getQ());
				break;
			case RETURN:
				ret(command.getP());
				break;
			}
		}
	}
	
	private void union(int p, int q) {
		UnionSet toRemove = valueToSet.get(q);
		UnionSet union = valueToSet.get(p);
		if (union.getId() == toRemove.getId())
			return;
		// Update hashmap
		for (int i : toRemove.getValues()) {
			valueToSet.put(i, union);
		}
		union.swallow(toRemove);
	}
	
	private void move(int p, int q) {
		UnionSet toAdd = valueToSet.get(q);
		UnionSet toRemove = valueToSet.get(p);
		if (toAdd.getId() == toRemove.getId())
			return;
		valueToSet.put(p, toAdd);
		toRemove.remove(p);
		toAdd.add(p);
	}
	
	private void ret(int p) {
		UnionSet set = valueToSet.get(p);
		sb.append(set.getValues().size()).append(" ");
		int sum = 0;
		for (int i : set.getValues()) {
			sum += i;
		}
		sb.append(sum).append("\n");
	}
	
	public void print() {
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		UnionSolver solver = new UnionSolver();
		solver.solve();
		solver.print();
	}

}
