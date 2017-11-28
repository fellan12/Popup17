import java.awt.Point;

public class ConvexHullKattis {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		while (true) {
			int amount = io.getInt();
			if (amount == 0)
				break;
			Point[] points = new Point[amount];
			for (int i = 0; i < points.length; i++) {
				points[i] = new Point(io.getInt(), io.getInt());
			}
			Point[] results = ConvexHull.convexHull(points);
			io.println(results.length);
			for (int i = 0; i < results.length; i++) {
				io.println(results[i].x+" "+results[i].y);
			}
		}
		io.close();
	}
}
