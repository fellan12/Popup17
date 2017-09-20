/*
 * Author: Felix De Silva
 *  
 * A class that represent an interval for the Interval Cover Problem.
 * Has an indentifing index and a start value and a end value that
 * represent the interval
 */
public class Interval {
  private int index;
  private double start;
  private double end;

  public Interval(int index, double start, double end){
    this.index = index;
    this.start = start;
    this.end = end;
  }

  public double getStart(){
    return start;
  }

  public double getEnd(){
    return end;
  }

  public int getIndex(){
    return index;
  }

  @Override
  public String toString(){
    return String.format("[" + start + ", " + end + "]");
  }
}
