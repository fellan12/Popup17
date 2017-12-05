/**
 * Authors Felix De Silva & Martin Engelin
 */
public class LineSegmentDistance {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int n = io.getInt(); // n <= 10000

		for (int i = 0; i < n; i++) {
			Point a = new Point(io.getDouble(), io.getDouble());
			Point b = new Point(io.getDouble(), io.getDouble());
			LineSegment l1 = new LineSegment(a, b);

			Point c = new Point(io.getDouble(), io.getDouble());
			Point d = new Point(io.getDouble(), io.getDouble());
			LineSegment l2 = new LineSegment(c, d);

			io.println(String.format("%.2f", LineSegment.distance(l1, l2)));
		}

		io.close();
	}
}
