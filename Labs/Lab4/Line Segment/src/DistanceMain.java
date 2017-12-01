import java.awt.Point;
import java.text.DecimalFormat;

public class DistanceMain {

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
			double distance = LineSegment.lineSegmentDistance(l1, l2);
			io.println(df.format(distance));
		}
		io.close();
	}

}
