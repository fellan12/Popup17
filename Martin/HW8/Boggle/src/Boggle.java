public class Boggle {
	
	private static final Kattio io = new Kattio(System.in);

	public static void main(String[] args) {
		BoggleBoard[] boards = InputParser.parseInput();
		for (BoggleBoard b : boards) {
			io.println(b.getResults().toString());
		}
		io.close();
	}
	
	private static class InputParser {
		public static BoggleBoard[] parseInput() {
			int wordAmount = io.getInt();
			BoggleTrie trie = new BoggleTrie();
			for (int i = 0; i < wordAmount; i++) {
				trie.addWord(io.getWord());
			}
			BoggleBoard[] res = new BoggleBoard[io.getInt()];
			for (int i = 0; i < res.length; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(io.getWord());
				sb.append(io.getWord());
				sb.append(io.getWord());
				sb.append(io.getWord());
				res[i] = new BoggleBoard(sb.toString(), trie);
			}
			return res;
		}
	}
}
