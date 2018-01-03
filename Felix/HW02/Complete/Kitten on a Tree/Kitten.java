import java.util.*;

public class Kitten {

  static Scanner io = new Scanner(System.in);
  public static void main(String[] args) {
    //The kitty's position
    int kittyPos = Integer.parseInt(io.nextLine());
    int[] roots = new int[101];

    int branchRoot = 0;
    while(io.hasNext()){
      String[] branch = io.nextLine().split(" ");
      //If branch > 1, then we have a branch to use
      if(branch.length > 1){
        //Point every branch to its root
        branchRoot = Integer.parseInt(branch[0]);
        for(int i = 1; i < branch.length; i++){
          roots[Integer.parseInt(branch[i])] = branchRoot;
        }
      //else we are at EOF
      }else{
        break;
      }
    }

    StringBuilder sb = new StringBuilder();
    int next = kittyPos;
    //Climb down until the cat is at ground level
    while(next != 0){ //worst case: O(n)
      sb.append(next + " ");
      //Find next step the cat shal take
      next = roots[next];
    }
    System.out.println(sb.toString().trim());
    io.close();
  }

}
