import java.util.*;

public class KitchenMeasurements {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int[] init = new int[n];
        int[] cupLimits = new int[n];
        for (int i = 0; i < n; i++) {
          cupLimits[i] = io.getInt();
        }
        int goal = io.getInt();
        init[0] = cupLimits[0];

        int pourAmount = calculatePourAmount(init, cupLimits, goal);
        io.println(pourAmount == -1 ? "impossible" : pourAmount);
        io.close();
    }

    /**
    * Calculate the smallest amount to pour to get the desired amount
    * on the largest cup
    * returns the smallest amount if it exists otherwise -1
    */
    public static int calculatePourAmount(int[] init, int[] cupLimits, int goal) {
        PriorityQueue<Node> nextToVisit = new PriorityQueue<>(new NodeComperator());
        Set<State> visitedStates = new HashSet<>();
        nextToVisit.add(new Node(new State(init, cupLimits)));
        while (!nextToVisit.isEmpty()) {
            Node currentNode = nextToVisit.poll();
            System.out.println("CURRENT: " + currentNode.getState() + " -> TOTAL COST: " + currentNode.getCost());

            //Is this node a goal node?
            if (currentNode.getState().isGoal(goal)) {
                System.out.println("GOAL: " + currentNode.getState() + " -> TOTAL COST: " + currentNode.getCost());
                return currentNode.getCost();
            }

            //Have we been here?
            if (visitedStates.contains(currentNode.getState())){
              System.out.println("SKIPPED: " + currentNode.getState() + " -> TOTAL COST: " + currentNode.getCost());
              continue; // Skip
            }

            //Add to visitedStates
            visitedStates.add(currentNode.getState());

            //Add all the current nodes neighbors to be visited next
            for (Map.Entry<State, Integer> entry : currentNode.getState().generateNeighbors().entrySet()) {
                System.out.print("NEXT?: " + entry.getKey());
                if (!visitedStates.contains(entry.getKey())) {
                    Node nextNode = new Node(entry.getKey(), currentNode, currentNode.getCost() + entry.getValue());
                    System.out.println(" --> ADDED" + " -> TOTAL COST: " + nextNode.getCost());
                    nextToVisit.add(nextNode);
                }else{
                  System.out.println();
                }
            }
        }
        // System.out.println("FOUND NO SOLUTION");
        return -1;
    }

    /**
    * Comparator that compares nodes by their cost
    */
    private static class NodeComperator implements Comparator<Node> {
  		@Override
  		public int compare(Node o1, Node o2) {
  			return Integer.compare(o1.getCost(), o2.getCost());
  		}
  	}
}
