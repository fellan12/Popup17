
public class Eksplozija {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		String mirkoString = io.getWord();
		String explosive = io.getWord();
		String result = solve(mirkoString, explosive);
		if (result.isEmpty())
			result = "FRULA";
		io.println(result);
		io.close();
	}
	
	private static String solve(String mirkoString, String explosive) {
		StringBuilder sb = new StringBuilder();
		for (int i = mirkoString.length()-1; i >= 0; i--) {
			char c = mirkoString.charAt(i);
			sb.append(c);

			if (c == explosive.charAt(0) && sb.length() >= explosive.length()) {
				int j = 1;
				int k = sb.length()-2;
				while (j < explosive.length() && sb.charAt(k) == explosive.charAt(j)) {
					j++;
					k--;
				}
				if (j == explosive.length()) {
					sb.setLength(sb.length()-j);
				}
			}
		}
		return sb.reverse().toString();
	}
}
