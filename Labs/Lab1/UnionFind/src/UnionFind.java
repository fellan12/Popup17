/*
* All the elements are trees with a root that first points to itself
* When merged the larger one gets the smallest one's elements inc. itself
* and the smallest one's new root is the larger one
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
