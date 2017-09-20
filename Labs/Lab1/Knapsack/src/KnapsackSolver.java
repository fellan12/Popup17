import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 * Authors: Felix De Silva & Martin Engelin
 */
public class KnapsackSolver {

	/**
	 * Solves a knapsack problem and returns a list of integers that represents 
	 * the index of the item in the argument array.
	 */
	public static List<Integer> knapsack(double capacity, Item[] items) {
		List<Integer> solution = new ArrayList<>();
		Arrays.sort(items, new ItemComparator());
		int[][] matrix = new int[items.length+1][(int)capacity+1];

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (j < items[i-1].getWeight()) {
					matrix[i][j] = matrix[i-1][j];
				} else {
					matrix[i][j] = Math.max(items[i-1].getValue() + matrix[i-1][j-items[i-1].getWeight()],  matrix[i-1][j]);
				}
			}
		}

		int j = matrix[0].length-1;
		for (int i = matrix.length-1; i >= 0; i--) {
			if(matrix[i][j] != matrix[i-1][j]){
				j -= items[i-1].getWeight();
				solution.add(items[i-1].getIndex());
			}
			if(j == 0 || i == 1){
				break;
			}
		}
		return solution;
	}

	public static void main(String[] args) {
		InputParser parser = new InputParser();
		StringBuilder sb = new StringBuilder();
		KnapsackProblem problem = null;
		while ((problem = parser.getNextProblem()) != null) {
			List<Integer> solution = KnapsackSolver.knapsack(problem.getCapacity(), problem.getItems());
			sb.append(solution.size()).append("\n");
			for (int index : solution)
				sb.append(index).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item o1, Item o2) {
			return Integer.compare(o1.getWeight(), o2.getWeight());
		}
		
	}

}
