import java.util.ArrayList;

public class TurboSolver {

	public static void main(String[] args) {
		Kattio kattio = new Kattio(System.in);
		int length = kattio.getInt();
		ArrayList<Integer> list = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			list.add(kattio.getInt());
		}
		for (int i = 0; i < length/2; i++) {
			int index = list.indexOf(i+1);
			kattio.println(index);
			list.remove(index);
			index = list.indexOf(length-i);
			kattio.println(list.size()-(index+1));
			list.remove(index);
		}
		if (list.size() == 1)
			kattio.println(0);
		kattio.close();
	}

}
