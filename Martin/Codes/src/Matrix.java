
public class Matrix {

	private byte[][] m;
	
	public Matrix(int height, int width) {
		m = new byte[height][width];
	}
	
	public Matrix(byte[][] matrix) {
		this.m = matrix;
	}
	
	public byte[] multiply(byte[] other) {
		byte[] res = new byte[getHeight()];
		for (int i = 0; i < m.length; i++) {
			res[i] = helpMultiply(m[i], other);
		}
		return res;
	}
	
	protected byte helpMultiply(byte[] first, byte[] second) {
		int ret = 0;
		for (int i = 0; i < first.length; i++) {
			ret += first[i] * second[i];
		}
		return (byte) (ret % 2);
	}
	
	public int getHeight() {
		return m.length;
	}
	
	public int getWidth() {
		if (m.length == 0)
			return 0;
		return m[0].length;
	}
	
	public byte[][] matrix() {
		return m;
	}
}
