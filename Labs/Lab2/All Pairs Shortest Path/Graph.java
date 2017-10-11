import java.util.*;
/**
 * Authors: Felix De Silva & Martin Engelin
 */
public class Graph {
  private int nodeCount;
  private int edgeCount;
  private List<Edge> edgeList;
  private int[][] distMatrix;
  private int[][] weightMatrix;

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

  public void setDistMatrix(int[][] matrix){
    this.distMatrix = matrix;
  }

  public int[][] getDistMatrix(){
    return distMatrix;
  }

  public int getDist(int x, int y){
    return distMatrix[x][y];
  }

  public void setDist(int x, int y, int value){
    distMatrix[x][y] = value;
  }

  public int getNodeCount(){
    return nodeCount;
  }

  public int getEdgeCount(){
    return edgeCount;
  }
}
