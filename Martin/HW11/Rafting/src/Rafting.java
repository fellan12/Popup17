public class Rafting {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		for (int i = 0; i < cases; i++) {
			Point[] innerPoints = new Point[io.getInt()];
			Line[] innerSegs = new Line[innerPoints.length];
			for (int j = 0; j < innerPoints.length; j++) {
				innerPoints[j] = new Point(io.getInt(), io.getInt()); 
				if (j > 0)
					innerSegs[j] = new Line(innerPoints[j-1], innerPoints[j]);
				if (j == innerPoints.length-1)
					innerSegs[0] = new Line(innerPoints[j], innerPoints[0]);
			}
			Point[] outerPoints = new Point[io.getInt()];
			Line[] outerSegs = new Line[outerPoints.length];
			for (int j = 0; j < outerPoints.length; j++) {
				outerPoints[j] = new Point(io.getInt(), io.getInt());
				if (j > 0)
					outerSegs[j] = new Line(outerPoints[j-1], outerPoints[j]);
				if (j == outerPoints.length-1)
					outerSegs[0] = new Line(outerPoints[j], outerPoints[0]);
			}
			double minDistance = Double.MAX_VALUE;
			for (Line in : innerSegs) {
				for (Line out : outerSegs) {
					double distance = in.distanceToLineSegment(out);
					if (distance < minDistance)
						minDistance = distance;
				}
			}
			io.println(minDistance/2);
		}
		io.close();
	}

}
