import java.util.*;

public class Herkabe {

  static Queue<String> queue = new LinkedList<String>();
  static int res = 1;


  public static void main(String[] args) {
    Kattio io = new Kattio(System.in);
    int numOfNames = io.getInt();


    for (int i = 0; i < numOfNames; i++) {
      queue.add(io.getWord() + " ");
    }

    grouping(queue);

    System.out.println(res);

  }

  public static void grouping(Queue<String> queue){
    HashMap<Character, LinkedList<String>> newUniqe = new HashMap<Character, LinkedList<String>>();
    while (!queue.isEmpty()) {
      String name = queue.poll();
      if(newUniqe.get(name.charAt(0)) == null){
        newUniqe.put(name.charAt(0), new LinkedList<String>());
      }
      if(name.length() > 1){
        newUniqe.get(name.charAt(0)).add(name.substring(1, name.length()));
      }
    }

    // printMatrix(newUniqe);
    // System.out.println();

    res *= factorial(newUniqe.size());


    for (Map.Entry<Character, LinkedList<String>> entry : newUniqe.entrySet()) {
      grouping(entry.getValue());
    }
  }

  public static long factorial(int number) {
    long result = 1;

    for (int factor = 2; factor <= number; factor++) {
      result *= factor;
    }

    return result;
  }

  public static void printMatrix(HashMap<Character, LinkedList<String>> map){
    for (Map.Entry<Character, LinkedList<String>> entry : map.entrySet()) {
      System.out.println(entry.getKey()+" : "+entry.getValue());
    }
  }
}
