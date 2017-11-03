import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DigiComp {

	private static boolean[] digiSolver(boolean[] modes, int[] left, int[] right, int[] topoList, long balls) {
		long[] turns = new long[modes.length];
		turns[0] = balls;
		for (int i : topoList) {
			long t = turns[i];
			long leftBalls = t / 2;
			long rightBalls = t / 2;
			if (t % 2 == 1) {
				if (modes[i])
					rightBalls++;
				else
					leftBalls++;
			}
			if (right[i] >= 0)
				turns[right[i]] += rightBalls;
			if (left[i] >= 0)
				turns[left[i]] += leftBalls;
		}
		for (int i = 0; i < modes.length; i++) {
			if (turns[i] % 2 != 0)
				modes[i] = !modes[i];
		}
		return modes;
	}

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		long balls = io.getLong();
		int switches = io.getInt();
		boolean[] modes = new boolean[switches];
		int[] left = new int[modes.length];
		int[] right = new int[modes.length];
		HashMap<Integer, List<Integer>> edges = new HashMap<>();
		int[] inedges = new int[modes.length];
		for (int i = 0; i < modes.length; i++) {
			modes[i] = io.getWord().equals("R");
			int l = io.getInt()-1;
			int r = io.getInt()-1;
			left[i] = l;
			right[i] = r;
			if (!edges.containsKey(i)) {
				edges.put(i, new ArrayList<>());
			}
			if (l >= 0) {
				edges.get(i).add(l);
				inedges[l]++;
			} if (r >= 0) {
				edges.get(i).add(r);
				inedges[r]++;
			}
		}
		int[] topoList = TopoSort.topoSort(edges, inedges);
		boolean[] lastState = digiSolver(modes, left, right, topoList, balls);
		printResults(lastState, io);
	}
	
	private static void printResults(boolean[] results, Kattio io) {
		for (boolean m : results) {
			if (m)
				io.print("R");
			else
				io.print("L");
		}
		io.println();
		io.close();
	}

}
