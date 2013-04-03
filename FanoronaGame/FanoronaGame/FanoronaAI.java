package FanoronaGame;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class FanoronaAI extends Board {
	char aiColor = 'B'; // default is AI player is Black
	boolean isTurn; 
	MiniMaxTree<Integer> mmTree = new MiniMaxTree<Integer>(); 
	
	public ArrayList<Integer> selectDirection(int i, int j, Boolean north, Boolean south, 
		Boolean east, Boolean west, Boolean southEast, Boolean southWest, 
		Boolean northEast, Boolean northWest) {

		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		boolean foundMove = false;

		if (north == true) {
			if (!foundMove)
			{
				int moveI = ++i; 
				int moveJ = j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true; 
			}
		}
		else if (south == true) {
			if (!foundMove) {
				int moveI = --i; 
				int moveJ = j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (east == true) {
			if (!foundMove) {
				int moveI = i; 
				int moveJ = --j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (west == true) {
			if (!foundMove) {
				int moveI = i; 
				int moveJ = ++j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (northEast == true) {
			if (!foundMove) {
				int moveI = ++i; 
				int moveJ = --j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (northWest == true) {
			if (!foundMove) {
				int moveI = ++i; 
				int moveJ = ++j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (southWest == true) {
			if (!foundMove) {
				int moveI = --i; 
				int moveJ = ++j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (southEast == true) {
			if (!foundMove) {
				int moveI = --i; 
				int moveJ = --j;
				coordinates.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		return coordinates; 
	}
	/**
	* Function for selecting which piece to move
	* @param aiColor can be either white or black
	* depending on what the user selects to play as
	*/
	public void selectPiece(char aiColor) {
		// randomly select a piece using X,Y coordinates that has a valid move
		int size = validMoves.size();
		int random = 0 + (int)(Math.random() * ((size - 0) + 1));
		char movement = 'E';
		
		//int xPos = fanorona.validMoves[random].x_index;
		int xPos = validMoves.get(random).x_index;
		int yPos = validMoves.get(random).y_index;
		ArrayList<Integer> coordinates = new ArrayList<Integer>(); 
		
		// Determine what pieces it can capture
		Boolean north = validMoves.get(random).north;
		Boolean northEast = validMoves.get(random).northEast;
		Boolean northWest = validMoves.get(random).northWest;
		Boolean south = validMoves.get(random).south;
		Boolean southEast = validMoves.get(random).southEast;
		Boolean southWest = validMoves.get(random).southWest;
		Boolean east = validMoves.get(random).east;
		Boolean west = validMoves.get(random).west; 
		Boolean isValid = false; 
		
		// Determine direction of piece movement (withdraw/advance)
		Boolean direction = validMoves.get(random).advance;
		coordinates = selectDirection(xPos, yPos, north, south, east, west, 
			southEast, southWest, northEast, northWest); 
		if (direction == true) {
			movement = 'A'; // moving toward enemy pieces
		}
		else if (direction == false) {
			movement = 'W'; // moving away from enemy pieces
		}
		//check that the move about to be performed is valid
		isValid = valid_move(aiColor, movement, xPos, yPos,coordinates.get(0), coordinates.get(1));
		// perform capture move with basic capture options
		if (isValid){
			turn(aiColor, movement, xPos, yPos, coordinates.get(0), coordinates.get(1));
		}
	}

	/**
	* Construct the AI which will pick active moves
	* @param isTurn allows AI to run only when there is a game active 
	* and it is the AI's turn
	*/
	public FanoronaAI(boolean isTurn) {
		while (isTurn) {
			int captures = 0;
			captures = check_for_capture(aiColor);
			if (captures > 0) {
				// It is possible for a piece to make a move
				selectPiece(aiColor);
			}
			else {
				String pieceColor;
				if (aiColor == 'B')
					pieceColor = "Black";
				else
					pieceColor = "White";
				System.out.println(pieceColor + " cannot make any moves. The game has ended!");
				isTurn = false;
			} 
		}
	}
}