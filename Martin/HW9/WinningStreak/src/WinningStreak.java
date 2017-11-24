import java.util.Scanner;

public class WinningStreak {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String[] s = in.nextLine().split(" ");
			int games = Integer.parseInt(s[0]);
			double pW = Double.parseDouble(s[1]);
			double pL = 1-pW;
			int[] winAmount = amountOfWins(games);
			double res = 0;
			for (int i = 0; i < winAmount.length; i++) {
				double n = 1;
				n *= Math.pow(pW, i);
				n *= Math.pow(pL, games-i);
				n *= i;
				n *= winAmount[i];
				res += n;
			}
			System.out.println(res);
		}
	}
	
	private static int[] amountOfWins(int games) {
		int[][] matrix = new int[games+1][games+1];
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][0] = 1;
		}
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				matrix[i][j] = matrix[i-1][j]+matrix[i-1][j-1];
			}
		}
		return matrix[games];
	}

}
