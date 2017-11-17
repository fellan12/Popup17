import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Trie {

    private Node root;
    private List<Node> nodeList;

    public Trie(String[] dictionary) {
        init();
        for (int i = 0; i < dictionary.length; i++) {
            String word = dictionary[i];
            addWord(word, i);
        }
    }
    public Trie() {
        init();
    }

    private void init() {
        root = new Node(-1);
        nodeList = new ArrayList<>();
        nodeList.add(root);
    }
    
    public List<Integer> getSortedIndexes() {
        List<Integer> res = new ArrayList<>();
        dfs(nodeList.get(0), res);
        return res;
    }

    private void dfs(Node n, List<Integer> res) {
        if (n.isWord())
            res.add(n.index);
        PriorityQueue<Character> pq = n.sortedList;
        while (!pq.isEmpty()) {
                char c = pq.poll();
                dfs(nodeList.get(n.getChild(c)), res);
        }
    }

    public void addWord(String word, int i) {
        Node n = root;
        root.setIndex(i);
        for (char c : word.toCharArray()) {
            if (!n.hasChild(c))
                n = nodeList.get(n.addChild(c, i));
            else {
                n = nodeList.get(n.getChild(c));
                n.setIndex(i);
            }
        }
        n.setIsWord();
    }

    public boolean isWord(String s) {
        return isWordGen(0, s);
    }

    public boolean isWordGen(int root, String s) {
        Node n = nodeList.get(root);
        for (char c : s.toCharArray()) {
            if (!n.hasChild(c))
                return false;
            n = nodeList.get(n.getChild(c));
        }
        return n.isWord();
    }

    public boolean isPrefix(String s) {
        return isPrefixGen(0, s);
    }

    public boolean isPrefixGen(int root, String s) {
        Node n = nodeList.get(root);
        for (char c : s.toCharArray()) {
            if (!n.hasChild(c))
                return false;
            n = nodeList.get(n.getChild(c));
        }
        return true;
    }

    public boolean isPrefixGen(int root, char c) {
        Node n = nodeList.get(root);
        if (!n.hasChild(c))
            return false;
        return true;
    }

    public int getNextNodeIndex(int index, char c) {
        return nodeList.get(index).getChild(c);
    }

    public class Node {
        private HashMap<Character, Integer> children;
        private PriorityQueue<Character> sortedList;
        private boolean word;
        private int index;

        public Node(int index) {
            children = new HashMap<>();
            this.index = index;
            sortedList = new PriorityQueue<>();
        }

        public int addChild(char c, int index) {
            if (hasChild(c)) {
                throw new RuntimeException("Trying to add new node when node already exists");
            }
            sortedList.add(c);
            int i = nodeList.size();
            children.put(c, i);
            nodeList.add(new Node(index));
            return i;
        }

        public int getChild(char c) {
            return children.get(c);
        }

        public boolean hasChild(char c) {
            return children.containsKey(c);
        }

        public boolean isWord() {
            return word;
        }

        public void setIsWord() {
            this.word = true;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    private static int charToIndex(char c) {
        return ((int) c)-32;
    }
}