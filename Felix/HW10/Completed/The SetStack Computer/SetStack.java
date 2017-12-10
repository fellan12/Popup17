import java.util.*;

public class SetStack {

  Stack<Set<Object>> stack;
  Map<Set<Object>, Integer> map;  //keep track of the size of the sets

  public SetStack() {
      this.stack = new Stack<Set<Object>>();
      this.map = new HashMap<Set<Object>, Integer>();
  }

  public void push() {
      this.stack.push(new HashSet<Object>());
  }

  public void dup() {
      Set<Object> set = this.stack.pop();
      this.stack.push(set);
      this.stack.push(set);
  }

  public void union() {
      Set<Object> set1 = this.stack.pop();
      Set<Object> set2 = this.stack.pop();
      Set<Object> newSet = new HashSet<Object>();
      newSet.addAll(set1);
      newSet.addAll(set2);
      this.stack.push(newSet);
  }

  public void intersect() {
      Set<Object> set1 = this.stack.pop();
      Set<Object> set2 = this.stack.pop();
      Set<Object> newSet = new HashSet<Object>();
      newSet.addAll(set1);
      newSet.retainAll(set2);
      this.stack.push(newSet);
  }

  public void add() {
    Set<Object> set1 = this.stack.pop();
    Set<Object> set2 = this.stack.pop();
    Set<Object> newSet = new HashSet<Object>();

    if (map.containsKey(set1)) {
        newSet.add(map.get(set1));
    } else {
        Integer size = map.size();
        map.put(set1, size);
        newSet.add(size);
    }

    newSet.addAll(set2);
    this.stack.push(newSet);
  }

  public int size(){
    return stack.peek().size();
  }
}
