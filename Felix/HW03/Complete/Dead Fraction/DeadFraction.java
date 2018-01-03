/**
* Math inspired solution
*
* Tries different fractions to find one that is the smallest
* and is closest to the decimal number
*
* Creates new fractions by subtracting the original decimal number
* with the first 0 -> n-1 decimals to get a new decimal and see
* if that fraction is the smallest
*/
public class DeadFraction {
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);

    while(io.hasMoreTokens()){
      String deadFrac = io.getWord();

      //End of file
      if(deadFrac.equals("0")){
        break;
      }

      //Remove unnecessary parts
      deadFrac = deadFrac.substring(2, deadFrac.length());  //remove "0."
      deadFrac = deadFrac.replace(".", "");                 //remove "..."

      //We need to find a numerator and a denumerator
      int bestnum = 0;
      int bestden = Integer.MAX_VALUE;

      //try different fractions to find the smallest denominator
      for (int i = 0; i < deadFrac.length(); i++ ){ //O(dec)  -> dec = numOfDecimals

        //What factor to reduce the numerator
        int reduceFactor = 0;
        if(i != 0){
          reduceFactor = Integer.parseInt(deadFrac.substring(0, i));
        }

        //Calculate the numerator and the denominator
        int num = Integer.parseInt(deadFrac) - reduceFactor;
        int den = (int) (Math.pow(10, deadFrac.length()) - Math.pow(10,i));

        // try to simplify the fraction
        int g = GCD(num, den);
        num /= g;
        den /= g;

        System.out.println(num);
        System.out.println(den);
        // minimize the denominator
        if (den < bestden) {
          bestnum = num;
          bestden = den;
        }
      }
      io.println(bestnum + "/" + bestden);
    }
    io.close();
  }

  /**
  * Calculate greatest common divisor of two numbers
  */
  private static int GCD(int a, int b) {
    if (b==0) return a;
    return GCD(b,a%b);
  }
}
