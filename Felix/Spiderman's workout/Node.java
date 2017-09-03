import java.util.ArrayList;
import java.util.List;

public class Node {
 private int id = -1;
 private final List<Node> children = new ArrayList<>();
 private final Node parent;
 private String path = "";
 private int peak = -1;

 public Node(Node parent) {
  this.parent = parent;
  if(parent != null){
    peak = parent.getPeak();
  }
 }

 public int getId() {
  return id;
 }

 public String getPath(){
   return path;
 }

 public void setId(int id) {
  this.id = id;
 }

 public void setPeak(int newPeak){
   if(peak < newPeak){
      peak = newPeak;
   }
 }

 public int getPeak(){
   return peak;
 }

 public void addToPath(String way){
   path += way;
 }

 public List<Node> getChildren() {
  return children;
 }

 public List<Node> getLeafNodes() {
    List<Node> leafNodes = new ArrayList<Node>();
    if (children.isEmpty()) {
        leafNodes.add(this);
    } else {
        for (Node child : this.children) {
            leafNodes.addAll(child.getLeafNodes());
        }
    }
    return leafNodes;
}

 public Node getParent() {
  return parent;
 }

}
