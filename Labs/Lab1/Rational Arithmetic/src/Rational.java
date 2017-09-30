/**
 * Author: Felix De Silva
 * Represent a rational number and has internal functions for
 * basic arithmetic operations
 */

public class Rational implements Comparable<Rational>{
	private long numerator;
	private long denominator;

	public Rational(long numerator, long denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public long getNumerator(){
		return numerator;
	}

	public long getDenominator(){
		return denominator;
	}

	public void setDenominator(long value){
		denominator = value;
	}

	public void setNumerator(long value){
		numerator = value;
	}

	/**
	 * Add the other rational number to this and
	 * reduces the number to it smallest form
	 */
	public Rational add(Rational other){
		Rational[] rationals = makeSameBase(other);
		long x = rationals[0].getNumerator() + rationals[1].getNumerator();
		long y = rationals[0].getDenominator();
		Rational added = new Rational(x,y);
		reduce(added);
		return added;
	}

	/**
	 * Substract the other rational number to this and
	 * reduces the number to it smallest form
	 */
	public Rational sub(Rational other){
		Rational[] rationals = makeSameBase(other);
		long x = rationals[0].getNumerator() - rationals[1].getNumerator();
		long y = rationals[0].getDenominator();
		Rational sub = new Rational(x,y);
		reduce(sub);
		return sub;
	}

	/**
	 * Multiply the other rational number to this and
	 * reduces the number to it smallest form
	 */
	public Rational mul(Rational other){
		long x = this.getNumerator() * other.getNumerator();
		long y = this.getDenominator() * other.getDenominator();
		Rational multiplied = new Rational(x,y);
		reduce(multiplied);
		return multiplied;
	}

	/**
	 * Divide the other rational number to this and
	 * reduces the number to it smallest form
	 */
	public Rational div(Rational other){
		long x = this.getNumerator() * other.getDenominator();
		long y = this.getDenominator() * other.getNumerator();
		Rational divided = new Rational(x,y);
		reduce(divided);
		return divided;
	}

	/**
	 * Recalculate this number and the other number
	 * such that they have the same denominator.
	 *
	 * Return an array consisting of the two rational numbers
	 * in the same base.
	 * 0 -> this
	 * 1 -> other
	 */
	private Rational[] makeSameBase(Rational other){
		long x1 = this.getNumerator() * other.getDenominator();
		long y1 = this.getDenominator() * other.getDenominator();
		long x2 = other.getNumerator() * this.getDenominator();
		long y2 = this.getDenominator() * other.getDenominator();
		Rational r1 = new Rational(x1, y1);
		Rational r2 = new Rational(x2, y2);
		return new Rational[]{r1, r2};
	}

	/**
	 * Reduces a rational number to its smallest form
	 */
	public void reduce(Rational value){
		long div = -1;
		while(div != 1){
			div = GCD(value.getNumerator(), value.getDenominator());
			value.setNumerator(value.getNumerator()/div);
			value.setDenominator(value.getDenominator()/div);
		}
	}

	/**
	 * Calculate greatest common divisor of two numbers
	 */
	private long GCD(long a, long b) {
		if (b==0) return a;
		return GCD(b,a%b);
	}

	/**
	 * Print the value of the rational number
	 * as a string
	 */
	@Override
	public String toString(){
		if(denominator < 0){
			return -numerator + " / " + -denominator;
		}else{
			return numerator + " / " + denominator;
		}
	}

	/**
	 * Compares this number with the other
	 */
	@Override
	public int compareTo(Rational other) {
		Rational[] rationals = makeSameBase(other);
		return Long.compare(rationals[0].getNumerator(), rationals[1].getNumerator());
	}



}
