import java.util.*;

public class Slikar {

  private static int[][] moves = {{1,0},
                                  {-1,0},
                                  {0,1},
                                  {0,-1}};

  private static int r;
  private static int c;

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in);
    r = io.getInt();
    c = io.getInt();

    //Fill flood and painter with -1
    int[][] flood = new int[r][c];
    int[][] painter = new int[r][c];
    for (int x = 0; x < flood.length; x++){
      Arrays.fill(flood[x], -1);
      Arrays.fill(painter[x], -1);
    }

    //Fill map
    char[][] map = new char[r][c];
    for (int x = 0; x < r; x++) {
      String line = io.getWord();
      for (int y = 0; y < c; y++) {
        map[x][y] = line.charAt(y);
      }
    }

    // Find the too be Flooded fields
    // Find Den position
    // Find Painter position
    int painterX = -1;
    int painterY = -1;
    int denX = -1;
    int denY = -1;
    Queue<Point> queue = new LinkedList<Point>();
    for ( int x=0; x<r; ++x ){
      for ( int y=0; y<c; ++y ) {
        //Found Painter
        if ( map[x][y] == 'S' ) {
          painterX = x; painterY = y;
        }

        //Found Den
        if ( map[x][y] == 'D' ) {
          denX = x; denY = y;
        }

        //Found Flood origin
        if ( map[x][y] == '*' ) {
          flood[x][y] = 0;
        }

        //Check surroundings for Flood origin
        if ( map[x][y] == '.' ) {
          boolean flooded = false;
          for (int k = 0; k < 4; k++) {
            int newX = x + moves[k][0];
            int newY = y + moves[k][1];

            //valid point=?
            if (notValidPoint(newX,newY)) {
              continue;
            }

            //Found Flood origin
            if ( map[newX][newY] == '*' ){
              flooded = true;
            }
          }

          //Point too be flooded
          if ( flooded ) {
            queue.add( new Point(x, y) );
            flood[x][y] = 1;
          }
        }

      }
    }

    // Fill flood matrix
   while ( !queue.isEmpty() ) {
      int x = queue.peek().getX();
      int y = queue.peek().getY();
      queue.poll();

      for ( int k=0; k<4; ++k ) {
         int newX = x + moves[k][0];
         int newY = y + moves[k][1];

         //Valid Point?
         if (notValidPoint(newX,newY)) {
           continue;
         }

         //Increase Flood
         if ( flood[newX][newY] == -1 && map[newX][newY] != 'X' && map[newX][newY] != 'D') {
            flood[newX][newY] = flood[x][y] + 1;
            queue.add( new Point( newX, newY ) );
         }
      }
   }

    // Fill painter matrix
    painter[painterX][painterY] = 0;
    queue.offer(new Point(painterX, painterY));
    while (!queue.isEmpty()) {
      Point p = queue.poll();
      int x = p.getX();
      int y = p.getY();

      for (int move = 0; move < moves.length; ++move) {
        int newX = x + moves[move][0];
        int newY = y + moves[move][1];

        //Valid point?
        if (notValidPoint(newX,newY)) {
          continue;
        }

        //Can Painter move?
        if(painter[newX][newY] == -1 && map[newX][newY] != 'X'){
          if(flood[newX][newY] > painter[x][y] + 1 || flood[newX][newY] == -1) {
            painter[newX][newY] = painter[x][y] + 1;
            queue.offer(new Point(newX, newY));
          }
        }
      }
    }

    //Did painter get to the den in time?
    if(painter[denX][denY] != -1){
      io.println(painter[denX][denY]);
    } else {
      io.println("KAKTUS");
    }
    io.close();
  }

  public static boolean notValidPoint(int newX, int newY){
    return (newX < 0 || newX >= r || newY < 0 || newY >= c);
  }

  public static void printMatrix(int[][] matrix){
    for (int[] r : matrix) {
      for (int c : r) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }

  public static void printMatrix(char[][] matrix){
    for (char[] r : matrix) {
      for (char c : r) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }
}
