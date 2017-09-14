import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KittenSolver {
	
	private Scanner io;
	private Kattio katt;
	private KittenTree tree;
	
	public KittenSolver() {
		io = new Scanner(System.in);
		tree = new KittenTree();
	}
	
	public void solve() {
		int kittenBranch = Integer.parseInt(io.nextLine());
		while (true) {
			katt = new Kattio(new ByteArrayInputStream(io.nextLine().getBytes()));
			int parent = katt.getInt();
			if (parent == -1)
				break;
			List<Integer> children = new ArrayList<>();
			while (katt.hasMoreTokens()) {
				children.add(katt.getInt());
			}
			tree.addChildren(parent, children);
		}
		TreeNode kitten = tree.get(kittenBranch);
		Kattio katt = new Kattio(System.in);
		while (kitten != null) {
			katt.print(kitten.getId());
			katt.print(" ");
			kitten = kitten.getParent();
		}
		katt.close();
	}

	public static void main(String[] args) {
		KittenSolver solver = new KittenSolver();
		solver.solve();
	}
}
