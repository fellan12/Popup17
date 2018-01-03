import java.util.*;

public class CardMagic {
  public static void main(String[] args) {
    int mod = 1000000009;
    Kattio io = new Kattio(System.in, System.out);
    int n = io.getInt();
    int k = io.getInt();
    int t = io.getInt();
    int[] distribution = new int[(n*k)+1];   //One indexed


    //Build a normal distrubution for all the card
    //Combinations
    distribution[0] = 1;
    System.out.println(Arrays.toString(distribution));
    for (int i = 0; i < n; i++) {         // For number of decks
      for (int j = i*k; j >= i; j--) {    // For deck#i * k
        for (int l = 1; l <= k ; l++) {   // For number of cards
          distribution[j+l] = (distribution[j] + distribution[j+l]) % mod;
          System.out.println(Arrays.toString(distribution) + "J = " + j + " L = " + l);

        }
        distribution[j] = 0;
        System.out.println(Arrays.toString(distribution) + "J = " + j);

      }
    }
    io.println(distribution[t]);
    io.close();
  }
}
