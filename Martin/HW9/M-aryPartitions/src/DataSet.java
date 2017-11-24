public class DataSet {
	private int id;
	private int base;
	private int goal;
	
	public DataSet(int id, int base, int goal) {
		this.id = id;
		this.base = base;
		this.goal = goal;
	}
	
	public int getBase() {
		return base;
	}
	
	public int getId() {
		return id;
	}
	
	public int getGoal() {
		return goal;
	}
}
