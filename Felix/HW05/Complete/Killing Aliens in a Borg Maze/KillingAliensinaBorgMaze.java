import java.util.*;

public class KillingAliensinaBorgMaze {

	public static void main(String[] args) {
		Scanner io = new Scanner(System.in);

		int kases = Integer.parseInt(io.nextLine());

		for (int kase = 0; kase < kases; kase++) {
			String[] l1 = io.nextLine().split(" ");
			int y = Integer.parseInt(l1[0]);
			int x = Integer.parseInt(l1[1]);

			//Generate maze graph
			char[][] maze = new char[x][y];
			for (int i = 0; i < x; i++) {
				char[] line = io.nextLine().toCharArray();
				for (int j = 0; j < y; j++) {
					if(j < line.length){
						maze[i][j] = line[j];
					} else {
						maze[i][j] = ' ';
					}
				}
			}

			//Structure need for later
			List<Node> dijkstraNodes = new ArrayList<Node>();
			Node startNode = null;
			Node[] graph =  new Node[2*x*y];

			// Parse the maze and fill startNode, dijkstraNodes
			// and graph with data for later
			parseMaze(x, y, startNode, dijkstraNodes, graph, maze);

			//Create a list of edges containing all edge combinations between StartNode and Alien Nodes
			List<Edge> edgeList = new ArrayList<Edge>();
			HashMap<Integer, Integer> nodeToIdx = new HashMap<Integer, Integer>();
			int newIndex = 0;
			for (Node s : dijkstraNodes) {
				//Run dijkstra's with start Node s
				Dijkstra.dijkstra(s);

				//Create edge from s to all other nodes
				for (Node a : dijkstraNodes) {
					if(!s.equals(a)){
						Node n1 = s.copy();
						if(!nodeToIdx.containsKey(s.getIndex())){
							n1.setIndex(newIndex);
							nodeToIdx.put(s.getIndex(), newIndex);
							newIndex++;
						} else {
							n1.setIndex(nodeToIdx.get(s.getIndex()));
						}

						Node n2 = a.copy();
						if(!nodeToIdx.containsKey(a.getIndex())){
							n2.setIndex(newIndex);
							nodeToIdx.put(a.getIndex(), newIndex);
							newIndex++;
						} else {
							n2.setIndex(nodeToIdx.get(a.getIndex()));
						}
						edgeList.add(new Edge(n1, n2, a.getDistance()));
					}
				}

				//Reset nodes to run dijkstra again
				resetNodes(graph);
			}

			//Create graph Object needed for Kruskal's Algorithm
			Graph g = new Graph(newIndex, edgeList.size());
			g.setEdgeList(edgeList);

			//Run kruskal and calculate the sum of all the weights in the result tree
			int sum = 0;
			for (Edge e : Kruskal.kruskal(g)) {
				sum += e.getWeight();
			}
			System.out.println(sum);
		}
	}

	// Genereate graph of the problem
	// Collect startNode
	// Collect startNode and aliens nodes used for the muliple runs of dijkstra's later
	public static void parseMaze(int x, int y, Node startNode, List<Node> dijkstraNodes, Node[] graph, char[][] maze){
		Map<Integer, Integer> nodeIndex = new HashMap<Integer, Integer>();
		int index = 0;
		for (int i = 0; i < x; i++ ) {
			for (int j = 0; j < y; j++) {
				if(maze[i][j] != '#'){
					Node A;
					Node B;

					//Set node A
					if(!nodeIndex.containsKey((i*100)+j)){
						nodeIndex.put((i*100)+j,index++);
						A = new Node(nodeIndex.get((i*100)+j));
					} else {
						A = graph[nodeIndex.get((i*100)+j)];
					}

					//Catch start node
					if(maze[i][j] == 'S'){
						A.setStart(true);
						startNode = A;
						dijkstraNodes.add(A);
					}

					//Catch Alien node
					if(maze[i][j] == 'A'){
						A.setAlien(true);
						dijkstraNodes.add(A);
					}

					//Set node B
					//Create edge left
					if(j > 0 && maze[i][j-1] != '#'){
						if(!nodeIndex.containsKey((i*100)+(j-1))){
							nodeIndex.put((i*100)+(j-1),index++);
							B = new Node(nodeIndex.get((i*100)+(j-1)));
						} else {
							B = graph[nodeIndex.get((i*100)+(j-1))];
						}

						B.addEdge(new Edge(B, A, 1));
						A.addEdge(new Edge(A, B, 1));
						graph[nodeIndex.get((i*100)+(j-1))] = B;
						graph[nodeIndex.get((i*100)+j)] = A;

					}

					//Create edge right
					if(j < y-1 && maze[i][j+1] != '#'){
						if(!nodeIndex.containsKey((i*100)+(j+1))){
							nodeIndex.put((i*100)+(j+1),index++);
							B = new Node(nodeIndex.get((i*100)+(j+1)));
						} else {
							B = graph[nodeIndex.get((i*100)+(j+1))];
						}

						B.addEdge(new Edge(B, A, 1));
						A.addEdge(new Edge(A, B, 1));
						graph[nodeIndex.get((i*100)+(j+1))] = B;
						graph[nodeIndex.get((i*100)+(j))] = A;
					}

					//Create edge up
					if(i > 0 && maze[i-1][j] != '#'){
						if(!nodeIndex.containsKey(((i-1)*100)+j)){
							nodeIndex.put(((i-1)*100)+j,index++);
							B = new Node(nodeIndex.get(((i-1)*100)+j));
						} else {
							B = graph[nodeIndex.get(((i-1)*100)+j)];
						}

						B.addEdge(new Edge(B, A, 1));
						A.addEdge(new Edge(A, B, 1));
						graph[nodeIndex.get(((i-1)*100)+j)] = B;
						graph[nodeIndex.get((i*100)+j)] = A;
					}

					//Create edge down
					if(i < x-1 && maze[i+1][j] != '#'){
						if(!nodeIndex.containsKey(((i+1)*100)+j)){
							nodeIndex.put(((i+1)*100)+j,index++);
							B = new Node(nodeIndex.get(((i+1)*100)+j));
						} else {
							B = graph[nodeIndex.get(((i+1)*100)+j)];
						}

						B.addEdge(new Edge(B, A, 1));
						A.addEdge(new Edge(A, B, 1));
						graph[nodeIndex.get(((i+1)*100)+j)] = B;
						graph[nodeIndex.get((i*100)+j)] = A;
					}
					index++;
				}
			}
		}
	}

	//Reset nodes in graph
	public static void resetNodes(Node[] graph){
		for (Node A : graph) {
			if(A != null){
				A.setDistance(Integer.MAX_VALUE);
			}
		}
	}
}
