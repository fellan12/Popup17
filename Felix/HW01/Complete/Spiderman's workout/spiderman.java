import java.util.*;
import java.io.*;

public class spiderman {
  static int maxInt = 9999;
  static boolean debug = false;
  static String UP = "U";
  static String DOWN = "D";
  static String IMPOSSIBLE = "IMPOSSIBLE";
  static Kattio io = new Kattio(System.in, System.out);

  public static void main(String[] args) {
    int testCases = io.getInt();

    //Iterate though each problem
    for(int t = 0; t < testCases; t++) {
      int numOfDist = io.getInt();

      //Collect all distances and calulate total sum
      int[] distances = new int[numOfDist+1];
      int sum = 0;
      for(int i = 1; i < distances.length; i++) {
        int dist = io.getInt();
        distances[i] = dist;
        sum += dist;
      }

      //No distances to traverse
      if(sum == 0){
        io.println(UP);
        continue;
      }

      //Sum is not even -> FAIL
      if (sum % 2 == 1){
        io.println(IMPOSSIBLE);
        continue;
      }

      //Buffer optimal solution to printer
      io.println(calculateOptimalPath(numOfDist, distances, sum));
    }

    //Print solutions and close printer
    io.close();
  }

  public static String calculateOptimalPath(int numOfDist, int[] distances, int sum){
    int row = distances.length;
    int col = (sum/2) + 1;

    //Matrix for tracking max height
    int[][] height = new int[row][col];
    //Matrix for tracking path
    String[][] path = new String[row][col];

    //Fill matrix
    for (int i = 0; i < row; i++){
      Arrays.fill(height[i], maxInt);
      Arrays.fill(path[i], "-");
    }

    height[0][0] = distances[0];  //Set first height at ground level
    for(int i = 1; i < distances.length; i++) { //distances
      for(int j = 0; j < col; j++) { //height
        int downHeight = j - distances[i];   //current height - distances to travel
        int upHeight = j + distances[i];     //current height + distances to travel

        //We can't go under groundlevel
        //Check that the previous value going down
        //is smaller than the current value
        if(downHeight >= 0 && height[i-1][downHeight] < height[i][j]) {
          //Use the value that is the largest as current Maxheight
          height[i][j] = Math.max(height[i-1][downHeight], j);
          path[i][j] = UP;
        }

        //We can't go over roof
        //Check tha the previous value going up
        //is smaller than the current value
        if(upHeight < col && height[i-1][upHeight] < height[i][j]) {
          //Use the value that is the largest as current Maxheight
          height[i][j] = Math.max(height[i-1][upHeight], j);
          path[i][j] = DOWN;
        }

        printMatrix(height);
        printMatrix(path);
      }

    }

    //Not on the ground at the end -> FAIL
    if(height[row-1][0] == maxInt) {
      return IMPOSSIBLE;
    }
    //Backtrack to get optimal path -> SUCCESS
    StringBuilder sol = new StringBuilder();
    int j = 0;
    for(int i = row-1; i>0; i--) {
      sol.insert(0, path[i][j]);
      if(path[i][j] == UP) {
        j -= distances[i];
      } else {
        j += distances[i];
      }
    }

    String res = sol.toString();
    if(!verify(res.toCharArray(), distances)){
      throw new RuntimeException("!!INCORRECT ANSWER!!");
    }
    return res;

  }

  private static boolean verify(char[] solution, int[] distances){
    int checker = 0;
    if(solution.length == (distances.length-1)){
      for (int i = 0; i < solution.length; i++) {
        if(solution[i] == 'U'){
          checker += distances[i+1];
        }else{
          checker -= distances[i+1];
        }
      }
    }else{
      System.err.println("Length match error: " + solution.length + " != " + (distances.length-1));
      return false;
    }

    return checker == 0 ? true : false;
  }

  /**
  * Print a matrix
  **/
  private static void printMatrix(int[][] matrix){
    if(debug){
      for(int i = 0; i < matrix.length; i++){
        for(int j = 0; j < matrix[0].length; j++){
          System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
      }

      System.out.println("Press \"ENTER\" to continue...");
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static <T> void printMatrix(T[][] matrix){
    if(debug){

      for(int i = 0; i < matrix.length; i++){
        for(int j = 0; j < matrix[0].length; j++){
          System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
      }
      System.out.println("Press \"ENTER\" to continue...");
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
