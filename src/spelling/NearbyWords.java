package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NearbyWords implements SpellingSuggest {
	Dictionary dict;

	public NearbyWords(Dictionary dict) {
		this.dict = dict;
	}

	/**
	 * Return the list of Strings that are one modification away from the input
	 * string.
	 * 
	 * @param s
	 *            input string
	 * @param wordsOnly
	 *            set whether the result string is a word in dictionary or not
	 */
	public List<String> distanceOne(String s, boolean wordsOnly) {
		List<String> retList = new ArrayList<String>();
		insertions(s, retList, wordsOnly);
		substitution(s, retList, wordsOnly);
		deletions(s, retList, wordsOnly);
		return retList;
	}

	/**
	 * Add to the currentList Strings that are one character mutation away from the
	 * input string.
	 * 
	 * @param s
	 *            Input string
	 * @param currentList
	 *            The list to add the results
	 * @param wordsOnly
	 *            set whether the result string is a word in dictionary or not
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		for (int index = 0; index < s.length(); index++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char) charCode);
				if (!currentList.contains(sb.toString()) && (!wordsOnly || dict.isWord(sb.toString()))
						&& !s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character insertion away from the
	 * input string.
	 * 
	 * @param s
	 *            Input string
	 * @param currentList
	 *            The list to add the results
	 * @param wordsOnly
	 *            set whether the result string is a word in dictionary or not
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly) {
		if (s == null)
			return;
		for (int index = 0; index <= s.length(); index++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
				String word = s.substring(0, index) + (char) charCode + s.substring(index);
				if (!currentList.contains(word) && (!wordsOnly || dict.isWord(word))) {
					currentList.add(word);
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character deletion away from the
	 * input string.
	 * 
	 * @param s
	 *            Input string
	 * @param currentList
	 *            The list to add the results
	 * @param wordsOnly
	 *            set whether the result string is a word in dictionary or not
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly) {
		if (s == null || s.length() < 2)
			return;
		String word = s.substring(1);
		if (!currentList.contains(word) && (!wordsOnly || dict.isWord(word)))
			currentList.add(word);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - 1)) {
				word = s.substring(0, i) + s.substring(i + 1);
				if (!currentList.contains(word) && (!wordsOnly || dict.isWord(word))) {
					currentList.add(word);
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character deletion away from the
	 * input string.
	 * 
	 * @param word
	 *            Input string
	 * @param numSuggestions
	 *            number of suggested words need
	 * @return List of suggested words
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		List<String> queue = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();

		List<String> retList = new LinkedList<String>();
		if (word == null || word.length() == 0)
			return retList;

		queue.add(word);
		visited.add(word);
		while (!queue.isEmpty() && retList.size() < numSuggestions) {
			for (String s : distanceOne(queue.remove(0), false)) {
				if (!visited.contains(s)) {
					visited.add(s);
					queue.add(s);
					if (dict.isWord(s))
						retList.add(s);
				}
			}
		}
		return retList;
	}
}
