
public class KattisSolver {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		for (int i = 0; i < cases; i++) {
			int[] a = new int[2];
			int[] m = new int[2];
			for (int j = 0; j < m.length; j++) {
				a[j] = io.getInt();
				m[j] = io.getInt();
			}
			int res = Chinese.chineseRemainder(a, m);
			int K = m[0]*m[1];
			io.println(res+ " "+K);
		}
		io.close();
	}
}