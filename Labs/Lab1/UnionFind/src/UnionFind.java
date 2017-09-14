/*
* All the elements are trees with a root that first points to itself
* When merged the larger one gets the smallest one's elements inc. itself
* and the smallest one's new root is the larger one
*/

public class UnionFind {

	private Kattio io;
    private UnionSet unionSet;
    
    public UnionFind() {
    	io = new Kattio(System.in);
    	unionSet = new UnionSet(io.getInt());
	}
	
	private Command getNextCommand() {
		if (!io.hasMoreTokens())
			return null;
		String operator = io.getWord();
        int a = io.getInt();
        int b = io.getInt();
        return new Command(operator, a, b);
	}
    
    public void solve() {
    	int commandAmount = io.getInt();
    	for (int i = 0; i < commandAmount; i++) {
    		Command c = getNextCommand();
    		int a = c.getA();
    		int b = c.getB();
			if (c.getType() == CommandType.UNION) {
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
