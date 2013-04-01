package FanoronaGame;

import java.util.ArrayList;
import java.util.Random;

public class FanoronaAI extends Board {
	MiniMaxTree mmTree = new MiniMaxTree(); 
	char aiColor = 'B'; // defualt is AI player is Black
	boolean isTurn; 

	public int[] selectDirection(int i, int j, Boolean north, Boolean south, 
		Boolean east, Boolean west, Boolean southEast, Boolean southWest, 
		Boolean northEast, Boolean northWest) {

		int coordinates = new int[2];
		boolean foundMove == false;

		if (north == true) {
			if (!foundMove)
			{
				int moveI = ++i; 
				int moveJ = j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true; 
			}
		}
		else if (south == true) {
			if(!foundMove) {
				int moveI = --i; 
				int moveJ = j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (east == true) {
			if(!foundMove) {
				int moveI = i; 
				int moveJ = --j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (west == true) {
			if(!foundMove) {
				int moveI = i; 
				int moveJ = ++j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (northEast == true) {
			if(!foundMove) {
				int moveI = ++i; 
				int moveJ = --j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (northWest == true) {
			if(!foundMove) {
				int moveI = ++i; 
				int moveJ = ++j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (southWest == true) {
			if(!foundMove) {
				int moveI = --i; 
				int moveJ = ++j;
				coordinate.add(moveI);
				coordinates.add(moveJ);
				foundMove = true;
			}
		}
		else if (southEast == true) {
			if(!foundMove) {
				int moveI = --i; 
				int moveJ = --j;
				coordinate.add(moveI);
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
		int xPos = validMoves[random].x_index;
		int yPos = validMoves[random].y_index;
		int[] coordinates = new int[2]; 
		// Determine what pieces it can capture
		Boolean north = validMoves[random].north;
		Boolean northEast = validMoves[random].northEast;
		Boolean northWest = validMoves[random].northWest;
		Boolean south = validMoves[random].south;
		Boolean southEast = validMoves[random].southEast;
		Boolean southWest = validMoves[random].southWest;
		Boolean east = validMoves[random].east;
		Boolean west = validMoves[random].west; 
		coordinates = selectDirection(); 
		// perform capture move with basic capture options
		turn(aiColor, 1, xPos, yPos, coordinates[0], coordinates[1]);
	}

	/**
	* Construct the AI which will pick active moves
	* @pasram isTurn allows AI to run only when there is a game active
	*/
	public FanoronaAI(boolean isTurn) {
		// TODO: This
		while(isTurn) {
			int captures = 0;
			captures = check_for_capture(aiColor)
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
				System.out.println(pieceColor + " cannot make any moves. The game has ended!")
				isTurn = false;
			} 
		}
	}
}