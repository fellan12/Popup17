
public class Chinese {
	
	public static int chineseRemainder(int[] as, int[] mods) {
		int M = 1;
		for(int i = 0; i < mods.length; i++)
			M *= mods[i];
		int[] multInv = new int[as.length];
		for(int i = 0; i < multInv.length; i++)
			multInv[i] = extendedEuclidean(M/mods[i], mods[i])[0];
		int x = 0;
		for(int i = 0; i < mods.length; i++)
			x += (M/mods[i])*as[i]*multInv[i];
		return lpe(x, M);
	}
	
	private static int[] extendedEuclidean(int x, int y) {
		if (x < y) {
			int[] coeffs = extendedEuclidean(y, x);
			int[] output = new int[] {coeffs[1], coeffs[0]};
			return output;
		}
		
		int q = x / y;
		int r = x -q * y;

		if(r == 0) {
			return new int[] {0, 1};
		}
		int[] next = extendedEuclidean(y, r);
		int[] output = {next[1], next[0] - q*next[1]};
		return output;
	}

	private static int lpe(int a, int m) {
		if(m < 0)
			return lpe(a, -m);
		if(a >= 0 && a < m)
			return a;
		if(a < 0)
			return -lpe(-a, m) + m;
		int q = a / m;
		return a - q * m;
	}
}
