package FanoronaGame;

import java.util.ArrayList;
import java.util.Random;

public class FanoronaAI extends Board {
	MiniMaxTree mmTree = new MiniMaxTree(); 
	char aiColor = 'B'; // defualt is AI player is Black
	boolean activeGame; 

	/**
	* Function for selecting which piece to move
	* @param aiColor can be either white or black
	* depending on what the user selects to play as
	*/
	public selectPiece(char aiColor) {
		// randomly select a piece using X,Y coordinates that has a valid move
		int size = x_index.size();
		int random = 0 + (int)(Math.random() * ((size - 0) + 1));
		int xPos = x_index[random];
		int yPos = y_index[random];
		int moveX = 0; // still working on this part
		int moveY = 0;
		int forward = 0;

		// perform capture move
		turn(aiColor, xPos, yPos, moveX, moveY, forward);
	}

	/**
	* Construct the AI which will pick active moves
	* @pasram activeGame allows AI to run only when there is a game active
	*/
	public FanoronaAI(boolean activeGame) {
		// TODO: This
		while(activeGame) {
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
				activeGame = false;
			} 
		}
	}
}