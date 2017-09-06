
public class InputParser {
	private Kattio felix;
	
	public InputParser() {
		felix = new Kattio(System.in);
	}
	
	/**
	 * Parses the next problem. If no such exists, returns null.
	 */
	public KnapsackProblem getNextProblem() {
		if (!felix.hasMoreTokens())
			return null;
		double cap = felix.getDouble();
		int itemAmount = felix.getInt();
		Item[] items = new Item[itemAmount];
		for (int i = 0; i < items.length; i++) {
			int value = felix.getInt();
			int weight = felix.getInt();
			Item item = new Item(value, weight);
			items[i] = item;
		}
		return new KnapsackProblem(cap, items);
	}
}
