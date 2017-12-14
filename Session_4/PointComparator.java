import java.util.Comparator;

/**
 * Authors Felix De Silva & Martin Engelin
 * 
 * Comparator that compares two points according to which one has the lowest x.
 * If both have the same x-value, compares according to whish has the lowest y.
 */
public class PointComparator implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		if (p1.equals(p2))
			return 0;
		if (p1.x > p2.x || (p1.x == p2.x && p1.y > p2.y))
			return 1;
		return -1;
	}
}
