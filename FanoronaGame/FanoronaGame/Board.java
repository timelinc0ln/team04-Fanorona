//package FanoronaGame;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Board {
// ---------------- This is a resize function for an array --------------------------
	private static Object resize_array (Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0)
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		return newArray; 
	}
// ----------------------------------------------------------------------------------
// -------------------------- Private members ---------------------------------------    
	private int row_limit;
	private int column_limit;
	private char board[][] = new char[row_limit][column_limit];
	// Move type functions
// -------------------------- Public members ---------------------------------------
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
		Boolean advance = false;
		int x_index;
		int y_index;
}

	static List<PossibleMoves> validMoves = new ArrayList<PossibleMoves>();
// -------------------------- Private Functions ---------------------------------------
	private void capture(char team_moved, int x_ps, int y_ps, int x_zs, int y_zs, char direction) {
		int i = 1;
		if (direction == 'A') {
			// Upper left
			if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) {
				while (x_zs-i >= 0 && y_zs-i >= 0) {
					if (board[x_zs-i][y_zs-i] != 'E') {
						if (board[x_zs-i][y_zs-i] != opponent(team_moved)) {
								break;
							}
						board[x_zs-i][y_zs-i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Upper
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) {
				while (x_zs-i >= 0) {
					if (board[x_zs-i][y_zs] != 'E') {
						if (board[x_zs-i][y_zs] != opponent(team_moved)) {
								break;
							}
						board[x_zs-i][y_zs] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Upper right
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) {
				while (x_zs-i >= 0 && y_zs+i < column_limit) {
					if (board[x_zs-i][y_zs+i] != 'E') {
						if (board[x_zs-i][y_zs+i] != opponent(team_moved)) {
								break;
							}
						board[x_zs-i][y_zs+i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Left
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0) {
				while (y_zs-i >= 0) {
					if (board[x_zs][y_zs-i] != 'E') {
						if (board[x_zs][y_zs-i] != opponent(team_moved)) {
								break;
							}
						board[x_zs][y_zs-i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Right
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) {
				while (y_zs+i < column_limit) {
					if (board[x_zs][y_zs+i] != 'E') {
						if (board[x_zs][y_zs+i] != opponent(team_moved)) {
								break;
							}
						board[x_zs][y_zs+i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Down Left
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0) {
				while (x_zs+i < row_limit && y_zs-i >= 0) {
					if (board[x_zs+i][y_zs-i] != 'E') {
						if (board[x_zs+i][y_zs-i] != opponent(team_moved)) {
								break;
							}
						board[x_zs+i][y_zs-i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Down
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) {
				while (x_zs+i < row_limit) {
					if (board[x_zs+i][y_zs] != 'E') {
						if (board[x_zs+i][y_zs] != opponent(team_moved)) {
								break;
							}
						board[x_zs+i][y_zs] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Down Right
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) {
				while (x_zs+i < row_limit && y_zs+i < column_limit) {
					if (board[x_zs+i][y_zs+i] != 'E') {
						if (board[x_zs+i][y_zs+i] != opponent(team_moved)) {
								break;
							}
						board[x_zs+i][y_zs+i] = 'E';
					}
					else
						break;
					i++;
				}
			}
		}
		// Withdraw capture
		else if (direction == 'W') {
			// Upper left
			if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) {
				while (x_ps+i < row_limit && y_ps+i < column_limit) {
					if (board[x_ps+i][y_ps+i] != 'E') {
						if (board[x_ps+i][y_ps+i] != opponent(team_moved)) {
								break;
							}
						board[x_ps+i][y_ps+i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Upper
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) {
				while (x_ps+i < row_limit) {
					if (board[x_ps+i][y_ps] != 'E') {
						if (board[x_ps+i][y_ps] != opponent(team_moved)) {
								break;
							}
						board[x_ps+i][y_ps] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Upper right
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) {
				while (x_ps+i < row_limit && y_ps-i >= 0) {
					if (board[x_ps+i][y_ps-i] != 'E') {
						if (board[x_ps+i][y_ps-i] != opponent(team_moved)) {
								break;
							}
						board[x_ps+i][y_ps-i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Left
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0) {
				while (y_ps+i < column_limit) {
					if (board[x_ps][y_ps+i] != 'E') {
						if (board[x_ps][y_ps+i] != opponent(team_moved)) {
								break;
							}
						board[x_ps][y_ps+i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Right
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) {
				while (y_ps-i >= 0) {
					if (board[x_ps][y_ps-i] != 'E') {
						if (board[x_ps][y_ps-i] != opponent(team_moved)) {
								break;
							}
						board[x_ps][y_ps-i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Down Left
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0) {
				while (x_ps-i >= 0 && y_ps+i < column_limit) {
					if (board[x_ps-i][y_ps+i] != 'E') {
						if (board[x_ps-i][y_ps+i] != opponent(team_moved)) {
								break;
							}
						board[x_ps-i][y_ps+i] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Down
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) {
				while (x_ps-i >= 0) {
					if (board[x_ps-i][y_ps] != 'E') {
						if (board[x_ps-i][y_ps] != opponent(team_moved)) {
								break;
							}
						board[x_ps-i][y_ps] = 'E';
					}
					else
						break;
					i++;
				}
			}
			// Down Right
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) {
				while (x_ps-i >= 0 && y_ps-i >= 0) {
					if (board[x_ps-i][y_ps-i] != 'E') {
						if (board[x_ps-i][y_ps-i] != opponent(team_moved)) {
								break;
							}
						board[x_ps-i][y_ps-i] = 'E';
					}
					else
						break;
					i++;
				}
			}
		}
	}

	private void paika(int x_ps, int y_ps, int x_zs, int y_zs) {
		char team = board[x_ps][y_ps];
		board[x_zs][y_zs] = team;
		board[x_ps][y_ps] = 'E';
	}
	
	private void sacrifice(char team, int x, int y) {
		if (team == 'W') 
			board[x][y] = 'O';
		else if (team == 'B')
			board[x][y] = 'Q';
	}

// ----------------------------------------------------------------------------------
// ------------------------------- Constructors -------------------------------------
	//Default Board constructor
	public Board() {
		row_limit = 5;
		column_limit = 9;
		// ------------------- Resize the board -------------------------
		board = (char[][])resize_array(board, row_limit);
		for (int z = 0; z < board.length; z++) {
			if (board[z] == null)
				board[z] = new char[column_limit];
			else
				board[z] = (char[])resize_array(board[z], column_limit);
		}
		// --------------------------------------------------------------
		for (int i = 0; i < row_limit; i++)
			for (int j = 0; j < column_limit; j++) {
				if (i==0 || i==1)
					board[i][j] = 'B';
				else if ( i == 2 && 
					(j == 0 || j == 2 || j == 5 || j == 7)) {
					board[i][j] = 'B';
					}
				else
					board[i][j] = 'W';
				}
		board[2][4] = 'E';
	}
	
	// Board constructor with size
	public Board(int row, int column) {
		if (row >= 1 && row <= 13 && (row % 2) == 1) {
			if (column >= 1 && column <= 13 && (row % 2) == 1) {
				row_limit = row;
				column_limit = column;
				// ------------------- Resize the board -------------------------
				board = (char[][])resize_array(board, row_limit);
				for (int z = 0; z < board.length; z++) {
					if (board[z] == null)
						board[z] = new char[column_limit];
					else
						board[z] = (char[])resize_array(board[z], column_limit);
				}
				// --------------------------------------------------------------
				for (int i = 0; i < row_limit; i++) {
					for (int j = 0; j < column_limit; j++) {
						if (i < row/2) 
							board[i][j] = 'B';
						else if (i == row/2) {
							if (((j % 2) == 0 && j < column/2) 
								|| ((j % 2) == 1 && j > column/2)) {
									board[i][j] = 'B';
							}
							else if (j == column/2)
								board[i][j] = 'E';
							else 
								board[i][j] = 'W';
						}
						else
							board[i][j] = 'W';
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
				if (board[i][j] == 'W')
					total += 1;
			}
		}
		return total;
	}
	
	public int black_remaining() {
		int total = 0;
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j] == 'B')
					total += 1;
			}
		}
		return total;
	}
	
	public void display_board() {
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();
		}
	}

	// This function returns the number of captures that are
	// available for a given team.
	// also adds valid capture moves to an array for the AI 
	public int check_for_capture(char team) {
		int Captures = 0;
		validMoves.clear();
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j] == 'E') {
					if ( i > 0 && i < row_limit-1 
						&& j > 0 && j < column_limit-1) {
							// Check nodes that can move in all directions
							if ( ((i % 2) == 1 && (j % 2) == 1) ||
								((i % 2) == 0 && (j % 2) == 0)) {
									// Check up and to the left 
									// Advances capture
									if (board[i-1][j-1] == team && 
										board[i+1][j+1] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northWest = true; 
											moves.advance = true; 
											validMoves.add(moves);											}
									// Withdraw capture
									if (i != 1 && j != 1) {
										if (board[i-1][j-1] == team && 
											board[i-2][j-2] == opponent(team)) {
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
									if (board[i-1][j] == team && 
										board[i+1][j] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
												moves.x_index = i;
												moves.y_index = j; 
												moves.north = true; 
												moves.advance = true;
												validMoves.add(moves);
										}
									// Withdraw capture
									if (i != 1) {
										if (board[i-1][j] == team && 
											board[i-2][j] == opponent(team)) {
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
									if (board[i-1][j+1] == team && 
										board[i+1][j-1] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.northEast = true; 
											moves.advance = true;
											validMoves.add(moves);
										}	
									// Withdraw capture
									if (i != 1 && j != column_limit-2) {
										if (board[i-1][j+1] == team && 
											board[i-2][j+2] == opponent(team)) {
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
									if (board[i][j-1] == team && 
										board[i][j+1] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.west = true; 
											moves.advance = true;
											validMoves.add(moves);
										}
									// Withdraw capture
									if (j != 1) {	
										if (board[i][j-1] == team && 
											board[i][j-2] == opponent(team)) {
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
									if (board[i][j+1] == team && 
										board[i][j-1] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.east = true; 
											moves.advance = true;
											validMoves.add(moves);
										}
									// Withdraw capture
									if (j != column_limit-2) {
										if (board[i][j+1] == team && 
											board[i][j+2] == opponent(team)) {
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
									if (board[i+1][j-1] == team && 
										board[i-1][j+1] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southWest = true; 
											moves.advance = true;
											validMoves.add(moves);
										}
									// Withdraw capture
									if (i != row_limit-2 && j != 1) {
										if (board[i+1][j-1] == team && 
											board[i+2][j-2] == opponent(team)) {
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
									if (board[i+1][j] == team && 
										board[i-1][j] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.south = true; 
											moves.advance = true;
											validMoves.add(moves);
										}
									// Withdraw capture
									if (i != row_limit-2) {
										if (board[i+1][j] == team && 
											board[i+2][j] == opponent(team)) {
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
									if (board[i+1][j+1] == team && 
										board[i-1][j-1] == opponent(team)) {
											Captures +=1;
											PossibleMoves moves = new PossibleMoves();
											moves.x_index = i;
											moves.y_index = j; 
											moves.southEast = true; 
											moves.advance = true;
											validMoves.add(moves);
										}
									// Withdraw capture
									if ( i != row_limit-2 && 
										j != column_limit-2) {
											if (board[i+1][j+1] == team && 
												board[i+2][j+2] == opponent(team)) {
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
							if (board[i-1][j] == team && 
								board[i+1][j] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										moves.advance = true;
										validMoves.add(moves);
								}
							// Withdraw capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] == opponent(team)) {
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
							if (board[i+1][j] == team &&
								board[i-1][j] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										moves.advance = true;
										validMoves.add(moves);
								}
							// Withdraw capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] == opponent(team)) {
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
							if (board[i][j-1] == team && 
								board[i][j+1] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										moves.advance = true;
										validMoves.add(moves);
								}
							// Withdraw capture
							if ( j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] == opponent(team)) {
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
							if (board[i][j+1] == team && 
								board[i][j-1] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										moves.advance = true;
										validMoves.add(moves);
								}
							// Withdraw caputre
							if (j != column_limit-2) {
								if (board[i][j+1] == team && 
									board[i][j+2] == opponent(team)) {
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
								if (board[i][j-1] == team && 
									board[i][j+1] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the left
							// Withdraw capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i+1][j-1] == team && 
										board[i+2][j-2] == opponent(team)) {
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
								if (board[i][j+1] == team && 
									board[i][j-1] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (j != column_limit-2) {
								if (board[i][j+1] == team && 
									board[i][j+2] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the right
							// Withdraw capture
							if (row_limit > 1) {							
								if ((j % 2) == 0) {
									if (board[i+1][j+1] == team && 
										board[i+2][j+2] == opponent(team)) {
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
								if (board[i][j-1] == team && 
									board[i][j+1] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}
							// Check up and to the left
							// Withdraw capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i-1][j-1] == team && 
										board[i-2][j-2] == opponent(team)) {
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
								if (board[i][j+1] == team && 
									board[i][j-1] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.east = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (j != column_limit-1) {
								if (board[i][j+1] == team && 
									board[i][j+2] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.west = true; 
										validMoves.add(moves);
									}
							}	
							// Check up and to the right
							// Withdraw capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i-1][j+1] == team && 
										board[i-2][j+2] == opponent(team)) {
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
								if (board[i-1][j] == team && 
									board[i+1][j] == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										moves.advance = true;
										validMoves.add(moves);									
									}
							}
							// Withdraw capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j]== opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Check up and to the right
							// Withdraw capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i-1][j+1] == team && 
										board[i-2][j+2] == opponent(team)) {
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
								if (board[i+1][j] == team && 
									board[i-1][j] == opponent(team)) {
										Captures += 1;	
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the right
							// Withdraw capture
							if (column_limit > 1) {	
								if ((i %2 ) == 0) {
									if (board[i+1][j+1] == team && 
										board[i+2][j+2] == opponent(team)) {
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
								if (board[i-1][j] == team && 
									board[i+1][j] == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] == opponent(team)) {
										Captures +=1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										validMoves.add(moves);
									}
							}
							// Check up and to the left
							// Withdraw capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i-1][j-1] == team && 
										board[i-2][j-2] == opponent(team)) {
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
								if (board[i+1][j] == team && 
									board[i-1][j] == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.south = true; 
										moves.advance = true;
										validMoves.add(moves);
									}
							}
							// Withdraw capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] == opponent(team)) {
										Captures += 1;
										PossibleMoves moves = new PossibleMoves();
										moves.x_index = i;
										moves.y_index = j; 
										moves.north = true; 
										validMoves.add(moves);
									}
							}
							// Check down and to the left
							// Withdraw capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i+1][j-1] == team && 
										board[i+2][j-2] == opponent(team)) {
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
	
	public boolean valid_move(char team, char move, int x_ps, int y_ps,
	 int x_zs, int y_zs) {
		boolean valid = false;
		if (board[x_zs][y_zs] == 'E' &&
			board[x_ps][y_ps] == team) {			
				if (in_limits(x_zs, y_zs)) {
					// UP
					if ((x_ps - x_zs) > 0 && 
						(y_ps - y_zs) == 0) {  
							if (move == 'A' && 
								board[x_zs-1][y_zs] == opponent(team) &&
								x_zs > 0) {
									valid = true;
								}
							else if (move == 'W' &&
								board[x_ps+1][y_ps] == opponent(team) &&
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
							board[x_zs][y_zs-1] == opponent(team) &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'W' && 
								board[x_ps][y_ps+1] == opponent(team) &&
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
							board[x_zs][y_zs+1] == opponent(team) &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'W' &&
								board[x_ps][y_ps-1] == opponent(team) &&
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
							board[x_zs+1][y_zs] == opponent(team) &&
								x_zs < row_limit-1) {
									valid = true;
								}
							else if (move == 'W' &&
							board[x_ps-1][y_ps] == opponent(team) &&
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
								board[x_zs-1][y_zs-1] == opponent(team) &&
								x_zs > 0    &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'W'   &&
								board[x_ps+1][y_ps+1] == opponent(team) &&
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
								board[x_zs-1][y_zs+1] == opponent(team) &&
								x_zs > 0    &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'W' &&
								board[x_ps+1][y_ps-1] == opponent(team) &&
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
								board[x_zs+1][y_zs-1] == opponent(team) &&
								x_zs < row_limit-1 &&
								y_zs > 0) {
									valid = true;
								}
							else if (move == 'W' &&
								board[x_ps-1][y_ps+1] == opponent(team) &&
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
								board[x_zs+1][y_zs+1] == opponent(team) &&
								x_zs < row_limit-1 &&
								y_zs < column_limit-1) {
									valid = true;
								}
							else if (move == 'W' &&
								board[x_ps-1][y_ps-1] == opponent(team) &&
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
			board[x_ps][y_ps] == team) {
				valid = true;
			}
		else
			valid = false;
		return valid;
	}

	public void turn(char team, char move, int x_ps, int y_ps, int x_zs, int y_zs) {
		if (move == 'A') {
			capture(team, x_ps, y_ps, x_zs, y_zs, move);
			board[x_ps][y_ps] = 'M';
			board[x_zs][y_zs] = team;
		}
		else if (move == 'W') {
			capture(team, x_ps, y_ps, x_zs, y_zs, move);
			board[x_ps][y_ps] = 'M';
			board[x_zs][y_zs] = team;
		}
		else if (move == 'P') {
			paika(x_ps, y_ps, x_zs, y_zs);
		}
		else if (move == 'S') {
			sacrifice(team, x_ps, y_ps);
		}
	}
	
	public char turn_change(char team) {
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j] == 'M') {
					board[i][j] = 'E';
				}
				else if (team == 'B' && board[i][j] == 'O') {
					board[i][j] = 'E';
				}
				else if (team == 'W' && board[i][j] == 'Q') {
					board[i][j] = 'E';
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
		char team = 'W';
		int turn_limit = 10 * column_limit;
		int turn = 1;
		char move, winner;
		int x_ps, y_ps, x_zs, y_zs;
		Scanner in = new Scanner(System.in);
		
		while (turn <= turn_limit && white_remaining() > 0 && black_remaining() > 0) {
			if (team == 'W')
				System.out.println("White turn");
			else
				System.out.println("Black turn");
			System.out.print("What type of move? (A --> Advance capture, W --> Withdraw capture, P --> Paika, S --> sacarifice) ");
			move = in.next().charAt(0);	
			System.out.print("current x: ");
			x_ps = in.nextInt();
			System.out.print("current y: ");
			y_ps = in.nextInt();
			if (move == 'S') {
				x_zs = 0;
				y_zs = 0;
			}
			else {
				System.out.print("next x: ");
				x_zs = in.nextInt();
				System.out.print("next y: ");
				y_zs = in.nextInt();
			}
			while (valid_move(team, move, x_ps, y_ps, x_zs, y_zs) != true) {
				System.out.println("Move was invalid :(");
				System.out.print("What type of move? (A --> Advance capture, W --> Withdraw capture, P --> Paika, S --> sacarifice) ");
				move = in.next().charAt(0);	
				System.out.print("current x: ");
				x_ps = in.nextInt();
				System.out.print("current y: ");
				y_ps = in.nextInt();
				if (move == 'S') {
					x_zs = 0;
					y_zs = 0;
				}
				else {
					System.out.print("next x: ");
					x_zs = in.nextInt();
					System.out.print("next y: ");
					y_zs = in.nextInt();
				}
			}
			turn(team, move, x_ps, y_ps, x_zs, y_zs);
			team = turn_change(team);
			System.out.println(" ");
			System.out.println(" ");
			display_board();
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
// -----------------------------------------------------------------------------------
// ------------------------------ Main testing ---------------------------------------	
	public static void main(String[]args) {
		char winner;
		int row =0, column =0;
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Fanorona");
		System.out.println("Enter in the size of the game board");
		System.out.println("Values has to be odd and between 1 and 13");
		System.out.print("Row: ");
		row = in.nextInt();
		System.out.print("Column: ");
		column = in.nextInt();
		System.out.print("Creating board.....  ");
		Board fanorona = new Board(row,column);
		System.out.println("Board Created");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Game start");
		fanorona.display_board();
		
		winner = fanorona.game_start();
		
		if (winner == 'W')
			System.out.println("White wins!");
		else if (winner == 'B')
			System.out.println("Black wins!");
		else if (winner == 'T')
			System.out.println("Tie game!");
		
	}
//------------------------------------------------------------------------------------
}