import java.util.*;

public class FenwickTree {


    private long[] tree;

    public FenwickTree(int n) {
        this.tree = new long[n+1];
    }

    public long sum(int index) {
        long result = 0;
        for (int i = index; i > 0; i -= (i & -i)) {
            result += tree[i];
        }
        return result;
    }

    public void add(int index, long delta) {
        for (int i = index; i < tree.length; i += (i &-i)) {
            tree[i] += delta;
        }
    }

    public long sumRange(int left, int right) {
        return sum(right) - sum(left - 1);
    }

    public String printTree(){
      return "TREE: "+ Arrays.toString(tree);
    }
}
