/**
 * Authors: Felix De Silva & Martin Engelin
 *
 * Representation of an item in the knapsack problem.
 */
public class Item {

	private int value;
	private int weight;
	private int index;

	public Item(int index, int value, int weight) {
		this.index = index;
		this.value = value;
		this.weight = weight;
	}

	public int getIndex(){
		return index;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString(){
		return index + " - (Value: " + value + ", Weight: " + weight + ")";
	}
}
