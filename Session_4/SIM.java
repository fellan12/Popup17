import java.util.HashSet;

public class SIM {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int[] sims = new int[io.getInt()];
		for (int i = 0; i < sims.length; i++) {
			sims[i] = io.getInt();
		}
		int mod = 1;
		while (true) {
			HashSet<Integer> set = new HashSet<>();
			int i = 0;
			for (; i < sims.length; i++) {
				int reduce = Math.floorMod(sims[i], mod);
				if (set.contains(reduce)) {
					break;
				}
				set.add(reduce);
			}
			if (i == sims.length)
				break;
			mod++;
		}
		io.println(mod);
		io.close();
	}
}
