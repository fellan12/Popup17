
public class Test {
	public static void main(String[] args) {
		Erastothenes e = new Erastothenes(121);
		for (int i = 0; i < 121; i++) {
			if (e.isPrime(i)) {
				System.out.println(i);
			}
		}
	}
}
