import java.util.*;

public class Graph {
  private int nodeCount;
  private int edgeCount;
  private List<Edge> edgeList;
  private List<Node> nodeList;

  public Graph(int n, int e){
    this.nodeCount = n;
    this.edgeCount = e;
  }

  public void setEdgeList(List<Edge> eList){
    this.edgeList = eList;
  }

  public void setNodeList(List<Node> nList){
    this.nodeList = nList;
  }

  public List<Edge> getEdgeList(){
    return edgeList;
  }

  public List<Node> getNodeList(){
    return nodeList;
  }

  public int getNodeCount(){
    return nodeCount;
  }

  public int getEdgeCount(){
    return edgeCount;
  }
}
