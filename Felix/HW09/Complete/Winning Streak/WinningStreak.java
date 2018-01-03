import java.util.Scanner;

public class WinningStreak {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		while(io.hasMoreTokens()){
			io.println(calculateExpectedWinStreak(io));
		}
		io.close();
	}

	public static double calculateExpectedWinStreak(Kattio io){
		int n = io.getInt();
		double p = io.getDouble();

		double[][] dp = new double[n+1][n+1];
		double[] pp = new double[n+1];

		pp[0] = 1.0;
		for (int i = 1; i <= n; ++i) pp[i] = pp[i-1] * p;

		for (int i = 0; i <= n; ++i) dp[0][i] = 1.0;

		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j <= n; ++j) {
				dp[i][j] = dp[i-1][j];

				if(j == i-1) {
					dp[i][j] -= pp[i];
				} else if(j < i - 1) {
					dp[i][j] -= dp[i - j - 2][j] * (1-p) * pp[j+1];
				}
			}
		}

		double ans = 0.0;
		for (int j = 1; j <= n; ++j) ans += j* (dp[n][j] - dp[n][j-1]);

		return ans;
	}

}
