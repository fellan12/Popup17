import java.util.ArrayList;
import java.util.List;

public class KnapsackSolver {
	
	/**
	 * Returns a list of indices
	 */
	public List<Integer> solve(KnapsackProblem problem) {
		List<Integer> solution = new ArrayList<>();
		// TODO
		return solution;
	}

	public static void main(String[] args) {
		InputParser parser = new InputParser();
		StringBuilder sb = new StringBuilder();
		KnapsackSolver solver = new KnapsackSolver();
		KnapsackProblem problem = null;
		while ((problem = parser.getNextProblem()) != null) {
			List<Integer> solution = solver.solve(problem);
			sb.append(solution.size()).append("\n");
			for (int index : solution)
				sb.append(index).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
