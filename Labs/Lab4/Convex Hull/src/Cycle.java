import java.awt.Point;
/**
 * Authors Martin Engelin & Felix De Silva
 */
public enum Cycle {
	CCW, CW, LINE;
	
	/**
	 * Takes three points and returns what kind of cycle they are
	 */
	public static Cycle kind(Point p1, Point p2, Point p3) {
		int i = (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
		if (i > 0)
			return Cycle.CCW;
		if (i < 0)
			return Cycle.CW;
		return Cycle.LINE;
	}
}
