import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

	private static Kattio io = new Kattio(System.in);

	private static int solve(DataSet d) {
		int[] m = generateAmountPerPow(d);
		int res = 1;
		for (int i = 1; i < m.length; i++) {
			res += part(m, i, i, d.getBase());
		}
		return res;
	}

	private static int part(int[] a, int index, int high, int base) {
		if (index > high || a[index] == 0)
			return 0;
		if (index == 1) {
			return a[1];
		}
		int[] copy = Arrays.copyOf(a, a.length);
		copy[index]--;
		copy[index-1] += base;
		int i = 1;
		for (int j = 1; j < index; j++) {
			i += part(copy, j, index-1, base);
		}
		i += part(copy, index, high, base);
		return i;
	}

	private static int[] generateAmountPerPow(DataSet d) {
		List<Integer> pow = powersOf(d.getBase(), d.getGoal());
		int[] m = new int[pow.size()];
		int goal = d.getGoal();
		for (int i = pow.size()-1; i > 0; i--) {
			int p = pow.get(i);
			while (goal >= p) {
				m[i]++;
				goal -= p;
			}
		}
		return m;
	}

	private static List<Integer> powersOf(int base, int goal) {
		List<Integer> powersOf = new ArrayList<>();
		powersOf.add(1);
		int p = 1;
		while (true) {
			int i = (int) Math.pow(base, p);
			if (i > goal)
				break;
			powersOf.add(i);
			p++;
		}
		return powersOf;
	}

	public static void main(String[] args) {
		for (DataSet d : InputParser.getInput()) {
			io.println(d.getId()+" "+solve(d));
			io.flush();
		}
		io.close();
	}
	
	private static int sum(int l) {
        return (l*(l+1))/2;
    }
	
	private static class InputParser {
		public static DataSet[] getInput() {
			DataSet[] input = new DataSet[io.getInt()];
			for (int i = 0; i < input.length; i++) {
				input[i] = new DataSet(io.getInt(), io.getInt(), io.getInt());
			}
			return input;
		}
	}
}
