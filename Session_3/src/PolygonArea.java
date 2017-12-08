import java.util.List;

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
	public static double shoelaceFormula(List<Point> points) {
		int n = points.size()-1;
		double sum1 = points.get(n).x*points.get(0).y;
		double sum2 = points.get(n).y*points.get(0).x;
		for (int i = 0; i < n; i++) {
			sum1 += points.get(i).x * points.get(i+1).y;
			sum2 += points.get(i).y * points.get(i+1).x;
		}
		return (sum1-sum2)/2;
	}
}
