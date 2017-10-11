import java.util.ArrayDeque;

public class EdmundKarp {

	public static int maxFlow(Node[] graph, int source, int tap) {
		Edge[] pred = new Edge[graph.length];
		int flow = 0;
		while(true) {
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			queue.push(source);
			while (!queue.isEmpty()) {
				Node cur = graph[queue.poll()];
				for (Edge e : cur.getEdges()) {
					if (pred[e.toIndex()] == null && e.toIndex() != source && e.getCapacity() > e.getFlow()) {
						pred[e.toIndex()] = e;
						queue.push(e.toIndex());
					}
				}
			}
			if (pred[tap] != null) {
				int diffFlow = Integer.MAX_VALUE;
				for (Edge e = pred[tap]; e != null; e = pred[e.fromIndex()]) {
					diffFlow = Math.min(diffFlow, e.getCapacity()-e.getFlow());
				}
				for (Edge e = pred[tap]; e != null; e = pred[e.fromIndex()]) {
					e.addFlow(diffFlow);
					e.getResidual().addFlow(-diffFlow);
				}
				flow += diffFlow;
			} else {
				break;
			}
			pred = new Edge[graph.length];
		}
		return flow;
	}
	
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int nodes = io.getInt();
		int edges = io.getInt();
		int source = io.getInt();
		int tap = io.getInt();
		Node[] graph = new Node[nodes];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new Node(i);
		}
		for (int i = 0; i < edges; i++) {
			int from = io.getInt();
			int to = io.getInt();
			int cap = io.getInt();
			Edge e = new Edge(graph[from], graph[to], cap);
			Edge res = e.generatedResidual();
			graph[from].addEdge(e);
			graph[to].addEdge(res);
		}
		int maxFlow = maxFlow(graph, source, tap);
		
		io.close();
	}
}
