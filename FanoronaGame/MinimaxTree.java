import java.awt.*;
import java.awt.List;
import java.util.*;

public class MinimaxTree extends FanoronaAI {
	class Tree<T> {
		class Node<T> {
			private T data;
			private Node<T> parent;
			private List children;
		}
		private Node<T> root;

		public Tree(T rootData) {
			root = new Node<T>();
			root.data = rootData;
			List<Node<T>>root.children = new ArrayList<Node<T>>();
		}


}
	public MinimaxTree() {

	}
}
