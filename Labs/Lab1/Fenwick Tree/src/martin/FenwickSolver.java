package martin;

public class FenwickSolver {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int length = io.getInt();
		int queries = io.getInt();
		SumFenwickTree tree = new SumFenwickTree(length);
		for (int i = 0; i < queries; i++) {
			Command c = getCommand(io.getWord());
			int index = io.getInt();
			if (c == Command.ADD) {
				int value = io.getInt();
				tree.add(index, value);
			} else {
				int answer = 0;
				if (index != 0)
					answer = tree.sumLeft(index-1);
				io.println(answer);
			}
		}
		io.close();
	}
	
	private static Command getCommand(String commandString) {
		if (commandString.equals("?"))
			return Command.SUM;
		else
			return Command.ADD;
	}
	
	private enum Command {
		SUM, ADD;
	}

}
