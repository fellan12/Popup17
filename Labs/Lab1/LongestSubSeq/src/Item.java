
public class Item {

	private int value;
	private int index;
	
	public Item(int index, int value) {
		this.value = value;
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "("+index+","+value+")";
	}
}
