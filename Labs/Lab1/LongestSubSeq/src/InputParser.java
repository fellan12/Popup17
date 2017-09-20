/**
 * Authors: Martin Engelin & Felix De Silva 
 */
public class InputParser {
	
	private Kattio io;
	
	public InputParser(Kattio io) {
		this.io = io;
	}
	
	public int[] getSequence() {
		int[] ret = new int[io.getInt()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = io.getInt();
		}
		return ret;
	}
	
	public boolean hasNext() {
		return io.hasMoreTokens();
	}
}
