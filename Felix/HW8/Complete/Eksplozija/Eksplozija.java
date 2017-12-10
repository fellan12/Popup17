import java.util.*;
import java.io.*;

public class Eksplozija {

  public static void main(String args[]){
    Kattio io = new Kattio(System.in);
    char[] word = io.getWord().toCharArray();
    char[] pat = io.getWord().toCharArray();

    int[] matchCount = new int[word.length+1];
    char[] ans = new char[word.length+1];
    int idx = 0;

    for(char c: word){
      int matchedSoFar = matchCount[idx];
      int totalMatched = 0;

      //match pattern char to word char
      if (pat[matchedSoFar] == c){
        totalMatched = matchedSoFar + 1;
      } else if (pat[0] == c) {
        totalMatched = 1;
      }

      idx++;
      matchCount[idx] = totalMatched;
      ans[idx] = c;

      //Found full match?
      //Move idx pattern length back
      if (totalMatched == pat.length){
        idx -= pat.length;
      }

      System.out.println("CurrentChar: " + c);
      System.out.println("MatchCount: " + Arrays.toString(matchCount));
      System.out.println("Answer: " + Arrays.toString(ans));
      System.out.println("idx: " + idx);
    }

    if (idx == 0)
    io.println("FRULA");
    else{
      StringBuilder sb = new StringBuilder();
      for(int i = 1; i <= idx; i++){
        sb.append(ans[i]+"");
      }
      io.println(sb.toString());
    }
    io.close();
  }
}
