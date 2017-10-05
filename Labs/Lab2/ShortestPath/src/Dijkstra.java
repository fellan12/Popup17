/**
 * Author: Felix De Silva & Martin Engelin
 **/

import java.util.*;

public class Dijkstra {

	public static void dijkstra(Node[] graph, int startNode) {
		Node start = graph[startNode];
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

	public static void dijkstraTimeLimit(TimeNode[] graph, int startNode) {
		TimeNode start = graph[startNode];
		start.setDistance(0);

		PriorityQueue<TimeNode> queue = new PriorityQueue<TimeNode>(new TimeNodeComperator());
		queue.add(start);
		while (!queue.isEmpty()) {
			TimeNode node = queue.poll();
			for (TimeEdge edge: node.getAdjecentNodes()) {
				int currentTime = node.getDistance();
				if (!edge.isWalkable(currentTime))
					continue;
				int distance = currentTime + edge.getWaitTime(currentTime) + edge.getTime();
				TimeNode end = edge.getEndNode();
				if (distance < end.getDistance()) {
					queue.remove(end);
					end.setDistance(distance);
					end.setPrevious(node);
					queue.add(end);
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

	private static class TimeNodeComperator implements Comparator<TimeNode> {
		@Override
		public int compare(TimeNode o1, TimeNode o2) {
			return Integer.compare(o1.getDistance(), o2.getDistance());
		}
	}
}
