import java.util.Arrays;
import java.util.HashMap;


public class Turbo {

    static Kattio io = new Kattio(System.in, System.out);
    static boolean debug = false;

    public static void main(String[] args) {

        int n = io.getInt();
        FenwickTree tree = new FenwickTree(n);
        int[] numPos = new int[n+1];
        for (int i = 1; i <= n; i++) {  //O(n)
            int num = io.getInt();
            numPos[num] = i;                                            //A numbers position
            tree.add(i, 1); //O(log(n))                                 //For every number att 1 to the tree
        }

        // debug(tree.printTree());

        //iterate though in the following fashion
        // 1, n, 2, n-1, 3, n-2, .....
        int left = 1;                                                       // Counts up
        int right = n;                                                      // Counts down
        for (int i = 1; i <= n; i++) {  //O(n)
            if (i % 2 == 1) { // odd number
                tree.add(numPos[left], -1);
                io.println(tree.sum(numPos[left])); // O(log(n))            // print moves for value
                left++;                                                     // one number up from the left
            } else { // even number
                tree.add(numPos[right], -1);
                io.println(tree.sumRange(numPos[right], n));  //O(2log(2))  // print moves for value
                right--;                                                    // one number down from the right
            }
        }

        io.close();
    }

    private static void debug(String str){
      if(debug){
        System.err.println(str);
      }
    }
}
