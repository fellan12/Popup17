import java.util.*;

public class MinimumSpanningTree {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
		while (io.hasMoreTokens()) {
			int nodes = io.getInt();
			int edges = io.getInt();

			if(nodes == 0){
				break;
			} else if (edges == 0){
        io.println("Impossible");
        continue;
      }

      //Create all edges
      List<Edge> edgeList = new ArrayList<Edge>();
  		for (int i = 0; i < edges; i++) {
        int u = io.getInt();
        int v = io.getInt();
        if(u <= v){
          edgeList.add(new Edge(u, v, io.getInt()));
        } else {
          edgeList.add(new Edge(v, u, io.getInt()));
        }
  		}

      //Create graph
      Graph graph = new Graph(nodes, edges);
      graph.setEdgeList(edgeList);

      //Run kruskal on graph
      List<Edge> mstList = Kruskal.kruskal(graph);

      //Were there a MST?
      if(mstList == null){
        io.println("Impossible");
        continue;
      }

      //Print output
      StringBuilder sb = new StringBuilder();
      long totalCost = 0;
      for (Edge e : mstList) {
        sb.append(e.getStartNode() + " " + e.getEndNode() + "\n");
        totalCost += e.getWeight();
      }
      io.println(totalCost);
      io.println(sb.toString().trim());

		}
		io.close();
	}

}
