import java.util.HashMap;
import java.util.Scanner;

public class Buzzword {
	// 29178371L
	// 2395461987697L
	// 291783277L
	// 23954619877L
	private static long M = 2395461987697L;
	private static long b = 127L;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Kattio io = new Kattio(System.in);
		while (true) {
			String line = scanner.nextLine();
			if (line.length() == 0)
				break;
			line = line.replaceAll("\\s", "");
			char[] chars = line.toCharArray();
			for (int i = 1; i < line.length(); i++) {
				int s = hashSubstringsOfLength(chars, i);
				if (s > 1)
					io.println(s);
				else
					break;
			}
			io.println();
		}
		io.close();
		scanner.close();
	}
	
	public static int hashSubstringsOfLength(char[] chars, int length) {
		HashMap<Long, Integer> map = new HashMap<>();
		int max = 1;
		long[] powersOf = new long[length];
		powersOf[0] = 1;
		if (length > 1)
			powersOf[1] = b;
		for (int i = 2; i < powersOf.length; i++) {
			powersOf[i] = Math.floorMod((powersOf[i-1] * b), M);
		}
		long[] res = new long[chars.length];
		for (int i = 0; i < length; i++) {
			res[0] += Math.floorMod((powersOf[length-1-i]*charToLong(chars[i])), M);
		}
		map.put(res[0], 1);
		for (int i = 0; i < chars.length-length; i++) {
			res[i+1] = Math.floorMod(((res[i]-(charToLong(chars[i])*powersOf[length-1])) * b + charToLong(chars[length+i])), M);
			long results = res[i+1];
			if (res[i+1] < 0)
				throw new RuntimeException();
			if (!map.containsKey(res[i+1]))
				map.put(res[i+1], 0);
			int current = map.get(res[i+1]);
			if (current + 1 > max)
				max = current + 1;
			map.put(res[i+1], current+1);
		}
		return max;
	}
	
	private static long charToLong(char c) {
		return (long) c - 64;
	}
}
