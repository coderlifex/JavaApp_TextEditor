package spelling;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 *
 */
public class WPTree implements WordPath {
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	/**
	 * WPTree constructor
	 */
	public WPTree () {
		this.root = null;
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}
	
	/** Return a path from word1 to word2 through dictionary words with
	 *  the restriction that each step in the path can involve only a
	 *  single character mutation  
	 * @param word1 The first word
	 * @param word2 The second word
	 * @return list of Strings which are the path from word1 to word2
	 *         including word1 and word2
	 */
	public List<String> findPath(String word1, String word2) {
		boolean wordsOnly = true;
		WPTreeNode curr = new WPTreeNode(word1, root);
		Queue<WPTreeNode> q = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		visited.add(word1);
		q.add(curr);
		while (q.size() > 0) {
			curr = q.poll();
			List<String> children = nw.distanceOne(curr.getWord(), wordsOnly);
			for (String child : children) {
				if (!visited.contains(child)) {
					WPTreeNode node = new WPTreeNode(child, curr);
					q.offer(node);
					if (child.equals(word2)) {
						return node.buildPathToRoot();
					}
				}
			}
		}
	    return new LinkedList<String>();
	}	
}
