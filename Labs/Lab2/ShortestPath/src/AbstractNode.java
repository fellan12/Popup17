package dijkstra;
/**
* Author: Felix De Silva
**/


import java.util.*;

public abstract class AbstractNode<T> {
    private  int index;
    private List<T> adjecentNodes;
    private int distance;
    private AbstractNode<T> previous;

    public AbstractNode(int index) {
        this.index = index;
        this.adjecentNodes = new ArrayList<T>();
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
    }

    public List<T> getAdjecentNodes(){
      return adjecentNodes;
    }

    public int getIndex(){
      return index;
    }

    public int getDistance(){
      return distance;
    }

    public void setDistance(int newDistance){
      distance = newDistance;
    }

    public AbstractNode<T> getPreviousNode(){
      return previous;
    }

    public void setPrevious(AbstractNode<T> newPrevious){
      previous = newPrevious;
    }

    public void addEdge(T t) {
    	this.adjecentNodes.add(t);
    }
}
