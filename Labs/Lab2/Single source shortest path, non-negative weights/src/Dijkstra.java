
import java.util.*;

public class Dijkstra {

  public static void runDijkstra(Node[] graph, int startNode) {
    Node start = graph[startNode];
    start.setDistance(0);

    PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComperator());
    queue.add(start);

    while (!queue.isEmpty()) {

      Node node = queue.poll();

      for (Edge edge: node.getAdjecentNodes()) {
        Node end = edge.getEndNode();
        int distance = node.getDistance() + edge.getWeight();

        if (distance < end.getDistance()) {
          queue.remove(end);          //remove endNode
          end.setDistance(distance);
          end.setPrevious(node);
          queue.add(end);             //Add updated endNode
        }
      }
    }
  }

  /**
  * As you like it martin <3
  */
  private static class NodeComperator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
      return Integer.compare(o1.getDistance(), o2.getDistance());
    }

  }
}
