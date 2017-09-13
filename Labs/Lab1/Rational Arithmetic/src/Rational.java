/*
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

  /*
  * Get the numerator of the rational
  */
  public long getNumerator(){
    return numerator;
  }

  /*
  * Get the denominator of the rational
  */
  public long getDenominator(){
    return denominator;
  }

  /*
  * Set the numerator of the rational to a new value
  */
  public void setDenominator(long value){
    denominator = value;
  }

  /*
  * Set the denominator of the rational to a new value
  */
  public void setNumerator(long value){
    numerator = value;
  }

  /*
  * Add the other rational number to this and
  * reduces the number to it smallest form
  */
  public Rational add(Rational other){
    makeSameBase(other);
    long x = this.getNumerator() + other.getNumerator();
    long y = this.getDenominator();
    Rational added = new Rational(x,y);
    reduce(added);
    return added;
  }

  /*
  * Substract the other rational number to this and
  * reduces the number to it smallest form
  */
  public Rational sub(Rational other){
    makeSameBase(other);
    long x = this.getNumerator() - other.getNumerator();
    long y = this.getDenominator();
    Rational sub = new Rational(x,y);
    reduce(sub);
    return sub;
  }

  /*
  * Multiply the other rational number to this and
  * reduces the number to it smallest form
  */
  public Rational mul(Rational other){
    long x = this.getNumerator() * other.getNumerator();
    long y = this.getDenominator() * other.getDenominator();
    Rational added = new Rational(x,y);
    reduce(added);
    return added;
  }

  /*
  * Divide the other rational number to this and
  * reduces the number to it smallest form
  */
  public Rational div(Rational other){
    long x = this.getNumerator() * other.getDenominator();
    long y = this.getDenominator() * other.getNumerator();
    Rational added = new Rational(x,y);
    reduce(added);
    return added;
  }

  /*
  * Recalculate this number and the other number
  * such that they have the same denominator
  */
  private void makeSameBase(Rational other){
    long x1 = this.getNumerator() * other.getDenominator();
    long y1 = this.getDenominator() * other.getDenominator();
    long x2 = other.getNumerator() * this.getDenominator();
    long y2 = this.getDenominator() * other.getDenominator();
    this.setNumerator(x1);
    this.setDenominator(y1);
    other.setNumerator(x2);
    other.setDenominator(y2);
    // System.out.println(toString());
    // System.out.println(other.toString());
  }

  /*
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

  /*
  * Calculate greatest common divisor of two numbers
  */
  private long GCD(long a, long b) {
    if (b==0) return a;
    return GCD(b,a%b);
  }

  /*
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

  /*
  * Compares this number with the other
  */
  @Override
  public int compareTo(Rational other) {
    makeSameBase(other);
    return Long.compare(this.getNumerator(), other.getNumerator());
  }



}
