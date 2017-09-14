
public class UnionSet {
	private int[] roots;
    private int[] treeSizes;
    
    public UnionSet(int elements) {
        roots = new int[elements];
        treeSizes = new int[elements];
        for (int i = 0; i < elements; i++) {
            roots[i] = i;
            treeSizes[i] = 1;
        }
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
    private void merge(int a, int b) {
    	treeSizes[roots[b]] += treeSizes[roots[a]];
        roots[a] = roots[b];
    }

    /*
     * Find the element's group, and update roots at the same time
     */
    private int findGroup(int element) {
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
}
