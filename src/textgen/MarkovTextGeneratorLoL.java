package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {
	private List<ListNode> wordList;
	private String starter;
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/**
	 * Train the generator by adding the sourceText
	 */
	@Override
	public void train(String sourceText) {
		if (wordList.size() > 0)
			return;
		String[] words = sourceText.split("[\\s]+");
		if (words.length < 1)
			return;
		starter = words[0];
		for (int i = 0; i < words.length; i++) {
			boolean found = false;
			for (ListNode node : wordList) {
				if (node.getWord().equals(words[i])) {
					found = true;
					if (i + 1 < words.length)
						node.addNextWord(words[i + 1]);
					break;
				}
			}
			if (!found) {
				ListNode newNode = new ListNode(words[i]);
				if (i + 1 < words.length) {
					newNode.addNextWord(words[i + 1]);
				} else {
					newNode.addNextWord(words[0]);
				}
				wordList.add(newNode);
			}
		}
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (numWords < 1 || wordList.size() == 0)
			return "";

		StringBuilder s = new StringBuilder();
		String cur = starter;
		s.append(cur);
		for (int i = 1; i < numWords; i++) {
			for (ListNode node : wordList) {
				if (node.getWord().equals(cur)) {
					cur = node.getRandomNextWord(rnGenerator);
					s.append(" " + cur);
					break;
				}
			}
		}
		return s.toString();
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}

}
