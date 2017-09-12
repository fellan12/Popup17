package good;
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
		for (int i = 0; i < a.getSize(); i++) {
			Element e1 = a.get(i);
			Element e2 = b.get(i);
			if (e1.getType() == Type.WORD && e2.getType() == Type.WORD) {
				if (!e1.getValue().equals(e2.getValue())) {
					kattio.println("-");
					return;
				}
			} else if (e1.getType() == Type.WORD && e2.getType() == Type.PLACEHOLDER) {
				b.replace(e2, e1);
				a.replace(e2, e1);
			} else if (e1.getType() == Type.PLACEHOLDER && e2.getType() == Type.WORD) {
				a.replace(e1, e2);
				b.replace(e1, e2);
			} else { // Both placeholders
				a.replace(e1, e2);
			}
		}
		
		printAnswer(a);
	}

	/**
	 * Prints the answer
	 */
	private void printAnswer(Pattern pattern) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (Element e : pattern.getElements()) {
			if (e.getType() == Type.PLACEHOLDER) {
				sb.append("martin");
			} else {
				sb.append(e.getValue());
			}
			sb.append(" ");
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
