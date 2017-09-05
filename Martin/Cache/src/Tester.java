
import java.io.ByteArrayInputStream;

public class Tester {

	public static void main(String[] args) {
		String testCase = null;
		long time = 0;
		try {
			testCase = TestCasePrinter.generateLarge();
			time = System.currentTimeMillis();
			new Solver(new NullWriter(), new ByteArrayInputStream(testCase.getBytes()));
		} catch (Exception e) {
			System.out.println("Case failed!");
			System.out.println(testCase);
		}
		System.out.println(System.currentTimeMillis() - time);
	}
}
