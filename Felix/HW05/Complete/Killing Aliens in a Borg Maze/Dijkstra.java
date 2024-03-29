/**
 * Author: Felix De Silva & Martin Engelin
 **/

import java.util.*;

public class Dijkstra {

	public static void dijkstra(Node startNode) {
		Node start = startNode;
		start.setDistance(0);


		PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComperator());
		queue.add(start);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (Edge edge: node.getAdjecentNodes()) {
				int distance = node.getDistance() + edge.getWeight();

				//new distance is smaller that end's current distance
				Node end = edge.getEndNode();
				if (distance < end.getDistance()) {
					queue.remove(end);          //remove endNode
					end.setDistance(distance);
					end.setPrevious(node);
					queue.add(end);             //Add updated endNode
				}
			}
		}
	}

	private static class NodeComperator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return Integer.compare(o1.getDistance(), o2.getDistance());
		}
	}
}
