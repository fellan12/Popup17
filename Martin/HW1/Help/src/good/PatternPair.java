package good;

public class PatternPair extends Pair<Pattern> {

	public PatternPair(Pattern first, Pattern second) {
		super(first, second);
	}

	@Override
	public String toString() {
		return "[" + first.toString() + ", " + second.toString() + "]";
	}

}
