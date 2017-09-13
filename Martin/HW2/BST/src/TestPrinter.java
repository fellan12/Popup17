import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPrinter {

	public static String getInput(int length) {
		StringBuilder sb = new StringBuilder();
		sb.append(length).append("\n");
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= length; i++) {
			list.add(i);
		}
		Random r = new Random();
		while (list.size() > 1) {
			sb.append(list.remove(r.nextInt(list.size()-1))).append("\n");
		}
		sb.append(list.get(0)).append("\n");
		return sb.toString();
	}
}
