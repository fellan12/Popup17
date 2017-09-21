/**
* Author: Felix De Silva
**/

public class ShortestPath1 {

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);

    while (io.hasMoreTokens()) {
      int n = io.getInt(); // nodes in the graph
      int m = io.getInt(); // edges
      int q = io.getInt(); // queries
      int s = io.getInt(); // starting node

      //We are done -> No more tests
      if(n == 0 && m == 0 && q == 0 && s == 0){
        break;
      }

      //Graph represented as a adjacency list
      Node[] graph = new Node[n];

      //Create graph
      for (int i = 0; i < n; i++) {
        graph[i] = new Node(i);
      }

      //Create Edges
      for (int i = 0; i < m; i++) {
        graph[io.getInt()].getAdjecentNodes().add(new Edge(graph[io.getInt()], io.getInt()));
      }

      //Run diijkstra on starting node
      Dijkstra.runDijkstra(graph, s);

      //For every query find distance
      for (int i = 0; i < q; i++) {
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
    io.close();
  }
}
