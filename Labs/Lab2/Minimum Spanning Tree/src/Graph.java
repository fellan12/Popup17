import java.util.*;

public class Graph {
  private int nodeCount;
  private int edgeCount;
  private List<Edge> edgeList;

  public Graph(int n, int e){
    this.nodeCount = n;
    this.edgeCount = e;
  }

  public void setEdgeList(List<Edge> eList){
    this.edgeList = eList;
  }

  public List<Edge> getEdgeList(){
    return edgeList;
  }

  public int getNodeCount(){
    return nodeCount;
  }

  public int getEdgeCount(){
    return edgeCount;
  }
}
