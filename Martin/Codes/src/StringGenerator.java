import java.util.Arrays;

public class StringGenerator {

	public byte[][] generate(int k) {
		int amount = (k*(k+1))/2;
		byte[][] strings = new byte[amount][k];
		int i = 0;
		int index = 0;
		byte[] b = new byte[k];
		while (i < k) {
			int j = i;
			while (j < k) {
				b[j] = 1;
				strings[index] = Arrays.copyOf(b, b.length);
				b[j] = 0;
				index++;
				j++;
			}
			b[i] = 1;
			i++;
		}
		//printStrings(strings);
		return strings;
	}
	
	public void printStrings(byte[][] strings) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			for (int j = 0; j < strings[i].length; j++) {
				sb.append(strings[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
