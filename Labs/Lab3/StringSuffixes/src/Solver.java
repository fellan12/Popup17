import java.util.List;
import java.util.Scanner;

public class Solver {
	
	public static void main(String[] args) {
		Scanner io = new Scanner(System.in);
		while (io.hasNextLine()) {
			String word = io.nextLine();
			Trie trie = new Trie();
			int index = 0;
			while (word.length() > 0) {
				trie.addWord(word, index);
				index++;
				word = word.substring(1);
			}
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
