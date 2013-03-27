package FanoronaGame;

import java.awt.*;
import java.awt.List;
import java.util.*;


public class MiniMaxTree {
	private Tree mmTree;

	/** 
	* Get the best move give the current board state
	*/
	public int getBestMove() {

		return 0;
	}

	/**
	* Node get's the largest of its children
	* @param node
	*/
	private void maximize(Node node) {
		double max = mmTree.MININT;
		List<Node<int>> children = node.getChildren(); // Node type may be an issue
		for (Node<int> child : children) {
			if (child.getData() > max) {
				// assign value as the new max
				max = child.getData();
			}
		}
	}

	/**
	* Node get's the largest of its chilren
	* @param node
	*/
	private void minimize(Node node) {
		double min = mmTree.MAXINT;
		List<Node<int>> children = node.getChildren();

		for (Node<int> child : children) {
			if (child.getData() < min) {
				// assign value to the new min
				min = child.getData();
			}
		}
	}


}