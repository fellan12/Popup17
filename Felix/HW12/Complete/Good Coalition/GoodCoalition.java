
public class GoodCoalition {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int tests = io.getInt();
    for (int c = 0; c < tests; c++){
      int numOfParties = io.getInt();

      //Collect
      int[] percentage = new int[numOfParties];
      int[] seats = new int[numOfParties];
      for (int i=0;i<numOfParties;i++){
         seats[i] = io.getInt();
         percentage[i] = io.getInt();
      }

      //Calculate
      double[] dp = new double[151];
      dp[0] = 1.0;
      for (int i=0; i<numOfParties; i++){
        for (int seat=150; seat>=seats[i]; seat--){
          dp[seat] = Math.max(dp[seat] , percentage[i]/100.0*dp[seat-seats[i]]);
        }
      }

      //Find the best
      double best = 0.0;
      for (int seat=76; seat<151; seat++) {
        best = Math.max(best , dp[seat]);
      }

      //Print
      io.println(String.format("%.6f",best*100.0));
    }
    io.close();
  }
}
