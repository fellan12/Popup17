/**
* Author: Felix De Silva & Martin Engelin
**/

import java.util.*;

public class Node {
  private int index;
  private List<Edge> adjecentNodes;
  private int distance;
  private Node previous;
  private boolean alien = false;
  private boolean start = false;

  public Node(int index) {
    this.index = index;
    this.adjecentNodes = new ArrayList<Edge>();
    this.distance = Integer.MAX_VALUE;
    this.previous = null;
  }

  public List<Edge> getAdjecentNodes(){
    return adjecentNodes;
  }

  public void setAdjecentNodes(List<Edge> list) {
    this.adjecentNodes = list;
  }

  public int getIndex(){
    return index;
  }

  public void setIndex(int index){
    this.index = index;
  }

  public Node copy(){
    Node n = new Node(this.index);
    n.setAdjecentNodes(new ArrayList<Edge>(this.adjecentNodes));
    n.setAlien(this.alien);
    n.setStart(this.start);
    n.setDistance(this.distance);
    n.setPrevious(this.previous);
    return n;
  }

  public void setAlien(boolean alien){
    this.alien = alien;
  }

  public boolean getAlien(){
    return alien;
  }

  public void setStart(boolean start){
    this.start = start;
  }

  public boolean getStart(){
    return start;
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

  public void addEdge(Edge t) {
    if(!this.adjecentNodes.contains(t)){
      this.adjecentNodes.add(t);
    }
  }

  @Override
  public boolean equals(Object o){
    if(o instanceof Node){
      Node n = (Node) o;
      return this.index == n.getIndex() &&
              this.distance == n.getDistance() &&
              this.adjecentNodes.equals(n.getAdjecentNodes());
    }
    return false;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    for (Edge e : this.adjecentNodes) {
      sb.append(getIndex() + "(" + getAlien() + ") - " + e.getEndNode().getIndex() + "("+ e.getEndNode().getAlien() + ")\n");
    }
    return sb.toString();
  }
}
