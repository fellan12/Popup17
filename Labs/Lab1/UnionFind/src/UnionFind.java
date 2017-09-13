/*
* All the elements are trees with a root that first points to itself
* When merged the larger one gets the smallest one's elements inc. itself
* and the smallest one's new root is the larger one
*/

public class UnionFind {

    private Kattio io = new Kattio(System.in);
    private int[] roots;
    private int[] treeSizes;
    
    public void solve() {
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
            int a = io.getInt();
            int b = io.getInt();
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
    public void union(int elementA, int elementB) {
        int a = findGroup(elementA);
        int b = findGroup(elementB);

        //If a is smaller than b
        //Merge a's tree to b's tree
        if (treeSizes[roots[a]] < treeSizes[roots[b]]) {
            merge(a, b);
        } else {
            merge(b, a);
        }
    }

    /*
    * Merge b to a
    * Where a is smaller than b
    * Thus b is now the root for a
    */
    public void merge(int a, int b) {
    	treeSizes[roots[b]] += treeSizes[roots[a]];
        roots[a] = roots[b];
    }

    /*
     * Find the element's group, and update roots at the same time
     */
    public int findGroup(int element) {
        int root = element;
        while (root != roots[root]) {
            // traverse upward, and update roots as you go
        	roots[root] = roots[roots[root]];
        	root = roots[root];
        }
        roots[element] = root;
        return root;
    }

    /*
    * Check if the two element belong to the same group
    */
    public boolean same(int elementA, int elementB) {
        return findGroup(elementA) == findGroup(elementB);
    }
    

    public static void main(String[] args) {
        UnionFind solver = new UnionFind();
        solver.solve();
    }
}
