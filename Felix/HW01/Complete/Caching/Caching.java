import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Queue;

public class Caching {

  static boolean debug = false;
  static Kattio io = new Kattio(System.in, System.out);

  public static void main(String args[]) {
    int c = io.getInt();
    int n = io.getInt();
    int a = io.getInt();

    HashMap<Integer, ItemInfo> objects = new HashMap<Integer, ItemInfo>();
    PriorityQueue<Item> cache = new PriorityQueue<Item>(c, Collections.reverseOrder());
    int[] Hit = new int[a];

    //create all ItemInfo objects
    for(int i = 0; i < a; i++) {
      int value = io.getInt();
      Hit[i] = value;
      ItemInfo info;
      //Fill in ItemInfo
      //The futute accesses of the Item
      if(!objects.containsKey(value)) {
        info = new ItemInfo(value, i);
        objects.put(value, info);
      } else {
        info = objects.get(value);
        info.getFutute().add(i);
      }
    }

    //Calculate Accesses
    int cached = 0;
    int replace = 0;
    for(int i = 0; i < Hit.length; i++) {
      int value = Hit[i];
      ItemInfo info = objects.get(value);

      //If item not in cache
      if(!info.getCacheStatus())  {
        //If cache is full
        if(cached >= c) {
          //Remove the item with highest weight
          Item Item = cache.poll();
          ItemInfo removedPageInfo = objects.get(Item.getID());
          removedPageInfo.setCacheStatus(false);
          replace++;
        } else {
          //Cache is not full
          cached++;
        }
      }
      //Calculate weight for the new item to be cached
      int weight = Integer.MAX_VALUE;
      if(!info.getFutute().isEmpty()) {
        for (Iterator<Integer> it = info.getFutute().iterator(); it.hasNext(); ) {
					int next = it.next();
          //Remove if index has been passed
          //Else use the value as weight for the newly caches item
					if(next <= i) {
						it.remove();
					} else {
						weight = next;
						break;
					}
				}
      }
      //Add new item and set to inCache
      cache.add(new Item(value, weight));
      info.setCacheStatus(true);
    }
    int updates = 0;
    if(cached < c) {
      io.println(cached);
    }else{
      io.println(c + replace);
    }
    io.close();
  }

  private static <T> void debug(T str){
    if(debug){
      System.out.println(str);

      System.out.println();
      System.out.println("Press \"ENTER\" to continue...");
      System.out.println();
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static <T> void debugArray(T[] arr){
    if(debug){
      System.out.println("CURRENT ITEMINCACHE-----");
      for (T i : arr) {
        System.out.print(i + " ");
      }
      System.out.println();
      System.out.println("Press \"ENTER\" to continue...");
      System.out.println();
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void debugArray(List<Item> arr){
    if(debug){
      System.out.println("CURRENT ITEMINCACHE-----");
      for (Item i : arr) {
        System.out.print(i.getID() + " -> " + i.getID());
      }
      System.out.println();
      System.out.println("Press \"ENTER\" to continue...");
      System.out.println();
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static class Item implements Comparable<Item> {
    private int itemID;
    private int weight;

    Item(int id, int weight){
      this.itemID = id;
      this.weight = weight;
    }

    public int getID(){
      return itemID;
    }

    public int getWeight(){
      return weight;
    }

    public void setWeight(int newWeight){
      weight = newWeight;
    }

    public int compareTo(Item other) {
      return Integer.compare(this.weight, other.weight);
    }
  }

  /**
  * Contains information of the Item
  **/
  private static class ItemInfo {
    private int id;
    private ArrayList<Integer> futureAccess = new ArrayList<Integer>();
    private boolean inCache = false;

    public ItemInfo(int id, int index) {
      this.id = id;
      futureAccess.add(index);
    }

    public int getID(){
      return id;
    }

    public ArrayList<Integer> getFutute(){
      return futureAccess;
    }

    public void setCacheStatus(boolean status){
      inCache = status;
    }

    public boolean getCacheStatus(){
      return inCache;
    }

  }

}
