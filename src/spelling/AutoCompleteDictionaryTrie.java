package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
		size = 0;
	}

	public boolean addWord(String word) {
		if (word == null || word.length() == 0)
			return false;
		char[] letters = word.toLowerCase().toCharArray();
		TrieNode node = root;
		for (int i = 0; i < letters.length; i++) {
			TrieNode nextNode = node.getChild(letters[i]);
			if (nextNode == null) {
				nextNode = node.insert(letters[i]);
			}
			node = nextNode;
		}
		if (node.endsWord())
			return false;
		node.setEndsWord(true);
		size++;
		return true;
	}

	public int size() {
		return size;
	}

	@Override
	public boolean isWord(String s) {
		if (s == null || s.length() == 0)
			return false;
		char[] letters = s.toLowerCase().toCharArray();
		TrieNode node = root;
		for (int i = 0; i < letters.length; i++) {
			TrieNode nextNode = node.getChild(letters[i]);
			if (nextNode == null)
				return false;
			node = nextNode;
		}
		return node.endsWord();
	}

	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		List<String> res = new LinkedList<>();
		if (prefix == null || numCompletions < 1)
			return res;
		char[] letters = prefix.toLowerCase().toCharArray();
		TrieNode node = root;
		for (int i = 0; i < letters.length; i++) {
			TrieNode nextNode = node.getChild(letters[i]);
			if (nextNode == null)
				return res;
			node = nextNode;
		}
		Queue<TrieNode> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty() && res.size() < numCompletions) {
			TrieNode head = queue.poll();
			if (head.endsWord())
				res.add(head.getText());
			for (char c : head.getValidNextCharacters()) {
				queue.add(head.getChild(c));
			}
		}
		return res;
	}
}