
public class AspenProblem {
	
	private int length;
	private int width;
	private int[] trees;
	
	public AspenProblem(int[] trees, int length, int width) {
		this.trees = trees;
		this.length = length;
		this.width = width;
	}
	
	public int getLength() {
		return length;
	}
	
	public int[] getTrees() {
		return trees;
	}
	
	public int getWidth() {
		return width;
	}

}
