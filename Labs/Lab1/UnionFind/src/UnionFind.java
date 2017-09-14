/**
 * The main class for solving the union find problem.
 * 
 * Has a unionSet and a list of commands.
 */
public class UnionFind {

	private Kattio io;
    private UnionSet unionSet;
    private Command[] commands;
    
    public UnionFind() {
    	InputParser parser = new InputParser();
    	unionSet = parser.getUnionSet();
    	commands = parser.getCommands();
    	io = new Kattio(System.in);
    }
    
    /**
     * Does all of the work: Reading commands, merging and printing.
     */
    public void solve() {
    	for (Command command : commands) {
    		CommandType type = command.getType();
    		int a = command.getA();
    		int b = command.getB();
			if (type == CommandType.UNION) {
				unionSet.union(a, b);
			} else {
				printBool(unionSet.same(a, b));
			}
		}
    	io.close();
    }
    
    
    private void printBool(boolean b) {
    	String output = "no";
    	if (b)
    		output = "yes";
    	io.println(output);
    }

    public static void main(String[] args) {
        UnionFind solver = new UnionFind();
        solver.solve();
    }
}
