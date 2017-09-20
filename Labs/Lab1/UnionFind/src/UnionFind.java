import java.util.HashMap;

/**
 * Authors: Martin Engelin & Felix De Silva
 * Representation of all of the sets.
 *
 * All the elements are trees with a root that first points to itself
 * When merged the larger one gets the smallest one's elements inc. itself
 * and the smallest one's new root is the larger one
*/
public class UnionFind {
	private HashMap<Integer, Integer> indexMap;
	private int[] roots;
    private int[] treeSizes;
    
    /**
     * Constructor that assumes that the elements are 
     * linear from 0 -> elements-1
     */
    public UnionFind(int elements) {
    	indexMap = new HashMap<>();
        roots = new int[elements];
        treeSizes = new int[elements];
        for (int i = 0; i < elements; i++) {
        	indexMap.put(i, i);
            roots[i] = i;
            treeSizes[i] = 1;
        }
    }
    
    /**
     * Constructor that uses a prefixed array of elements
     */
    public UnionFind(int[] elements) {
    	indexMap = new HashMap<>();
        roots = new int[elements.length];
        treeSizes = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
        	indexMap.put(elements[i], i);
            roots[i] = i;
            treeSizes[i] = 1;
        }
    }
    
    /**
     * Merges groups which elements A and B belong to
     */
    public void union(int elementA, int elementB) {
        int a = findGroup(indexMap.get(elementA));
        int b = findGroup(indexMap.get(elementB));

        //If a is smaller than b
        //Merge a's tree to b's tree
        if (treeSizes[roots[a]] < treeSizes[roots[b]]) {
            merge(a, b);
        } else {
            merge(b, a);
        }
    }

    /**
    * Merge b to a
    * Where a is smaller than b
    * Thus b is now the root for a
    */
    private void merge(int a, int b) {
    	treeSizes[roots[b]] += treeSizes[roots[a]];
        roots[a] = roots[b];
    }

    /**
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

    /**
    * Check if the two element belong to the same group
    */
    public boolean same(int elementA, int elementB) {
        return findGroup(indexMap.get(elementA)) == findGroup(indexMap.get(elementB));
    }
}
