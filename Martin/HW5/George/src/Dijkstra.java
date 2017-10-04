/**
 * Author: Felix De Silva & Martin Engelin
 **/

import java.util.*;

public class Dijkstra {

	public static void dijkstraTimeLimit(TimeNode[] graph, int startNode, int startTime) {
		TimeNode start = graph[startNode];
		start.setDistance(startTime+1);

		PriorityQueue<TimeNode> queue = new PriorityQueue<TimeNode>(new TimeNodeComperator());
		queue.add(start);
		while (!queue.isEmpty()) {
			TimeNode node = queue.poll();
			for (TimeEdge edge: node.getAdjecentNodes()) {
				int currentTime = node.getDistance();
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

	private static class TimeNodeComperator implements Comparator<TimeNode> {
		@Override
		public int compare(TimeNode o1, TimeNode o2) {
			return Integer.compare(o1.getDistance(), o2.getDistance());
		}
	}
}
