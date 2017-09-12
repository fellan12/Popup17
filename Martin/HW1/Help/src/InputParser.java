
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
	
	public List<Element[]> getLines() {
		int amount = Integer.parseInt(scanner.nextLine());
		List<Element[]> elems = new ArrayList<>();
		for (int i = 0; i < amount*2; i++) {
			elems.add(getNext(scanner.nextLine()));
		}
		return elems;
	}
	
	private Element[] getNext(String line) {
		List<Element> elems = new ArrayList<>();
		Kattio kattio = new Kattio(new ByteArrayInputStream(line.getBytes()));
		while (kattio.hasMoreTokens()) {
			elems.add(new Element(kattio.getWord()));
		}
		//kattio.close();
		Element[] e = new Element[elems.size()];
		return elems.toArray(e);
	}

}
