
public class LineSegmentDistance {

  public static Kattio io = new Kattio(System.in, System.out);

  public static void main(String[] args) {

    int n = io.getInt(); // n <= 10000

    for (int i = 0; i < n; i++) {
      Point a = new Point(io.getDouble(), io.getDouble());
      Point b = new Point(io.getDouble(), io.getDouble());
      Line l1 = new Line(a, b);

      Point c = new Point(io.getDouble(), io.getDouble());
      Point d = new Point(io.getDouble(), io.getDouble());
      Line l2 = new Line(c, d);

      io.println(String.format("%.2f", l1.distanceToLineSegment(l2)));
    }

    io.close();
  }
}
