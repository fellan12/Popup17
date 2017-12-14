import java.util.*;

public class Egypt {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in);
    while(true){
      double y = io.getDouble();
      double x = io.getDouble();
      double h = io.getDouble();

      if(y == 0 && x == 0 && h == 0){
        break;
      }

      if(Math.hypot(x,y) == h || Math.hypot(x,h) == y || Math.hypot(y,h) == x){
        io.println("right");
      } else {
        io.println("wrong");
      }
    }
    io.close();
  }
}
