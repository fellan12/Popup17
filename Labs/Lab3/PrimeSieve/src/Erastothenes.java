
public class Erastothenes {
	
	private boolean[] notPrime;
	
	public Erastothenes(int limit) {
		notPrime = sieve(limit);
	}
	
	public boolean isPrime(int l) {
		return !notPrime[l];
	}
	
	public static boolean[] sieve(int limit) {
		boolean[] array = new boolean[limit+1];
		array[0] = true;
		array[1] = true;
		for (int i = 2; i < array.length; i++) {
			if (array[i])
				continue;
			int index = i;
			while (index*i < array.length) {
				array[index*i] = true;
				index++;
			}
		}
		return array;
	}

}
