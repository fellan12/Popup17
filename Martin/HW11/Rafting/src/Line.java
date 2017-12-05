
/**
 * Authors Felix De Silva & Martin Engelin
 */
public class Line {

    public final Point a;
    public final Point b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Calculates the distance from this line segment to the other line segment
     */
    public double distanceToLineSegment(Line l) {

        // Line segments ab
        Point a = this.a;
        Point b = this.b;

        // Line segments cd
        Point c = l.a;
        Point d = l.b;

        // intersect => distance = 0
        if (this.intersect(l)) {
            return 0.0;
        }

        // Calculate all distances to line segment
        double[] distances = new double[4];
        distances[0] = this.distanceToPoint(c);
        distances[1] = this.distanceToPoint(d);
        distances[2] = l.distanceToPoint(a);
        distances[3] = l.distanceToPoint(b);

        // Find the minimum
        double min = Double.MAX_VALUE;
        for (double dist: distances) {
            if (dist < min) {
                min = dist;
            }
        }

        return min;
    }

    public boolean intersect(Line l) {

      // Line segments ab
      Point a = this.a;
      Point b = this.b;

      // Line segments cd
      Point c = l.a;
      Point d = l.b;

      // Get vectors from origin
      Point e = b.subtract(a);
      Point f = d.subtract(c);

      // 2d cross product
      double x = f.twoDimCrossProduct(e);

      // SPECIAL CASE: segments are parallel
      if (x == 0.0) {
          return false;
      }

      // Check for intersection
      double y = (e.x * (c.y - a.y) + e.y * (a.x - c.x)) / x;
      double z = (f.x * (a.y - c.y) + f.y * (c.x - a.x)) / -x;

      // Check if in range [0, 1], i.e. intersection on the line segment
      return 0.0 <= y && y <= 1.0 && 0.0 <= z && z <= 1.0;
    }

    public double distanceToPoint(Point p) {

        // Line segment ab
        Point a = this.a;
        Point b = this.b;

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

}
