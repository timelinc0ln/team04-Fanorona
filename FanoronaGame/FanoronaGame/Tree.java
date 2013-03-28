package FanoronaGame;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Tree<T> {

	public static final int MAXDEPTH = 2;
	public static final int MAXINT = Integer.MAX_VALUE/2;
	public static final int MININT = Integer.MIN_VALUE/2;

	private Node<T> rootElement;
	private List<List<Node<T>>> levels;

	/**
	 * Default ctor.
	 */
	public Tree() {
		super();
	}

	/**
	 * Return the root Node of the tree.
	 * 
	 * @return the root element.
	 */
	public Node<T> getRootElement() {
		return this.rootElement;
	}

	/**
	* Returns a list of every node in a particular level
	*/
	public List<Node<T>> getLevel (int level) {
		return levels.get(level);
	}

	/**
	 * Set the root Element for the tree.
	 * 
	 * @param rootElement
	 *            the root element to set.
	 */
	public void setRootElement(Node<T> rootElement) {
		this.rootElement = rootElement;
	}

	/**
	 * Returns the Tree<T> as a List of Node<T> objects. The elements of the
	 * List are generated from a pre-order traversal of the tree.
	 * 
	 * @return a List<Node<T>>.
	 */
	public List<Node<T>> toList() {
		List<Node<T>> list = new ArrayList<Node<T>>();
		walk(rootElement, list);
		return list;
	}

	/**
	 * Returns a String representation of the Tree. The elements are generated
	 * from a pre-order traversal of the Tree.
	 * 
	 * @return the String representation of the Tree.
	 */
	public String toString() {
		return toList().toString();
	}

	/**
	 * Walks the Tree in pre-order style. This is a recursive method, and is
	 * called from the toList() method with the root element as the first
	 * argument. It appends to the second argument, which is passed by reference
	 * * as it recurses down the tree.
	 * 
	 * @param element
	 *            the starting element.
	 * @param list
	 *            the output of the walk.
	 */
	private void walk(Node<T> element, List<Node<T>> list) {
		list.add(element);
		for (Node<T> data : element.getChildren()) {
			walk(data, list);
		}
	}
}