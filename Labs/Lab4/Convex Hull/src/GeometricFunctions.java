import java.awt.Point;
/**
 * Authors Martin Engelin & Felix De Silva
 */
public class GeometricFunctions {
	
	/**
	 * Returns the angle between two points
	 */
	public static double angle(Point p1, Point p2) {
		return Math.atan2(p1.y-p2.y, p1.x-p2.x);
	}
	
	/**
	 * Returns the distance between two points
	 */
	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.x-p1.x, 2) + Math.pow(p2.y-p1.y, 2));
	}
	
	/**
	 * Returns the index of the lowest point in an array of points.
	 * If there are several with same low y, returns the leftmost one.
	 */
	public static int findLowestPointIndex(Point[] points) {
		int lowestY = 0;
		for (int i = 0; i < points.length; i++) {
			if (points[i].y < points[lowestY].y)
				lowestY = i;
			else if (points[i].y == points[lowestY].y && points[i].x < points[lowestY].x)
				lowestY = i;
		}
		return lowestY;
	}
}
