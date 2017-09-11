import java.util.ArrayList;
import java.util.Arrays;
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
			int l = find(last, seq[i-1]);
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
	
	public void solve3(int[] seq) {
		List<int[]> matrix = new ArrayList<>();
		matrix.add(new int[0]);
		for (int i = 0; i < seq.length; i++) {
			int low = 0;
			int high = matrix.size()-1;
			/*for (; j < matrix.size(); j++) {
				int[] foo = matrix.get(j);
				if (foo[j-1] >= seq[i]) {
					foo[j-1] = seq[i];
					break;
				}
			}*/
			int mid = 1;
			while (low <= high) {
				mid = low + (high - low) / 2;
				if (mid == 0) {
					mid = 1;
					break;
				}
				if (matrix.get(mid)[mid-1] > seq[i]) {
					high = mid-1;
				} else if (matrix.get(mid)[mid-1] < seq[i]) {
					low = mid+1;
				} else {
					break;
				}
			}
			if (mid < matrix.size() && matrix.get(mid)[mid-1] < seq[i])
				mid++;
			if (mid == matrix.size()) {
				int[] nev = Arrays.copyOf(matrix.get(matrix.size()-1), mid);
				nev[nev.length-1] = seq[i];
				matrix.add(nev);
			} else {
				matrix.get(mid)[mid-1] = seq[i];
			}
		}
		int[] results = matrix.get(matrix.size()-1);
		int index = results.length-1;
		StringBuilder sb = new StringBuilder();
		for (int i = seq.length-1; i >= 0; i--) {
			if (index < 0)
				break;
			if (seq[i] == results[index]) {
				sb.insert(0, i+" ");
				index--;
			}
		}
		sb.insert(0, results.length+"\n");
		kattio.println(sb.toString());
	}
	
	
	public void solve2(int[] seq) {
		int[] last = new int[seq.length+1];
		last[0] = Integer.MIN_VALUE;
		for (int i = 1; i < last.length; i++) {
			last[i] = Integer.MAX_VALUE;
		}
		
		int[][] matrix = new int[seq.length][last.length];
		
		for (int i = 1; i < last.length; i++) {
			int l = find(last, seq[i-1]);
			last[l] = Math.min(last[l], seq[i-1]);
			matrix[i-1] = last.clone();
		}
		
		List<Integer> results = new ArrayList<>();
		int y = 0;
		int x = matrix[0].length-1;
		while (matrix[y][1] != Integer.MAX_VALUE && y < matrix.length-1)
			y++;
		while (matrix[y][x] == Integer.MAX_VALUE)
			x--;
		for (; x >= 0; x--) {
			int diff = matrix[y][x];
			while (y > 0 && matrix[y-1][x] == diff) {
				y--;
			}
			results.add(0, y);
			y--;
			if (y < 0)
				break;
		}
		printResults(results);
	}
	
	private void printResults(List<Integer> results) {
		kattio.println(results.size());
		for (int i : results) {
			kattio.print(i+" ");
		}
		kattio.println();
	}
	
	private int find(int[] last, int a) {
		for (int i = 0; i < last.length; i++) {
			if (last[i] >= a)
				return i;
		}
		throw new RuntimeException("WHAAAT");
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
