import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputParser {

	private HashMap<Integer, UnionSet> valueToSet;
	private Kattio kattio;
	private List<Command> commands;
	
	public InputParser() {
		this.valueToSet = new HashMap<>();
		this.kattio = new Kattio(System.in);
		this.commands = new ArrayList<>();
		init();
	}
	
	private void init() {
		int n = kattio.getInt();
		int m = kattio.getInt();
		for (int i = 0; i < n; i++) {
			UnionSet set = new UnionSet(i);
			set.add(i+1);
			valueToSet.put(i+1, set);
		}
		for (int i = 0; i < m; i++) {
			int c = kattio.getInt();
			Command command = new Command(c);
			command.setP(kattio.getInt());
			if (command.getType() != CommandType.RETURN)
				command.setQ(kattio.getInt());
			commands.add(command);
		}
	}
	
	public HashMap<Integer, UnionSet> getValueToSet() {
		return valueToSet;
	}
	
	public List<Command> getCommands() {
		return commands;
	}
}
