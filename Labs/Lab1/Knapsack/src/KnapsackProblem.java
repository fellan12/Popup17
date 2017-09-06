
public class KnapsackProblem {
	
	private double capacity;
	private Item[] items;
	
	public KnapsackProblem(double capacity, Item[] items) {
		this.capacity = capacity;
		this.items = items;
	}

	public double getCapacity() {
		return capacity;
	}
	
	public Item[] getItems() {
		return items;
	}
	
	public Item getItem(int i) {
		return items[i];
	}
	
	public int getItemAmount() {
		return items.length;
	}
}
