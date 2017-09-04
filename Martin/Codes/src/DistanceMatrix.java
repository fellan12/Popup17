
public class DistanceMatrix extends Matrix {
	
	public DistanceMatrix(byte[][] matrix) {
		super(matrix);
	}

	public int multiplyGetDist(byte[] other) {
		int res = 0;
		for (int i = 0; i < matrix().length; i++) {
			res += helpMultiply(matrix()[i], other);
		}
		return res;
	}

}
