import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Arrays;
/**
 * Authors Martin Engelin & Felix De Silva
 */
public final class ConvexHull {
	/**
	 * Calculates the convex hull of a set of points.
	 * Returns them in counter clockwise order.
	 */
	public static Point[] convexHull(Point[] input) {
		if (input.length < 3) {
			return smallInput(input);
		}
		Point[] sortedPoints = sortPoints(input);
		ArrayDeque<Point> hull = grahamScan(sortedPoints);
		return dequeToArray(hull);
	}

	/**
	 * Calculates the convex hull using the Graham Scan algorithm
	 */
	private static ArrayDeque<Point> grahamScan(Point[] points) {
		ArrayDeque<Point> convexHull = new ArrayDeque<>();
		convexHull.push(points[0]);
		convexHull.push(points[1]);

		for(int i = 2; i < points.length; i++) {
			Point p3 = points[i];
			Point p2 = convexHull.poll();
			Point p1 = convexHull.peek();

			switch (Cycle.kind(p1, p2, p3)) {
			case CCW:
				convexHull.push(p2);
				convexHull.push(p3);
				break;
			case CW:
				i--;
				break;
			case LINE:
				convexHull.push(p3);
				break;
			}
		}
		
		if (points[0].equals(convexHull.peek()))
			convexHull.poll();
		
		return convexHull;
	}
	
	/**
	 * Sorts points according to the requirements of Graham Scan:
	 *  - Finds the lowest point (lowest y) and sets it to be the first
	 *  - Sorts the rest of the points according to the lowest points angle to them
	 */
	private static Point[] sortPoints(Point[] points) {
		int lowest = GeometricFunctions.findLowestPointIndex(points);
		points = swap(points, 0, lowest);
		Arrays.sort(points, new PointComparator(points[0]));
		return points;
	}
	
	/**
	 * Takes an ArrayDeque of Points and returns a Point array
	 */
	private static Point[] dequeToArray(ArrayDeque<Point> deque) {
		Point[] results = new Point[deque.size()];
		for (int i = results.length-1; i >= 0; i--) {
			results[i] = deque.poll();
		}
		return results;
	}
	
	/**
	 * Solves Convex Hull for inputs smaller than 3
	 */
	private static Point[] smallInput(Point[] input) {
		if (input.length == 2 && input[0].equals(input[1]))
			input = new Point[]{input[0]};
		return input;
	}

	/**
	 * Swaps to points in an array of Points
	 */
	private static Point[] swap(Point[] points, int p1, int p2) {
		Point tmp = points[p1];
		points[p1] = points[p2];
		points[p2] = tmp;
		return points;
	}
}
