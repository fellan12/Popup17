import java.text.DecimalFormat;
import java.util.List;

/**
 * Authors Felix De Silva & Martin Engelin
 */
public class LineSegmentIntersection {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		DecimalFormat df = new DecimalFormat("0.00");

		int cases = io.getInt();

		for (int i = 0; i < cases; i++) {
			Point p1 = new Point(io.getInt(), io.getInt());
			Point p2 = new Point(io.getInt(), io.getInt());
			Point p3 = new Point(io.getInt(), io.getInt());
			Point p4 = new Point(io.getInt(), io.getInt());

			LineSegment l1 = new LineSegment(p1, p2);
			LineSegment l2 = new LineSegment(p3, p4);

			List<Point> intersection = LineSegment.intersection(l1, l2);

			switch (intersection.size()) {
				case 0:
					// No intersection
					io.println("none");
					break;
				case 1:
					// One intersection
					Point res = intersection.get(0);
					io.println(df.format(res.x) + " " + df.format(res.y));
					break;
				case 2:
					// Intersection is line segment itself
					Point first = intersection.get(0);
					Point second = intersection.get(1);
					io.println(df.format(first.x) + " " + df.format(first.y) + " " + df.format(second.x) + " " + df.format(second.y));
					break;
			}
		}
		io.close();
	}

}
