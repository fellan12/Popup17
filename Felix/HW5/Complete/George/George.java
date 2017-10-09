
public class George {

	public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
		int n = io.getInt();                      //Number of nodes
		int m = io.getInt();                      //Number of TimeEdges
		int start = io.getInt();                  //Luka start position
		int end = io.getInt();                    //Luka end position
		int diff = io.getInt();                   //Difference between luka and george
		int GeorgePathLength = io.getInt();       //Length of georges path

    //Collect george's path
		int[] georgeVisits = new int[GeorgePathLength];
		for (int i = 0; i < georgeVisits.length; i++) {
			georgeVisits[i] = io.getInt();
		}

    //Create graph
    TimeNode[] graph = new TimeNode[n+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new TimeNode(i);
		}

    //Create undirected edge and map edges to index
    int edgeIndex = 0;
    TimeEdge[] edges = new TimeEdge[m*2];
		int[][] edgeIndexMatrix = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			TimeNode from = graph[io.getInt()];
			TimeNode to = graph[io.getInt()];
			int time = io.getInt();

			TimeEdge e = new TimeEdge(from, to, time);
			edges[edgeIndex] = e;
			edgeIndexMatrix[from.getIndex()][to.getIndex()] = edgeIndex;
			edgeIndex++;
			from.addAdjecent(e);

      e = new TimeEdge(to, from, time);
			edges[edgeIndex] = e;
			edgeIndexMatrix[to.getIndex()][from.getIndex()] = edgeIndex;
			edgeIndex++;
			to.addAdjecent(e);
		}

    //Add george path to the concerned edges
		int time = -diff;
		for (int i = 0; i < georgeVisits.length-1; i++) {
			//Set george blocking time in first direction
			edgeIndex = edgeIndexMatrix[georgeVisits[i]][georgeVisits[i+1]];
			TimeEdge e = edges[edgeIndex];
			e.setBlockedInterval(time);

			//Set george blocking time in second direction
			edgeIndex = edgeIndexMatrix[georgeVisits[i+1]][georgeVisits[i]];
			e = edges[edgeIndex];
			e.setBlockedInterval(time);

			//Advance time
			time += e.getTime();
		}

		Print all edges
		for (TimeEdge e : edges){
			System.out.println(e);
			System.out.println();
		}
		System.out.println();

		Dijkstra.dijkstraTimeLimit(graph, start, 0);	//

		io.println(graph[end].getDistance());
    io.close();
	}

}
