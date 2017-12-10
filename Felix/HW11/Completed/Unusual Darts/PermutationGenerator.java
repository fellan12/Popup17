import java.util.Arrays;

public class PermutationGenerator {

	public static int[][] permutations(int[] A) {
		int[][] res = new int[5040][7];
		int[] c = new int[A.length];
		res[0] = A;
		int index = 1;
		int i = 0;
		while (i < A.length) {
			if (c[i] < i) {
				A = even(i) ? swap(A, 0, i) : swap(A, c[i], i);
				res[index] = A;
				index++;
				c[i]++;
				i = 0;
			} else {
				c[i] = 0;
				i++;
			}
		}
		return res;
	}

	private static boolean even(int i) {
		return i % 2 == 0;
	}

	private static int[] swap(int[] A, int i, int j) {
		A = copy(A);
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
		return A;
	}

	private static int[] copy(int[] A) {
		return Arrays.copyOf(A, A.length);
	}
}
