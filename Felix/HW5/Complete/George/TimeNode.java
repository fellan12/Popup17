
import java.util.ArrayList;
import java.util.List;

public class TimeNode {
    private  int index;
    private List<TimeEdge> adjecentEdges;
    private int distance;
    private TimeNode previous;

    public TimeNode(int index) {
        this.index = index;
        this.adjecentEdges = new ArrayList<TimeEdge>();
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
    }

    public List<TimeEdge> getAdjecentNodes(){
      return adjecentEdges;
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

    public TimeNode getPreviousNode(){
      return previous;
    }

    public void setPrevious(TimeNode newPrevious){
      previous = newPrevious;
    }

    public void addAdjecent(TimeEdge t) {
    	this.adjecentEdges.add(t);
    }

    public String toString(){
      return ""+index;
    }
}
