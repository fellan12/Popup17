/**
* Splits the interval into thirds and check
* if the sought number is in the interval edges
* if not, check which interval the number is in and
* if the number is in the edge intervals, split that interval into
* thirds and check again.
*
* if the number falls into the middle interval at some point
* the number is not in the cantor set
*/
public class Cantor {
  static double EPSILON = 0.0000001;

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in);
    String value;
    while (io.hasMoreTokens()) {
      if((value = io.getWord()).equals("END")) {
        break;
      }
      io.println(isCantor(Double.parseDouble(value), 0, 1));  //Initial left and right = [0,1]
    }
    io.close();
  }

  /**
  * recursive function that return the string "MEMBER" if the number is part
  * of the cantor set, else "NON-MEMBER"
  * O(n log(n))
  */
  public static String isCantor(double num, double left, double right) {
    double newRight = left + (right-left)/3;
    double newLeft = newRight + (right-left)/3;
  
    //Check if the number is on the edges
    if((num-EPSILON <= left && num+EPSILON >= left) ||
       (num-EPSILON <= right && num+EPSILON >= right)||
       (num-EPSILON <= newLeft && num+EPSILON >= newLeft)||
       (num-EPSILON <= newRight && num+EPSILON >= newRight)){
      return "MEMBER";
    }

    //recure again and see if the number is on the new edges
    System.out.println("num > left: " + (num > left) +" &&  num < newRight: " + (num < newRight));
    System.out.println("num > newLeft: " + (num > newLeft)  + " num < right: " + ( num < right));
    if(num > left && num < newRight){
      return isCantor(num, left, newRight);
    } else if(num > newLeft && num < right){
      return isCantor(num, newLeft, right);
    } else {
      return "NON-MEMBER";
    }
  }
}
