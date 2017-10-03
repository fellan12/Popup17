import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Authors Martin Engelin & Felix De Silva
 */
public class Graph {
	
	private int vertices;
	private int start;
	private int end;
	private Edge[] edges;
	private int edgesIndex;
	private HashMap<Integer, Deque<Integer>> outgoingEdges;
	private HashMap<Integer, Integer> incomingEdgesAmount;
	
	public Graph(int vertices, int edges) {
		this.vertices = vertices;
		this.edges = new Edge[edges];
		this.edgesIndex = 0;
		this.outgoingEdges = new HashMap<>();
		this.incomingEdgesAmount = new HashMap<>();
		this.start = -1;
		this.end = -1;
	}
	
	/**
	 * Adds an edge to the graph
	 */
	public void addEdge(int from, int to) {
		edges[edgesIndex] = new Edge(from, to);
		if (!outgoingEdges.containsKey(from)) {
			outgoingEdges.put(from, new ArrayDeque<>());
		}
		if (!incomingEdgesAmount.containsKey(to)) {
			incomingEdgesAmount.put(to, 0);
		}
		outgoingEdges.get(from).add(to);
		incomingEdgesAmount.put(to, incomingEdgesAmount.get(to)+1);
	}
	
	/**
	 * Checks if the graph could be Semi Eularian.
	 */
	public boolean feasible() {
		for (int i = 0; i < vertices; i++) {
			if (degree(i) % 2 == 0 && outgoingEdges(i) == incomingEdges(i)) {
				// Normal, do nothing
			} else if (outgoingEdges(i) == incomingEdges(i)+1 && start == -1) {
				start = i;
			} else if (outgoingEdges(i)+1 == incomingEdges(i) && end == -1) {
				end = i;
			} else {
				return false;
			}
		}
		if (start == -1)
			start = 0;
		return true;
	}
	
	/**
	 * Returns the start node.
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Returns the end node, or -1 if no node needs to be the end.
	 */
	public int getEnd() {
		return end;
	}
	
	private int degree(int node) {
		return outgoingEdges(node)+incomingEdges(node);
	}
	
	private int outgoingEdges(int node) {
		if (!outgoingEdges.containsKey(node)) {
			return 0;
		}
		return outgoingEdges.get(node).size();
	}
	
	private int incomingEdges(int node) {
		if (!incomingEdgesAmount.containsKey(node))
			return 0;
		return incomingEdgesAmount.get(node);
	}
	
	/**
	 * Checks if a node has any more unvisited edges.
	 */
	public boolean hasUnvisitedEdges(int node) {
		return (outgoingEdges.containsKey(node) && !outgoingEdges.get(node).isEmpty());
	}
	
	/**
	 * Returns an unvisited edge of the specified node.
	 * returns -1 if no such node exists.
	 */
	public int getUnvisitedEdge(int node) {
		Deque<Integer> out = outgoingEdges.get(node);
		if (out == null || out.isEmpty())
			return -1;
		if (out.getFirst() == getEnd() && out.size() > 1) {
			out.removeFirst();
			out.addLast(getEnd());
		}
		return outgoingEdges.get(node).removeFirst();
	}
	
	public int size() {
		return vertices;
	}
	
	class Edge {
		private int from, to;
		
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
		
		public int to() {
			return to;
		}
		
		public int from() {
			return from;
		}
	}

}
