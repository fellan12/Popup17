public class Main {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		for (int c = 0; c < cases; c++) {
			int amount = io.getInt();
			long[] mods = new long[amount];
			long[] as = new long[amount];
			for (int i = 0; i < as.length; i++) {
				mods[i] = io.getLong();
				as[i] = io.getLong();
			}
			solveCase(as, mods);
		}
		io.close();
	}

	private static void solveCase(long[] as, long[] mods) {
		long a, b, m, n;
		a = as[0];
		m = mods[0];
		int i = 1;
		while (i < as.length) {
			b = as[i];
			n = mods[i];
			long gcd = gcd(m,n);
			if (!possible(a, b, m, n, gcd)) {
				System.out.println("Impossible");
				return;
			}
			long[] euclids = extendedEuclidian(a, b);
			long u = euclids[0];
			long v = euclids[1];
			long gamma = (a-b)/gcd;
			a = a - m*u*gamma;
			m = lcm(m,n, gcd);
			i++;
		}
		System.out.println(a % m);
	}
	
	private static boolean possible(long a, long b, long m, long n, long gcd) {
		long x = a - b;//Math.abs(a-b);
		long tmp = x / gcd;
		if (tmp * gcd == x)
			return true;
		return false;
	}
	
	private static long gcd(long a, long b) {
		while (a != b) {
			if (a > b)
				a -= b;
			else
				b -= a;
		}
		return a;
	}
	
	private static long lcm(long a, long b, long gcd) {
		return (a*b)/gcd;
	}
	
	private static long[] extendedEuclidian(long a, long b) {
		long t = 0, s = 1, lastt = 1, lasts = 0, temp;
        while (b != 0) {
            long q = a / b;
            long r = a - q * b;
            a = b;
            b = r;
            temp = t;
            t = lastt - q * t;
            lastt = temp;
            temp = s;
            s = lasts - q * s;
            lasts = temp;            
        }
        return new long[]{lastt, lasts};
	}
}
