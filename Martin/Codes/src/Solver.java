import java.util.HashMap;

public class Solver {
	
	private StringGenerator generator;
	private HashMap<Integer, byte[][]> map;
	
	public Solver() {
		this.generator = new StringGenerator();
		this.map = new HashMap<>();
	}
	
	public int calcMinimumDistance(DistanceMatrix m) {
		int ret = Integer.MAX_VALUE;
		for (byte[] cw : getCodeWords(m.getWidth())) {
			int dist = m.multiplyGetDist(cw);
			if (dist != 0 && dist < ret)
				ret = dist;
		}
		return ret;
	}
	
	private byte[][] getCodeWords(int amount) {
		byte[][] codewords;
		if (map.containsKey(amount)) {
			codewords = map.get(amount);
		} else {
			codewords = generator.generate(amount);
			map.put(amount, codewords);
		}
		return codewords;
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
