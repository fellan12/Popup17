import java.util.*;

/**
 * Author: Felix De Silva
 * Input
 * Target interval [L, R] <= R
 * N interval [a_i,b_i] where i = [1,2,3,....]
 * 
 * Output
 * Smallest sumset S <= [1,2,3,...]
 * s.t. union([a_i,b_i], [L,R]) where i is in S
 * 
 * SPECIAL CASE
 * L=R => Then just take one that contains the point [L,R]
 */
public class IntervalCover {

	public static void main(String[] args) {

		Kattio io = new Kattio(System.in, System.out);

		while (io.hasMoreTokens()) {
			Interval targetInterval = new Interval(-1, io.getDouble(), io.getDouble());
			int n = io.getInt();
			ArrayList<Interval> intervals = new ArrayList<Interval>();
			for (int i = 0; i < n; i++) {
				intervals.add(new Interval(i, io.getDouble(), io.getDouble()));
			}

			List<Integer> solution = cover(targetInterval, intervals);
			if (solution == null) {
				io.println("impossible");
				continue;
			}
			io.println(solution.size());

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < solution.size(); i++) {
				sb.append(solution.get(i) + " ");
			}
			io.println(sb.toString().trim());
		}

		io.close();
	}
	
	/**
	 * Finds the least amount of intervals to cover the target.
	 * If there is no solution, returns null.
	 * @param target
	 * @param intervals
	 * @return
	 */
	public static List<Integer> cover(Interval target, List<Interval> intervals) {
		Collections.sort(intervals, new IntervalComparator());
		double start = target.getStart();
		double end = target.getEnd();
		ArrayList<Integer> res = new ArrayList<Integer>();
		double farthest = start;
		int index = 0;
		while (true) {
			int intervalIndex = -1;
			while (index < intervals.size()) {
				Interval interval = intervals.get(index);
				index++;
				if (interval.getStart() <= start && interval.getEnd() >= farthest) {
					farthest = interval.getEnd();
					intervalIndex = interval.getIndex();
				} else if (interval.getStart() > start){
					index--;
					break;
				}
			}
			if (intervalIndex == -1)
				return null;
			res.add(intervalIndex);
			if (farthest >= end) {
				return res;
			}
			start = farthest;
		}
	}

	private static class IntervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return Double.compare(o1.getStart(), o2.getStart());
		}
		
	}
}
