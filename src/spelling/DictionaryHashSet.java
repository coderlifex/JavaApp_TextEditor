/**
 * 
 */
package spelling;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * A class that implements the Dictionary interface with a HashSet
 */
public class DictionaryHashSet implements Dictionary {

	private HashSet<String> words;

	public DictionaryHashSet() {
		words = new HashSet<String>();
	}

	@Override
	public boolean addWord(String word) {
		return words.add(word.toLowerCase());
	}

	@Override
	public int size() {
		return words.size();
	}

	@Override
	public boolean isWord(String s) {
		return words.contains(s.toLowerCase());
	}

}
