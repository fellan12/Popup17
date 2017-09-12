
public class InputParser {
	private Kattio kattio;
	
	public InputParser() {
		kattio = new Kattio(System.in);
	}
	
	/**
	 * Parses the next problem. If no such exists, returns null.
	 */
	public KnapsackProblem getNextProblem() {
		if (!kattio.hasMoreTokens())
			return null;
		double cap = kattio.getDouble();
		int itemAmount = kattio.getInt();
		Item[] items = new Item[itemAmount];
		for (int i = 0; i < items.length; i++) {
			int value = kattio.getInt();
			int weight = kattio.getInt();
			Item item = new Item(i, value, weight);
			items[i] = item;
		}
		return new KnapsackProblem(cap, items);
	}
}
