import java.util.List;
import java.util.Scanner;

public class Solver {
	
	public static void main(String[] args) {
		Scanner io = new Scanner(System.in);
		while (io.hasNextLine()) {
			String word = io.nextLine();
			String[] dictionary = new String[word.length()];
			for (int i = 0; i < dictionary.length; i++) {
				dictionary[i] = word;
				word = word.substring(1);
			}
			Trie trie = new Trie(dictionary);
			List<Integer> sortedIndexes = trie.getSortedIndexes();
			String[] queries = io.nextLine().split(" ");
			for (int i = 1; i < queries.length; i++) {
				int q = Integer.parseInt(queries[i]);
				System.out.print(sortedIndexes.get(q)+" ");
			}
			System.out.print("\n");
		}
	}

}
