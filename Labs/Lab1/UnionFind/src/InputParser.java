
public class InputParser {
	private UnionSet unionSet;
	private Command[] commands;
	private Kattio io;
	
	public InputParser() {
		io = new Kattio(System.in);
		unionSet = new UnionSet(io.getInt());
		initCommands();
	}
	
	private void initCommands() {
		commands = new Command[io.getInt()];
        for (int i = 0; i < commands.length; i++) {
            String operator = io.getWord();
            int a = io.getInt();
            int b = io.getInt();
            commands[i] = new Command(operator, a, b);
        }
	}
	
	public Command[] getCommands() {
		return commands;
	}
	
	public UnionSet getUnionSet() {
		return unionSet;
	}
}
