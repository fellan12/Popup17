import java.util.HashSet;

public class Results {

	private int points;
	private String longestWord;
	private HashSet<String> wordIndexes;

	public Results() {
		wordIndexes = new HashSet<>();
		points = 0;
		longestWord = "";
	}

	public void add(String word) {
		if (!wordIndexes.contains(word)) {
			wordIndexes.add(word);
			points += stringToPoints(word);
			if (word.length() > longestWord.length())
				longestWord = word;
			else if (word.length() == longestWord.length() && word.compareTo(longestWord) < 0)
				longestWord = word;
		}
	}

	public String getLongestWord() {
		return longestWord;
	}

	public int size() {
		return wordIndexes.size();
	}

	public int getPoints() {
		return points;
	}

	private static int stringToPoints(String word) {
		switch (word.length()) {
		case 3:
		case 4:
			return 1;
		case 5:
			return 2;
		case 6:
			return 3;
		case 7:
			return 5;
		case 8:
			return 11;
		default:
			throw new RuntimeException();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(points)
		.append(" ")
		.append(longestWord)
		.append(" ")
		.append(size());
		return sb.toString();
	}
}
