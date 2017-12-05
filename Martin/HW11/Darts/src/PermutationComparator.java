import java.util.Comparator;

public class PermutationComparator implements Comparator<int[]> {
	@Override
	public int compare(int[] arg0, int[] arg1) {
		if (arg0.length != arg1.length)
			throw new ArrayIndexOutOfBoundsException();
		for (int i = 0; i < arg1.length; i++) {
			if (arg0[i] < arg1[i])
				return -1;
			else if (arg0[i] > arg1[i])
				return 1;
		}
		return 0;
	}
}

