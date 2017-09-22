import java.util.*;
import java.io.*;

public class TrollHunt {
  public static void main(String [] args) {

    Scanner sc = new Scanner(System.in);
    int bridges = sc.nextInt();
    int knights = sc.nextInt();
    int knightPerGroup = sc.nextInt();

    bridges -= 1;
    if(knightPerGroup == 0)
    {
        System.out.println(0);
    }
    int groups = knights / knightPerGroup;
    int day = bridges / groups + (bridges % groups > 0 ? 1 : 0) ;

    System.out.println(day);
  }
}
