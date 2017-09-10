
public class Item implements Comparable<Item>{

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

	public double getRatio(){
		return ((double)value) / ((double)weight);
	}

	@Override
	public String toString(){
		return index + " - (Value: " + value + ", Weight: " + weight + ")";
	}

	@Override
  public int compareTo(Item other){
		return Integer.compare(this.getWeight(), other.getWeight());
  }
}
