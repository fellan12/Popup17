import java.util.ArrayList;
import java.util.List;

public class LongestSolver {

	private Kattio kattio;

	public LongestSolver() {
		kattio = new Kattio(System.in);
	}

	public void solve() {
		InputParser parser = new InputParser();
		int[] seq;
		while (parser.hasNext()) {
			seq = parser.getSequence();
			List<Integer> longest = new ArrayList<>();
			List<Integer> alt = new ArrayList<>();
			int[] last = new int[seq.length+1];
			// Initiate last
			last[0] = Integer.MIN_VALUE;
			for (int i = 1; i < last.length; i++) {
				last[i] = Integer.MAX_VALUE;
			}

			for (int i = 0; i < seq.length; i++) {
				// Find the lowest index l such that seq[i] is smaller than the next
				int l = find(last, longest.size(), seq[i]);
				// update last
				last[l] = Math.min(last[l], seq[i]);
				// If l > longest, longest must be updated
				if (l > longest.size()) {
					if (alt.size() == longest.size() && alt.size() != 0 && alt.get(alt.size()-1) < longest.get(longest.size()-1)) {
						// If alt has the same length as longest, it means that longest should get the value of alt.
						longest = alt;
						alt = new ArrayList<>();
					}
					longest.add(seq[i]);
				} else {
					// Make sure alt is up-to speed with longest
					while (alt.size() < l) {
						alt.add(longest.get(alt.size()));
					}
					alt.add(l-1, seq[i]);
					// Remove every element after the newly added
					while (alt.size() > l)
						alt.remove(alt.size()-1);
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append(longest.size()).append("\n");
			int[] res = new int[longest.size()];
			int index = res.length-1;
			for (int i = seq.length-1; i >= 0; i--) {
				if (seq[i] == longest.get(longest.size()-1)) {
					longest.remove(longest.size()-1);
					res[index] = i;
					index--;
					if (index < 0)
						break;
				}
			}
			for (int i : res) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			kattio.print(sb.toString());
		}
	}
	
	/**
	 * Prints the indexes of the longest inc subseq
	 */
	private void print(List<Integer> longest, int[] seq) {
		
	}

	private int find(int[] last, int start, int insert) {
		int low = 0;
		int high = start;
		int mid = 0;
		while (low <= high) {
			mid = low + (high-low)/2 + (low+(high-low) % 2 == 1 ? 1 : 0);
			if (last[mid] > insert) {
				high = mid-1;
			} else if (last[mid] < insert) {
				low = mid+1;
			} else {
				return mid;
			}
		}
		if (last[mid] < insert)
			mid++;
		return mid;
	}

	public void close() {
		kattio.flush();
		kattio.close();
	}

	public static void main(String[] args) {
		LongestSolver s = new LongestSolver();
		s.solve();
		s.close();
	}

}
