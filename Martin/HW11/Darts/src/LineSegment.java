public class LineSegment {
	
	/**
	 * Returns the intersection of the lines
	 */
	public static DPoint[] intersection(DPoint p1, DPoint p2, DPoint p3, DPoint p4) {
		DPoint p = new DPoint(p1.x, p1.y);
		DPoint r = new DPoint(p2.x-p1.x, p2.y-p1.y);
		DPoint q = new DPoint(p3.x, p3.y);
		DPoint s = new DPoint(p4.x-p3.x, p4.y-p3.y);
		double rscross = r.cross(s);
		DPoint qminp = q.subtract(p);
		double tnum = qminp.cross(s);
		double unum = qminp.cross(r);
		if (rscross == 0) {
			// Parallel
			if (unum == 0 && tnum == 0) {
				// Colinear
				DPoint[] d1 = colinear(p, q, r, s);
				DPoint[] d2 = colinear(q, p, s, r);
				
				if (d1 == null || d2 == null)
					return null;
				
				if (d1.length == 1 && d2.length == 1) {
					if (d1[0].equals(d2[0]))
						return d1;
				} 
				
				if (d1.length == 2 && d2.length == 2) {
					if (d1[0].equals(d2[0]) && d1[1].equals(d2[1]))
						return d1;
				}
				return null;
			} else {
				return null;
			}
		} else {
			double t = tnum / rscross;
			double u = unum / rscross;
			if (0 <= t && t <= 1 && 0 <= u && u <= 1) {
				return new DPoint[] {p.add(r.mult(t))};
			} else {
				return null;
			}
		}
	}
	
	private static DPoint[] colinear(DPoint p, DPoint q, DPoint r, DPoint s) {
		double rdotr = r.dot(r);
		if (rdotr == 0)
			return new DPoint[] {p};
		double t0  = q.subtract(p).dot(r) / rdotr;
		double t1 = t0 + s.dot(r) / rdotr;
		if (t0 > t1) {
			double tmp = t0;
			t0 = t1;
			t1 = tmp;
		}
		if (t0 > 1 || t1 < 0)
			// Disjoint
			return null;
		
		t0 = Math.max(t0, 0);
		t1 = Math.min(t1, 1);
		if (Math.abs(t1 - t0) < 0.0001)
			return new DPoint[] {p.add(r.mult(t0))};
		return new DPoint[] {p.add(r.mult(t0)), p.add(r.mult(t1))};
	}
}
