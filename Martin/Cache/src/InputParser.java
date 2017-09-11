import java.io.InputStream;

public class InputParser {
	
	private Kattio kattio;
	private int cacheSize;
	private int objectAmount;
	private int accessAmount;
	private int[] accesses;
	
	public InputParser(InputStream in) {
		kattio = new Kattio(in);
		initiate();
	}
	
	private void initiate() {
		this.cacheSize = kattio.getInt();
		this.objectAmount = kattio.getInt();
		this.accessAmount = kattio.getInt();
		this.accesses = new int[accessAmount];
		for (int i = 0; i < accesses.length; i++) {
			accesses[i] = kattio.getInt();
		}
	}
	
	public FutureAccesses getAccesses() {
		return new FutureAccesses(accesses);
	}
	
	public Cache getCache() {
		return new Cache(cacheSize, objectAmount);
	}

}
