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
	List<Integer> x_index = new ArrayList<Integer>();
	List<Integer> y_index = new ArrayList<Integer>();
// -------------------------- Private Functions ---------------------------------------
	private void capture(char team_moved, int x_ps, int y_ps, int x_zs, int y_zs, char forward) {
		int i = 1;
		if (forward == 'F') {
			// Upper left
			if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) {
				while (x_zs-i >= 0 && y_zs-i >= 0) {
					if (board[x_zs-i][y_zs-i] != 'E') {
						if (board[x_zs-i][y_zs-i] == team_moved && 
							board[x_zs-i][y_zs-i] != 'X') {
								break;
							}
						board[x_zs-i][y_zs-i] = 'E';
					}
					i++;
				}
			}
			// Upper
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) {
				while (x_zs-i >= 0) {
					if (board[x_zs-i][y_zs] != 'E') {
						if (board[x_zs-i][y_zs] == team_moved && 
							board[x_zs-i][y_zs] != 'X') {
								break;
							}
						board[x_zs-i][y_zs] = 'E';
					}
					i++;
				}
			}
			// Upper right
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) {
				while (x_zs-i >= 0 && y_zs+i < column_limit) {
					if (board[x_zs-i][y_zs+i] != 'E') {
						if (board[x_zs-i][y_zs+i] == team_moved && 
							board[x_zs-i][y_zs+i] != 'X') {
								break;
							}
						board[x_zs-i][y_zs+i] = 'E';
					}
					i++;
				}
			}
			// Left
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0) {
				while (y_zs-i >= 0) {
					if (board[x_zs][y_zs-i] != 'E') {
						if (board[x_zs][y_zs-i] == team_moved && 
							board[x_zs][y_zs-i] != 'X') {
								break;
							}
						board[x_zs][y_zs-i] = 'E';
					}
					i++;
				}
			}
			// Right
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) {
				while (y_zs+i < column_limit) {
					if (board[x_zs][y_zs+i] != 'E') {
						if (board[x_zs][y_zs+i] == team_moved && 
							board[x_zs][y_zs+i] != 'X') {
								break;
							}
						board[x_zs][y_zs+i] = 'E';
					}
					i++;
				}
			}
			// Down Left
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0) {
				while (x_zs+i < row_limit && y_zs-i >= 0) {
					if (board[x_zs+i][y_zs-i] != 'E') {
						if (board[x_zs+i][y_zs-i] == team_moved && 
							board[x_zs+i][y_zs-i] != 'X') {
								break;
							}
						board[x_zs+i][y_zs-i] = 'E';
					}
					i++;
				}
			}
			// Down
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) {
				while (x_zs+i < row_limit) {
					if (board[x_zs+i][y_zs] != 'E') {
						if (board[x_zs+i][y_zs] == team_moved && 
							board[x_zs+i][y_zs] != 'X') {
								break;
							}
						board[x_zs+i][y_zs] = 'E';
					}
					i++;
				}
			}
			// Down Right
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) {
				while (x_zs+i < row_limit && y_zs+i < column_limit) {
					if (board[x_zs+i][y_zs+i] != 'E') {
						if (board[x_zs+i][y_zs+i] == team_moved && 
							board[x_zs+i][y_zs+i] != 'X') {
								break;
							}
						board[x_zs+i][y_zs+i] = 'E';
					}
					i++;
				}
			}
		}
		// Backwards capture
		else if (forward == 'B') {
			// Upper left
			if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) {
				while (x_ps+i < row_limit && y_ps+i < column_limit) {
					if (board[x_ps+i][y_ps+i] != 'E') {
						if (board[x_ps+i][y_ps+i] == team_moved && 
							board[x_ps+i][y_ps+i] != 'X') {
								break;
							}
						board[x_ps+i][y_ps+i] = 'E';
					}
					i++;
				}
			}
			// Upper
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) {
				while (x_ps+i < row_limit) {
					if (board[x_ps+i][y_ps] != 'E') {
						if (board[x_ps+i][y_ps] == team_moved && 
							board[x_ps+i][y_ps] != 'X') {
								break;
							}
						board[x_ps+i][y_ps] = 'E';
					}
					i++;
				}
			}
			// Upper right
			else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) {
				while (x_ps-i < row_limit && y_ps+i >= 0) {
					if (board[x_ps+i][y_ps-i] != 'E') {
						if (board[x_ps+i][y_ps-i] == team_moved && 
							board[x_ps+i][y_ps-i] != 'X') {
								break;
							}
						board[x_ps+i][y_ps-i] = 'E';
					}
					i++;
				}
			}
			// Left
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0) {
				while (y_ps-i < column_limit) {
					if (board[x_ps][y_ps+i] != 'E') {
						if (board[x_ps][y_ps+i] == team_moved && 
							board[x_ps][y_ps+i] != 'X') {
								break;
							}
						board[x_ps][y_ps+i] = 'E';
					}
					i++;
				}
			}
			// Right
			else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) {
				while (y_ps+i >= 0) {
					if (board[x_ps][y_ps-i] != 'E') {
						if (board[x_ps][y_ps-i] == team_moved && 
							board[x_ps][y_ps-i] != 'X') {
								break;
							}
						board[x_ps][y_ps-i] = 'E';
					}
					i++;
				}
			}
			// Down Left
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0) {
				while (x_ps+i >= 0 && y_ps-i < column_limit) {
					if (board[x_ps-i][y_ps+i] != 'E') {
						if (board[x_ps-i][y_ps+i] == team_moved && 
							board[x_ps-i][y_ps+i] != 'X') {
								break;
							}
						board[x_ps-i][y_ps+i] = 'E';
					}
					i++;
				}
			}
			// Down
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) {
				while (x_ps+i >= 0) {
					if (board[x_ps-i][y_ps] != 'E') {
						if (board[x_ps-i][y_ps] == team_moved && 
							board[x_ps-i][y_ps] != 'X') {
								break;
							}
						board[x_ps-i][y_ps] = 'E';
					}
					i++;
				}
			}
			// Down Right
			else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) {
				while (x_ps+i >= 0 && y_ps+i >= 0) {
					if (board[x_ps-i][y_ps-i] != 'E') {
						if (board[x_ps-i][y_ps-i] == team_moved && 
							board[x_ps-i][y_ps-i] != 'X') {
								break;
							}
						board[x_ps-i][y_ps-i] = 'E';
					}
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
	
	private void sacrifice(int x, int y) {
		board[x][y] = 'X';
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
	public int check_for_capture(char team) {
		int Captures = 0;
		x_index.clear();
		y_index.clear();
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j] == 'E') {
					if ( i > 0 && i < row_limit-1 
						&& j > 0 && j < column_limit-1) {
							// Check nodes that can move in all directions
							if ( ((i % 2) == 1 && (j % 2) == 1) ||
								((i % 2) == 0 && (j % 2) == 0)) {
									// Check up and to the left 
									// Forwards capture
									if (board[i-1][j-1] == team && 
										board[i+1][j+1] != team && 
										board[i+1][j+1] != 'E'  &&
										board[i+1][j+1] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);	
										}
									// Backwards capture
									if (i != 1 && j != 1) {
										if (board[i-1][j-1] == team && 
											board[i-2][j-2] != team && 
											board[i-2][j-2] != 'E'  &&
											board[i-2][j-2] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}	
									// Check up 
									// Forwards capture
									if (board[i-1][j] == team && 
										board[i+1][j] != team && 
										board[i+1][j] != 'E'  &&
										board[i+1][j] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
									// Backwards capture
									if (i != 1) {
										if (board[i-1][j] == team && 
											board[i-2][j] != team && 
											board[i-2][j] != 'E'  &&
											board[i-2][j] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}
									// Check up and to the right 
									// Forwards capture
									if (board[i-1][j+1] == team && 
										board[i+1][j-1] != team && 
										board[i+1][j-1] != 'E'	&&
										board[i+1][j-1] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}	
									// Backwards capture
									if (i != 1 && j != column_limit-2) {
										if (board[i-1][j+1] == team && 
											board[i-2][j+2] != team && 
											board[i-2][j+2] != 'E'	&&
											board[i-2][j+2] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}	
									// Check left 
									// Forwards capture
									if (board[i][j-1] == team && 
										board[i][j+1] != team && 
										board[i][j+1] != 'E'  &&
										board[i][j+1] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
									// Backwards capture
									if (j != 1) {	
										if (board[i][j-1] == team && 
											board[i][j-2] != team && 
											board[i][j-2] != 'E'  &&
											board[i][j-2] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}
									// Check right 
									// Forwards capture
									if (board[i][j+1] == team && 
										board[i][j-1] != team && 
										board[i][j-1] != 'E'  &&
										board[i][j-1] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
									// Backwards capture
									if (j != column_limit-2) {
										if (board[i][j+1] == team && 
											board[i][j+2] != team && 
											board[i][j+2] != 'E'&&
											board[i][j+2] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}	
									// Check down and to the left 
									// Forwards capture
									if (board[i+1][j-1] == team && 
										board[i-1][j+1] != team && 
										board[i-1][j+1] != 'E'	&&
										board[i-1][j+1] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
									// Backwards capture
									if (i != row_limit-2 && j != 1) {
										if (board[i+1][j-1] == team && 
											board[i+2][j-2] != team && 
											board[i+2][j-2] != 'E'  &&
											board[i+2][j-2] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}	
									// Check down 
									// Forwards capture
									if (board[i+1][j] == team && 
										board[i-1][j] != team && 
										board[i-1][j] != 'E'  &&
										board[i-1][j] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
									// Backwards capture
									if (i != row_limit-2) {
										if (board[i+1][j] == team && 
											board[i+2][j] != team && 
											board[i+2][j] != 'E'  &&
											board[i+2][j] != 'X') {
												Captures +=1;
												x_index.add(i);
												y_index.add(j);
											}
									}	
									// Check down and to the right 
									// Forwards capture
									if (board[i+1][j+1] == team && 
										board[i-1][j-1] != team && 
										board[i-1][j-1] != 'E' &&
										board[i-1][j-1] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
									// Backwards capture
									if ( i != row_limit-2 && 
										j != column_limit-2) {
											if (board[i+1][j+1] == team && 
												board[i+2][j+2] != team && 
												board[i+2][j+2] != 'E'	&&
												board[i+2][j+2] != 'X') {
													Captures +=1;
													x_index.add(i);
													y_index.add(j);
												}
									}
								}
							// Check nodes that can move up, down, left, and right 	
							else {
							// Check up 
							// Forwards capture
							if (board[i-1][j] == team && 
								board[i+1][j] != team && 
								board[i+1][j] != 'E'  &&
								board[i+1][j] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
								}
							// Backwards capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] != team && 
									board[i-2][j] != 'E'  &&
									board[i-2][j] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check down 
							// Forwards capture
							if (board[i+1][j] == team &&
								board[i-1][j] != team && 
								board[i-1][j] != 'E'  &&
								board[i-1][j] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
								}
							// Backwards capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] != team && 
									board[i+2][j] != 'E'  &&
									board[i+2][j] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}	
							// Check left 
							// Forwards capture
							if (board[i][j-1] == team && 
								board[i][j+1] != team && 
								board[i][j+1] != 'E'  &&
								board[i][j+1] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
								}
							// Backwards capture
							if ( j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] != team && 
									board[i][j-2] != 'E'  &&
									board[i][j-2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}	
							// Check right 
							// Forwards capture
							if (board[i][j+1] == team && 
								board[i][j-1] != team && 
								board[i][j-1] != 'E'  &&
								board[i][j-1] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
								}
							// Backwards caputre
							if (j != column_limit-2) {
								if (board[i][j+1] == team && 
									board[i][j+2] != team &&
									board[i][j+2] != 'E'  &&
									board[i][j+2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
						}
					}
					// Checks the top edge
					else if (i == 0) {
						if (j > 0) {
							// Check left 
							// Forwards capture
							if ( j != column_limit-1) {
								if (board[i][j-1] == team && 
									board[i][j+1] != team && 
									board[i][j+1] != 'E'  &&
									board[i][j+1] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] != team && 
									board[i][j-2] != 'E'  &&
									board[i][j-2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check down and to the left
							// Backwards capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i+1][j-1] == team && 
										board[i+2][j-2] != team && 
										board[i+2][j-2] != 'E'  &&
										board[i+2][j-2] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
								}
							}
						}
						if (j < column_limit-1) {
							// Check right 
							// Forwards capture
							if (j != 0) {
								if (board[i][j+1] == team && 
									board[i][j-1] != team && 
									board[i][j-1] != 'E'  &&
									board[i][j-1] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (j != column_limit-2) {
								if (board[i][j+1] == team && 
									board[i][j+2] != team && 
									board[i][j+2] != 'E'  &&
									board[i][j+2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check down and to the right
							// Backwards capture
							if (row_limit > 1) {							
								if ((j % 2) == 0) {
									if (board[i+1][j+1] == team && 
										board[i+2][j+2] != team && 
										board[i+2][j+2] != 'E'  &&
										board[i+2][j+2] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
								}
							}
						}
					}
					// Checks the bottom edge
					else if (i == row_limit-1) {
						if (j > 0) {
							// Check left 
							// Forwards capture
							if ( j != column_limit-2) {
								if (board[i][j-1] == team && 
									board[i][j+1] != team && 
									board[i][j+1] != 'E'  &&
									board[i][j+1] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] != team && 
									board[i][j-2] != 'E'  &&
									board[i][j-2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check up and to the left
							// Backwards capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i-1][j-1] == team && 
										board[i-2][j-2] != team && 
										board[i-2][j-2] != 'E'  &&
										board[i-2][j-2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
										}
								}
							}
						}
						if (j < column_limit-1) {
							// Check right 
							// Forwards capture
							if (j != 0) {
								if (board[i][j+1] == team && 
									board[i][j-1] != team && 
									board[i][j-1] != 'E'  &&
									board[i][j-1] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (j != column_limit-1) {
								if (board[i][j+1] == team && 
									board[i][j+2] != team && 
									board[i][j+2] != 'E'  &&
									board[i][j+2] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}	
							// Check up and to the right
							// Backwards capture
							if (row_limit > 1) {
								if ((j % 2) == 0) {
									if (board[i-1][j+1] == team && 
										board[i-2][j+2] != team && 
										board[i-2][j+2] != 'E'  &&
										board[i-2][j+2] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
								}
							}
						}
					}
					// Checks the left edge
					else if (j == 0) {
						if (i > 0) {
							// Check up 
							// Forwards capture
							if (i != row_limit-1) {
								if (board[i-1][j] == team && 
									board[i+1][j] != team && 
									board[i+1][j] != 'E'  &&
									board[i+1][j] != 'X') {
										Captures += 1;
										x_index.add(i);
										y_index.add(j);									
									}
							}
							// Backwards capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] != team && 
									board[i-2][j] != 'E'  &&
									board[i-2][j] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check up and to the right
							// Backwards capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i-1][j+1] == team && 
										board[i-2][j+2] != team && 
										board[i-2][j+2] != 'E'  &&
										board[i-2][j+2] != 'X') {
											Captures +=1;											
											x_index.add(i);
											y_index.add(j);
										}
								}
							}
						}
						if (i < row_limit-1) {
							// Check down 
							// Forwards capture
							if (i != 0) {
								if (board[i+1][j] == team && 
									board[i-1][j] != team && 
									board[i-1][j] != 'E'  &&
									board[i-1][j] != 'X') {
										Captures += 1;	
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] != team && 
									board[i+2][j] != 'E'  &&
									board[i+2][j] != 'X') {
										Captures += 1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check down and to the right
							// Backwards capture
							if (column_limit > 1) {	
								if ((i %2 ) == 0) {
									if (board[i+1][j+1] == team && 
										board[i+2][j+2] != team && 
										board[i+2][j+2] != 'E'  &&
										board[i+2][j+2] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
								}
							}
						}
					}
					// Checks right edge
					else if (j == column_limit-1) {
						if (i > 0) {
							// Check up 
							// Forwards capture
							if (i != row_limit-1) {
								if (board[i-1][j] == team && 
									board[i+1][j] != team && 
									board[i+1][j] != 'E' &&
									board[i+1][j] != 'X') {
										Captures += 1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] != team && 
									board[i-2][j] != 'E'  &&
									board[i-2][j] != 'X') {
										Captures +=1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check up and to the left
							// Backwards capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i-1][j-1] == team && 
										board[i-2][j-2] != team && 
										board[i-2][j-2] != 'E'  &&
										board[i-2][j-2] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
										}
								}
							}
						}
						if (i < row_limit-1) {
							// Check down 
							// Forwards capture
							if (i != 0) {
								if (board[i+1][j] == team && 
									board[i-1][j] != team && 
									board[i-1][j] != 'E'  &&
									board[i-1][j] != 'X') {
										Captures += 1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Backwards capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] != team && 
									board[i+2][j] != 'E' &&
									board[i+2][j] != 'X') {
										Captures += 1;
										x_index.add(i);
										y_index.add(j);
									}
							}
							// Check down and to the left
							// Backwards capture
							if (column_limit > 1) {
								if ((i % 2) == 0) {
									if (board[i+1][j-1] == team && 
										board[i+2][j-2] != team && 
										board[i+2][j-2] != 'E'  &&
										board[i+2][j-2] != 'X') {
											Captures +=1;
											x_index.add(i);
											y_index.add(j);
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
	
	public bool in_limits(int x, int y) {
		bool valid;
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
	
	public bool valid_move(char team, char move, int x_ps, int y_ps, int x_zs, int y_zs) {
		bool valid;
		char not_team;
		if (team == 'W')
			not_team = 'B';
		else
			not_team = 'W';
		if (check_for_capture(team) > 0 && move != 'P') {
			if (board[x_zs][y_zs] == 'E') {
				if (move == 'F') {
					if (in_limits(x_zs, y_zs) {
						if (y_zs > 0 ||
							x_zs > 0 ||
							y_zs < column_limit-1 ||
							x_zs < row_limit-1 ||
							((x_ps % 2) == 0 && (y_ps % 2) == 0) || 
	}

	public void turn(char team, char move, int x_ps, int y_ps, int x_zs, int y_zs) {
		Scanner in = new Scanner(System.in);
		while (check_for_capture(team) > 0 && move == 'P') {
			System.out.println("You must preform a capture move or sacrifice move");
			System.out.print("current x: ");
			x_ps = in.nextInt();
			System.out.print("current y: ");
			y_ps = in.nextInt();
			System.out.print("next x: ");
			x_zs = in.nextInt();
			System.out.print("next y: ");
			y_zs = in.nextInt();
		}
		if (move == 'F') {
			capture(team, x_ps, y_ps, x_zs, y_zs, move);
			board[x_ps][y_ps] = 'M';
			board[x_zs][y_zs] = team;
		}
		else if (move == 'B') {
			capture(team, x_ps, y_ps, x_zs, y_zs, move);
			board[x_ps][y_ps] = 'M';
			board[x_zs][y_zs] = team;
		}
		else if (move == 'P') {
			paika(x_ps, y_ps, x_zs, y_zs);
		}
		else if (move == 'S') {
			sacrifice(x_ps, y_ps);
		}
	}
	
	public char turn_change(char team) {
		for (int i = 0; i < row_limit; i++) {
			for (int j = 0; j < column_limit; j++) {
				if (board[i][j] == 'M') {
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
			System.out.print("What type of move? (F --> forward capture, B --> Backwards capture, P --> Paika, S --> sacarifice) ");
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
			turn(team, move, x_ps, y_ps, x_zs, y_zs);
			team = turn_change(team);
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
		System.out.println("Default Board");
		Board fanorona = new Board();
		fanorona.display_board();
		System.out.println("White has " + fanorona.white_remaining() + " remaining");
		System.out.println("Black has " + fanorona.black_remaining() + " remaining");
		System.out.println("White currently has " + fanorona.check_for_capture('W') + " captures");
		System.out.println("Black currently has " + fanorona.check_for_capture('B') + " captures");
		winner = fanorona.game_start();
		
		if (winner == 'W')
			System.out.println("White wins!");
		else if (winner == 'B')
			System.out.println("Black wins!");
		else if (winner == 'T')
			System.out.println("Tie game!");
		// fanorona.turn('W', 1, 2, 3, 2, 4);
		// fanorona.display_board();
		// fanorona.turn_change();
		// System.out.println("Turn change");
		// fanorona.display_board();
		// System.out.println("13x1");
		// Board test = new Board(13,1);
		// test.display_board();
		// System.out.println("White has " + test.white_remaining() + " remaining");
		// System.out.println("Black has " + test.black_remaining() + " remaining");
		// System.out.println("White currently has " + test.check_for_capture('W') + " captures");
		// System.out.println("Black currently has " + test.check_for_capture('B') + " captures");
	}
//------------------------------------------------------------------------------------
}