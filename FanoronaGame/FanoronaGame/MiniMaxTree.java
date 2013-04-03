package FanoronaGame;

import java.awt.*;
import java.awt.List;
import java.util.*;

public class MiniMaxTree<T> {
	private Tree<Integer> mmTree;

	/**
	 * Get the best move given the current board state
	 */
	public int getBestMove(char team) {
		for (int i = mmTree.MAXDEPTH - 1; i > 0; i--) {
			java.util.List<Node<Integer>> currentLevel = mmTree.getLevel(i);
			if (!currentLevel.isEmpty()) {
				if (currentLevel.get(0) != null)
					for (Node node : currentLevel) {
						maximize(node);
					} else {
						for (Node node : currentLevel) {
							minimize(node);
						}
					}
			}
		}

		int minMove = mmTree.getLevel(1).get(0).getData();

		for(int i = 0; i < mmTree.getLevel(1).size(); i++) {
			if (mmTree.getLevel(1).get(i).getData() < minMove) {
				minMove = mmTree.getLevel(1).get(i).getData();
			}
		}
		return minMove;
	}

	/**
	 * Node get's the largest of its children
	 * 
	 * @param node
	 */
	private void maximize(Node node) {
		double max = mmTree.MININT;
		ArrayList<Node<Integer>> children = new ArrayList<Node<Integer>>();
		children = node.getChildren();

		for (Node<Integer> child : children) {
			if (child.getData() > max) {
				// assign value as the new max
				max = child.getData();
			}
		}
	}

	/**
	 * Node get's the largest of its chilren
	 * 
	 * @param node
	 */
	private void minimize(Node node) {
		double min = mmTree.MAXINT;
		ArrayList<Node<Integer>> children = new ArrayList<Node<Integer>>();
				node.getChildren();

		for (Node<Integer> child : children) {
			if (child.getData() < min) {
				// assign value as the new min
				min = child.getData();
			}
		}
	}

}