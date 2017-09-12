import java.io.InputStream;
import java.util.HashMap;

public class InputParser {
	
	private Kattio kattio;
	private int cacheSize;
	private int accessAmount;
	private int[] accesses;
	private HashMap<Integer, AccessObject> objects;
	
	public InputParser(InputStream in) {
		kattio = new Kattio(in);
		initiate();
	}
	
	private void initiate() {
		this.cacheSize = kattio.getInt();
		kattio.getInt();
		this.accessAmount = kattio.getInt();
		this.accesses = new int[accessAmount];
		this.objects = new HashMap<>();
		for (int i = 0; i < accesses.length; i++) {
			int id = kattio.getInt();
			accesses[i] = id;
			if (!objects.containsKey(id))
				objects.put(id, new AccessObject(id));
			objects.get(id).addFutureAccess(i);
		}
	}
	
	public int[] getAccesses() {
		return accesses;
	}
	
	public HashMap<Integer, AccessObject> getObjects() {
		return objects;
	}
	
	public Cache getCache() {
		return new Cache(cacheSize);
	}
	


}
