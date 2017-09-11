import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Parses the input of the problem Codes
 */
public class InputParser {
	
	private Scanner scanner;

	public InputParser() {
		scanner = new Scanner(System.in);
	}
	
	public List<PatternPair> getPairs() {
		int amount = Integer.parseInt(scanner.nextLine());
		List<PatternPair> pairs = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			String line1 = scanner.nextLine();
			String line2 = scanner.nextLine();
			pairs.add(getPair(line1, line2));
		}
		return pairs;
	}

	private PatternPair getPair(String line1, String line2) {
		Kattio kattio = new Kattio(new ByteArrayInputStream(line1.getBytes()));
		Pattern first = getElementList(kattio);
		kattio = new Kattio(new ByteArrayInputStream(line2.getBytes()));
		Pattern second = getElementList(kattio);
		return new PatternPair(first, second);
	}

	private Pattern getElementList(Kattio kattio) {
		Pattern pattern = new Pattern();
		while (kattio.hasMoreTokens()) {
			pattern.add(kattio.getWord());
		}
		return pattern;
	}
}
