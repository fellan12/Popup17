
public class Edge {

	private Node from;
	private Node to;
	private Edge residual;
	private int capacity;
	private int flow;
	
	public Edge(Node from, Node to, int capacity) {
		this.from = from;
		this.to = to;
		this.capacity = capacity;
		this.flow = 0;
		this.residual = null;
	}
	
	public Node getTo() {
		return to;
	}
	
	public int toIndex() {
		return to.getIndex();
	}
	
	public Node getFrom() {
		return from;
	}
	
	public int fromIndex() {
		return from.getIndex();
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getFlow() {
		return flow;
	}
	
	public void addFlow(int addedFlow) {
		if (flow + addedFlow > capacity)
			throw new RuntimeException();
		flow += addedFlow;
		residual.flow -= addedFlow;
	}
	
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	public Edge getResidual() {
		return residual;
	}
	
	public Edge generatedResidual() {
		this.residual = new Edge(to, from, capacity);
		this.residual.flow = capacity;
		this.residual.residual = this;
		return residual;
	}
	
	@Override
	public String toString() {
		return from+"->"+to+"("+flow+"/"+capacity+")";
	}
	
}
