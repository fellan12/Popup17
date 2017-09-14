import java.util.*;

public class Kitten {

  static Scanner io = new Scanner(System.in);
  public static void main(String[] args) {
    int kittyPos = Integer.parseInt(io.nextLine());
    int[] roots = new int[101];

    int branchRoot = 0;
    while(io.hasNext()){
      String[] branch = io.nextLine().split(" ");
      if(branch.length > 1){
        branchRoot = Integer.parseInt(branch[0]);
        for(int i = 1; i < branch.length; i++){
          roots[Integer.parseInt(branch[i])] = branchRoot;
        }
      }else{
        break;
      }
    }

    StringBuilder sb = new StringBuilder();
    int next = kittyPos;
    while(next != 0){
      sb.append(next + " ");
      next = roots[next];
    }
    System.out.println(sb.toString().trim());
    io.close();
  }

}
