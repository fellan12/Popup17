public class TurboSolver {

	public static void main(String[] args) {
		// Parse input 
		Kattio io = new Kattio(System.in);
		int length = io.getInt();
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[i] = io.getInt();
		}
		TurboList turboList = new TurboList(list);
		// Calculate swaps
		for (int i = 0; i < length/2; i++) {
			// Get first object
			int object = i+1;
			turboList.remove(object);
			int swaps = turboList.amountLeftOf(object);
			io.println(swaps);
			// Get last object
			object = length-i;
			turboList.remove(object);
			swaps = turboList.amountRightOf(object);
			io.println(swaps);
		}
		// If the list size is odd, we need to add the last element
		// (Which is always 0 swaps, obv)
		if (length % 2 != 0)
			io.println(0);
		io.close();
	}

}
