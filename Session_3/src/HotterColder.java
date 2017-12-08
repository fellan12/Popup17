import java.util.ArrayList;
import java.util.List;

public class HotterColder {
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		
	}
	
	public static void solve(LineSegment[] lines, HC[] hc) {
		List<Point> poly = startPoly();
		for (int j = 0; j < lines.length; j++) {
			LineSegment line = lines[j];
			Point diff = line.b.subtract(line.a);
			Point vec = line.a.add(diff);
			HC direction = hc[j];
			if (direction == HC.SAME) {
				if (vec.length() == 0)
					continue;
				System.out.println("0.0");
				return;
			}
			Point middle = line.a.add(diff.multiply(0.5));
			LineSegment normal = line.normal();
			LineSegment intersectionLine = new LineSegment(
					middle.add(normal.b.subtract(normal.a)), 
					middle.subtract(normal.b.subtract(normal.a)));
			List<Point> intersections = new ArrayList<>();
			poly.add(poly.get(0));
			for (int i = 0; i < poly.size()-1; i++) {
				LineSegment l = new LineSegment(poly.get(i), poly.get(i+1));
				List<Point> ints = LineSegment.intersection(l, intersectionLine);
				if (ints.isEmpty())
					continue;
				if (ints.size() > 1)
					throw new RuntimeException();
				intersections.add(ints.get(0));
			}
			if (intersections.isEmpty())
				continue;
			if (intersections.size() != 2)
				throw new ArrayIndexOutOfBoundsException();
			Point p1 = intersections.get(0);
			Point p2 = intersections.get(1);
			if (p1.equals(p2)) {
				
				return;
			}
			if (p1.x == p2.x && p1.y < p2.y || p1.x > p2.x || vec.x < 0 || vec.x == 0 && vec.y > 0) {
				Point tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			if (hc[j] == HC.COLDER) {
				Point tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			List<Point> newPoly = new ArrayList<>();
			for (int i = 0; i < poly.size()-1; i++) {
				LineSegment l = new LineSegment(poly.get(i), poly.get(i+1));
				if (LineSegment.pointOnLine(l, p1)) {
					newPoly.add(p1);
					i++;
					while (!poly.get(i).equals(p2)) {
						newPoly.add(poly.get(i));
						i++;
					}
					newPoly.add(p2);
				}
			}
			double area = 100.0 / PolygonArea.shoelaceFormula(newPoly);
			System.out.println(area);
			poly = newPoly;
		}
	}
	
	private static List<Point> startPoly() {
		List<Point> points = new ArrayList<>();
		points.add(new Point(0,0));
		points.add(new Point(10,0));
		points.add(new Point(10,10));
		points.add(new Point(0,10));
		return points;
	}
}
