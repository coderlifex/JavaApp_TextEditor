package application;

import java.util.Random;

/**
 * Class factory
 *
 */

public class LaunchClass {

	static public String dictFile = "data/dict.txt";

	public LaunchClass() {
		super();
	}

	static public document.Document getDocument(String text) {
		return new document.EfficientDocument(text);
	}

	static public textgen.MarkovTextGenerator getMTG() {
		return new textgen.MarkovTextGeneratorLoL(new Random());
	}

	static public spelling.WordPath getWordPath() {
		return new spelling.WPTree();
	}

	static public spelling.AutoComplete getAutoComplete() {
		spelling.AutoCompleteDictionaryTrie tr = new spelling.AutoCompleteDictionaryTrie();
		spelling.DictionaryLoader.loadDictionary(tr, dictFile);
		return tr;
	}

	static public spelling.Dictionary getDictionary() {
		spelling.Dictionary d = new spelling.AutoCompleteDictionaryTrie();
		//spelling.Dictionary d = new spelling.DictionaryBST();
		//spelling.Dictionary d = new spelling.DictionaryHashSet();
		spelling.DictionaryLoader.loadDictionary(d, dictFile);
		return d;
	}

	static public spelling.SpellingSuggest getSpellingSuggest(spelling.Dictionary dic) {
		return new spelling.NearbyWords(dic);

	}
}
