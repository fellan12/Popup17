public class BoggleBoard {
	
	private static final int MAX_DEPTH = 8;
	
	private char[] board;
	private BoggleTrie trie;
	
	public BoggleBoard(String boardString, BoggleTrie trie) {
		if (boardString.length() != 16)
			throw new RuntimeException("BoardString "+boardString+" not correct");
		board = boardString.toCharArray();
		this.trie = trie;
	}
	
	public Results getResults() {
		Results results = new Results();
		for (int i = 0; i < board.length; i++) {
			dfs(1, i, "", new boolean[16], results);
		}
		return results;
	}
	
	private void dfs(int currentDepth, int currentIndex, String currentString, boolean[] visited, Results results) {
		currentString += board[currentIndex];
		if (!trie.isPrefix(currentString))
			return;
		visited[currentIndex] = true;
		if (currentDepth >= 3 && trie.isWord(currentString))
			results.add(currentString);
		if (MAX_DEPTH > currentDepth) {
			for(int i : getNeighborIndexes(currentIndex)) {
				if (!visited[i])
					dfs(currentDepth+1, i, currentString, visited, results);
			}
		}
		visited[currentIndex] = false;
	}
	
	public static int[] getNeighborIndexes(int i) {
		switch (i) {
		case 0:
			return new int[] {1, 4, 5};
		case 3:
			return new int[] {2, 6, 7};
		case 15:
			return new int[] {10, 11, 14};
		case 12:
			return new int[] {8, 9, 13};
		case 1:
		case 2:
			return new int[] {i-1, i+1, i+3, i+4, i+5};
		case 4:
		case 8:
			return new int[] {i-4, i-3, i+1, i+4, i+5};
		case 7:
		case 11:
			return new int[] {i-5, i-4, i-1, i+3, i+4};
		case 13:
		case 14:
			return new int[] {i-5, i-4, i-3, i-1, i+1};
		default:
			return new int[] {i-5, i-4, i-3, i-1, i+1, i+3, i+4, i+5};
		}
	}
}
