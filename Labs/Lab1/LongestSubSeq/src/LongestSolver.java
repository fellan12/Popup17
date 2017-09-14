
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
			StringBuilder sb = new StringBuilder();
			//sb.append(longest);
			int print = last[longest];
			while (print != Integer.MIN_VALUE) {
				sb.insert(0, print+" ");
				print = parent[print];
			}
			io.println(longest);
			io.println(sb.toString());
		}
		io.flush();
	}

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
