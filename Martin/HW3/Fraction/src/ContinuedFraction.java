import java.util.ArrayList;
import java.util.List;

public class ContinuedFraction {
	
	private Rational r;
	
	public ContinuedFraction(int[] quotients) {
		this.r = toRational(quotients);
	}
	
	public ContinuedFraction(Rational r) {
		this.r = r;
	}
	
	public Rational getRational() {
		return r;
	}
	
	public List<Long> toQuotients() {
		List<Long> ret = new ArrayList<>();
		long n = r.getDenominator();
		long d = r.getNumerator();
		while (n != 0) {
			long l = d/n;
			ret.add(l);
			long rest = d-l*n;
			d = n;
			n = rest;
		}
		return ret;
	}
	
	private Rational toRational(int[] quotients) {
		if (quotients.length == 1)
			return new Rational(quotients[0], 1);
		long n = 1;
		long d = quotients[quotients.length-1];
		for (int i = quotients.length-2; i >= 0; i--) {
			long tmp = n;
			n = d;
			d = quotients[i]*d+tmp;
		}
		return new Rational(d, n);
	}
}
