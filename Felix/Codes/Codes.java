import java.util.*;
import java.io.*;

public class Codes {
  static Kattio io = new Kattio(System.in, System.out);

  public static int computeDistance(int n, int k, int[][] genMatrix){
    int minDist = n;    //The max possible value
    int currDist = 0;   //Current distance

    for (int i = 0; i < 1<<k; i++) {  //1<<k = k^2
      int num = i;  //The number to convert to binary codeword
      int[] codeword = new int[n];

      //matrix multiplication
      for (int x = 0; x < n; x++) {
        int sum = 0;
        for (int y = 0; y < k ; y++) {
          //Travers row by row
          //Each (bit of the row) * (a bit of the num) is summerized
          //bit of the num is gathered though math, using shifting.
          //we go backwards, så k-(y+1) gets ut k-(y+1)-th bit of the num
          //The sum % 2 is the value of the codeword row by row
          //Math magic likes shifting
          sum += genMatrix[x][y]*((num >> k-(y+1)) & 1);
        }

        //Add sum % 2 to codeword[x]
        codeword[x] = (sum%2 == 0) ? 0 : 1;
      }

      //Calculate current distance for codeword
      currDist = 0;
  		for (int c = 0; c < n; c++) {
  			currDist += (codeword[c] == 1) ? 1 : 0;
  		}

      //if current distance is not 0, get new minimum distance
      if(currDist != 0){
        minDist = Math.min(minDist, currDist);
      }
    }

    return minDist;
  }

  public static void main(String[] args) {
    int numOfCases = io.getInt();

    for (int t = 0; t < numOfCases; t++) {
  		int n = io.getInt();
  		int k = io.getInt();
  		int[][] genMatrix = new  int[n][15];
  		for (int rows = 0; rows < n; rows++) {
  			for(int cols = 0; cols < k; cols++) {
  				genMatrix[rows][cols] = io.getInt();
  			}
  		}
      io.println(computeDistance(n, k, genMatrix));
  	}

    io.flush();
    io.close();
  }
}
