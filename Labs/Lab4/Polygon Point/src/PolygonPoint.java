import java.awt.Point;
/**
 * Authors: Martin Engelin & Felix De Silva
 */
public class PolygonPoint {
	
	/**
	 * Determines whether a point is inside a simple polygon.
	 * 
	 * If point is inside of polygon, returns 1
	 * If point is outside of polygon, returns -1
	 * If point is on the border, returns 0
	 */
	public static int pointInPolygon(Point[] polygon, Point point) {
		int intersections = 0;
		/*
		 * Idea: We draw a line from the point upwards, if the amount of intersecting
		 * lines in the polygon is even, point is outside polygon, otherwise inside.
		 */
		for (int i = 0; i < polygon.length; i++) {
			int j = i == polygon.length-1 ? 0 : i+1;
			Point p1 = polygon[i];
			Point p2 = polygon[j];
			// The line's leftmost point must be <= point.x, and rightmost must be >= point.x
			if ((p1.x > point.x && p2.x > point.x) || (p1.x < point.x && p2.x < point.x))
				continue;
			// The line has to have an endpoint at at least the same height as the point
			if (p1.y < point.y && p2.y < point.y)
				continue;
			// Special case: Vertical line
			if (p1.x == point.x && p2.x == point.x) {
				// Check if point is on the line
				if ((point.y <= p1.y && point.y >= p2.y) || (point.y <= p2.y && point.y >= p1.y))
					return 0;
				continue;
			}
			// If point has same x as one of the p1/p2, only count if its the leftmost
			if (p2.x > p1.x && p2.x == point.x || p1.x > p2.x && p1.x == point.x)
				continue;
			
			// Determine whether point is above, below or on line
			double slope = slope(p1, p2);
			double y = point.y + (p2.x-point.x)*slope;
			
			if (p2.y == y)
				return 0;
			if (p2.y > y)
				intersections++;
		}
		return intersections % 2 == 1 ? 1 : -1;
	}
	
	/**
	 * Returns the slope of the line between point p1 and p2.
	 * Assumes not a vertical line.
	 */
	public static double slope(Point p1, Point p2) {
		return (double) (p2.y - p1.y) / (p2.x - p1.x);
	}

}
