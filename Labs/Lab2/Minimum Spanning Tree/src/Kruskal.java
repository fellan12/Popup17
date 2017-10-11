import java.util.*;
/**
 * Authors: Felix De Silva & Martin Engelin
 *
 * Contains a static method for solving the Minimum Spanning Tree problem
 */
public class Kruskal {

  /**
   * Returns a minimum spanning tree from the argument graph, using
   * the Kruskal Algorithm.
   *
   * If no minimum spanning tree can be found, returns null.
   */
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
    boolean correct = false;
    for (int s : nodesSet.getSetsSizes()) {
      if(s == g.getNodeCount()){
        correct = true;
        break;
      }
    }

    if(correct){
      Collections.sort(tree, new LexicographicalComperator());
    } else {
      tree = null;
    }
    return tree;
  }

  /**
   * Comparator that sorts Edges according to their weights.
   */
  private static class WeightComperator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return Integer.compare(o1.getWeight(), o2.getWeight());
		}
	}

  /**
   * Comparator that sorts Edges lexilogically.
   */
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
