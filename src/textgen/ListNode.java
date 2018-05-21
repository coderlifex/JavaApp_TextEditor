package textgen;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListNode {
	private String word;
	private List<String> nextWords;

	public ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}