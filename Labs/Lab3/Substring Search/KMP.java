import java.util.*;

public class KMP {

  public static List<Integer> kmp(String pattern, String text){
    char[] pat = pattern.toCharArray();
    char[] txt = text.toCharArray();
    int[] longestProperPrefix = new int[pattern.length()];
    int i = 0;  //Position in the text
    int j = 0;  //Position in the pattern

    generateLPS(pattern, longestProperPrefix);

    List<Integer> res = new ArrayList<>();
    while (i < text.length()){
      if (pat[j] == txt[i]){
        i++;
        j++;
      }
      if (j == pattern.length()){
        res.add(i-j);
        j = longestProperPrefix[j-1];
      } else if (i < text.length() && pat[j] != txt[i]){
        if (j != 0){
          j = longestProperPrefix[j-1];
        } else {
          i++;
        }
      }
    }
    return res;
  }

  private static void generateLPS(String pattern, int[] longestProperPrefix){
    char[] pat = pattern.toCharArray();
    int len = 0;
    int i = 1;

    //Set first value to 0
    longestProperPrefix[0] = 0;

    //Compute the rest
    while (i < pattern.length()){
      if (pat[i] == pat[len]) {
        len++;
        longestProperPrefix[i] = len;
        i++;

      } else {
        if (len != 0){
          len = longestProperPrefix[len-1];
        } else {
          longestProperPrefix[i] = 0;
          i++;
        }
      }
    }
  }
}
