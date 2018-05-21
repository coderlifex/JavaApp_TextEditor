package spelling;

import java.util.TreeSet;

/**
 * A class that implements the Dictionary interface using a Binary Search Tree
 *
 */
public class DictionaryBST implements Dictionary {
	private TreeSet<String> dict;

	public DictionaryBST() {
		dict = new TreeSet<String>();
	}

	public boolean addWord(String word) {
		if (word == null || word.length() == 0)
			return false;
		word = word.toLowerCase();
		if (isWord(word))
			return false;
		dict.add(word);
		return true;
	}

	public int size() {
		return dict.size();
	}

	public boolean isWord(String s) {
		if (s == null || s.length() == 0)
			return false;
		s = s.toLowerCase();
		return dict.contains(s);
	}
}
