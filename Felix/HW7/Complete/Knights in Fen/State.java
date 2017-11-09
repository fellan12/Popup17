import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class State {
  private char[][] board;
  private char[][] goal = {
    {'B','B','B','B','B'},
    {'W','B','B','B','B'},
    {'W','W','X','B','B'},
    {'W','W','W','W','B'},
    {'W','W','W','W','W'}
  };

  public State(char[][] board) {
    this.board = board;
  }


  /**
  * Calculate the number of pieces that is wrongly placed
  */
  public int getWronglyPlacedCount() {
    int counter = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (this.board[i][j] != 'X' && this.board[i][j] != this.goal[i][j]) {
          counter++;
        }
      }
    }
    return counter;
  }

  /**
  * Generate the neighbors from the given state
  */
  public Set<State> getNeighbors() {
    Set<State> set = new HashSet<>();
    int movement[] = {-2,-1,1,2};  //move-set of a hourse
    int[] x = findX();
    for (int i : movement) {
      for (int j : movement) {
        if (Math.abs(i) + Math.abs(j) == 3) {
          if (validIndex(x[0] + i) && validIndex(x[1] + j)) {
            State tmp = new State(this.copyBoard());
            char knight = tmp.board[x[0] + i][x[1] + j];
            tmp.board[x[0] + i][x[1] + j] = 'X';
            tmp.board[x[0]][x[1]] = knight;
            set.add(tmp);
          }
        }
      }
    }
    return set;
  }

  /**
  * Is this the goal state
  */
  public boolean isGoalState() {
    return Arrays.deepEquals(this.board, this.goal);
  }

  /**
  * Create and return a copy of the board
  */
  private char[][] copyBoard() {
    char[][] copy = new char[this.board.length][];
    for (int r = 0; r < this.board.length; r++) {
      copy[r] = this.board[r].clone();
    }
    return copy;
  }

  /**
  * Is this a valid index
  */
  private boolean validIndex(int i) {
    return i >= 0 && i < 5;
  }

  /**
  * Find X in the board and return its coordinates
  */
  private int[] findX() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (this.board[i][j] == 'X') return new int[]{i,j};
      }
    }
    return new int[]{};
  }


  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        str += this.board[i][j] + " ";
      }
      str += '\n';
    }
    return str;
  }
}
