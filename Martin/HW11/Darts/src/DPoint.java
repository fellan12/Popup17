public class DPoint {
	
	public final double x, y;
	
	public DPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public DPoint subtract(DPoint p) {
		return subtract(this, p);
	}
	
	public DPoint add(DPoint p) {
		return add(this, p);
	}
	
	public DPoint mult(double d) {
		return mult(d, this);
	}
	
	public double cross(DPoint p) {
		return cross(this, p);
	}
	
	public double dot(DPoint p) {
		return dot(this, p);
	}
	
	public static double cross(DPoint v, DPoint w) {
		return v.x*w.y - v.y*w.x;
	}
	
	public static double dot(DPoint v, DPoint w) {
		return v.x*w.x + v.y*w.y;
	}
	
	public static DPoint subtract(DPoint p1, DPoint p2) {
		return new DPoint (p1.x-p2.x, p1.y-p2.y);
	}
	
	public static DPoint add(DPoint p1, DPoint p2) {
		return new DPoint (p1.x+p2.x, p1.y+p2.y);
	}
	
	public static DPoint mult(double d, DPoint p) {
		return new DPoint(d*p.x, d*p.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			DPoint other = (DPoint) obj;
			return Math.abs(this.x - other.x) < 0.00001 && Math.abs(this.y-other.y) < 0.00001;
		} catch (Exception e) {}
		return false;
	}
}
