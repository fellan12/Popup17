public class PrimeSieve {

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int n = io.getInt();
    int q = io.getInt();

    SieveOfEratosthenes soe = new SieveOfEratosthenes(n);

    io.println(soe.getNumOfPrimes());

    for (int i = 0; i < q ; i++) {
        io.println(soe.isPrime(io.getInt()));
    }
    io.close();
  }
}
