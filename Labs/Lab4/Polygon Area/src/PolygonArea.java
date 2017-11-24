import java.awt.Point;
/**
 * Authors: Martin Engelin & Felix De Silva
 * 
 * Class to calculate the area of a simple polygon.
 */
public class PolygonArea {

	/**
	 * Uses the shoelace formula in order to calculate the area of a simple polygon.
	 * 
	 * Returns: - positive area if input points are counter clockwise
	 * 			- negative area if input points are clockwise
	 */
	public static double shoelaceFormula(Point[] points) {
		int n = points.length-1;
		double sum1 = points[n].x*points[0].y;
		double sum2 = points[n].y*points[0].x;
		for (int i = 0; i < n; i++) {
			sum1 += points[i].x * points[i+1].y;
			sum2 += points[i].y * points[i+1].x;
		}
		return (sum1-sum2)/2;
	}
}
