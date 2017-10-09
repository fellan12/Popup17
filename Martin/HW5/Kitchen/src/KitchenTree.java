import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class KitchenTree {

	int[] cupSizes;
	private PriorityQueue<Node> states;
	private HashSet<Node> visited;

	public KitchenTree(int[] cupSizes) {
		this.cupSizes = cupSizes;
		this.states = new PriorityQueue<>();
		visited = new HashSet<>();
	}

	public int solve(int goal) {
		int[] cups = new int[cupSizes.length];
		cups[0] = cupSizes[0];
		Node current = new Node(cups, 0);
		states.add(current);
		while (!states.isEmpty()) {
			current = states.poll();
			if (visited.contains(current))
				continue;
			visited.add(current);
			if (current.cups[0] == goal)
				 return current.poured;
			current.generateChildren();
		}
		return -1;
	}

	private class Node implements Comparable<Node> {
		private int[] cups;
		private int poured;

		public Node(int[] cups, int poured) {
			this.cups = cups;
			this.poured = poured;
		}

		public void generateChildren() {
			for (int from = 0; from < cups.length; from++) {
				for (int to = 0; to < cups.length; to++) {
					if (from != to && cups[from] != 0) {
						int[] newCups = Arrays.copyOf(cups, cups.length);
						int amount = newCups[from] > cupSizes[to] - newCups[to] ? cupSizes[to] - newCups[to] : newCups[from];
						if (amount > 0) {
							newCups[from] -= amount;
							newCups[to] += amount;
							Node n = new Node(newCups, poured+amount);
							if (!visited.contains(n)) {
								states.add(n);
							}
						}
					}
				}
			}
		}

		@Override
		public int hashCode() {
			int ret = 0;
			for (int i = 0; i < cups.length; i++) {
				ret += ((int) Math.pow(100, i)) * cups[i];
			}
			return ret;
		}

		@Override
		public boolean equals(Object arg0) {
			try {
				Node other = (Node) arg0;
				for (int i = 0; i < cups.length; i++) {
					if (this.cups[i] != other.cups[i])
						return false;
				}
			} catch (Exception e) {
				return false;
			}
			return true;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(poured, o.poured);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if (cups.length > 0)
				sb.append(cups[0]);
			for (int i = 1; i < cups.length; i++) {
				sb.append(", ").append(cups[i]);
			}
			sb.append("]");
			return sb.toString();
		}
	}
}
