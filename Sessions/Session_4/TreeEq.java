import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeEq {

  List<Node> graph1;
  HashMap<Character, String[]> map = HashMap<>();

	public static void main(String[] args) {
		new TreeEq();
	}

	public TreeEq() {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		for (int i = 0; i < cases; i++) {
			graph1 = new ArrayList<>();
			List<Node> graph2 = new ArrayList<>();
			String g1 = io.getWord();
			// String g2 = io.getWord();
      parseString(g1);
		}
	}

	private void parseString(String s) {
		String tree = s;
    String[] subs = findSubTree(tree.substring(1, tree.length()-1));
    map.put(tree.charAt(0), subs);
    tree.substring(1, tree.length()-1)
    graph1.add(n);
    System.out.println(graph1);
	}

	private boolean isLabel(char c) {
		return Character.isLetter(c);
	}

	private class Node {
		final char c;
		HashMap<Character, Node> neighbors;

		Node(char c) {
			this.c = c;
			neighbors = new HashMap<>();
		}

		void addNeighbor(Node n) {
			neighbors.put(n.c, n);
		}

		boolean hasNeighbor(char c) {
			return neighbors.containsKey(c);
		}

		Node getNeighbor(char c) {
			return neighbors.get(c);
		}
	}
}
