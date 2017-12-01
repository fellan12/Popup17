import java.awt.Point;
import java.text.DecimalFormat;

public class IntersectionMain {

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
			double[] intersection = LineSegment.intersection(l1, l2);
			if (intersection == null)
				io.println("none");
			else if (intersection.length == 2)
				io.println(df.format(intersection[0]) + " " + df.format(intersection[1]));
			else if (intersection.length == 4)
				io.println(df.format(intersection[0]) + " " + df.format(intersection[1]) + " " + df.format(intersection[2]) + " " + df.format(intersection[3]));
		}
		io.close();
	}

}
