public class Point {
  private int index;
  private double x;
  private double y;

  public Point(int index, double x, double y){
    this.index = index;
    this.x = x;
    this.y = y;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public int getIndex(){
    return index;
  }

  public double getDistanceBetween(Point other){
    return Math.hypot(this.x - other.x,this.y - other.y);
  }
}
