import java.util.*;
public class GearsInAction {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int tests = io.getInt();
    boolean solutionFound = true;
    for (int i = 0; i < tests; i++) {
      int gears = io.getInt();
      long n = io.getLong();
      long a = io.getLong();
      Long[] res = new Long[0];
      for (int j = 1; j < gears; j++ ) {
          long m = io.getLong();
          long b = io.getLong();
         if(!solutionFound){
           continue;
         }

        /**
        * j even
        * x = a mod n
        * x = b mod m
        *
        * j odd
        * x = a mod n
        * x = m-b mod m
        **/
        if(j%2 != 0){
          b = m-b;
        }

        // System.out.println(a);
        // System.out.println(n);
        // System.out.println(b);
        // System.out.println(m);
        res = ChineseRemainderTheorem.crt(a,n,b,m);
        // System.out.println(Arrays.toString(res));
        if(res.length == 0){
          solutionFound = false;
          continue;
        }
        a = res[0];
        n = res[1];
      }
      // System.out.println(res.length);

      if(solutionFound){
        if(gears == 1){
          io.println(n);
        }else {
          io.println(res[0]);
        }
      }else{
        io.println("impossible");
      }
      solutionFound = true;
    }
    io.close();
  }
}
