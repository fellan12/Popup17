import java.awt.Point;
import java.util.HashMap;
/**
 * Authors: Martin Engelin & Felix De Silva
 * 
 * Class to determine the maximum amount of colinear points.
 */
public class MaxColinear {
	
	/**
	 * Returns the maximum amount of colinear points
	 */
	public static int maxColinear(Point[] points) {
		if (points.length < 3)
			return points.length;
		int max = 0;
		for (int i = 0; i < points.length; i++) {
			int m = findMaxForPoint(points, i);
			if (m > max)
				max = m;
		}
		return max;
	}
	
	/**
	 * Takes an array of points and an index.
	 * Calculates the maximum amount of colinear points to the one specified with the index.
	 */
	public static int findMaxForPoint(Point[] points, int index) {
		HashMap<Double, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0; i < points.length; i++) {
			if (i == index)
				continue;
			double slope = slope(points[i], points[index]);
			if (!map.containsKey(slope)) {
				map.put(slope, 0);
			}
			int current = map.get(slope);
			if (current+1 > max)
				max = current + 1;
			map.put(slope, current+1);
		}
		return max+1;
	}
	
	/**
	 * Returns the slope of the line between point p1 and p2.
	 */
	public static double slope(Point p1, Point p2) {
		if (p1.x > p2.x)
			return slope(p2, p1);
		if (p2.x - p1.x == 0)
			return Integer.MAX_VALUE;
		return (double) (p2.y - p1.y) / (p2.x - p1.x);
	}

}
