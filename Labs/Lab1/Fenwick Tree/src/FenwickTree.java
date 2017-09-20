/**
 * Author: Felix De Silva
 * 
 * A binary index tree that easily can sum up ranges
 * of elements from 1 to n
 */

public class FenwickTree {
	protected static long[] binaryIndexTree;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int numOfElements = io.getInt();
		binaryIndexTree = new long[numOfElements+1];

		long numOfOperations = io.getLong();
		for (long i = 0; i < numOfOperations; i++) {
			if(io.getWord().equals("+")){
				add(io.getInt(), io.getLong());
			}else{
				io.println(sum(io.getInt()));
			}
		}
		io.close();
	}

	/**
	 * Constructor for the FenwickTree
	 */
	public FenwickTree(int length){
		binaryIndexTree = new long[length+1];
	}

	/**
	 * Add delta to value as index i
	 * and add delta to the rest that is to be updated
	 */
	public static void add(int i, long delta){
		i++;
		while(i < binaryIndexTree.length){
			binaryIndexTree[i] += delta;
			// To get next number to add delta
			// i AND -i
			// binary(i) AND-op 2compBinary(i)
			i += (i & -i);
		}
	}

	/**
	 * Sum up the from 0 to i from the binaryIndexTree
	 */
	public static long sum(int i){
		if(i == 0){
			return 0;
		}
		long res = 0;
		while(i > 0){
			res += binaryIndexTree[i];
			// To get next number to sum up
			// i AND -i
			// binary(i) AND-op 2compBinary(i)
			i -= (i & -i);
		}
		return res;
	}
	
	/**
	 * Sums the values for the subsequence between left and right.
	 */
	public static long sumRange(int left, int right) {
		return sum(right) - sum(left-1);
	}
}
