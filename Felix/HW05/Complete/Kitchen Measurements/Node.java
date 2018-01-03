
public class Node {
    private State state;
    private Node parent;
    private int cost;

    /**
    * Custom node with state, parent and cost speficied
    */
    public Node(State state, Node parent, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    /**
    * Default node with cost 0
    */
    public Node(State state) {
        this(state, null, 0);
    }

    public State getState() {
        return this.state;
    }

    public Node getParent() {
        return this.parent;
    }

    public int getCost() {
        return this.cost;
    }
}
