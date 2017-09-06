import java.util.Random;

public class Solver {

	private Kattio kattio;

	public Solver() {
		kattio = new Kattio(System.in);
	}

	/**
	 * Takes a pair of patterns and prints the answer.
	 */
	public void solvePair(PatternPair pair) {
		Pattern a = pair.getFirst();
		Pattern b = pair.getSecond();
		if (a.getSize() != b.getSize()) {
			kattio.println("-");
			return;
		}
		Element[] elemsA = a.getElements();
		Element[] elemsB = b.getElements();
		for (int i = 0; i < a.getSize(); i++) {
			Element e1 = elemsA[i];
			Element e2 = elemsB[i];
			if (e1.getType() == Type.WORD && e2.getType() == Type.WORD) {
				if (!e1.getValue().equals(e2.getValue())) {
					kattio.println("-");
					return;
				}
			} else if (e1.getType() == Type.WORD && e2.getType() == Type.PLACEHOLDER) {
				e2.copy(e1);
			} else if (e1.getType() == Type.PLACEHOLDER && e2.getType() == Type.WORD) {
				e1.copy(e2);
			} else { // Both placeholders
				elemsA[i] = e2;
			}
		}
		
		printAnswer(elemsA);
	}

	/**
	 * Prints the answer
	 */
	private void printAnswer(Element[] elems) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (Element e : elems) {
			if (e.getType() == Type.PLACEHOLDER) {
				sb.append("martin").append(" ");
			} else {
				sb.append(e.getValue()).append(" ");
			}
		}
		kattio.println(sb.toString());
	}

	public void close() {
		kattio.flush();
		kattio.close();
	}

	public static void main(String[] args) {
		Solver s = new Solver();
		InputParser parser = new InputParser();
		for (PatternPair pair : parser.getPairs()) {
			s.solvePair(pair);
		}
		s.close();
	}
}
