
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NullWriter extends PrintWriter {
	public NullWriter() throws FileNotFoundException {
		super("/tmp/null");
	}

}