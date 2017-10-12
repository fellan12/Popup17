import java.lang.*;

public class ModularArithmeric {

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int mod = -1;
    int cases = -1;
    while(mod != 0 && cases != 0){
      mod = io.getInt();
      cases = io.getInt();
      for (int i = 0; i < cases; i++) {
        Modulo x = new Modulo(io.getInt(), mod);
        String op = io.getWord();
        Modulo y = new Modulo(io.getInt(), mod);

        switch (op) {
          case "+": io.println(x.addition(y));
                    break;

          case "-": io.println(x.subtract(y));
                    break;

          case "*": io.println(x.multiply(y));
                    break;

          case "/": io.println(x.divide(y) == null ? -1 : x.divide(y));
                    break;

          default: throw new RuntimeException("No Matching Operation");
        }
      }
    }
    io.close();
  }
}
