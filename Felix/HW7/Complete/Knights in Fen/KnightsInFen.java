import java.io.*;
import java.util.*;

public class KnightsInFen {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int cases = Integer.parseInt(in.readLine());
    for (int c = 0; c < cases; c++) {
      char[][] board = new char[5][5];
      for (int i = 0; i < 5; i++) {
        String s = in.readLine();
        for (int j = 0; j < 5; j++) {
          board[i][j] = s.charAt(j) == '0' ? 'W' : s.charAt(j) == '1' ? 'B' : 'X';
        }
      }

      int moves = calculateMoves(new State(board));
      // System.out.println("MOVES: " + moves);
      if (moves < 0 || moves > 10) {
        System.out.println("Unsolvable in less than 11 move(s).");
      } else {
        System.out.println("Solvable in " + moves + " move(s).");
      }
    }
  }


  public static int calculateMoves(State init) {
    Set<State> visited = new HashSet<State>();
    PriorityQueue<Node> next = new PriorityQueue<>(new NodeComperator());

    //Add initail node to queue and map
    next.add(new Node(init, 0));

    while (!next.isEmpty()) {
      Node current = next.poll();

      // System.out.println("CURRENT NODE:");
      // System.out.println(current);

      //Add current as visited
      visited.add(current.getState());

      //current state is the goal or has to many moves
      if (current.getState().isGoalState() || current.getMoveCount() >= 11) {
        return current.getMoveCount();
      }

      //For every new node, add to queue and map
      for (State child : current.getState().getNeighbors()) {
        if (!visited.contains(child)) {
          Node newNode = new Node(child, current.getMoveCount() + 1);
          next.add(newNode);
          // System.out.println("ADDED STATE: ");
          // System.out.println(newNode);
        } else {
          // System.out.println("ALREADY VISITED STATE");
          // System.out.println(child);
        }
      }
    }
    return -1;
  }

  /**
  * Comparator that sorts node according to their comapreFactor.
  */
  private static class NodeComperator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
      return Integer.compare(o1.getCompareFactor(), o2.getCompareFactor());
    }
  }
}
