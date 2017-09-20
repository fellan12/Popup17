/**
 * Authors: Martin Engelin & Felix De Silva
 * 
 * Parses the input and returns a UnionSet and an array of Commands
 */
public class InputParser {
	private UnionFind unionSet;
	private Command[] commands;
	private Kattio io;
	
	public InputParser() {
		io = new Kattio(System.in);
		unionSet = new UnionFind(io.getInt());
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
	
	public UnionFind getUnionSet() {
		return unionSet;
	}
}
