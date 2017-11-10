import java.util.ArrayList;
import java.util.List;

public class BoggleTrie {

	private Node root;
	private List<Node> nodeList;

	public BoggleTrie(String[] dictionary) {
		init();
		for (String word : dictionary) {
			addWord(word);
		}
	}
	public BoggleTrie() {
		init();
	}
	
	private void init() {
		root = new Node();
		nodeList = new ArrayList<>();
		nodeList.add(root);
	}
	
	public void addWord(String word) {
		Node n = root;
		for (char c : word.toCharArray()) {
			if (!n.hasChild(c))
				n = nodeList.get(n.addChild(c));
			else
				n = nodeList.get(n.getChild(c));
		}
		n.setIsWord();
	}

	public boolean isWord(String s) {
		return isWordGen(0, s);
	}

	public boolean isWordGen(int root, String s) {
		Node n = nodeList.get(root);
		for (char c : s.toCharArray()) {
			if (!n.hasChild(c))
				return false;
			n = nodeList.get(n.getChild(c));
		}
		return n.isWord();
	}

	public boolean isPrefix(String s) {
		return isPrefixGen(0, s);
	}

	public boolean isPrefixGen(int root, String s) {
		Node n = nodeList.get(root);
		for (char c : s.toCharArray()) {
			if (!n.hasChild(c))
				return false;
			n = nodeList.get(n.getChild(c));
		}
		return true;
	}

	public boolean isPrefixGen(int root, char c) {
		Node n = nodeList.get(root);
		if (!n.hasChild(c))
			return false;
		return true;
	}

	public int getNextNodeIndex(int index, char c) {
		return nodeList.get(index).getChild(c);
	}

	public class Node {
		private int[] children;
		private boolean word;

		public Node() {
			children = new int[26];
		}

		public int addChild(char c) {
			if (hasChild(c)) {
				throw new RuntimeException("Trying to add new node when node already exists");
			}
			int index = nodeList.size();
			children[charToIndex(c)] = index;
			nodeList.add(new Node());
			return index;
		}

		public int getChild(char c) {
			return children[charToIndex(c)];
		}

		public boolean hasChild(char c) {
			return getChild(c) != 0;
		}

		public boolean isWord() {
			return word;
		}

		public void setIsWord() {
			this.word = true;
		}
	}

	private static int charToIndex(char c) {
		return ((int) c)-65;
	}
}
