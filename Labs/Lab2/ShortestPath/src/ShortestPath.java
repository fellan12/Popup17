/**
 * Author: Felix De Silva & Martin Engelin
 */

public class ShortestPath {
	
	private static Kattio io = new Kattio(System.in, System.out);

	public static void main(String[] args) {
		while (io.hasMoreTokens()) {
			int nodes = io.getInt();
			int edges = io.getInt();
			int queries = io.getInt();
			int start = io.getInt();

			//We are done -> No more tests
			if(nodes == 0 && edges == 0 && queries == 0 && start == 0){
				break;
			}
			//dijkstraNonNeg(n, m, q, s);
			dijkstraTime(nodes, edges, queries, start);
		}
		io.close();
	}
	
	private static void dijkstra(int nodes, int edges, int queries, int start) {
		Node[] graph = new Node[nodes];
		for (int i = 0; i < nodes; i++) {
			graph[i] = new Node(i);
		}

		//Create Edges
		for (int i = 0; i < edges; i++) {
			Node current = graph[io.getInt()];
			Node neighbor = graph[io.getInt()];
			int weight = io.getInt();
			current.addEdge(new Edge(neighbor, weight));
		}

		//Run diijkstra on starting node
		Dijkstra.dijkstra(graph, start);

		//For every query find distance
		for (int i = 0; i < queries; i++) {
			int query = io.getInt();

			int distance = graph[query].getDistance();

			if (distance == Integer.MAX_VALUE) {
				io.println("Impossible");
			} else {
				io.println(distance);
			}
		}
		io.println();
	}
	
	private static void dijkstraTime(int nodes, int edges, int queries, int start) {
		TimeNode[] graph = new TimeNode[nodes];
		for (int i = 0; i < nodes; i++) {
			graph[i] = new TimeNode(i);
		}
		for (int i = 0; i < edges; i++) {
			TimeNode current = graph[io.getInt()];
			TimeNode neighbor = graph[io.getInt()];
			int firstTime = io.getInt();
			int period = io.getInt();
			int distance = io.getInt();
			current.addEdge(new TimeEdge(neighbor, distance, firstTime, period));
		}

		Dijkstra.dijkstraTimeLimit(graph, start);

		for (int i = 0; i < queries; i++) {
			int query = io.getInt();

			int distance = graph[query].getDistance();

			if (distance == Integer.MAX_VALUE) {
				io.println("Impossible");
			} else {
				io.println(distance);
			}
		}
		io.println();
	}
}
