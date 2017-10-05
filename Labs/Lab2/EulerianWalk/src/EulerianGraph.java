import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Authors Martin Engelin & Felix De Silva
 * 
 * Representation of a graph for the Eulerian path problem.
 */
public class EulerianGraph {
	
	private int vertices;
	private int start;
	private int end;
	private int edges;
	private HashMap<Integer, Deque<Integer>> outgoingEdges;
	private HashMap<Integer, Integer> incomingEdgesAmount;
	
	public EulerianGraph(int vertices, int edges) {
		this.vertices = vertices;
		this.edges = edges;
		this.outgoingEdges = new HashMap<>();
		this.incomingEdgesAmount = new HashMap<>();
		this.start = -1;
		this.end = -1;
	}
	
	/**
	 * Adds an edge to the graph
	 */
	public void addEdge(int from, int to) {
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
	 * Checks if the graph could be Semi Eulerian.
	 */
	public boolean feasible() {
		for (int i = 0; i < vertices; i++) {
			if (outgoingEdges(i) == incomingEdges(i)+1 && start == -1) {
				start = i;
			} else if (outgoingEdges(i)+1 == incomingEdges(i) && end == -1) {
				end = i;
			} else if (outgoingEdges(i) != incomingEdges(i)) {
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
	
	/**
	 * Returns the amount of outgoing edges from the
	 * argument node.
	 */
	private int outgoingEdges(int node) {
		if (!outgoingEdges.containsKey(node)) {
			return 0;
		}
		return outgoingEdges.get(node).size();
	}
	
	/**
	 * Returns the amount of incoming edges from the
	 * argument node.
	 */
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
	 * Returns an unvisited edge of the specified node, and removes it from the graph.
	 * returns -1 if no such node exists.
	 */
	public int removeUnvisitedEdge(int node) {
		Deque<Integer> out = outgoingEdges.get(node);
		if (out == null || out.isEmpty())
			return -1;
		return out.removeFirst();
	}
	
	/**
	 * Returns the amount of vertices as specified during its initialization.
	 */
	public int size() {
		return vertices;
	}
	
	/**
	 * Returns the amount of edges as specified during its initialization.
	 */
	public int edges() {
		return edges;
	}
}
