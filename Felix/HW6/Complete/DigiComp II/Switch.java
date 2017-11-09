public class Switch {
  private int index;
  private State state;
  private Switch leftSwitch;
  private Switch rightSwitch;
  private long balls;

  public Switch(int index){
    this.index = index;
    this.balls = 0;
  }

  public int getIndex(){
    return index;
  }

  public void insertBalls(long balls){
    this.balls += balls;
  }

  public void releaseBalls(long balls){
    this.balls -= balls;
  }

  public long getBalls(){
    return balls;
  }

  public State getState(){
    return state;
  }

  public void setState(String state){
    this.state = (state.equals("R") ? State.R : (state.equals("L") ? State.L : State.N));
  }

  public Switch getLeftSwitch(){
    return leftSwitch;
  }

  public void setLeftSwitch(Switch left){
    this.leftSwitch = left;
  }

  public void setRightSwitch(Switch right){
    this.rightSwitch = right;
  }

  public Switch getRightSwitch(){
    return rightSwitch;
  }

  public void flipSwitch(){
    state = (state == State.R ? State.L : State.R);
  }

  @Override
  public String toString(){
    return index + " (" + state + ", " + balls +")" + " (" + leftSwitch.getIndex() + ", " + rightSwitch.getIndex() + ")";
  }
}

enum State {
	L, R, N
}
