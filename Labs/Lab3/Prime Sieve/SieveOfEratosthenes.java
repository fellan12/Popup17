import java.util.*;

public class SieveOfEratosthenes {

  private BitSet primes;
  private int numOfPrimes;

  public SieveOfEratosthenes(int n) {

    primes = new BitSet(n);
    numOfPrimes = n-1;
    primes.set(1, n, true);

    // For 2 -> sqrt(n)
    for (int i = 2; i <= Math.ceil(Math.sqrt(n)); i++) {

      // If prime[i-1] is not changed, then it is a prime
      if (primes.get(i - 1)) {

        // Update all multiples of i
        for (int j = i * i; j <= n; j += i) {
          if(primes.get(j-1)){
            numOfPrimes--;
          }
          primes.set(j - 1, false);
        }
      }
    }
  }

  public int isPrime(int n) {
    return primes.get(n-1) ? 1: 0;
  }

  public int getNumOfPrimes(){
    return numOfPrimes;
  }
}
