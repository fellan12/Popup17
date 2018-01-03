import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Authors Felix De Silva & Martin Engelin
 */
public class LineSegment {

    public Point a;
    public Point b;

    public LineSegment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    
    /**
     * Returns the minimum distance between the two line segments
     */
    public static double distance(LineSegment l1, LineSegment l2) {
    	 // Line segments ab
        Point a = l1.a;
        Point b = l1.b;

        // Line segments cd
        Point c = l2.a;
        Point d = l2.b;

        // intersect => distance = 0
        if (LineSegment.intersects(l1, l2)) {
            return 0.0;
        }

        // Calculate all distances to line segment
        double[] distances = new double[4];
        distances[0] = LineSegment.distanceToPoint(l1, c);
        distances[1] = LineSegment.distanceToPoint(l1, d);
        distances[2] = LineSegment.distanceToPoint(l2, a);
        distances[3] = LineSegment.distanceToPoint(l2, b);

        // Find the minimum
        double min = Double.MAX_VALUE;
        for (double dist: distances) {
            if (dist < min) {
                min = dist;
            }
        }

        return min;
    }

    /**
     * Returns the distance between this line segment to the other.
     */
    public double distanceToLineSegment(LineSegment l) {
    	return LineSegment.distance(this, l);
    }

    /**
     * Checks if the two line segments intersect with each other.
     */
    public static boolean intersects(LineSegment l1, LineSegment l2) {
    	return intersection(l1, l2).size() != 0;
    }
    
    /**
     * Returns the intersection of the two line segments as a list of points.
     * If there is no intersection, the list is empty.
     * If there is one intersection, the list has size 1.
     * If the intersection is a line segment itself, list contains the two
     * points that make up the line.
     */
    public static List<Point> intersection(LineSegment l1, LineSegment l2) {
    	List<Point> ret = new ArrayList<>();
    	// Line segments ab
        Point a = l1.a;
        Point b = l1.b;

        // Line segments cd
        Point c = l2.a;
        Point d = l2.b;

        // Get vectors from origin
        Point e = b.subtract(a);
        Point f = d.subtract(c);

        // 2d cross product
        double x = f.twoDimCrossProduct(e);

        // SPECIAL CASE: segments are parallel
        if (x == 0.0) {
            boolean[] intersects = new boolean[] 
          		  {LineSegment.pointOnLine(l1, c),
          		   LineSegment.pointOnLine(l1, d),
          		   LineSegment.pointOnLine(l2, a),
          		   LineSegment.pointOnLine(l2, b)};
            
            Point[] ps = new Point[] {c,d,a,b};
            HashSet<Point> set = new HashSet<>();
            
            // Add all unique points
            for (int i = 0; i < ps.length; i++) {
				if (intersects[i])
					set.add(ps[i]);
			}
            
            // If no points where on the lines, no intersection
            if (set.size() == 0)
            	return ret;
            
            // Otherwise, move objects in set to a List
            Iterator<Point> it = set.iterator();
            while (it.hasNext())  {
            	ret.add(it.next());
            }
            
            // Sort it according to x. If x's are the same, sort by y.
            Collections.sort(ret, new PointComparator());
            return ret;
        }

        // Check for intersection
        double y = (e.x * (c.y - a.y) + e.y * (a.x - c.x)) / x;
        double z = (f.x * (a.y - c.y) + f.y * (c.x - a.x)) / -x;

        // Check if in range [0, 1], i.e. intersection on the line segment
        if (0.0 <= y && y <= 1.0 && 0.0 <= z && z <= 1.0) {
        	ret.add(a.add(e.multiply(z)));
        }
        return ret;
    }

    /**
     * Returns the distance between the line and the point.
     */
    public static double distanceToPoint(LineSegment l, Point p) {

        // Line segment ab
        Point a = l.a;
        Point b = l.b;

        // // Vector from origin
        Point d = b.subtract(a);

        // SPECIAL CASE: The segment == a point
        if (d.equals(Point.ZERO)) {
            return p.subtract(a).length();
        }

        // Distance from line segement to point
        // if point is in the shadow of line
        // t is inside [0,1]
        double t = p.subtract(a).scalarProduct(d) / d.scalarProduct(d);

        if (t < 0.0) {
            return p.subtract(a).length();
        } else if (t > 1.0) {
            return p.subtract(b).length();
        } else {
            return p.subtract(a.add(d.multiply(t))).length();
        }
    }
    
    /**
     * Checks if the points is somewhere on the line.
     */
    public static boolean pointOnLine(LineSegment l, Point dot) {
    	Point a = l.a;
    	Point b = l.b;
    	// Line segment is actually a point
		if (a.equals(b)) {
			if (a.equals(dot))
				return true;
			return false;
		}
		
		Point bDelta = b.subtract(a);
		Point dotDelta = dot.subtract(a);
		
		// Points not colinear
		if (bDelta.twoDimCrossProduct(dotDelta) != 0.0)
			return false;
		
		// Length of dot
		double d = bDelta.scalarProduct(dotDelta);
		// Length of bDelta
		double squareLength = bDelta.x*bDelta.x+bDelta.y*bDelta.y;
		// If dot is in the other direction, or further away
		if (d < 0 || d > squareLength)
			return false;
		return true;
	}

}
