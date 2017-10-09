
public class KitchenSolver {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int[] cupSizes = new int[io.getInt()];
		for (int i = 0; i < cupSizes.length; i++) {
			cupSizes[i] = io.getInt();
		}
		KitchenTree tree = new KitchenTree(cupSizes);
		int results = tree.solve(io.getInt());
		if (results == -1)
			io.println("impossible");
		else
			io.println(results);
		io.close();
	}

}
