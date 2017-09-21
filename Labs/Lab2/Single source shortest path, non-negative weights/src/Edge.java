
public class Edge {

    private Node endNode;
    private int weight;

    public Edge(Node endNode, int weight) {
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
