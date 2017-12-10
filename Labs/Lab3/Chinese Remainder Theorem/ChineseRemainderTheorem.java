import java.awt.List;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.ArrayList;


public class ChineseRemainderTheorem {

  public static void main(String[] args) {
    int T;
    Kattio io = new Kattio(System.in);
    T = io.getInt();
    for (int i = 0; i < T; i++){
      long a = io.getLong();
      long n = io.getLong();
      long b = io.getLong();
      long m = io.getLong();

      Long[] res = crt(a, n, b, m);
      if(res.length == 0){
        io.println("no solution");
      } else {
        io.println(res[0] + " " + res[1]);
      }
    }
    io.close();
  }

  public static Long[] crt(long a, long n, long b, long m){
    BigInteger x = BigInteger.ONE;
    long d = gcd(n,m);
    m /= d;
    long l = n * m;


    //No Solution
    if (a % d != b % d){
      return new Long[0];
    }


    long m2 = Math.max(n, m);
    m = Math.min(n, m);
    ArrayList<Long> tmp1 = new ArrayList<Long>();
    ArrayList<Long> tmp2 = new ArrayList<Long>();
    for (long i = 2 ; i < Math.sqrt(m2) ; i++){
      long temp = 1;
      while (m2 % i == 0){
        temp *= i;
        m2 /= i;
      }
      while (m % i == 0){
        temp *= i;
        m /= i;
      }
      if (temp != 1){
        tmp1.add(temp);
        tmp2.add(temp);
      }
    }

    if (m > 1) {
      tmp1.add(m);
      tmp2.add(m);
    }

    if (m2 > 1) {
      tmp1.add(m2);
      tmp2.add(m2);
    }

    for (int i=0 ;i<tmp1.size();i++){
      tmp2.set(i, l / (long)tmp1.get(i));
    }

    ArrayList<BigInteger> tmp3 = new ArrayList<BigInteger>();
    ArrayList<BigInteger> tmp4 = new ArrayList<BigInteger>();
    for (int i=0;i<tmp1.size();i++){
      tmp4.add(new BigInteger(tmp2.get(i) + ""));
      tmp3.add(new BigInteger(tmp1.get(i) + ""));
    }

    BigInteger L = new BigInteger(l+"");
    BigInteger A = new BigInteger(a+"");
    BigInteger B = new BigInteger(b+"");

    // set x
    x = BigInteger.ZERO;
    for (int i=0;i<tmp1.size();i++){
      if (n % (long)tmp1.get(i) != 0)
      x = x.add((B.multiply(tmp4.get(i).multiply(mod_inv(tmp4.get(i), tmp3.get(i))))).mod(L));
      else
      x = x.add((A.multiply(tmp4.get(i).multiply(mod_inv(tmp4.get(i), tmp3.get(i))))).mod(L));
    }

    x = x.mod(L);

    return new Long[]{x.longValue(),l};
  }

  private static long gcd(long a, long b){
    while (b > 0){
      long temp = b;
      b = a % b; // % is remainder
      a = temp;
    }
    return a;
  }

  private static BigInteger mod_inv(BigInteger m, BigInteger n) {
    BigInteger bb = n, temp, q;
    BigInteger x0 = BigInteger.ZERO;
    BigInteger x1 = BigInteger.ONE;
    if (n.equals(1)) return BigInteger.ONE;
    while (m.compareTo(BigInteger.ONE) > 0) { // m > 1
      q = m.divide(n);
      temp = n; n = m.mod(n); m = temp;
      temp = x0; x0 = x1.add(q.multiply(x0).negate()); x1 = temp;
    }
    if (x1.compareTo(BigInteger.ZERO) < 0) {
      x1 = x1.add(bb);
    }
    return x1;
  }
}
