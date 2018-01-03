import java.util.*;

public class State {
    private int[] cupLimits;
    private int[] cups;

    /**
    * A state represented by a number of filled/unfilled cups and its limits
    */
    public State(int[] cups, int [] limits) {
        this.cups = cups;
        this.cupLimits = limits;
    }

    /**
    * Create and return connections to all other cups
    */
    public Map<State, Integer> generateNeighbors() {
        Map<State, Integer> neighbors = new HashMap<>();
        for (int i = 0; i < cups.length; i++) {
            for (int j = i + 1; j < cups.length; j++) {
                //Undirected edge
                createEdge(i, j, neighbors);
                createEdge(j, i, neighbors);
            }
        }
        return neighbors;
    }

    /**
    * Create edge from i to j
    * if cup[i] is not empty and cup[j] is not full
    */
    public void createEdge(int i, int j, Map<State, Integer> map) {
        if (cups[i] != 0 && cups[j] != cupLimits[j]) {
            int free = cupLimits[j] - cups[j];
            //New state -> amount left untill full createEdge i to j
            if (cups[i] > free) {
                map.put(pour(free, i, j), free);
            } else {
                map.put(pour(cups[i], i, j), cups[i]);
            }
        }
    }

    /**
    * Pour createEdge cup[i] to cup[j] and return the new state
    */
    public State pour(int amount, int i, int j) {
        int[] newMeasurements = Arrays.copyOf(cups, cups.length);
        newMeasurements[i] -= amount;
        newMeasurements[j] += amount;
        return new State(newMeasurements, cupLimits);
    }

    /**
    * Are we finished?
    */
    public boolean isGoal(int goal) {
        return this.cups[0] == goal;
    }

    @Override
    public boolean equals(Object o) {
        return Arrays.equals(this.cups, ((State)o).cups);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.cups);
    }

    @Override
    public String toString(){
      return Arrays.toString(cups);
    }
}
