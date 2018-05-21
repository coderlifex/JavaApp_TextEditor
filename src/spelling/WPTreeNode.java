package spelling;

import java.util.LinkedList;
import java.util.List;

/**
 * Tree Node in a WordPath Tree. Each node having
 * any number of possible children. Each node should only contain a word in the
 * dictionary and the relationship between nodes is that a child is one
 * character mutation (deletion, insertion, or substitution) away from its
 * parent
 */
class WPTreeNode {

	private String word;
	private List<WPTreeNode> children;
	private WPTreeNode parent;

	/**
	 * Construct a node with the word w and the parent p (pass a null parent to
	 * construct the root)
	 * 
	 * @param w
	 *            The new node's word
	 * @param p
	 *            The new node's parent
	 */
	public WPTreeNode(String w, WPTreeNode p) {
		this.word = w;
		this.parent = p;
		this.children = new LinkedList<WPTreeNode>();
	}

	/**
	 * Add a child of a node containing the String s precondition: The word is not
	 * already a child of this node
	 * 
	 * @param s
	 *            The child node's word
	 * @return The new WPTreeNode
	 */
	public WPTreeNode addChild(String s) {
		WPTreeNode child = new WPTreeNode(s, this);
		this.children.add(child);
		return child;
	}

	/**
	 * Get the list of children of the calling object (pass a null parent to
	 * construct the root)
	 * 
	 * @return List of WPTreeNode children
	 */
	public List<WPTreeNode> getChildren() {
		return this.children;
	}

	/**
	 * Build a path from the root node to the calling object
	 * 
	 * @return The list of strings starting at the root and ending at the calling
	 *         object
	 */
	public List<String> buildPathToRoot() {
		WPTreeNode curr = this;
		List<String> path = new LinkedList<String>();
		while (curr != null) {
			path.add(0, curr.getWord());
			curr = curr.parent;
		}
		return path;
	}

	/**
	 * Get the word for the calling object
	 *
	 * @return Getter for calling object's word
	 */
	public String getWord() {
		return this.word;
	}
}