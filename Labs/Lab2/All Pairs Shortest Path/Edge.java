/**
 * Authors: Felix De Silva & Martin Engelin
 */
public class Edge {
  private int startNode;
  private int endNode;
  private int weight;

  public Edge(int startNode, int endNode, int weight) {
    this.startNode = startNode;
    this.endNode = endNode;
    this.weight = weight;
  }

  public int getStartNode(){
    return startNode;
  }

  public int getEndNode(){
    return endNode;
  }

  public int getWeight(){
    return weight;
  }

  @Override
  public String toString(){
    return startNode+"-"+endNode+"("+weight+")";
  }


}
