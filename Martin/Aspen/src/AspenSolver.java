import java.util.Arrays;

public class AspenSolver {
	
	public void solve(AspenProblem problem) {
		int[] trees = problem.getTrees();
		Arrays.sort(trees);
		if (trees.length % 2 != 0)
			throw new RuntimeException("Odd number of trees: "+trees.length);
		double distanceBetweenTrees = (double) problem.getLength() / (trees.length/2-1);
		double len = 0;
		double moveDistance = 0;
		for (int i = 0; i < trees.length-1; i=i+2) {
			int tree1 = trees[i];
			int tree2 = trees[i+1];
			double dist1 = Math.abs(tree1 - len);
			double dist2 = Math.abs(tree2 - len);
			double horizontal = dist1 > dist2 ? dist2 : dist1;
			double diagonal = horizontal==dist1 ? dist2 : dist1;
			moveDistance += horizontal;
			moveDistance += pythagoras(diagonal, problem.getWidth());
			len += distanceBetweenTrees;
		}
		System.out.println(moveDistance);
	}
	
	private double pythagoras(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	public static void main(String[] args) {
		AspenSolver solver = new AspenSolver();
		InputParser parser = new InputParser();
		while (parser.hasNext()) {
			solver.solve(parser.nextProblem());
		}
	}
	
}
