
public class InputParser {
	private Kattio felix;
	
	public InputParser() {
		felix = new Kattio(System.in);
	}
	
	public int[] getSequence() {
		int[] ret = new int[felix.getInt()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = felix.getInt();
		}
		return ret;
	}
	
	public boolean hasNext() {
		return felix.hasMoreTokens();
	}
}
