/**
 * Authors: Martin Engelin & Felix De Silva
 */
public class TimeEdge {

	private int firstTime;
	private int period;
	private TimeNode endNode;
    private int time;

    public TimeEdge(TimeNode endNode, int time, int t0, int period) {
		this.endNode = endNode;
		this.time = time;
		this.firstTime = t0;
		this.period = period;
	}

    public TimeNode getEndNode(){
      return endNode;
    }

    public int getTime() {
		return time;
	}

	public int getFirstTime() {
		return firstTime;
	}

	public int getPeriod() {
		return period;
	}

	/**
	 * Returns the amount of time until the edge is walkable.
	 * If it isn't walkable from now, throws RuntimeException.
	 */
	public int getWaitTime(int currentTime) {
		if (!isWalkable(currentTime))
			throw new RuntimeException("Calling getWaitTime for non walkable edge.");
		if (currentTime <= firstTime)
			return firstTime - currentTime;
		currentTime -= firstTime;
		int ret = currentTime % period;
		if (ret != 0)
			ret = period - ret;
		return ret;
	}

	public boolean isWalkable(int currentTime) {
		return !(period == 0 && currentTime > firstTime);
	}
}
