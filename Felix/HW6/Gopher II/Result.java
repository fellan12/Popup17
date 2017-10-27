import java.util.*;

public class Result {
    private int maxflow;
    private StringBuilder edges;
    private int numOfEdges;

    public Result(int flow){
      this.maxflow = flow;
      this.edges = new StringBuilder();
    }

    public void addEdge(String edge){
      edges.append(edge).append("\n");
      numOfEdges++;
    }

    public String getEdges(){
      return edges.toString().trim();
    }

    public int getMaxFlow(){
      return maxflow;
    }

    public int getNumOfEdges(){
      return numOfEdges;
    }
}
