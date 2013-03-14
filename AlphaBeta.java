// http://software-talk.org/blog/2012/01/mill-the-board-game-including-an-ai-using-alpha-beta-pruning-java/
    public int alphaBeta(Node node, int depth, int alpha, int beta) {
        if (win()) {
            return playerWin ? winRating : -winRating;
        } else if (depth <= 0) {
            return nodeRating;
        }

        List<Node> children = generateChildren(); // generates children. also rates them and applies move to copy of field. 

        if (currentPlayer == ai) { // ai tries to maximize the score
            for (Node child : children) {
                alpha = Math.max(alpha, alphaBeta(child, depth - 1, alpha, beta));

                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            return alpha;
        } else { // enemy tries to minimize the score
            for (Node child : children) {
                beta = Math.min(beta, alphaBeta(child, depth - 1, alpha, beta));
                if (beta <= alpha) {
                    break; // cutoff
                }
            }
            return beta;
        }
    }

    package algorithms;


// http://csf11.acs.uwosh.edu/cs300/MinimaxAlphaBeta.java
import blocktree.*;
import domains.*;
import domains.konane.*;
import java.util.*;

/**
 * This class implements the Minimax with Alpha-Beta Pruning Algorithm for the
 * system by extending the <code>Algorithm</code> class.
 *
 * @author Andrew Jungwirth
 * @version 27 September 2006
 */
public class MinimaxAlphaBeta extends Algorithm{

    private int plyLimit;
    private BTNode root = null;
    private BTNode previous_root;
    private boolean visualize;

    /**
     * Constructs a new <code>Minimax</code> object.
     *
     * @param driver    The <code>BlockTree</code> that created this 
     *                  <code>Minimax</code> object. This allows the algorithm
     *                  to update the status panel as the algorithm runs. This
     *                  can be set to <code>null</code> for game mode.
     * @param plyLimit  The depth to which the Minimax Algorithm is to search
     *                  before invoking the heuristic function to determine
     *                  which node to choose.
     * @param visualize Indicates whether this search is going to be visualized
     *                  or not. A value of <code>true</code> causes the
     *                  algorithm to maintain additional information in order
     *                  to show the nodes that were chosen in previous runs of
     *                  the algorithm. This allows the user to click on
     *                  previous nodes in the visualization to go back in the
     *                  game and choose other nodes at previous levels. A value
     *                  of <code>false</code> avoids storing this additional
     *                  information. This allows the algorithm to run faster if
     *                  the algorithm is not intended to be visualized.
     */
    public MinimaxAlphaBeta(BlockTree driver, int plyLimit, boolean visualize){
    this.plyLimit = plyLimit;
    this.driver = driver;
    this.visualize = visualize;
    }

    /**
     * Sets the ply limit for this minimax search.
     *
     * @param plyLimit The new depth to which the minimax search should be run.
     */
    public void setPlyLimit(int plyLimit){
    this.plyLimit = plyLimit;
    }

    /**
     * Specifies whether this tree is meant to be visualized. This controls how
     * this Minimax Algorithm works. If the value of <code>visualize</code> is
     * <code>true</code>, then the tree returned by the <code>run()</code> 
     * method will include the linear path to the given start node so the path
     * from the initial starting state can be visualized. Since constructing
     * the path to the new starting node takes additional time, the value of
     * <code>visualize</code> can be set to <code>false</code> so that the
     * <code>run()</code> method only returns the minimax tree from the given
     * starting node.
     *
     * @param visualize Specifies the value of the <code>visualize</code>
     *                  variable to control how the <code>run()</code> method
     *                  executes.
     */
    public void setVisualize(boolean visualize){
    this.visualize = visualize;
    }

    /**
     * Runs the Minimax Algorithm from the given starting node. Successors for
     * each node are determined using the <code>expand()</code> method in
     * <code>BTNode</code>, and the heuristic values for nodes are set using
     * the <code>updateH()</code> method in <code>BTNode</code>.
     *
     * @param start The start state for this Minimax Search. Consult the 
     *              description for the <code>setVisualize(bool)</code> method 
     *              for an explanation of how this value influences the tree
     *              that is returned by this method.
     * @throws IncompatibleNodeException Indicates that the given start node 
     *                                   is not a <code>GameNode</code> object.
     *                                   This algorithm requires a
     *                                   <code>GameNode</code> object because
     *                                   of the additional information needed
     *                                   for game tree searches.
     */
    public BTNode run(BTNode start) throws IncompatibleNodeException{
    if(!(start instanceof GameNode)){
        throw new IncompatibleNodeException("MinimaxAlphaBeta.run cannot construct a tree with a root from class " + start.getClass() + ".");
    }

    if(driver != null){
        driver.setStatus("Building Minimax tree.");
    }

    // This code is skipped if the tree is not being visualized.
    if(visualize){
        if(root == null){
        root = start;
        previous_root = start;
        }else{
        BTNode node = root;
        BTNode previous_node = null;
        ArrayList<BTNode> children = previous_root.children;
        boolean goal_found = false;

        // Check if the node clicked was already stored in the path
        // above the current root.
        while(!goal_found){
            if(node.equals(start)){
            if(previous_node == null){
                root = start;
                previous_root = start;
            }else{
                previous_node.children.clear();
                previous_node.children.add(start);
                previous_node.numChildren = 1;
                previous_root = start;
            }

            goal_found = true;

            break;
            }else if(node.numChildren > 0){
            previous_node = node;

            if(previous_node.equals(previous_root)){
                break;
            }

            node = node.children.get(0);
            }else{
            break;
            }
        }

        previous_node = null;

        // Otherwise, add the necessary nodes to show the path to the
        // new root.
        while(!goal_found){
            if(node.status == BTNode.BEST_SUCC_STATE || 
               node.status == BTNode.PATH_STATE){
            node.status = BTNode.NORMAL_STATE;
            }

            int length = children.size();
            
            for(int i = 0; i < length; i++){
            node = children.get(i);

            if(node.equals(start)){
                if(previous_node == null){
                previous_root.children.clear();
                previous_root.children.add(start);
                previous_root.numChildren = 1;
                previous_root = start;
                }else{
                previous_node.children.clear();
                previous_node.children.add(start);
                previous_node.numChildren = 1;
                previous_root = start;
                }

                goal_found = true;
                
                break;
            }else if(node.x1 <= start.x1 && 
                 (node.x1 + node.x2) >= (start.x1 + start.x2)){
                if(previous_node == null){
                previous_root.children.clear();
                previous_root.children.add(node);
                previous_root.numChildren = 1;
                previous_node = node;
                children = node.children;
                }else{
                previous_node.children.clear();
                previous_node.children.add(node);
                previous_node.numChildren = 1;
                previous_node = node;
                children = node.children;
                }
                
                break;
            }
            }
        }
        }
    }else{
        root = start;
    }

    Move move = minimax((GameNode)start, plyLimit, 
                Integer.MIN_VALUE, Integer.MAX_VALUE);

    GameNode parent = (GameNode)start;
    GameNode successor;

    parent.status = BTNode.PATH_STATE;

    while(parent.numChildren > 0){
        //      System.out.println("TRACE: Building path");
        for(int i = 0; i < parent.numChildren; i++){
        successor = (GameNode)parent.children.get(i);

        if(successor.status == BTNode.BEST_SUCC_STATE){
            parent = successor;
            parent.status = BTNode.PATH_STATE;
            break;
        }
        }
    }

    return root;
    }

    private Move minimax(GameNode node, int ply, int alpha, int beta){
    // The ply limit has been reached. Fire the heuristic and return.
    if(ply == 0){
        node.updateH();
        return new Move(node.h, null);
    }

    BTNode[] succs = node.expand();

    // The node is a leaf. Fire the heuristic and return.
    if(succs.length == 0){
        node.updateH();
        return new Move(node.h, null);
    }

//  System.out.println("TRACE: Length of successor array is " + succs.length);
// 
//  if (node instanceof KonaneNode) {
//      for (int i = 0; i < succs.length; i++) {
//      System.out.println("TRACE: " + ((KonaneNode)node).getBoard().legal_moves().get(i).toString());
//      }
//  }

    Move returnMove;
    Move bestMove = null;

    if(node.max){
        // The node is a maximizing node.

        for(int i = 0; i < succs.length; i++){
        // Add the node to the list of children for visualization.
            node.children.add(succs[i]);
            node.numChildren++;

        // Run minimax on the successor.
        returnMove = minimax((GameNode)succs[i], ply - 1, alpha, beta);

        if(bestMove == null){
            bestMove = returnMove;
            bestMove.moveNode = (GameNode)succs[i];
        }else if(bestMove.returnValue < returnMove.returnValue){
            bestMove = returnMove;
            bestMove.moveNode = (GameNode)succs[i];
        }

        // Update alpha value if a greater value was found.
        if(returnMove.returnValue > alpha){
            alpha = returnMove.returnValue;
            bestMove = returnMove;
        }

        // Test if alpha equals or exceeds beta.
        if(beta <= alpha){
            bestMove.moveNode.status = BTNode.BEST_SUCC_STATE;

            // Prune this node.
            bestMove.returnValue = beta;
            bestMove.moveNode = null;

            // Add blank space for visualization.
            if(visualize && (++i) < succs.length){
            succs[i].status = BTNode.INVISIBLE_STATE;
            succs[i].size = (float)(succs.length - i);
            
            node.children.add(succs[i]);
            node.numChildren++;
            }

            return bestMove;
        }
        }

        // Set the child's status to indicate that it is the best child.
        bestMove.moveNode.status = BTNode.BEST_SUCC_STATE;

        // If this node was not pruned, return the best move found.
        return bestMove;
    }else{
        // The node is a minimizing node.

        for(int i = 0; i < succs.length; i++){
        // Add the node to the list of children for visualization.
        //if(visualize){
            node.children.add(succs[i]);
            node.numChildren++;
            //}

        // Run minimax on the successor.
        returnMove = minimax((GameNode)succs[i], ply - 1, alpha, beta);

        if(bestMove == null){
            bestMove = returnMove;
            bestMove.moveNode = (GameNode)succs[i];
        }else if(bestMove.returnValue > returnMove.returnValue){
            bestMove = returnMove;
            bestMove.moveNode = (GameNode)succs[i];
        }

        // Update beta value if a lesser value was found.
        if(returnMove.returnValue < beta){
            beta = returnMove.returnValue;
            bestMove = returnMove;
        }

        // Test if beta equals or is less than alpha.
        if(beta <= alpha){
            bestMove.moveNode.status = BTNode.BEST_SUCC_STATE;

            // Prune this node.
            bestMove.returnValue = alpha;
            bestMove.moveNode = null;

            // Add blank space for visualization.
            if(visualize && (++i) < succs.length){
            succs[i].status = BTNode.INVISIBLE_STATE;
            succs[i].size = (float)(succs.length - i);
            
            node.children.add(succs[i]);
            node.numChildren++;
            }

            return bestMove;
        }
        }

        // Set the child's status to indicate that it is the best child.
        bestMove.moveNode.status = BTNode.BEST_SUCC_STATE;

        // If this node was not pruned, return the best move found.
        return bestMove;
    }
    }

    class Move{
    // Stores the value that is to be returned from the minimax method.
    // This is either alpha, beta, or the heuristic value.
    public int returnValue;

    // This is the node that encapsulates the state that is the best move.
    public GameNode moveNode;

    public Move(int value, GameNode node) {
        returnValue = value;
        moveNode = node;
    }
    }

}
