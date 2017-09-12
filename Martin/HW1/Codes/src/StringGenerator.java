public class StringGenerator {
	
	public byte[][] generate(int k) {
		int amount = (int) Math.pow(2, k) - 1;
		byte[][] strings = new byte[amount][k];
		for (int i = 1; i <= amount; i++) {
			strings[i-1] = toBinary(i, k);
		}
		//printStrings(strings);
		return strings;
	}
	
	private byte[] toBinary(int number, int base) {
	    final byte[] ret = new byte[base];
	    for (int i = 0; i < base; i++) {
	    	byte n = 0;
	    	if ((1 << i & number) != 0)
	    		n = 1;
	    	ret[base - 1 - i] = n;
	    }
	    return ret;
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
