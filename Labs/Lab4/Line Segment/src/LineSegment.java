import java.awt.Point;
import java.util.Arrays;

public class LineSegment {

	private final Point p1, p2;
	
	public LineSegment(Point a, Point b) {
		this.p1 = a;
		this.p2 = b;
	}
	
	public double length() {
		return pointDistance(p1, p2);
	}
	
	public static double[] intersection(LineSegment l1, LineSegment l2) {
		Point p1 = l1.p1;
		Point p2 = l1.p2;
		Point p3 = l2.p1;
		Point p4 = l2.p2;
		
		if (p1.equals(p2))
			return pointLineIntersection(l2, p1);
		if (p3.equals(p4))
			return pointLineIntersection(l1, p3);
		
		double denom = (p4.y-p3.y)*(p2.x-p1.x)-(p4.x-p3.x)*(p2.y-p1.y);
		double ua = ((p4.x-p3.x)*(p1.y-p3.y)-(p4.y-p3.y)*(p1.x-p3.x));
		double ub = ((p2.x-p1.x)*(p1.y-p3.y)-(p2.y-p1.y)*(p1.x-p3.x));
		
		if (denom == 0) {
			// Parallel
			if (ua == 0 && ub == 0) {
				// Coincident
				Point[] array = new Point[] {p1, p2, p3, p4};
				Arrays.sort(array, new PointComparator());
				if (array[1].equals(array[2]))
					return new double[] {array[1].x, array[1].y};
				return new double[] {array[1].x, array[1].y, array[2].x, array[2].y};
			}
			return null;
		}
		
		ua = ua / denom;
		ub = ub / denom;
		
		if (0 <= ua && ua <= 1 && 0 <= ub && ub <= 1) {
			return new double[] {p1.x+ua*(p2.x-p1.x), p1.y+ua*(p2.y-p1.y)};
		}
		return null;
	}
	
	public static double[] pointLineIntersection(LineSegment line, Point p) {
		if (pointToLineDistance(line, p) == 0)
			return pointIntersection(p,	p);
		return null;
	}
	
	public static double[] pointIntersection(Point p1, Point p2) {
		if (p1.equals(p2))
			return new double[] {p1.x, p1.y};
		return null;
	}
	
	public static double lineSegmentDistance(LineSegment l1, LineSegment l2) {
		if (intersection(l1, l2) != null)
			return 0;
		double min = Double.MAX_VALUE;
		double tmp = pointToLineDistance(l1, l2.p1);
		if (tmp < min)
			min = tmp;
		tmp = pointToLineDistance(l1, l2.p2);
		if (tmp < min)
			min = tmp;
		tmp = pointToLineDistance(l2, l1.p1);
		if (tmp < min)
			min = tmp;
		tmp = pointToLineDistance(l2, l1.p2);
		if (tmp < min)
			min = tmp;
		return min;
	}
	
	public static double pointToLineDistance(LineSegment lineSegment, Point p) {
		Point a = lineSegment.p1;
		Point b = lineSegment.p2;
		double xDelta = b.x - a.x;
		double yDelta = b.y - a.y;
		double length = lineSegment.length();
		if (length == 0)
			return pointDistance(a, p);
		double u = ((p.x-a.x)*xDelta + (p.y-a.y)*yDelta)/(xDelta*xDelta + yDelta+yDelta);
		u = Math.min(Math.max(0, u), 1);
		double x = a.x + u*(b.x-a.x);
		double y = a.y + u*(b.y-a.y);
		return pointDistance(x, y, p.x, p.y);
	}
	
	public static double pointDistance(Point a, Point b) {
		return pointDistance(a.x, a.y, b.x, b.y);
	}
	
	public static double pointDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
}
