import java.util.*;

public class MovieCollection {
  static Kattio io = new Kattio(System.in, System.out);

  public static void main(String[] args) {
    int numOfCases = io.getInt();

    for (int i = 0; i < numOfCases; i++) {
      int numOfMovies = io.getInt();
      int numOfRequests = io.getInt();

      int[] moviePos = new int[numOfMovies+1];                                // movies + 1
      FenwickTree movieTree = new FenwickTree(numOfMovies + numOfRequests);   // request + movies

      //Add one for every movie on the tree
      for(int j = 1; j <= numOfMovies; j++){  // O(m)
        movieTree.add(j, 1);                  // O(log(m+r))
        moviePos[j] = numOfMovies - j + 1;
      }

      //perform the requests
      StringBuilder sb = new StringBuilder();

      // System.out.println(movieTree.printTree());
      for (int j = numOfMovies + 1; j <= numOfMovies + numOfRequests; j++) {  // O(m+r)
        int req = io.getInt();
        int reqPos = moviePos[req];

        //Add the number of movies above the requested one
        sb.append(numOfMovies - movieTree.sum(reqPos) + " ");   // O(log(m+r))

        movieTree.add(reqPos, -1);                              // O(log(m+r))
        moviePos[req] = j;
        movieTree.add(j, 1);                                    // O(log(m+r))

        // System.out.println(movieTree.printTree());
      }

      io.println(sb.toString());
    }
    io.close();
  }
}
