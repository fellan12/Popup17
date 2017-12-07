import java.awt.Point;
import java.util.Comparator;

/**
 * Authors Martin Engelin & Felix De Silva
 * 
 * Comparator that compares two points according to their angle 
 * to a specified "compare-point".
 */
public class PointComparator implements Comparator<Point> {

	private Point comparePoint;

	public PointComparator(Point comparePoint) {
		this.comparePoint = comparePoint;
	}

	@Override
	public int compare(Point p1, Point p2) {
		if (p1.equals(comparePoint))
			return -1;
		if (p2.equals(comparePoint))
			return 1;
		if (p1.equals(p2))
			return 0;

		double angle = GeometricFunctions.angle(p1, comparePoint) - GeometricFunctions.angle(p2, comparePoint);
		if (angle > 0)
			return 1;
		else if (angle < 0)
			return -1;
		else {
			//Colinear, return the one farthest away
			if (GeometricFunctions.distance(p1, comparePoint) < GeometricFunctions.distance(p2, comparePoint))
				return -1;
			else
				return 1;
		}
	}
}
