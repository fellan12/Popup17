
public class Solver {
	
	private StringGenerator generator;
	
	public Solver() {
		this.generator = new StringGenerator();
	}
	
	public int calcMinimumDistance(DistanceMatrix m) {
		int ret = Integer.MAX_VALUE;
		for (byte[] cw : generator.generate(m.getWidth())) {
			int dist = m.multiplyGetDist(cw);
			if (dist != 0 && dist < ret)
				ret = dist;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Solver s = new Solver();
		InputParser parser = new InputParser(System.in);
		Kattio kattio = new Kattio(System.in);
		for (DistanceMatrix m : parser.getMatrices()) {
			kattio.println(s.calcMinimumDistance(m));
		}
		kattio.flush();
		kattio.close();
	}
}
