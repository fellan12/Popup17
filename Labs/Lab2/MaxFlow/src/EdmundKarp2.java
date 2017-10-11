import java.util.ArrayDeque;
import java.util.Arrays;

public class EdmundKarp2 {

  public static int[][] maxFlow(int[][] adjMatrix, int[][] capacity, int source, int tap) {
    int[][] flowMatrix = new int[capacity.length][capacity[0].length];
    while (true) {
      int diff = 0;
      int[] pred = new int[capacity.length];
      Arrays.fill(pred, -1);
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.push(source);
      int minFlow = Integer.MAX_VALUE;
      while(!q.isEmpty()) {
        int cur = q.poll();
        int i = 0;
        while (adjMatrix[cur][i] != -1) {
          int neighbor = adjMatrix[cur][i];
          if (pred[neighbor] == -1 && neighbor != source && capacity[cur][neighbor] > flowMatrix[cur][neighbor]) {
            int flowLeft = capacity[cur][neighbor] - flowMatrix[cur][neighbor];
            pred[neighbor] = cur;
            q.push(neighbor);
            if (flowLeft < minFlow)
              minFlow = flowLeft;
          }
          i++;
        }
      }
      if (pred[tap] == -1)
        break;
      int v = tap;
      while (v != source) {
        int u = pred[v];
        flowMatrix[u][v] += minFlow;
        flowMatrix[v][u] -= minFlow;
        v = u;
      }
    }
    return flowMatrix;
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
		int nodes = io.getInt();
		int edges = io.getInt();
		int source = io.getInt();
		int tap = io.getInt();
    int[][] capacity = new int[nodes][nodes];
    int[][] adjMatrix = new int[nodes][nodes];
    int[] neighborAmount = new int[nodes];
    for (int i = 0; i < nodes; i++)
      adjMatrix[i][0] = -1;

		for (int i = 0; i < edges; i++) {
			int from = io.getInt();
			int to = io.getInt();
			int cap = io.getInt();
			capacity[from][to] = cap;
      adjMatrix[from][neighborAmount[from]] = to;
      neighborAmount[from]++;
      adjMatrix[from][neighborAmount[from]] = -1;
		}
    StringBuilder sb = new StringBuilder();
    StringBuilder head = new StringBuilder();
    int[][] flowMatrix = maxFlow(adjMatrix, capacity, source, tap);
    int maxFlow = 0;
    int edgeAmount = 0;
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; adjMatrix[i][j] != -1; j++) {
        int n = adjMatrix[i][j];
        if (flowMatrix[i][n] > 0) {
          edgeAmount++;
          sb.append(i).append(" ").append(n).append(" ").append(flowMatrix[i][n]).append("\n");
          if (n == tap) {
            maxFlow += flowMatrix[i][n];
          }
        }
      }
    }
    head.append(nodes).append(" ").append(maxFlow).append(" ").append(edgeAmount);
    io.println(head.toString());
    io.print(sb.toString());
    io.close();
  }
}
