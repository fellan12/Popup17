
public class Node implements Comparable<Node> {
  private State state;
  public int moveCount;

  public Node(State state, int moveCount) {
    this.state = state;
    this.moveCount = moveCount;
  }

  /**
  * Compare factor represent the amount of moved pieces
  * added with number of wrongly places pieces
  *
  * Is used when sorting nodes to get the node with the lowest of moved
  * pieces and minimal wrongly placed pieces
  */
  public int getCompareFactor(){
    return this.moveCount + this.state.getWronglyPlacedCount();
  }

  public State getState() {
    return this.state;
  }

  public int getMoveCount(){
    return this.moveCount;
  }

  @Override
  public int compareTo(Node other) {
    return this.moveCount - other.getCompareFactor();
  }

  @Override
  public String toString(){
    return "MoveCount: " + this.moveCount +
            "\nCompareFactor: " + getCompareFactor() +
            "\nState\n" + this.state;
  }
}
