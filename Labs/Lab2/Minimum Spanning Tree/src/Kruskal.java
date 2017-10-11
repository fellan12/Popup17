import java.util.*;

public class Kruskal {

  public static List<Edge> kruskal(Graph g){

    UnionFind nodesSet = new UnionFind(g.getNodeCount());
    List<Edge> edgeList = g.getEdgeList();
    List<Edge> tree = new ArrayList<Edge>();

    Collections.sort(edgeList, new WeightComperator());
    //Kruskal's Algorithm
    for (Edge e : edgeList) {
      if(!nodesSet.same(e.getStartNode(), e.getEndNode())){
        nodesSet.union(e.getStartNode(), e.getEndNode());
        tree.add(e);
      }
    }

    //If number of sets != 1 -> no MST
    boolean corrent = false;
    for (int s : nodesSet.getSetsSizes()) {
      if(s == g.getNodeCount()){
        corrent = true;
        break;
      }
    }

    if(corrent){
      Collections.sort(tree, new LexicographicalComperator());
    } else {
      tree = null;
    }

    return tree;
  }

  private static class WeightComperator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return Integer.compare(o1.getWeight(), o2.getWeight());
		}
	}

  private static class LexicographicalComperator implements Comparator<Edge> {
    @Override
    public int compare(Edge o1, Edge o2) {
      int r = Integer.compare(o1.getStartNode(), o2.getStartNode());
      if(r == 0){
        return Integer.compare(o1.getEndNode(), o2.getEndNode());
      }
      return r;
    }
  }
}
