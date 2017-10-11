import java.util.*;
import java.io.*;

public class SubstringSearch {

  public static void main(String[] args) throws IOException{
    BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
    String match = "";
    String text = "";
    List<Integer> res = new ArrayList<>();
    while((match = io.readLine()) != null){
      text = io.readLine();
      res = KMP.kmp(match, text);
      for (Integer i : res) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

}
