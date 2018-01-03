public class FareySequenceLength {

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in);
    int t = io.getInt();
    int[] fareyLength = new int[10001];
    fareyLength[1] = 2;

    int currentFarey = 1;
    for (int i = 0; i < t; ++i) {
      int z = io.getInt();
      int n = io.getInt();

      if (fareyLength[n] != 0) {
        io.println(z + " " + fareyLength[n]);
        continue;
      }

      for (int j = currentFarey; j < n; ++j) {
        //Formula
        fareyLength[j+1] = fareyLength[j] + eulerFunction(j+1);
      }

      currentFarey = n;
      io.println(z + " " + fareyLength[n]);
    }
    io.close();
  }

  public static int eulerFunction(int n) {
    int ans = n;
    for (int i = 2; i*i <= n; ++i) {
      if (n%i == 0) {
        while (n%i == 0) {
          n = n/i;
        }
        ans -= ans/i;
      }
    }

    if (n > 1) {
      ans -= ans/n;
    }

    return ans;
  }
}
