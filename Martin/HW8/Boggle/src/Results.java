public class Results {

	private int points;
	private String longestWord;
	private BoggleTrie words;
	private int size;

	public Results() {
		words = new BoggleTrie();
		points = 0;
		size = 0;
		longestWord = "";
	}

	public void add(String word) {
		if (!words.isWord(word)) {
			words.addWord(word);
			size++;
			points += stringToPoints(word);
			if (isLongest(word))
				longestWord = word;
		}
	}
	
	private boolean isLongest(String newWord) {
		if (newWord.length() > longestWord.length())
			return true;
		else if (newWord.length() == longestWord.length())
			return newWord.compareTo(longestWord) < 0;
		return false;
			
	}

	public String getLongestWord() {
		return longestWord;
	}

	public int size() {
		return size;
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
			return 0;
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
