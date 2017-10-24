import java.util.ArrayDeque;
import java.util.Arrays;

public class EdmundKarp {
  public static Result maxFlow(int[][] graph, int s, int t) {
    int numOfNodes = graph.length;
    int residual[][] = new int[numOfNodes][numOfNodes];

    for (int i = 0; i < numOfNodes; i++){
      for (int j = 0; j < numOfNodes; j++){
        residual[i][j] = graph[i][j];
      }
    }
    // This array is filled by BFS and to store path
    int pred[] = new int[numOfNodes];

    int flow = 0;  // There is no flow initially

    // Augment the flow while tere is path from source
    // to sink
    while (true) {

      boolean visited[] = new boolean[numOfNodes];
      Arrays.fill(visited, false);

      ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
      queue.add(s);
      visited[s] = true;
      pred[s]=-1;

      while (queue.size()!=0) {
        int i = queue.poll();

        for (int j = 0; j < numOfNodes; j++) {
          if (visited[j]==false && residual[i][j] > 0) {
            queue.add(j);
            pred[j] = i;
            visited[j] = true;
          }
        }
      }

      if(visited[t] != true){
        break;
      }

      int minFlow = Integer.MAX_VALUE;
      for (int j = t; j != s; j = pred[j]) {
        int i = pred[j];
        minFlow = Math.min(minFlow, residual[i][j]);
      }

      for (int j = t; j != s; j = pred[j]){
        int i = pred[j];
        residual[i][j] -= minFlow;
        residual[j][i] += minFlow;
      }

      flow += minFlow;
    }

    Result res = new Result(flow);
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[0].length; j++) {
        if(graph[i][j] - residual[i][j] > 0){
          res.addEdge(i + " " + j + " " + (graph[i][j] - residual[i][j]));
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int nodes = io.getInt();
    int edges = io.getInt();
    int source = io.getInt();
    int tap = io.getInt();
    int[][] graph = new int[nodes][nodes];

    for (int i = 0; i < edges; i++) {
      int from = io.getInt();
      int to = io.getInt();
      int cap = io.getInt();
      graph[from][to] = cap;
    }

    Result res = maxFlow(graph, source, tap);

    io.println(nodes + " " + res.getMaxFlow() + " " + res.getNumOfEdges());
    io.println(res.getEdges());
    io.close();
  }
}
