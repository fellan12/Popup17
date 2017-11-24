import java.awt.Point;
import java.text.DecimalFormat;
/**
 * Authors: Martin Engelin & Felix De Silva
 */
public class Main {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		DecimalFormat df = new DecimalFormat("0.0");
		while (true) {
			int amount = io.getInt();
			if (amount == 0)
				break;
			Point[] points = new Point[amount];
			for (int i = 0; i < points.length; i++) {
				points[i] = new Point(io.getInt(), io.getInt());
			}
			double area = PolygonArea.shoelaceFormula(points);
			if (area < 0) {
				io.println("CW "+df.format(-area));
			} else {
				io.println("CCW "+df.format(area));
			}
		}
		io.close();
	}
}
