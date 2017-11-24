
public class Chinese {
	private static int[] extendedEuclidean(int x, int y) {
		if(y > x) {
			int[] coeffs = extendedEuclidean(y, x);
			int[] output = {coeffs[1], coeffs[0]};
			return output;
		}

		int q = x/y;
		int r = x -q*y;

		if(r == 0) {
			int[] output = {0, 1};
			return output;
		}
		int[] next = extendedEuclidean(y, r);
		int[] output = {next[1], next[0] - q*next[1]};
		return output;
	}

	private static int leastPosEquiv(int a, int m) {
		if(m < 0)
			return leastPosEquiv(a, -1*m);
		if(a >= 0 && a < m)
			return a;
		if(a < 0)
			return -1*leastPosEquiv(-1*a, m) + m;
		int q = a/m;
		return a - q*m;
	}

	public static int[] chineseRemainder(int[] as, int[] mods) {
		int M = 1;
		for(int i = 0; i < mods.length; i++)
			M *= mods[i];
		int[] multInv = new int[as.length];
		for(int i = 0; i < multInv.length; i++)
			multInv[i] = extendedEuclidean(M/mods[i], mods[i])[0];
		int x = 0;
		for(int i = 0; i < mods.length; i++)
			x += (M/mods[i])*as[i]*multInv[i];
		x = leastPosEquiv(x, M);
		return new int[] {x, M};
	}
	
	/**
	 * **********************************************************************************
	 * **********************************************************************************
	 * **********************************************************************************
	 */
	
	private static long findMinX(long[] num, long[] rem, long start, long inc) {
	    long x = start;
	    while (true) {
	        // Check if remainder of x % num[j] is 
	        // rem[j] or not (for all j from 0 to k-1)
	        int j;
	        for (j=0; j<num.length; j++) {
	            if (x % num[j] != rem[j])
	               break;
	        }
	 
	        // If all remainders matched, we found x
	        if (j == num.length)
	            return x;
	 
	        // Else try next number
	        x += inc;
	    }
	}
	 
	public static long[] chinese2(long[] as, long[] mods) {
		long M = 1;
		long start = 0;
		long inc = 1;
		for(int i = 0; i < mods.length; i++) {
			M *= mods[i];
			if (mods[i] > inc) {
				inc = mods[i];
				start = as[i];
			}
		}
	    long x = findMinX(mods, as, start, inc);
	    return new long[] {x, M};
	}
	
	/**
	 * **********************************************************************************
	 * **********************************************************************************
	 * **********************************************************************************
	 */
	
	public static int chinese3(int[] as, int[] mods) {
		int k = 1;
		int[] array = new int[mods.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = 1;
		}
		for (int i = 0; i < mods.length; i++) {
			k *= mods[i];
			for (int j = 0; j < mods.length; j++) {
				if (i == j)
					continue;
				array[j] *= mods[i];
			}
		}
		for (int i = 0; i < array.length; i++) {
			int rem = array[i] % mods[i];
			if (rem == as[i])
				continue;
			int r = rem;
			int mult = 1;
			while (true) {
				r += rem;
				mult++;
				if (r % mods[i] == as[i])
					break;
			}
			array[i] *= mult;
		}
		int answer = 0;
		for (int i = 0; i < array.length; i++) {
			answer += array[i];
		}
		return answer % k;
	}

}
