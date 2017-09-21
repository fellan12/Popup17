/**
* Author: Felix De Silva
**/


import java.util.*;

public class Node {
    private  int index;
    private List<Edge> adjecentNodes;
    private int distance;
    private Node previous;

    public Node(int index) {
        this.index = index;
        this.adjecentNodes = new ArrayList<Edge>();
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
    }

    public List<Edge> getAdjecentNodes(){
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

    public Node getPreviousNode(){
      return previous;
    }

    public void setPrevious(Node newPrevious){
      previous = newPrevious;
    }

}
