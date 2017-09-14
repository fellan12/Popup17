public class TurboSolver {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int length = io.getInt();
		int[] indexMap = new int[length+1];
		FenwickTree fenwick = new FenwickTree(length);
		for (int i = 0; i < length; i++) {
			fenwick.add(i, 1);
			indexMap[io.getInt()] = i;
		}
		for (int i = 0; i < length/2; i++) {
			int index = i+1;
			fenwick.add(indexMap[index], -1);
			int sum = fenwick.sumLeft(indexMap[index]);
			io.println(sum);
			fenwick.add(indexMap[length-i], -1);
			sum = fenwick.sumRight(indexMap[length-i]);
			io.println(sum);
		}
		if (length % 2 != 0)
			io.println(0);
		io.close();
	}

}
