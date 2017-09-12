
public class Solver {

	private Kattio kattio;
	
	public Solver() {
		kattio = new Kattio(System.in);
		int result = solve(kattio.getInt(), kattio.getInt(), kattio.getInt());
		kattio.print(result);
		kattio.flush();
		kattio.close();
	}
	
	private int solve(int bridges, int knights, int groupSize) {
		bridges--;
		int bridgesPerDay = knights/groupSize; //Is rounded down
		int days = bridges / bridgesPerDay + (bridges % bridgesPerDay == 0 ? 0 : 1);
		return days;
	}
	
	public static void main(String[] args) {
		new Solver();
	}
}
