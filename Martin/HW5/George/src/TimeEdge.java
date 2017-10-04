public class TimeEdge {

	private TimeNode endNode;
	TimeInterval blocked;
	private int time;

	public TimeEdge(TimeNode endNode, int time) {
		this.endNode = endNode;
		this.time = time;
		this.blocked = new TimeInterval(-1, -1);
	}

	public void setBlockedInterval(int start) {
		blocked = new TimeInterval(start, start+time-1);
	}

	public TimeNode getEndNode(){
		return endNode;
	}

	public int getTime() {
		return time;
	}

	public int getWaitTime(int currentTime) {
		int wait = 0;
		if (blocked.insideInterval(currentTime))
			wait = blocked.end+1-currentTime;
		return wait;
	}

	private class TimeInterval {
		private int start;
		private int end;
		public TimeInterval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public boolean insideInterval(int i) {
			if (start <= i && i <= end)
				return true;
			return false;
		}
	}
}
