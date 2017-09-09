import java.util.*;

/*
Input
Target interval [L, R] <= R
N interval [a_i,b_i] where i = [1,2,3,....]

Output
Smallest sumset S <= [1,2,3,...]
s.t. union([a_i,b_i], [L,R]) where i is in S

SPECIAL CASE
L=R => Then just take one that contains the point [L,R]
*/

public class IntervalCover {

  public static void main(String[] args) {

    Kattio io = new Kattio(System.in, System.out);

    while (io.hasMoreTokens()) {
      //Create target interval
      Interval targetInterval = new Interval(-1, io.getDouble(), io.getDouble());

      //number of intervals
      int n = io.getInt();
      ArrayList<Interval> intervals = new ArrayList<Interval>();

      //Create the intervals
      for (int i = 0; i < n; i++) {
        intervals.add(new Interval(i, io.getDouble(), io.getDouble()));
      }

      //Get the solution of the Interval Cover Problem
      ArrayList<Integer> solution = cover(targetInterval, intervals);

      //Print no solution
      if (solution == null) {
        io.println("impossible");
        continue;
      }

      //Print number of intervals for the solution
      io.println(solution.size());

      //Print the intervals of the solution
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < solution.size(); i++) {
        sb.append(solution.get(i) + " ");
      }
      io.println(sb.toString().trim());
    }

    io.close();
  }

  public static ArrayList<Integer> cover(Interval targetInterval, ArrayList<Interval> intervals) {
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    //Sort interval
    Collections.sort(intervals);
    return coverHelper(targetInterval, intervals, indexes);
  }

  private static ArrayList<Integer> coverHelper(Interval targetInterval, ArrayList<Interval> intervals, ArrayList<Integer> indexes) {
    double farthest = targetInterval.getStart();
    int intervalIndex = -1;
    int index = -1;
    for (int i=0; i<intervals.size(); i++) {
      Interval interval = intervals.get(i);
      if(interval.getEnd() < targetInterval.getStart() || interval.getStart() > targetInterval.getEnd()){
        intervals.remove(i);
        i--;
        index = (index != -1 ? index-- : index);
      } else if (interval.getStart() <= targetInterval.getStart() && interval.getEnd() >= farthest) {
        farthest = interval.getEnd();
        intervalIndex = interval.getIndex();
        index = i;
      } else if (interval.getStart() > targetInterval.getStart()){
        break;
      }
    }

    if (intervalIndex == -1) {
      return null;
    }

    try{
      indexes.add(intervalIndex);
      intervals.remove(index);
    }catch (Exception e){
      e.printStackTrace();
    }

    //Need recurse more
    if (farthest < targetInterval.getEnd()) {
      return coverHelper(new Interval(-1, farthest, targetInterval.getEnd()), intervals, indexes);
    }else{
      return indexes;
    }
  }
}
