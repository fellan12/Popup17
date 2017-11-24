import java.awt.Point;

public class KattisSolver {
	
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
			io.println(MaxColinear.maxColinear(points));
		}
		io.close();
	}

}
