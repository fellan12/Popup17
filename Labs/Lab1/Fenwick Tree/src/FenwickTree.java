import java.util.*;

/*
* A binary index tree that easily can sum up ranges
* of elements from 1 to n
*/

public class FenwickTree {
  static Kattio io = new Kattio(System.in, System.out);
  static long[] binaryIndexTree;

  public static void main(String[] args) {
    int numOfElements = io.getInt();
    binaryIndexTree = new long[numOfElements+1];

    long numOfOperations = io.getLong();
    for (long i = 0; i < numOfOperations; i++) {
      if(io.getWord().equals("+")){
        add(io.getInt(), io.getLong());
      }else{
        io.println(sum(io.getInt()));
      }
    }
    io.close();
  }

  /*
  *   Constructor for the FenwickTree
  */
  public FenwickTree(int num){
    binaryIndexTree = new long[num+1];
  }

  /*
  * Add delta to value as index i
  * and add delta to the rest that is to be updated
  */
  public static void add(int i, long delta){
    i++;
    while(i < binaryIndexTree.length){
      binaryIndexTree[i] += delta;
      // To get next number to add delta
      // i AND -i
      // binary(i) AND-op 2compBinary(i)
      i += (i & -i);
    }
  }

  /*
  * Sum up the from i to 0 from the binaryIndexTree
  */
  public static long sum(int i){
    //Empty set
    if(i == 0){
      return 0;
    }
    long res = binaryIndexTree[0];
    while(i > 0){
      res += binaryIndexTree[i];
      // To get next number to sum up
      // i AND -i
      // binary(i) AND-op 2compBinary(i)
      i -= (i & -i);
    }
    return res;
  }
}
