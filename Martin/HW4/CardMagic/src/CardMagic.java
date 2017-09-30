
public class CardMagic {
	private static Kattio io = new Kattio(System.in);

	
	private static void solve(int N, int K, int T) {
		if (N == 1) {
			io.println("1");
			return;
		}
			
		long[][] matrix = new long[N-1][T];
		for (int j = 0; j < K; j++) {
			if (j == matrix[0].length)
				break;
			matrix[0][j] = 1;
		}
		for (int i = 1; i < matrix.length; i++) {
			for (int j = i; j < K*(i+1) && j < T; j++) {
				long sum = 0;
				int traverseIndex = j-1;
				while (traverseIndex >= j-K && traverseIndex >= 0) {
					sum = (sum + matrix[i-1][traverseIndex]) % 1000000009;
					traverseIndex--;
				}
				matrix[i][j] = sum;
			}
		}
		
		long answer = 0;
		for (int i = 0; i < K; i++) {
			if (T-i-2 < 0 || N-2 < 0)
				break;
			answer = (answer + matrix[N-2][T-i-2]) % 1000000009;
		}
		io.println(answer);
	}
	
	public static void main(String[] args) {
		solve(io.getInt(), io.getInt(), io.getInt());
		io.close();
	}
}
