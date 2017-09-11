import java.util.ArrayList;
import java.util.List;

public class LongestSolver {
	
	private Kattio kattio;
	
	public LongestSolver() {
		kattio = new Kattio(System.in);
	}
	
	public void solve(int[] seq) {
		List<Integer> longest = new ArrayList<>();
		int[] last = new int[seq.length+1];
		last[0] = Integer.MIN_VALUE;
		for (int i = 1; i < last.length; i++) {
			last[i] = Integer.MAX_VALUE;
		}
				
		for (int i = 1; i < last.length; i++) {
			int l = find(last, longest.size(), seq[i-1]);
			last[l] = Math.min(last[l], seq[i-1]);
			if (l == longest.size()) {
				longest.remove(l-1);
				longest.add(seq[i-1]);
			} else if (l > longest.size()) {
				longest.add(seq[i-1]);
			}
		}
		int index = longest.size()-1;
		StringBuilder sb = new StringBuilder();
		for (int i = seq.length-1; i >= 0; i--) {
			if (index < 0)
				break;
			if (seq[i] == longest.get(index)) {
				sb.insert(0, i+" ");
				index--;
			}
		}
		sb.insert(0, longest.size()+"\n");
		kattio.println(sb.toString());
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
