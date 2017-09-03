import java.util.*;
import java.util.stream.Collectors;


public class spiderman3 {

  static Kattio io = new Kattio(System.in, System.out);
  static int maxInt = 9999;

  public static void main(String [] args) {
    int numOfCases = io.getInt();
    for (int a = 0; a < numOfCases ;a++ ) {
      int numOfDist = io.getInt();
      Queue<Node> childQueue = new LinkedList<Node>();
      Queue<Integer> distQueue = new LinkedList<Integer>();
      Node floor = new Node(null);
try{
      int isEven = 0;
      for(int i = 0; i < numOfDist; i++){
        int value = io.getInt();
        distQueue.add(value);
        isEven += value;
      }

      //If not even, there is no solution
      if(isEven % 2 == 1 || isEven > 1000 || distQueue.size() != numOfDist){
        io.println("IMPOSSIBLE");
        continue;
      }

      //Floor node
      // Node floor = new Node(null);
      floor.setId(distQueue.poll());
      floor.setPeak(floor.getId());
      floor.addToPath("U");

      //Floor children
      int value = distQueue.poll();
      childQueue.add(addChild(floor, floor.getId() + value, "U"));
      childQueue.add(addChild(floor, floor.getId() - value, "D"));
}catch(RuntimeException e) {
}

      //childrens children
      for(int i = 0; i < numOfDist-2; i++){
        int dist = distQueue.poll();
        ArrayList<Node> newChild = new ArrayList<Node>();
        while(!childQueue.isEmpty()){
          Node node = childQueue.poll();
          if(node.getId() + dist <= 1000){                             //NOTE at most 1000
            newChild.add(addChild(node, node.getId() + dist, "U"));
          }else{
            newChild.add(addChild(node, maxInt, "U"));
          }
          if(node.getId() - dist >= 0) {
            newChild.add(addChild(node, node.getId() - dist, "D"));
          }else{
            newChild.add(addChild(node, -maxInt, "D"));
          }
        }
        childQueue.addAll(newChild);
      }

      // System.out.println("FLOOR");
      // printTree(floor, " ");
      //
      // for (Node node : floor.getLeafNodes()) {
      //   System.out.println(node.getId() + " " + node.getPath());
      // }

      List<Node> leafs = floor.getLeafNodes().stream().filter(s -> s.getId() == 0)
                             .collect(Collectors.toList());
      //
      // System.out.println("PATH:");
      // for (Node node : Leafs) {
      //   System.out.println(node.getPath() + "->" + node.getPeak());
      // }

      //Find path
      if(leafs.isEmpty()){
        io.println("IMPOSSIBLE");
      }else{
        Node res = leafs.get(0);
        for (int i = 0; i < leafs.size(); i++) {
          if (res.getPeak() > leafs.get(i).getPeak()){
            res = leafs.get(i);
          }
        }
        io.println(res.getPath());
      }
    }

    io.flush();
    io.close();
  }

  private static Node addChild(Node parent, int id, String way) {
    Node node = new Node(parent);
    node.setId(id);
    node.setPeak(id);
    node.addToPath(parent.getPath() + way);
    parent.getChildren().add(node);
    return node;
  }

  private static void printTree(Node node, String appender) {
    System.out.println(appender + node.getId() + "(" + node.getPath() + ")" + "->" + node.getPeak());
    for (Node each : node.getChildren()) {
      printTree(each, appender + appender);
    }
  }

}
