public class TimeEdge {

	private TimeNode startNode;
	private TimeNode endNode;
	private int georgeStartTime;
	private int georgeEndTime;
	private int time;

	public TimeEdge(TimeNode startNode, TimeNode endNode, int time) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.time = time;
		georgeStartTime = -1;
		georgeEndTime = -1;
	}

	public TimeNode getStartNode(){
		return startNode;
	}

	public TimeNode getEndNode(){
		return endNode;
	}

	public int getTime() {
		return time;
	}

	/**
	* Get time to wait caused by george
	*/
	public int getWaitTime(int currentTime) {
		int wait = 0;
		if (georgeStartTime <= currentTime && currentTime <= georgeEndTime)
			wait = georgeEndTime+1-currentTime;
		return wait;
	}

	/**
	* Set block interval caused by george
	*/
	public void setBlockedInterval(int georgeStartTime) {
		this.georgeStartTime = georgeStartTime;
		this.georgeEndTime = georgeStartTime+time-1;
	}

	public String toString(){
		return "START: " + startNode + " -> END: " + endNode + " -> (" + time + ")\n" +
						"GStartTime: " + georgeStartTime + " -> " + " GEndTime: " + georgeEndTime;
	}
}
