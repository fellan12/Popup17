import java.io.InputStream;

/**
 * Parses the input of the problem Codes
 */
public class InputParser {
	
	private Kattio kattio;
	
	public InputParser(InputStream in) {
		this.kattio = new Kattio(in);
	}
	
	/**
	 * Reads the input and returns an array of Matrix objects.
	 */
	public DistanceMatrix[] getMatrices() {
		DistanceMatrix[] matrices = new DistanceMatrix[kattio.getInt()];
		for (int i = 0; i < matrices.length; i++) {
			matrices[i] = readNext();
		}
		return matrices;
	}
	
	/**
	 * Reads the next matrix and returns it as a Matrix object
	 */
	private DistanceMatrix readNext() {
		int n = kattio.getInt();
		int k = kattio.getInt();
		byte[][] m = new byte[n][k];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = (byte) kattio.getInt();
			}
		}
		return new DistanceMatrix(m);
	}
}
