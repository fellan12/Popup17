import java.util.ArrayList;
import java.util.List;

public class AccessObject implements Comparable<AccessObject> {
	
	private int id;
	private List<Integer> futureAccesses;
	private boolean inCache;

	public AccessObject(int id) {
		this.id = id;
		this.futureAccesses = new ArrayList<>();
		this.inCache = false;
	}
	
	public void addFutureAccess(int i) {
		futureAccesses.add(i);
	}
	
	public int removeNextAccess() {
		try {
			futureAccesses.remove(0);
			return futureAccesses.get(0);
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}
	
	public int getNextAccess() {
		try {
			return futureAccesses.get(1);
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}
	
	public boolean isInCache() {
		return inCache;
	}
	
	public void setInCache() {
		this.inCache = true;
	}
	
	public void removeFromCache() {
		this.inCache = false;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public int compareTo(AccessObject o) {
		return o.getNextAccess() - this.getNextAccess();
	}
	
	@Override
	public String toString() {
		return "["+id+","+getNextAccess()+"]";
	}
}
