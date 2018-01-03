
public class WhiteWaterRafting {

    public static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        int testcases = io.getInt();

        for (int testcase = 0; testcase < testcases; testcase++) {

            // Get all inner points
            int numInner = io.getInt();
            Point[] innerPoints = new Point[numInner];
            for (int i = 0; i < numInner; i++) {
                innerPoints[i] = new Point(io.getDouble(), io.getDouble());
            }

            // Construct all inner line segments
            Line[] innerSegments = new Line[numInner];
            innerSegments[numInner - 1] = new Line(innerPoints[numInner - 1], innerPoints[0]);
            for (int i = 0; i < numInner - 1; i++) {
                innerSegments[i] = new Line(innerPoints[i], innerPoints[i + 1]);
            }

            // Get all outer points
            int numOuter = io.getInt();
            Point[] outerPoints = new Point[numOuter];
            for (int i = 0; i < numOuter; i++) {
                outerPoints[i] = new Point(io.getDouble(), io.getDouble());
            }

            // Construct all outer line segments
            Line[] outerSegments = new Line[numOuter];
            outerSegments[numOuter - 1] = new Line(outerPoints[numOuter - 1], outerPoints[0]);
            for (int i = 0; i < numOuter - 1; i++) {
                outerSegments[i] = new Line(outerPoints[i], outerPoints[i + 1]);
            }

            // Find the narrowest part between all the outer and inner segments
            double minDistance = Double.MAX_VALUE;
            for (Line outer: outerSegments) {
                for (Line inner: innerSegments) {
                    double distance = outer.distanceToLineSegment(inner);
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }
            }

            io.println(minDistance / 2.0); // asked for radius -> 1/2
        }

        io.close();
    }
}
