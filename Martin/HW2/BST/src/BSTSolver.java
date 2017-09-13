import java.io.ByteArrayInputStream;

public class BSTSolver {

	public static void main(String[] args) {
		test();
	}
	
	private static void run() {
		Kattio kattio = new Kattio(System.in);
		int length = kattio.getInt();
		Tree tree = new Tree();
		int counter = 0;
		tree.addRoot(kattio.getInt());
		kattio.println(0);
		for (int i = 1; i < length; i++) {
			counter += tree.add(kattio.getInt());
			kattio.println(counter);
		}
		kattio.flush();
		kattio.close();
	}
	
	private static void test() {
		String input = TestPrinter.getInput(300000);
		Kattio kattio = new Kattio(new ByteArrayInputStream(input.getBytes()));
		long time = System.currentTimeMillis();
		int length = kattio.getInt();
		Tree tree = new Tree();
		int counter = 0;
		tree.addRoot(kattio.getInt());
		kattio.println(0);
		for (int i = 1; i < length; i++) {
			counter += tree.add(kattio.getInt());
			kattio.println(counter);
		}
		time = System.currentTimeMillis() - time;
		kattio.println("*** TIME: "+time+" ms ***");
		kattio.flush();
		kattio.close();
	}

}
