/*
* All the elements are trees with a root that first points to itself
* When merged the larger one gets the smallest one's elements inc. itself
* and the smallest one's new root is the larger one
*/

public class UnionFind {

    static Kattio io = new Kattio(System.in, System.out);
    static int[] roots;
    static int[] treeSizes;

    public static void main(String[] args) {
        int elements = io.getInt();
        roots = new int[elements];
        treeSizes = new int[elements];
        for (int i = 0; i < elements; i++) {
            roots[i] = i;
            treeSizes[i] = 1;
        }
        int numOfOperations = io.getInt();
        for (int i = 0; i < numOfOperations; i++) {
            String operator = io.getWord();
            String a = io.getInt();
            String b = io.getInt();
            if (operator.equals("=")) {
                //merge a and b
                union(a, b);
            } else {
                //are a and b in the same group?
                io.println(same(a, b) ? "yes" : "no");
            }
        }
        io.close();
    }

    /**
     * Merges groups which elements A and B belong to
     */
    public static void union(int elementA, int elementB) {
        int a = findGroup(elementA);
        int b = findGroup(elementB);

        //If a is smaller than b
        //Merge a's tree to b's tree
        if (treeSizes[a] < treeSizes[b]) {
            merge(a, b);
        } else {
            merge(b, a);
        }
    }

    /*
    * Merge b to a
    * Where a is smaler than b
    * Thus b is now the root for a
    */
    public static void merge(int a, int b) {
        roots[a] = b;
        treeSizes[b] += treeSizes[a];
    }

    /*
     * Find the element's group
     */
    public static int findGroup(int element) {
        int root = element;
        while (root != roots[root]) {
            // traverse upward
            root = roots[root];
        }
        return root;
    }

    /*
    * Check if the two element belong to the same group
    */
    public static boolean same(int elementA, int elementB) {
        return findGroup(elementA) == findGroup(elementB);
    }
}
