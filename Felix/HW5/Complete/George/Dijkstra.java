/**
 * Author: Felix De Silva & Martin Engelin
 **/

import java.util.*;

public class Dijkstra {

	public static void dijkstraTimeLimit(TimeNode[] graph, int startNode, int startTime) {
		TimeNode start = graph[startNode];
		start.setDistance(startTime);

		PriorityQueue<TimeNode> queue = new PriorityQueue<TimeNode>(new TimeNodeComperator());
		queue.add(start);
		while (!queue.isEmpty()) {
			TimeNode node = queue.poll();
			for (TimeEdge edge: node.getAdjecentNodes()) {
				int currentTime = node.getDistance();
				int distance = currentTime + edge.getWaitTime(currentTime) + edge.getTime();
				System.out.println("CURR: " + edge);
				System.out.println("CURRTIME: " + currentTime);
				System.out.println("WAITTIME: " + edge.getWaitTime(currentTime));
				System.out.println("CURRDIST: " + distance);
				TimeNode end = edge.getEndNode();
				if (distance < end.getDistance()) {
					System.out.println("ADDED");
					queue.remove(end);
					end.setDistance(distance);
					end.setPrevious(node);
					queue.add(end);
				}
				System.out.println();
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
