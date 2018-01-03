import java.util.*;

public class Edge {
  private int start;
	private int end;
	private int fuelCost;

	Edge(int start, int end, int cost) {
    this.start = start;
		this.end = end;
		this.fuelCost = cost;
	}

  public int getStartNode(){
    return start;
  }

  public int getEndNode(){
    return end;
  }

  public int getFuelCost(){
    return fuelCost;
  }

  @Override
  public String toString(){
    return start + " - (" + fuelCost + ") - " + end;
  }
}
