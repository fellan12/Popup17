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

  public static ArrayList<Integer> cover(Interval target, ArrayList<Interval> intervals) {
    //Sort the intervals based on their starting point (increasing order)
    Collections.sort(intervals);
    double start = target.getStart();
    double end = target.getEnd();
    ArrayList<Integer> res = new ArrayList<Integer>();
    double farthest = start;
    while (true) {
      int intervalIndex = -1;
      int index = -1;
      for (int i=0; i<intervals.size(); i++) {
        Interval interval = intervals.get(i);
        //Remove intervals outside targetInterval
        if (interval.getEnd() < start || interval.getStart() > end) {
          intervals.remove(i);
          i--;
          index = (index != -1 ? index-- : index);
        }

        //Get the interval that gets you the farthest
        else if (interval.getStart() <= start && interval.getEnd() >= farthest) {
          farthest = interval.getEnd();
          intervalIndex = interval.getIndex();
          index = i;
        }

        //There is no interval that starts before targetsInterval
        else if (interval.getStart() > start){
          break;
        }
      }

      if (intervalIndex == -1) {
        return null;
      }

      try {
        res.add(intervalIndex);
        intervals.remove(index);
      }
      catch (Exception e) {}

        if (farthest >= end) {	//done
          return res;
        }
        start = farthest;
      }
    }
  }
