package dijkstra;
/**
* Author: Felix De Silva
**/

public class NonNegEdge {

    private Node endNode;
    private int weight;

    public NonNegEdge(Node endNode, int weight) {
        this.endNode = endNode;
        this.weight = weight;
    }

    public Node getEndNode(){
      return endNode;
    }

    public int getWeight(){
      return weight;
    }
}
