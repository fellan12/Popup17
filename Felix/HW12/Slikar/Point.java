
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
      return x;
    }

    public int getY(){
      return y;
    }

    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Point) {
            Point that = (Point) o;
            result = (this.x == that.x && this.y == that.y);
        }
        return result;
    }

    @Override
    public int hashCode() {
    	return 1439 * ((int) x) + ((int) y);
    }

    public String toString(){
      return "(" + x + ", " + y + ")";
    }
}
