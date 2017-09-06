import java.io.InputStream;

public class InputParser {
	
	private Kattio kattio;
	private int cacheSize;
	//private int objectAmount;
	private int referenceAmount;
	private int[] references;
	
	public InputParser(InputStream in) {
		kattio = new Kattio(in);
		initiate();
	}
	
	private void initiate() {
		this.cacheSize = kattio.getInt();
		/*this.objectAmount = */kattio.getInt();
		this.referenceAmount = kattio.getInt();
		this.references = new int[referenceAmount];
		for (int i = 0; i < references.length; i++) {
			references[i] = kattio.getInt();
		}
	}
	
	public FutureReferences getReferences() {
		return new FutureReferences(references);
	}
	
	public Cache getCache() {
		return new Cache(cacheSize);
	}

}
