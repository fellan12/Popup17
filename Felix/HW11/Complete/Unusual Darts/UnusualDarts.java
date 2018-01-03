import java.util.Arrays;
import java.util.Comparator;


public class UnusualDarts {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		int[][] permutations = PermutationGenerator.permutations(new int[]{0,1,2,3,4,5,6});
		Arrays.sort(permutations, new PermutationComparator());
		for (int c = 0; c < cases; c++) {
			Point[] points = new Point[7];
			for (int i = 0; i < points.length; i++) {
				points[i] = new Point(io.getDouble(), io.getDouble());
			}
			double bobWin = io.getDouble();

			//For each permutation of a polygon
			//check if it is a notSimple polygon
			for (int[] p : permutations) {
				double area = Math.abs(dartArea(points, p));
				double prob = area/4;
				prob = prob*prob*prob;
				if (Math.abs(bobWin - prob) <= 0.0001) {					//Check all segents
					boolean notSimple = false;		//pretend it is simple
					LineSegment l1;
					LineSegment l2;
					for (int i = 0; i < p.length-1; i++) {
						l1 = new LineSegment(points[p[i]], points[p[i+1]]);
						if (notSimple){
							break;
						}
						for (int j = i+2; j < p.length-1; j++) {
							l2 = new LineSegment(points[p[j]], points[p[j+1]]);
							if (notSimple){
								break;
							}
							if (LineSegment.intersects(l1, l2)){
								notSimple = true;
							}
						}
					}

					//Check all to end segement
					l1 = new LineSegment(points[p[6]], points[p[0]]);
					for (int j = 1; j < p.length-2; j++) {
						l2 = new LineSegment(points[p[j]], points[p[j+1]]);
						if (notSimple){
							break;
						}
						if (LineSegment.intersects(l1, l2)){
							notSimple = true;
						}
					}

					//If the polygon is notSimple
					if (!notSimple) {
						for (int i = 0; i < p.length; i++) {
							io.print((p[i]+1)+" ");
						}
						io.println();
						break;
					}
				}
			}
		}
		io.close();
	}

	public static double dartArea(Point[] points, int[] pointIndex) {
		int n = points.length-1;
		double sum1 = points[pointIndex[n]].x*points[pointIndex[0]].y;
		double sum2 = points[pointIndex[n]].y*points[pointIndex[0]].x;
		for (int i = 0; i < n; i++) {
			sum1 += points[pointIndex[i]].x * points[pointIndex[i+1]].y;
			sum2 += points[pointIndex[i]].y * points[pointIndex[i+1]].x;
		}
		return (sum1-sum2)/2;
	}

	public static class PermutationComparator implements Comparator<int[]> {
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
}
