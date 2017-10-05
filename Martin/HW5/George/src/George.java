
public class George {
	private static Kattio io = new Kattio(System.in);

	public static void main(String[] args) {
		int vertices = io.getInt();
		int edgeLength = io.getInt();
		int start = io.getInt();
		int end = io.getInt();
		int diff = io.getInt();
		int routeLength = io.getInt();
		int[][] edgeMap = new int[vertices+1][vertices+1];
		TimeEdge[] edges = new TimeEdge[edgeLength*2];
		int ei = 0;
		TimeNode[] graph = new TimeNode[vertices+1];
		int[] visits = new int[routeLength];
		for (int i = 0; i < visits.length; i++) {
			visits[i] = io.getInt();
		}
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new TimeNode(i);
		}
		for (int i = 0; i < edgeLength; i++) {
			TimeNode from = graph[io.getInt()];
			TimeNode to = graph[io.getInt()];
			int time = io.getInt();
			TimeEdge e = new TimeEdge(to, time);
			edges[ei] = e;
			edgeMap[from.getIndex()][to.getIndex()] = ei;
			ei++;
			from.addEdge(e);
			e = new TimeEdge(from, time);
			edges[ei] = e;
			edgeMap[to.getIndex()][from.getIndex()] = ei;
			ei++;
			to.addEdge(e);
		}
		int time = 0;
		for (int i = 0; i < visits.length-1; i++) {
			TimeEdge e = edges[edgeMap[visits[i]][visits[i+1]]];
			e.setBlockedInterval(time);
			e = edges[edgeMap[visits[i+1]][visits[i]]];
			e.setBlockedInterval(time);
			time += e.getTime();
		}
		Dijkstra.dijkstraTimeLimit(graph, start, diff);
		System.out.println(graph[end].getDistance()-diff);
	}

}
