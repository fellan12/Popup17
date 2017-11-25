import java.awt.Point;

/**
 * Authors: Martin Engelin & Felix De Silva
 */
public class KattisMain {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		while (true) {
			int points = io.getInt();
			if (points == 0)
				break;
			Point[] polygon = new Point[points];
			for (int i = 0; i < polygon.length; i++) {
				polygon[i] = new Point(io.getInt(), io.getInt());
			}
			int queries = io.getInt();
			for (int i = 0; i < queries; i++) {
				Point p = new Point(io.getInt(), io.getInt());
				switch (PolygonPoint.pointInPolygon(polygon, p)) {
				case 1:
					io.println("in");
					break;
				case -1:
					io.println("out");
					break;
				case 0:
					io.println("on");
					break;
				default:
					throw new RuntimeException();
				}
			}
		}
		io.close();
	}

}
