public class Point {
  private double x;
  private double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double getDistanceBetween(Point other){
    return Math.sqrt(Math.pow(Math.abs(this.x - other.x),2) + Math.pow(Math.abs(this.y - other.y),2));
  }
}
