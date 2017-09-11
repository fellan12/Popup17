import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapsackSolver {

	/**
	 * Returns a list of indices
	 */
	public List<Integer> solve(KnapsackProblem problem) {
		List<Integer> solution = new ArrayList<>();
		Item[] items = problem.getItems();
		Arrays.sort(items);
		int[][] matrix = new int[items.length+1][(int)problem.getCapacity()+1];

		//Calculate the best value we can get with the capacity
		for (int i = 1; i < matrix.length; i++) {         //0 -> Items.size()+1
			for (int j = 0; j < matrix[0].length; j++) {    //0 -> Sum(items[i).getWeight())
				if (j < items[i-1].getWeight()) {
					matrix[i][j] = matrix[i-1][j];
				} else {
					matrix[i][j] = Math.max(items[i-1].getValue() + matrix[i-1][j-items[i-1].getWeight()],  matrix[i-1][j]);
				}

			}
		}

		//Backtrack to get the items that gives the best value
		int j = matrix[0].length-1;
		for (int i = matrix.length-1; i >= 0; i--) {
			//If the current max value is not based on the row above max value
			//it's an item we take
			if(matrix[i][j] != matrix[i-1][j]){
				j -= items[i-1].getWeight();
				solution.add(items[i-1].getIndex());
			}

			//When we reach 0 weight or the final item
			if(j == 0 || i == 1){
				break;
			}
		}
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
