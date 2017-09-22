import java.util.*;

public class Help{

  static Kattio io = new Kattio(System.in, System.out);

  public static void main(String[] args){
    int numOfCases = io.getInt();
    String[] res = new String[numOfCases];

    for (int a = 0; a < numOfCases ; a++) {

      String[] line1 = io.getLine().split(" ");
      String[] line2 = io.getLine().split(" ");

      if(line1.length != line2.length){
        io.println("-");
        continue;
      }

      findPattern(line1, line2);

      if (!Arrays.equals(line1,line2)){
        io.println("-");
      } else {
        io.println(String.join(" ", line1).trim());
      }
    }
    io.close();
  }

  /*
  * Check if a word is a pattern
  */
  public static boolean isPattern(String word){
    return word.charAt(0) == '<' && word.charAt(word.length()-1) == '>';
  }

  /*
   * Replace a pattern in the line with a word
  */
  public static void replace(String[] line, String pattern, String word) {
    //REMEMBER when comparing strings use equals!!!!!
    for(int i = 0; i < line.length; i++) {
			if(line[i].equals(pattern)) {
				line[i] = word;
			}
		}
  }

  /*
  * Find patterns in the lines and replace
  * with the correct substitute
  */
  public static void findPattern(String[] line1, String[] line2){
    /*
    Recursive function that starts over when you have found a pattern to replace
    Recursion messes with my head... Had right code before but didn't see that is
    was correct...
    */

    //Find singel pattern
    //Replace pattern with substitute
    for(int i = 0; i < line1.length; i++) {
			if(isPattern(line1[i]) && !isPattern(line2[i])) {
				replace(line1, line1[i], line2[i]);
				findPattern(line1, line2);
			} else if(isPattern(line2[i]) && !isPattern(line1[i])) {
				replace(line2, line2[i], line1[i]);
				findPattern(line1, line2);
			}
		}

    //Find pair pattern if there is any
    //Replace both with whateva
    for(int i = 0; i < line1.length; i++) {
      if(isPattern(line1[i]) && isPattern(line2[i])) {
        //BUG: In kattis, if the pair replacement is something with CAPS or
        //some special character you will get a wrong answer...
        replace(line1, line1[i], "whateva");
        replace(line2, line2[i], "whateva");
        findPattern(line1, line2);
      }
    }
  }

}
