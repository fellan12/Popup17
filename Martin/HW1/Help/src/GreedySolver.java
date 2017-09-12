import java.util.List;

public class GreedySolver {

	public static void main(String[] args) {
		InputParser parser = new InputParser();
		List<Element[]> patterns = parser.getLines();
		for (int i = 0; i < patterns.size(); i=i+2) {
			solve(patterns.get(i), patterns.get(i+1));
		}
	}

	private static void solve(Element[] a, Element[] b) {
		if (a.length != b.length) {
			System.out.println("-");
			return;
		}
		int index = 0;
		while (index < a.length) {
			Element e1 = a[index];
			Element e2 = b[index];
			if (e1.getType() == Type.WORD && e2.getType() == Type.WORD) {
				if (!e1.getValue().equals(e2.getValue())) {
					System.out.println("-");
					return;
				}
				index++;
			} else if (e1.getType() == Type.WORD && e2.getType() == Type.PLACEHOLDER) {
				update(b, e2, e1);
				index = 0;
			} else if (e1.getType() == Type.PLACEHOLDER && e2.getType() == Type.WORD) {
				update(a, e1, e2);
				index = 0;
			} else { // Both placeholders
				index++;
			}
		}
		String dummy = "martin";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			if (a[i].getType() == Type.PLACEHOLDER)
				sb.append(dummy);
			else 
				sb.append(a[i].getValue());
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}

	private static void update(Element[] elems, Element from, Element to) {
		for (int i = 0; i < elems.length; i++) {
			if (elems[i].equals(from)) {
				elems[i] = to;
			}
		}
	}

}
