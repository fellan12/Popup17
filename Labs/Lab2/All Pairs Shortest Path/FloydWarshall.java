/**
 * Authors: Felix De Silva & Martin Engelin
 * Uses the Floyd Warshall algorithm to solve the All Pairs Shortest Path problem.
 */
public class FloydWarshall {
  /**
   * Given a graph returns a matrix containing the length of the shortest path
   * in between every vertix in the graph.
   * Two Vertices that cannot reach each other is represented in the graph with
   * value Integer.MAX_VALUE.
   * Any node reachable from a negative cycle is represented in the graph with
   * value Integer.MIN_VALUE.
   */
  public static int[][] floydWarshall(Graph graph) {
    //Initialize
    for(int i = 0; i < graph.getNodeCount(); i++) {
      graph.setDist(i,i,0);
    }
    for (Edge e : graph.getEdgeList() ) {
      //No egde to itself
      //Replace if current value if larger
      if(e.getStartNode() != e.getEndNode() && graph.getDist(e.getStartNode(), e.getEndNode()) > e.getWeight()){
        graph.setDist(e.getStartNode(), e.getEndNode(), e.getWeight());
      }
    }

    //Floyd-Warshall Algorithm
    for(int k = 0; k < graph.getNodeCount(); k++) {
      for(int i = 0; i < graph.getNodeCount(); i++) {
        for (int j = 0; j < graph.getNodeCount(); j++) {
          if(graph.getDist(i,j) > graph.getDist(i,k) + graph.getDist(k,j)){
            if (graph.getDist(i,k) == Integer.MAX_VALUE || graph.getDist(k,j) == Integer.MAX_VALUE) {
              continue; // Skip check
            }
            graph.setDist(i,j, graph.getDist(i,k) + graph.getDist(k,j));
          }
        }
      }
    }

    //Detect Negative Cycles
    for(int i = 0; i < graph.getNodeCount(); i++){
      for(int j = 0; j < graph.getNodeCount(); j++){
        for(int k = 0; graph.getDist(i,j) != Integer.MIN_VALUE && k < graph.getNodeCount(); k++){
          if( graph.getDist(i,k) != Integer.MAX_VALUE &&  // path from i to k?
              graph.getDist(k,j) != Integer.MAX_VALUE &&  // path from k to j?
              graph.getDist(k,k) < 0){                    // path k to k negative?
              graph.setDist(i,j, Integer.MIN_VALUE);      // path from i to k is wrong
          }
        }
      }
    }
    return graph.getDistMatrix();
  }
}
