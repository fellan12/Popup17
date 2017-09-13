import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestSolver {

	private Kattio kattio;

	public LongestSolver() {
		kattio = new Kattio(System.in);
	}

	public void solve(int[] seq) {
		List<Integer> longest = new ArrayList<>();
		List<Integer> alt = new ArrayList<>();
		int[] last = new int[seq.length+1];
		last[0] = Integer.MIN_VALUE;
		for (int i = 1; i < last.length; i++) {
			last[i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < last.length; i++) {
			int l = find(last, longest.size(), seq[i-1]);
			last[l] = Math.min(last[l], seq[i-1]);
			if (l > longest.size()) {
				if (alt.size() == longest.size() && alt.size() != 0 && alt.get(alt.size()-1) < longest.get(longest.size()-1)) {
					Collections.copy(longest, alt);
				}
				longest.add(seq[i-1]);
			} else {
				while (alt.size() < l) {
					alt.add(longest.get(alt.size()));
				}
				alt.add(l-1, seq[i-1]);
				alt = alt.subList(0, l);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(longest.size()).append("\n");
		/*for (int i : longest) {
			sb.append(i).append(" ");
		}*/
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
		InputParser parser = new InputParser();
		LongestSolver s = new LongestSolver();
		while (parser.hasNext()) {
			s.solve(parser.getSequence());
		}
		s.close();
	}

}
