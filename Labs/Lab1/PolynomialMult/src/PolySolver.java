
public class PolySolver {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int amount = io.getInt();
		for (int i = 0; i < amount; i++) {
			int[] first;
			int[] second;
			first = new int[io.getInt()+1];
			for (int j = 0; j < first.length; j++) {
				first[j] = io.getInt();
			}
			second = new int[io.getInt()+1];
			for (int j = 0; j < second.length; j++) {
				second[j] = io.getInt();
			}
			int[] results = new int[first.length+second.length-1];
			int biggest = 0;
			for (int j = 0; j < first.length; j++) {
				for (int k = 0; k < second.length; k++) {
					int prod = first[j] * second[k];
					results[j+k] += prod;
					if (j+k > biggest && prod != 0)
						biggest = j+k;
				}
			}
			io.println(biggest);
			for (int j = 0; j <= biggest; j++) {
				io.print(results[j]);
				io.print(" ");
			}
			io.println();
		}
		io.close();
	}

}
