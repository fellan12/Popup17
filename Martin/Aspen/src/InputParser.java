
public class InputParser {

	private Kattio kattio;
	
	public InputParser() {
		this.kattio = new Kattio(System.in);
	}
	
	public boolean hasNext() {
		return kattio.hasMoreTokens();
	}
	
	public AspenProblem nextProblem() {
		int amount = kattio.getInt();
		int length = kattio.getInt();
		int width = kattio.getInt();
		int[] trees = new int[amount];
		for (int i = 0; i < trees.length; i++) {
			trees[i] = kattio.getInt();
		}
		return new AspenProblem(trees, length, width);
	}
}
