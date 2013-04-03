package FanoronaGame;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FanoronaGame.Board.PossibleMoves;

////////////////I TRIED TO CHANGE THIS TO DEAL WITH GAMEPIECES RATHER THAN JUST CHARACTERS... DON'T THINK I GOT ANYWHERE
/////////////////BUT MAYBE IT CAN BE USED? NOT SURE. THIS IS WHY I MADE playingFieldPanel STATIC IN THE FANORONAGAME CLASS//////


// This function returns the number of captures that are
	// available for a given team.
public class BoardforGamePieces {

	JPanel panel;
	MiniMaxTree mmTree = new MiniMaxTree();


 //---------------- This is a resize function for an array --------------------------
	private Object resize_array (Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class<?> elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0)
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		return newArray; 
	}
	
	
	
	/**
	* Creates a class to be used by FanoronaAI for determing valid moves
	* Stores the x and y indices of pieces that have valid moves
	* Boolean values indicate which directions in releation to the pice have valid moves
	*/
	public class PossibleMoves {
		Boolean north = false;
		Boolean northEast = false;
		Boolean northWest = false;
		Boolean south = false;
		Boolean southEast = false;
		Boolean southWest = false;
		Boolean east = false;
		Boolean west = false;
		int x_index;
		int y_index;
}

	static List<PossibleMoves> validMoves = new ArrayList<PossibleMoves>();
//----------------------------------------------------------------------------------
//-------------------------- Private members ---------------------------------------    
	private int row_limit;
	private int column_limit;
	GamePieces board[][] = new GamePieces[row_limit][column_limit];
	

     // private JPanel playingFieldPanel;
 
	
	public void capture(char team_moved, int x_ps, int y_ps, int x_zs, int y_zs, char direction) {
		int i = 1;
		if (direction == 'A') {
			// Upper left
			if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) {
				while (x_zs-i >= 0 && y_zs-i >= 0) {
					if (board[x_zs-i][y_zs-i].team != 'E') {
						if (board[x_zs-i][y_zs-i].team != opponent(team_moved)) {
								break;
							}
						board[x_zs-i][y_zs-i].team = 'E';
					}
					i++;
				}
			}
			// Upper
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) {
				while (x_zs-i >= 0) {
					if (board[x_zs-i][y_zs].team != 'E') {
						if (board[x_zs-i][y_zs].team != opponent(team_moved)) {
								break;
							}
						board[x_zs-i][y_zs].team = 'E';
					}
					i++;
				}
			}
			// Upper right
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) {
				while (x_zs-i >= 0 && y_zs+i < column_limit) {
					if (board[x_zs-i][y_zs+i].team != 'E') {
						if (board[x_zs-i][y_zs+i].team != opponent(team_moved)) {
								break;
							}
						board[x_zs-i][y_zs+i].team = 'E';
					}
					i++;
				}
			}
			// Left
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0) {
				while (y_zs-i >= 0) {
					if (board[x_zs][y_zs-i].team != 'E') {
						if (board[x_zs][y_zs-i].team != opponent(team_moved)) {
								break;
							}
						board[x_zs][y_zs-i].team = 'E';
					}
					i++;
				}
			}
			// Right
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) {
				while (y_zs+i < column_limit) {
					if (board[x_zs][y_zs+i].team != 'E') {
						if (board[x_zs][y_zs+i].team != opponent(team_moved)) {
								break;
							}
						board[x_zs][y_zs+i].team = 'E';
					}
					i++;
				}
			}
			// Down Left
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0) {
				while (x_zs+i < row_limit && y_zs-i >= 0) {
					if (board[x_zs+i][y_zs-i].team != 'E') {
						if (board[x_zs+i][y_zs-i].team != opponent(team_moved)) {
								break;
							}
						board[x_zs+i][y_zs-i].team = 'E';
					}
					i++;
				}
			}
			// Down
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) {
				while (x_zs+i < row_limit) {
					if (board[x_zs+i][y_zs].team != 'E') {
						if (board[x_zs+i][y_zs].team != opponent(team_moved)) {
								break;
							}
						board[x_zs+i][y_zs].team = 'E';
					}
					i++;
				}
			}
			// Down Right
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) {
				while (x_zs+i < row_limit && y_zs+i < column_limit) {
					if (board[x_zs+i][y_zs+i].team != 'E') {
						if (board[x_zs+i][y_zs+i].team != opponent(team_moved)) {
								break;
							}
						board[x_zs+i][y_zs+i].team = 'E';
					}
					i++;
				}
			}
		}
		// Down-board capture
		else if (direction == 'D') {
			// Upper left
			if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) {
				while (x_ps+i < row_limit && y_ps+i < column_limit) {
					if (board[x_ps+i][y_ps+i].team != 'E') {
						if (board[x_ps+i][y_ps+i].team != opponent(team_moved)) {
								break;
							}
						board[x_ps+i][y_ps+i].team = 'E';
					}
					i++;
				}
			}
			// Upper
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) {
				while (x_ps+i < row_limit) {
					if (board[x_ps+i][y_ps].team != 'E') {
						if (board[x_ps+i][y_ps].team != opponent(team_moved)) {
								break;
							}
						board[x_ps+i][y_ps].team = 'E';
					}
					i++;
				}
			}
			// Upper right
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) {
				while (x_ps+i < row_limit && y_ps-i >= 0) {
					if (board[x_ps+i][y_ps-i].team != 'E') {
						if (board[x_ps+i][y_ps-i].team != opponent(team_moved)) {
								break;
							}
						board[x_ps+i][y_ps-i].team = 'E';
					}
					i++;
				}
			}
			// Left
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0) {
				while (y_ps+i < column_limit) {
					if (board[x_ps][y_ps+i].team != 'E') {
						if (board[x_ps][y_ps+i].team != opponent(team_moved)) {
								break;
							}
						board[x_ps][y_ps+i].team = 'E';
					}
					i++;
				}
			}
			// Right
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) {
				while (y_ps-i >= 0) {
					if (board[x_ps][y_ps-i].team != 'E') {
						if (board[x_ps][y_ps-i].team != opponent(team_moved)) {
								break;
							}
						board[x_ps][y_ps-i].team = 'E';
					}
					i++;
				}
			}
			// Down Left
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0) {
				while (x_ps-i >= 0 && y_ps+i < column_limit) {
					if (board[x_ps-i][y_ps+i].team != 'E') {
						if (board[x_ps-i][y_ps+i].team != opponent(team_moved)) {
								break;
							}
						board[x_ps-i][y_ps+i].team = 'E';
					}
					i++;
				}
			}
			// Down
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) {
				while (x_ps-i >= 0) {
					if (board[x_ps-i][y_ps].team != 'E') {
						if (board[x_ps-i][y_ps].team != opponent(team_moved)) {
								break;
							}
						board[x_ps-i][y_ps].team = 'E';
					}
					i++;
				}
			}
			// Down Right
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) {
				while (x_ps-i >= 0 && y_ps-i >= 0) {
					if (board[x_ps-i][y_ps-i].team != 'E') {
						if (board[x_ps-i][y_ps-i].team != opponent(team_moved)) {
								break;
							}
						board[x_ps-i][y_ps-i].team = 'E';
					}
					i++;
				}
			}
		}
	}

	private void paika(int x_ps, int y_ps, int x_zs, int y_zs) {
		char team = board[x_ps][y_ps].team;
		board[x_zs][y_zs].team = team;
		board[x_ps][y_ps].team = 'E';
	}

	private void sacrifice(char team, int x, int y) {
		if (team == 'W') 
			board[x][y].team = 'X';
		else if (team == 'B')
			board[x][y].team = 'X';
	}

// ----------------------------------------------------------------------------------
// ------------------------------- Constructors -------------------------------------
	//Default Board constructor
	public BoardforGamePieces() {
		row_limit = 5;
		column_limit = 9;
		// ------------------- Resize the board -------------------------
		board = (GamePieces[][])resize_array(board, row_limit);
		for (int z = 0; z < board.length; z++) {
			if (board[z] == null)
				board[z] = new GamePieces[column_limit];
			else
				board[z] = (GamePieces[])resize_array(board[z], column_limit);
		}
		// --------------------------------------------------------------
		for (int i = 0; i < row_limit; i++)
			for (int j = 0; j < column_limit; j++) {
				if (i==0 || i==1)
					board[i][j].team = 'B';
				else if ( i == 2 && 
					(j == 0 || j == 2 || j == 5 || j == 7)) {
					board[i][j].team = 'B';
					}
				else
					board[i][j].team = 'W';
				}
		board[2][4].team = 'E';
	}

	// Board constructor with size
	public BoardforGamePieces(int row, int column) {
		if (row >= 1 && row <= 13 && (row % 2) == 1) {
			if (column >= 1 && column <= 13 && (row % 2) == 1) {
				row_limit = row;
				column_limit = column;
				// ------------------- Resize the board -------------------------
				board = (GamePieces[][])resize_array(board, row_limit);
				for (int z = 0; z < board.length; z++) {
					if (board[z] == null)
						board[z] = new GamePieces[column_limit];
					else
						board[z] = (GamePieces[])resize_array(board[z], column_limit);
				}
				// --------------------------------------------------------------
				for (int i = 0; i < row_limit; i++) {
					for (int j = 0; j < column_limit; j++) {
						if (i < row/2) 
							board[i][j].team = 'B';
						else if (i == row/2) {
							if (((j % 2) == 0 && j < column/2) 
								|| ((j % 2) == 1 && j > column/2)) {
									board[i][j].team = 'B';
							}
							else if (j == column/2)
								board[i][j].team = 'E';
							else 
								board[i][j].team = 'W';
						}
						else
							board[i][j].team = 'W';
					}
				}
			}
		}
	}		
// ----------------------------------------------------------------------------------
// ---------------------------- Public Functions ------------------------------------
	public char opponent(char team) {
		char opponent = 'E';
		if (team == 'W')
			opponent = 'B';
		else if (team == 'B')
			opponent = 'W';
		return opponent;
	}

	public int white_remaining() {
		int total = 0;
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j].team == 'W')
					total += 1;
			}
		}
		return total;
	}

	public int black_remaining() {
		int total = 0;
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j].team == 'B')
					total += 1;
			}
		}
		return total;
	}

	
	BoardforGamePieces b;
//	public void display_board() {
//		for (int i = 0; i < row_limit; i++) {
//			for (int j = 0; j < column_limit; j++) {
//				System.out.print(board[i][j] + "  ");
//			}
//			System.out.println();
//		}
//	}

	// This function returns the number of captures that are
	// available for a given team.
	// also adds valid capture moves to an array for the AI 
	public int check_for_capture(char team) {
		int Captures = 0;
		validMoves.clear();
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j].team == 'E') {
					if ( i > 0 && i < row_limit-1 
						&& j > 0 && j < column_limit-1) {
							// Check nodes that can move in all directions
							if ( ((i % 2) == 1 && (j % 2) == 1) ||
								((i % 2) == 0 && (j % 2) == 0)) {
									// Check up and to the left 
									// Advances capture
									if (board[i-1][j-1].team == team && 
										board[i+1][j+1].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northWest = true; 
											validMoves.add(moves);											}
									// Down-board capture
									if (i != 1 && j != 1) {
										if (board[i-1][j-1].team == team && 
											board[i-2][j-2].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.southEast = true; 
												validMoves.add(moves);
											}
									}	
									// Check up 
									// Advances capture
									if (board[i-1][j].team == team && 
										board[i+1][j].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.north = true; 
												validMoves.add(moves);
										}
									// Down-board capture
									if (i != 1) {
										if (board[i-1][j].team == team && 
											board[i-2][j].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.south = true; 
												validMoves.add(moves);
											}
									}
									// Check up and to the right 
									// Advances capture
									if (board[i-1][j+1].team == team && 
										board[i+1][j-1].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northEast = true; 
											validMoves.add(moves);
										}	
									// Down-board capture
									if (i != 1 && j != column_limit-2) {
										if (board[i-1][j+1].team == team && 
											board[i-2][j+2].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.southWest = true; 
												validMoves.add(moves);
											}
									}	
									// Check left 
									// Advances capture
									if (board[i][j-1].team == team && 
										board[i][j+1].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.west = true; 
											validMoves.add(moves);
										}
									// Down-board capture
									if (j != 1) {	
										if (board[i][j-1].team == team && 
											board[i][j-2].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.east = true; 
												validMoves.add(moves);
											}
									}
									// Check right 
									// Advances capture
									if (board[i][j+1].team == team && 
										board[i][j-1].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.east = true; 
											validMoves.add(moves);
										}
									// Down-board capture
									if (j != column_limit-2) {
										if (board[i][j+1].team == team && 
											board[i][j+2].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.west = true; 
												validMoves.add(moves);
											}
									}	
									// Check down and to the left 
									// Advances capture
									if (board[i+1][j-1].team == team && 
										board[i-1][j+1].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southWest = true; 
											validMoves.add(moves);
										}
									// Down-board capture
									if (i != row_limit-2 && j != 1) {
										if (board[i+1][j-1].team == team && 
											board[i+2][j-2].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.northEast = true; 
												validMoves.add(moves);
											}
									}	
									// Check down 
									// Advances capture
									if (board[i+1][j].team == team && 
										board[i-1][j].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.south = true; 
											validMoves.add(moves);
										}
									// Down-board capture
									if (i != row_limit-2) {
										if (board[i+1][j].team == team && 
											board[i+2][j].team == opponent(team)) {
												Captures +=1;
												PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.north = true; 
												validMoves.add(moves);
											}
									}	
									// Check down and to the right 
									// Advances capture
									if (board[i+1][j+1].team == team && 
										board[i-1][j-1].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southEast = true; 
											validMoves.add(moves);
										}
									// Down-board capture
									if ( i != row_limit-2 && 
										j != column_limit-2) {
											if (board[i+1][j+1].team == team && 
												board[i+2][j+2].team == opponent(team)) {
													Captures +=1;
													PossibleMoves moves = new PossibleMoves();
													moves.x_index = i;
													moves.y_index = j; 
													moves.northWest = true; 
													validMoves.add(moves);
												}
									}
								}
							// Check nodes that can move up, down, left, and right 	
							else {
							// Check up 
							// Advances capture
							if (board[i-1][j].team == team && 
								board[i+1][j].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
								}
							// Down-board capture
							if (i != 1) {
								if (board[i-1][j].team == team && 
									board[i-2][j].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Check down 
							// Advances capture
							if (board[i+1][j].team == team &&
								board[i-1][j].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
								}
							// Down-board capture
							if (i != row_limit-2) {
								if (board[i+1][j].team == team && 
									board[i+2][j].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
									}
							}	
							// Check left 
							// Advances capture
							if (board[i][j-1].team == team && 
								board[i][j+1].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
								}
							// Down-board capture
							if ( j != 1) {
								if (board[i][j-1].team == team && 
									board[i][j-2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										validMoves.add(moves);
									}
							}	
							// Check right 
							// Advances capture
							if (board[i][j+1].team == team && 
								board[i][j-1].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										validMoves.add(moves);
								}
							// Down-board caputre
							if (j != column_limit-2) {
								if (board[i][j+1].team == team && 
									board[i][j+2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
						}
					}
					// Checks the top edge
					else if (i == 0) {
						if (j > 0) {
							// Check left 
							// Advances capture
							if ( j != column_limit-1) {
								if (board[i][j-1].team == team && 
									board[i][j+1].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (j != 1) {
								if (board[i][j-1].team == team && 
									board[i][j-2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the left
							// Down-board capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i+1][j-1].team == team && 
										board[i+2][j-2].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northEast = true; 
											validMoves.add(moves);
										}
								}
							}
						}
						if (j < column_limit-1) {
							// Check right 
							// Advances capture
							if (j != 0) {
								if (board[i][j+1].team == team && 
									board[i][j-1].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (j != column_limit-2) {
								if (board[i][j+1].team == team && 
									board[i][j+2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the right
							// Down-board capture
							if (row_limit > 1) {							
								if ((j % 2) == 0) {
									if (board[i+1][j+1].team == team && 
										board[i+2][j+2].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northWest = true; 
											validMoves.add(moves);
										}
								}
							}
						}
					}
					// Checks the bottom edge
					else if (i == row_limit-1) {
						if (j > 0) {
							// Check left 
							// Advances capture
							if ( j != column_limit-2) {
								if (board[i][j-1].team == team && 
									board[i][j+1].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (j != 1) {
								if (board[i][j-1].team == team && 
									board[i][j-2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
							// Check up and to the left
							// Down-board capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i-1][j-1].team == team && 
										board[i-2][j-2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.southEast = true; 
										validMoves.add(moves);
										}
								}
							}
						}
						if (j < column_limit-1) {
							// Check right 
							// Advances capture
							if (j != 0) {
								if (board[i][j+1].team == team && 
									board[i][j-1].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (j != column_limit-1) {
								if (board[i][j+1].team == team && 
									board[i][j+2].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}	
							// Check up and to the right
							// Down-board capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i-1][j+1].team == team && 
										board[i-2][j+2].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southWest = true; 
											validMoves.add(moves);
										}
								}
							}
						}
					}
					// Checks the left edge
					else if (j == 0) {
						if (i > 0) {
							// Check up 
							// Advances capture
							if (i != row_limit-1) {
								if (board[i-1][j].team == team && 
									board[i+1][j].team == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);									
									}
							}
							// Down-board capture
							if (i != 1) {
								if (board[i-1][j].team == team && 
									board[i-2][j].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Check up and to the right
							// Down-board capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i-1][j+1].team == team && 
										board[i-2][j+2].team == opponent(team)) {
											Captures +=1;											
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southWest = true; 
											validMoves.add(moves);
										}
								}
							}
						}
						if (i < row_limit-1) {
							// Check down 
							// Advances capture
							if (i != 0) {
								if (board[i+1][j].team == team && 
									board[i-1][j].team == opponent(team)) {
										Captures += 1;	
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (i != row_limit-2) {
								if (board[i+1][j].team == team && 
									board[i+2][j].team == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the right
							// Down-board capture
							if (column_limit > 1) {	
								if ((i %2 ) == 0) {
									if (board[i+1][j+1].team == team && 
										board[i+2][j+2].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northWest = true; 
											validMoves.add(moves);
										}
								}
							}
						}
					}
					// Checks right edge
					else if (j == column_limit-1) {
						if (i > 0) {
							// Check up 
							// Advances capture
							if (i != row_limit-1) {
								if (board[i-1][j].team == team && 
									board[i+1][j].team == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (i != 1) {
								if (board[i-1][j].team == team && 
									board[i-2][j].team == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Check up and to the left
							// Down-board capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i-1][j-1].team == team && 
										board[i-2][j-2].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southEast = true; 
											validMoves.add(moves);
										}
								}
							}
						}
						if (i < row_limit-1) {
							// Check down 
							// Advances capture
							if (i != 0) {
								if (board[i+1][j].team == team && 
									board[i-1][j].team == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Down-board capture
							if (i != row_limit-2) {
								if (board[i+1][j].team == team && 
									board[i+2][j].team == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the left
							// Down-board capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i+1][j-1].team == team && 
										board[i+2][j-2].team == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northEast = true; 
											validMoves.add(moves);
										}
								}
							}
						}
					}
				}
			}
		}
		return Captures;
	}

	public boolean in_limits(int x, int y) {
		boolean valid = false;
		if (x >= 0 &&
			y >= 0 && 
			x < row_limit && 
			y < column_limit) {
				valid = true;
			}
		else
			valid = false;
		return valid;
	}

	public boolean valid_move(char team, char move, int x_ps, int y_ps, int x_zs, int y_zs) {
		boolean valid = false;
		if (board[x_zs][y_zs].team == 'E' &&
			board[x_ps][y_ps].team == team) {			
				if (in_limits(x_zs, y_zs)) {
					// UP
					if ((x_ps - x_zs) > 0 && 
						(y_ps - y_zs) == 0) {  
							if (move == 'A' && 
								board[x_zs-1][y_zs].team == opponent(team) &&
								x_zs > 0) {
									valid = true;
								}
							else if (move == 'D' &&
								board[x_ps+1][y_ps].team == opponent(team) &&
								x_zs < row_limit-1) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
					// Left
					if ((x_ps - x_zs) == 0  &&
						(y_ps - y_zs) > 0) {
							if (move == 'A' && 
							board[x_zs][y_zs-1].team == opponent(team) &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'D' && 
								board[x_ps][y_ps+1].team == opponent(team) &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'P' &&
							check_for_capture(team) == 0 ) {
								valid = true;
							}
						}
					// Right
					if ((x_ps - x_zs) == 0  &&
						(y_ps - y_zs) < 0) {
							if (move == 'A' &&
							board[x_zs][y_zs+1].team == opponent(team) &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'D' &&
								board[x_ps][y_ps-1].team == opponent(team) &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
					// Down
					if ((x_ps - x_zs) < 0 &&
						(y_ps - y_zs) == 0) {
							if (move == 'A' &&
							board[x_zs+1][y_zs].team == opponent(team) &&
								x_zs < row_limit-1) {
									valid = true;
								}
							else if (move == 'D' &&
							board[x_ps-1][y_ps].team == opponent(team) &&
								x_zs > 0) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
					// Up-left
					if ((x_ps - x_zs) > 0  &&
						(y_ps - y_zs) > 0  &&
						(((x_ps == row_limit-1 && (y_ps % 2) == 0)) ||
						(((x_ps % 2) == 0   && y_ps == column_limit-1)) ||
						((x_zs == row_limit-1 && (y_zs % 2) == 0)) ||
						(((x_zs % 2) == 0   && y_zs == column_limit-1)) ||
						(((x_ps % 2) == 0  && (y_ps % 2) == 0)  ||
						((x_ps % 2) == 1   && (y_ps % 2) == 1)) ||
						(((x_zs % 2) == 0  && (y_zs % 2) == 0)  ||
						((x_zs % 2) == 1   && (y_zs % 2) == 1)))) {
							if (move == 'A' &&
								board[x_zs-1][y_zs-1].team == opponent(team) &&
								x_zs > 0    &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'D'   &&
								board[x_ps+1][y_ps+1].team == opponent(team) &&
								x_zs < row_limit-1 &&
								y_zs < column_limit) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
					// Up-right
					if ((x_ps - x_zs) > 0  &&
						(y_ps - y_zs) < 0  &&
						(((x_ps == row_limit-1 && (y_ps % 2) == 0)) ||
						(((x_ps % 2) == 0   && y_ps == 0))		||
						((x_zs == row_limit-1 && (y_zs % 2) == 0)) ||
						(((x_zs % 2) == 0   && y_zs == 0))		||
						(((x_ps % 2) == 0  && (y_ps % 2) == 0)  ||
						((x_ps % 2) == 1   && (y_ps % 2) == 1)) ||
						(((x_zs % 2) == 0  && (y_zs % 2) == 0)  ||
						((x_zs % 2) == 1   && (y_zs % 2) == 1)))) {
							if (move == 'A' &&
								board[x_zs-1][y_zs+1].team == opponent(team) &&
								x_zs > 0    &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'D' &&
								board[x_ps+1][y_ps-1].team == opponent(team) &&
								x_zs < row_limit-1 &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
					// Down-left
					if ((x_ps - x_zs) < 0  &&
						(y_ps - y_zs) > 0  &&
						(((x_ps == 0         && (y_ps % 2) == 0))  ||
						(((x_ps % 2) == 0   && y_ps == column_limit-1)) ||
						((x_zs == 0         && (y_zs % 2) == 0))  ||
						(((x_zs % 2) == 0   && y_zs == column_limit-1)) ||
						(((x_ps % 2) == 0  && (y_ps % 2) == 0)  ||
						((x_ps % 2) == 1   && (y_ps % 2) == 1)) ||
						(((x_zs % 2) == 0  && (y_zs % 2) == 0)  ||
						((x_zs % 2) == 1   && (y_zs % 2) == 1)))) {
							if (move == 'A' &&
								board[x_zs+1][y_zs-1].team == opponent(team) &&
								x_zs < row_limit-1 &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'D' &&
								board[x_ps-1][y_ps+1].team == opponent(team) &&
								x_zs > 0 		 && 
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
					// Down-right
					if ((x_ps - x_zs) < 0  &&
						(y_ps - y_zs) < 0  &&
						(((x_ps == 0         && (y_ps % 2) == 0))  ||
						(((x_ps % 2) == 0   && y_ps == 0)) 	    ||
						((x_zs == 0         && (y_zs % 2) == 0))  ||
						(((x_zs % 2) == 0   && y_zs == 0)) 	    ||
						(((x_ps % 2) == 0  && (y_ps % 2) == 0)  ||
						((x_ps % 2) == 1   && (y_ps % 2) == 1)) ||
						(((x_zs % 2) == 0  && (y_zs % 2) == 0)  ||
						((x_zs % 2) == 1   && (y_zs % 2) == 1)))) {
							if (move == 'A'		   &&
								board[x_zs+1][y_zs+1].team == opponent(team) &&
								x_zs < row_limit-1 &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'W' &&
								board[x_ps-1][y_ps-1].team == opponent(team) &&
								x_zs > 0 		 &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'P' &&
								check_for_capture(team) == 0 ) {
									valid = true;
								}
						}
				}
			}
		else if (move == 'S' &&
			board[x_ps][y_ps].team == team) {
				valid = true;
			}
		else
			valid = false;
		return valid;
	}

	public void turn(char team, char move, int x_ps, int y_ps, int x_zs, int y_zs) {
		if (FanoronaGame.moveChar() == 'A') {
			capture(team, x_ps, y_ps, x_zs, y_zs, move);
			board[x_ps][y_ps].team = 'M';
			board[x_zs][y_zs].team = team;
		}
		else if (FanoronaGame.moveChar() == 'D') {
			capture(team, x_ps, y_ps, x_zs, y_zs, move);
			board[x_ps][y_ps].team = 'M';
			board[x_zs][y_zs].team = team;
		}
		else if (FanoronaGame.moveChar() == 'P') {
			paika(x_ps, y_ps, x_zs, y_zs);
		}
		else if (FanoronaGame.moveChar() == 'S') {
			sacrifice(team, x_ps, y_ps);
		}
	}

	public char turn_change(char team) {
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j].team == 'M') {
					board[i][j].team = 'E';
				}
				else if (team == 'B' && board[i][j].team == 'X') {
					board[i][j].team = 'E';
				}
				else if (team == 'W' && board[i][j].team == 'X') {
					board[i][j].team = 'E';
				}
			}
		}
		if (team == 'W')
			team = 'B';
		else if (team == 'B') 
			team = 'W';
		return team;
	}

	public char game_start() {
		char player = FanoronaGame.source.team;
		FanoronaGame.source.team = 'W';
		int turn_limit = 10 * column_limit;
		int turn = 1;
		char move, winner;
		int x_ps, y_ps, x_zs, y_zs;

		while (turn <= turn_limit && white_remaining() > 0 && black_remaining() > 0) {
			if (FanoronaGame.source.team == 'W')
				JOptionPane.showMessageDialog(null, "White Starts", "Move Type", JOptionPane.PLAIN_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Black Starts", "Move Type", JOptionPane.PLAIN_MESSAGE);
			//System.out.print("What type of move? (A --> Advance capture, D --> Down-board capture, P --> Paika, S --> sacrifice) ");
			move = FanoronaGame.moveChar();	
			x_ps = FanoronaGame.source.x;
			y_ps = FanoronaGame.source.y;
			if (move == 'S') {
				x_zs = 0;
				y_zs = 0;
			}
			else {
				x_zs = FanoronaGame.target.x;
				y_zs = FanoronaGame.target.y;
			}
			while (valid_move(FanoronaGame.source.team, move, x_ps, y_ps, x_zs, y_zs) != true) {
				JOptionPane.showMessageDialog(null, "Illegal Move", "Move Type", JOptionPane.ERROR_MESSAGE);
				//System.out.print("What type of move? (A --> Advance capture, D --> Down-board capture, P --> Paika, S --> sacarifice) ");
				move = FanoronaGame.moveChar();	
				x_ps = FanoronaGame.source.x;
				y_ps = FanoronaGame.source.y;
				if (move == 'S') {
					x_zs = 0;
					y_zs = 0;
				}
				else {
					x_zs = FanoronaGame.target.x;
					y_zs = FanoronaGame.target.y;
				}
			}
			turn(player, move, x_ps, y_ps, x_zs, y_zs);
			player = turn_change(player);
			//display_board();
			turn++;
		}
		if (white_remaining() > 0)
			winner = 'W';
		else if (black_remaining() > 0) 
			winner = 'B';
		else
			winner = 'T';
		return winner;
	}
}
