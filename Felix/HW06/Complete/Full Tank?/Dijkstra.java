import java.util.*;

public class Dijkstra {

  public static int getCheapestTrip(ArrayList<ArrayList<Edge>> graph, int[] prices, int capacity, int start, int end) {
		int[][] costTable = new int[graph.size()][capacity+1];
		for(int[] row : costTable) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		costTable[start][0] = 0;
		pq.offer(new Node(start,0,0));

		while(pq.size() != 0) {
			Node node = pq.poll();

      //We are already at the end
			if(node.getIndex() == end) {
				return node.getMinCost();
			}

      //For alla neighbors, check is you can travel
      for(int i = 0; i < graph.get(node.getIndex()).size(); i++) {
				Edge e = graph.get(node.getIndex()).get(i);

        //Have we enough fuel to travel
        if(node.getFuelLevel() >= e.getFuelCost()) {
					int fuelAfter = node.getFuelLevel() - e.getFuelCost();

          //Fuel travel cost after is less
          if(costTable[e.getEndNode()][fuelAfter] > node.getMinCost()) {
						costTable[e.getEndNode()][fuelAfter] = node.getMinCost();
						pq.offer(new Node(e.getEndNode(), fuelAfter, node.getMinCost()));
					}
				}
			}

      //Fuel level is less that max capacity
      //Add one more fuel
			if(node.getFuelLevel() < capacity) {
				int addFuelCost =  node.getMinCost() + prices[node.getIndex()];

        //Fuel travel cost before is less
        if(costTable[node.getIndex()][node.getFuelLevel()+1] > addFuelCost) {
					costTable[node.getIndex()][node.getFuelLevel()+1] = addFuelCost;
					pq.offer(new Node(node.getIndex(), node.getFuelLevel()+1, addFuelCost));
				}
			}
		}
		return -1;
	}

  public static void printMatrix(int[][] m){
    for (int[] r : m) {
      for (int i : r) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
