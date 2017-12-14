import java.text.DecimalFormat;

public class MapProblem {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		DecimalFormat df = new DecimalFormat("0.0000000000");
		while (true) {
			double r = io.getDouble();
			if (r == -1)
				break;
			Point landing = new Point(io.getDouble(), io.getDouble());
			Point X = new Point(io.getDouble(), io.getDouble());
			Point vector = X.subtract(landing);
			
			double a = angle(vector);
			if (a == 0) {
				// RIGHT
				io.println("east "+df.format(vector.length()));
			} else if (a == 90) {
				// UP
				io.println("north "+df.format(vector.length()));
			} else if (a == 180) {
				io.println("west "+df.format(vector.length()));
			} else if (a == -90) {
				io.println("south "+df.format(vector.length()));
			} else if (0 < a && a < 90) {
				if (a == 45) {
					io.println("northeast "+df.format(vector.length()));
				} else if (a < 45) {
					Point hypot = new Point(vector.y, vector.y);
					if (landing.add(hypot).length() <= r) {
						io.println("northeast "+df.format(hypot.length()));
						io.println("east "+df.format(vector.absX()-vector.absY()));
					} else {
						io.println("east "+df.format(vector.absX()-vector.absY()));
						io.println("northeast "+df.format(hypot.length()));
					}
				} else if (a > 45) {
					Point hypot = new Point(vector.x, vector.x);
					if (landing.add(hypot).length() <= r) {
						io.println("northeast "+df.format(hypot.length()));
						io.println("north "+df.format(vector.absY()-vector.absX()));
					} else {
						io.println("north "+df.format(vector.absY()-vector.absX()));
						io.println("northeast "+df.format(hypot.length()));
					}
				}
			} else if (90 < a && a < 180) {
				if (a == 135) {
					// NW
					io.println("northwest "+df.format(vector.length()));
				} else if (a < 135) {
					Point hypot = new Point(vector.x, vector.x);
					if (landing.add(hypot).length() <= r) {
						io.println("northwest "+df.format(hypot.length()));
						io.println("north "+df.format(vector.absY()-vector.absX()));
					} else {
						io.println("north "+df.format(vector.absY()-vector.absX()));
						io.println("northwest "+df.format(hypot.length()));
					}
				} else if (a > 135) {
					Point hypot = new Point(vector.y, vector.y);
					if (landing.add(hypot).length() <= r) {
						io.println("northwest "+df.format(hypot.length()));
						io.println("west "+df.format(vector.absX()-vector.absY()));
					} else {
						io.println("west "+df.format(vector.absX()-vector.absY()));
						io.println("northwest "+df.format(hypot.length()));
					}
				}
			} else if (0 > a && a > -90) {
				if (a == -45) {
					// SE
					io.println("southeast "+df.format(vector.length()));
				} else if (a > -45) {
					Point hypot = new Point(vector.y, vector.y);
					if (landing.add(hypot).length() <= r) {
						io.println("southeast "+df.format(hypot.length()));
						io.println("east "+df.format(vector.absX()-vector.absY()));
					} else {
						io.println("east "+df.format(vector.absX()-vector.absY()));
						io.println("southeast "+df.format(hypot.length()));
					}
				} else if (a < -45) {
					Point hypot = new Point(vector.x, vector.x);
					if (landing.add(hypot).length() <= r) {
						io.println("southeast "+df.format(hypot.length()));
						io.println("south "+df.format(vector.absY()-vector.absX()));
					} else {
						io.println("south "+df.format(vector.absY()-vector.absX()));
						io.println("southeast "+df.format(hypot.length()));
					}
				}
			} else if (-90 > a) {
				if (a == -135) {
					// SW
					io.println("southwest "+df.format(vector.length()));
				} else if (a > -135) {
					Point hypot = new Point(vector.x, vector.x);
					if (landing.add(hypot).length() <= r) {
						io.println("southwest "+df.format(hypot.length()));
						io.println("south "+df.format(vector.absY()-vector.absX()));
					} else {
						io.println("south "+df.format(vector.absY()-vector.absX()));
						io.println("southwest "+df.format(hypot.length()));
					}
				} else if (a < -135) {
					Point hypot = new Point(vector.y, vector.y);
					if (landing.add(hypot).length() <= r) {
						io.println("southwest "+df.format(hypot.length()));
						io.println("west "+df.format(vector.absX()-vector.absY()));
					} else {
						io.println("west "+df.format(vector.absX()-vector.absY()));
						io.println("southwest "+df.format(hypot.length()));
					}
				}
			}
			io.println();
		}
		io.close();
	}
	
	private static double angle(Point vector) {
		return Math.toDegrees(GeometricFunctions.angle(vector, new Point(0, 0)));
	}
}
