package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary {
	private LinkedList<String> dict;

	public DictionaryLL() {
		dict = new LinkedList<String>();
	}

	public boolean addWord(String word) {
		if (word == null || word.length() == 0 || isWord(word.toLowerCase()))
			return false;
		dict.add(word.toLowerCase());
		return true;
	}

	public int size() {
		return dict.size();
	}

	public boolean isWord(String s) {
		if (s == null || s.length() == 0)
			return false;
		s = s.toLowerCase();
		for (String word : dict) {
			if (word.equals(s))
				return true;
		}
		return false;
	}
}
