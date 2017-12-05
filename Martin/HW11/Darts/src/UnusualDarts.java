import java.util.Arrays;

public class UnusualDarts {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		int[][] permutations = HeapsAlgorithm.permutations(new int[] {0,1,2,3,4,5,6});
		Arrays.sort(permutations, new PermutationComparator());
		for (int c = 0; c < cases; c++) {
			DPoint[] points = new DPoint[7];
			for (int i = 0; i < points.length; i++) {
				points[i] = new DPoint(io.getDouble(), io.getDouble());
			}
			double probability = io.getDouble();
			int[] valid = null;
			for (int[] p : permutations) {
				double area = Math.abs(PolygonArea.dart(points, p));
				double prob = area/4;
				prob = prob*prob*prob;
				if (Math.abs(probability - prob) <= 0.0001) {
					boolean simple = true;
					for (int i = 0; i < p.length-1; i++) {
						if (!simple)
							break;
						for (int j = i+2; j < p.length-1; j++) {
							if (!simple)
								break;
							if (LineSegment.intersection(points[p[i]], points[p[i+1]], points[p[j]], points[p[j+1]]) != null)
								simple = false;
						}
					}
					for (int j = 1; j < p.length-2; j++) {
						if (!simple)
							break;
						if (LineSegment.intersection(points[p[6]], points[p[0]], points[p[j]], points[p[j+1]]) != null)
							simple = false;
					}
					if (simple) {
						valid = p;
						break;
					}
				}
			}
			if (valid == null)
				throw new NullPointerException("Couldn't find valid");
			for (int i = 0; i < valid.length; i++) {
				io.print((valid[i]+1)+" ");
			}
			io.println();
		}
		io.close();
	}
}
