import java.util.*;
import java.io.*;

public class Codes {
  public static void main(String [] args) {

    Scanner sc = new Scanner(System.in);
      int numOfCases = sc.nextInt();
      for (int a = 0; a < numOfCases ; a++) {
      int row = sc.nextInt();
      int column = sc.nextInt();

      //Build matrix
      int[][] matrix = new int[row][column];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
          matrix[i][j] = sc.nextInt();
        }
      }

      //Print matrix
      /*
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
          System.out.print(matrix[i][j]);
        }
        System.out.println();
      }
      */

      //Calculate minimum distance
      int dist = 0;
      for (int i = 0; i < column; i++) {
        int colDist = 0;
        for (int j = 0; j < row; j++) {
          colDist += matrix[j][i];
        }
        dist += colDist % 2;
      }

      System.out.println(dist);
    }



  }
}
