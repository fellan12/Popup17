
public class Edge {
    private Node startNode;
    private Node endNode;
    private int weight;

    public Edge(Node startNode, Node endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public Node getStartNode(){
      return startNode;
    }

    public Node getEndNode(){
      return endNode;
    }

    public int getWeight(){
      return weight;
    }

    @Override
    public boolean equals(Object other){
      Edge e = ((Edge) other);
      return this.endNode.equals(e.endNode) && this.weight == e.getWeight();
    }

    @Override
    public String toString(){
      return startNode.getIndex() + " -(" + weight + ")- " + endNode.getIndex();
    }
}
