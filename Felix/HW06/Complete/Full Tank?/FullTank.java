import java.util.*;

public class FullTank {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cities = io.getInt();			//Number of Nodes
		int roads = io.getInt();			//Number of Edges

		int[] prices = new int[cities];
		for(int i = 0; i < prices.length; i++) {
			prices[i] = io.getInt();		//Fuel price at city i
		}

		//Initialize Edge matrix
		ArrayList<ArrayList<Edge>> edgeMatrix = new ArrayList<ArrayList<Edge>>();
		for(int i = 0; i < cities; i++) {
			edgeMatrix.add(new ArrayList<Edge>());
		}

		for(int i = 0; i < roads; i++) {
			int u = io.getInt();
			int v = io.getInt();
			int dist = io.getInt();
			edgeMatrix.get(u).add(new Edge(u, v, dist));
			edgeMatrix.get(v).add(new Edge(v, u, dist));
		}

		int queries = io.getInt();
		for(int i = 0; i < queries; i++) {
			int capacity = io.getInt();
			int start = io.getInt();
			int end = io.getInt();
			int cheapestTrip = Dijkstra.getCheapestTrip(edgeMatrix, prices, capacity, start, end);
			io.println((cheapestTrip == -1) ? "Impossible" : cheapestTrip);
		}
		io.close();
	}
}
