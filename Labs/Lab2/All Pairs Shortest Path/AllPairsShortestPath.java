import java.util.*;
/**
 * Authors: Felix De Silva & Martin Engelin
 * Class containing the main method for solving the problem in Kattis.
 */
public class AllPairsShortestPath {

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);

    while(io.hasMoreTokens()){
      int n = io.getInt();
      int e = io.getInt();
      int q = io.getInt();

      int[][] distMatrix = new int[n][n];

      for (int[] arr : distMatrix) {
        Arrays.fill(arr, Integer.MAX_VALUE);
      }

      List<Edge> edges = new ArrayList<Edge>();
      for (int i = 0; i < e; i++) {
        int u = io.getInt();
        int v = io.getInt();
        int weight = io.getInt();
        edges.add(new Edge(u,v,weight));
      }

      Graph graph = new Graph(n, e);
      graph.setDistMatrix(distMatrix);
      graph.setEdgeList(edges);

      distMatrix = FloydWarshall.floydWarshall(graph);

      for (int i = 0; i < q; i++) {
        int u = io.getInt();
        int v = io.getInt();
        int value = distMatrix[u][v];
        if (value == Integer.MAX_VALUE) {
          io.println("Impossible");
        } else if (value == Integer.MIN_VALUE){
          io.println("-Infinity");
        } else {
          io.println(value);
        }
      }
      io.println();
    }
    io.close();
  }
}
