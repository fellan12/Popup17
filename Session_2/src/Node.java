import java.util.ArrayList;
import java.util.List;

public class Node {

	private int index;
	private List<Edge> edges;
	
	public Node(int index) {
		this.index = index;
		this.edges = new ArrayList<>();
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return ""+index;
	}
}
