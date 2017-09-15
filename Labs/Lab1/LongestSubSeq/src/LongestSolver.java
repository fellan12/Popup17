
public class LongestSolver {

	private Kattio io;
	private int[] seq;

	public LongestSolver() {
		io = new Kattio(System.in);
	}

	public void solve() {
		InputParser parser = new InputParser();
		while (parser.hasNext()) {
			seq = parser.getSequence();
			int longest = 0;
			int[] parent = new int[seq.length];
			int[] last = new int[seq.length+1];
			// Initiate last
			last[0] = Integer.MIN_VALUE;
			for (int i = 1; i < last.length; i++) {
				last[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < seq.length; i++) {
				// Find the lowest index l such that seq[i] is smaller than the next
				int l = find(last, longest, i);
				if (l > longest)
					longest = l;
				// update last
				if (last[l] == Integer.MAX_VALUE || seq[last[l]] > seq[i]) {
					last[l] = i;
					parent[i] = last[l-1];
				}
			}
			printAnswer(parent, last[longest], longest);
		}
		io.flush();
	}
	
	/**
	 * Prints the answer from the arguments.
	 * parent = array that maps all indexes to it's "parent", ie the previous index
	 *          in the longest inc subseq
	 * start  = The last element in the last array. The only one we can know for
	 *          sure is correct
	 * size   = The size of the answer
	 */
	private void printAnswer(int[] parent, int start, int size) {
		int[] arr = new int[size];
		int i = 0;
		while (start != Integer.MIN_VALUE) {
			arr[i] = start;
			start = parent[start];
			i++;
		}
		io.println(size);
		while (i > 0) {
			i--;
			io.print(arr[i]);
			io.print(" ");
		}
		io.println();
	}

	/**
	 * Binary searches last in order to find the correct l
	 */
	private int find(int[] last, int start, int i) {
		int low = 0;
		int high = start;
		int mid = 0;
		while (low <= high) {
			mid = low + (high-low)/2 + (low+(high-low) % 2 == 1 ? 1 : 0);
			if (last[mid] == Integer.MAX_VALUE) {
				high = mid-1;
			} else if (last[mid] == Integer.MIN_VALUE) {
				low = mid+1;
			} else if (seq[last[mid]] > seq[i]) {
				high = mid-1;
			} else if (seq[last[mid]] < seq[i]) {
				low = mid+1;
			} else {
				return mid;
			}
		}
		if (last[mid] == Integer.MIN_VALUE || seq[last[mid]] < seq[i])
			mid++;
		return mid;
	}

	public static void main(String[] args) {
		new LongestSolver().solve();
	}

}
