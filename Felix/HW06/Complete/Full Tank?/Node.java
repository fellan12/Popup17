import java.util.*;

class Node implements Comparable<Node>{
	private int index;
	private int fuelLevel;
	private int minCost;

	Node(int index, int fuelLevel, int minimalCost) {
		this.index = index;
		this.fuelLevel = fuelLevel;
		this.minCost = minimalCost;
	}

	public int getIndex(){
		return index;
	}

	public int getFuelLevel(){
		return fuelLevel;
	}

	public int getMinCost(){
		return minCost;
	}

	@Override
	public int compareTo(Node other) {
		if(minCost == other.minCost){
			return minCost - other.minCost;
		} else if (minCost - other.minCost < 0){
			return -1;
		}
		return 1;
	}

	@Override
  public boolean equals(Object o){
    if(o instanceof Node){
      Node c = (Node)o;
      return index == c.getIndex();
    }
    return false;
  }

	@Override
	public String toString(){
		return index + " (" + fuelLevel +  ")";
	}
}
