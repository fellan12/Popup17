import java.util.*;

public class DigiCompII {

	public static void main(String args[]) {
		Kattio io = new Kattio(System.in);
		long numOfBalls = io.getLong();
		int numOfSwitches = io.getInt();

		Switch[] switches = new Switch[numOfSwitches+1];

		for(int i = 0; i < switches.length; i++) {
			switches[i] = new Switch(i);
		}

    switches[0].setState("N");      //Neutral state
    switches[0].setLeftSwitch(new Switch(-1));
    switches[0].setRightSwitch(new Switch(-1));
		for(int i = 1; i < switches.length; i++) {
			switches[i].setState(io.getWord());
			switches[i].setLeftSwitch(switches[io.getInt()]);
			switches[i].setRightSwitch(switches[io.getInt()]);
		}

    //Perform ball pouring in topological order
		Switch[] topSorted = topologicalSort(switches);
		topSorted[0].insertBalls(numOfBalls);
		for(int i = 0; i < topSorted.length; i++) {
			Switch s = topSorted[i];

      //Check that current switch is not null or end-switch
			if(s != null && s.getIndex() != 0) {
				s.getLeftSwitch().insertBalls(s.getBalls()/2);
				s.getRightSwitch().insertBalls(s.getBalls()/2);

        //Uneven number of balls that reached that state
				if((s.getBalls() % 2) != 0 && s.getBalls() != 0) {
					if(s.getState() == State.R) {
						s.getRightSwitch().insertBalls(1);
						s.flipSwitch();
					} else {
						s.getLeftSwitch().insertBalls(1);
						s.flipSwitch();
					}
				}
			}
		}

    //print in normal order
		for(int i = 1; i < switches.length; i++) {
			io.print((switches[i].getState() == State.R) ? 'R' : 'L');
		}
    io.println();

		io.close();
	}

  private static Switch[] topologicalSort(Switch[] switches) {
		Switch[] topSorted = new Switch[switches.length];
		Stack<Switch> stack = new Stack<Switch>();
		boolean[] visited = new boolean[switches.length];

    //Perform DFS from switch 1 to get inverted topological order
		dfs(switches[1], visited, stack);

    //Invert to get topological order
		for(int i = 0; !stack.empty(); i++) {
			topSorted[i] = stack.pop();
		}
		return topSorted;
	}

  //DFS-Recursive
	private static void dfs(Switch s, boolean[] visited, Stack<Switch> res) {
		if(visited[s.getIndex()]) return;
		visited[s.getIndex()] = true;
		if(s.getIndex() != 0) {
			dfs(s.getLeftSwitch(), visited, res);
			dfs(s.getRightSwitch(), visited, res);
		}
		res.push(s);
	}

}
