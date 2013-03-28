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
	public char game_piece(int i, int j) {
		return board[i][j];
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
	public int check_for_capture(char team) {
		int Captures = 0;
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
											Captures += 1;	
										}
									// Backwards capture
									if (i != 1 && j != 1) {
										if (board[i-1][j-1] == team && 
											board[i-2][j-2] != team && 
											board[i-2][j-2] != 'E'  &&
											board[i-2][j-2] != 'X') {
												Captures += 1;
											}
									}	
									// Check up 
									// Forwards capture
									if (board[i-1][j] == team && 
										board[i+1][j] != team && 
										board[i+1][j] != 'E'  &&
										board[i+1][j] != 'X') {
											Captures += 1;
										}
									// Backwards capture
									if (i != 1) {
										if (board[i-1][j] == team && 
											board[i-2][j] != team && 
											board[i-2][j] != 'E'  &&
											board[i-2][j] != 'X') {
												Captures += 1;
											}
									}
									// Check up and to the right 
									// Forwards capture
									if (board[i-1][j+1] == team && 
										board[i+1][j-1] != team && 
										board[i+1][j-1] != 'E'	&&
										board[i+1][j-1] != 'X') {
											Captures += 1;
										}	
									// Backwards capture
									if (i != 1 && j != column_limit-2) {
										if (board[i-1][j+1] == team && 
											board[i-2][j+2] != team && 
											board[i-2][j+2] != 'E'	&&
											board[i-2][j+2] != 'X') {
												Captures += 1;
											}
									}	
									// Check left 
									// Forwards capture
									if (board[i][j-1] == team && 
										board[i][j+1] != team && 
										board[i][j+1] != 'E'  &&
										board[i][j+1] != 'X') {
											Captures += 1;
										}
									// Backwards capture
									if (j != 1) {	
										if (board[i][j-1] == team && 
											board[i][j-2] != team && 
											board[i][j-2] != 'E'  &&
											board[i][j-2] != 'X') {
												Captures += 1;
											}
									}
									// Check right 
									// Forwards capture
									if (board[i][j+1] == team && 
										board[i][j-1] != team && 
										board[i][j-1] != 'E'  &&
										board[i][j-1] != 'X') {
											Captures += 1;
										}
									// Backwards capture
									if (j != column_limit-2) {
										if (board[i][j+1] == team && 
											board[i][j+2] != team && 
											board[i][j+2] != 'E'&&
											board[i][j+2] != 'X') {
												Captures += 1;
											}
									}	
									// Check down and to the left 
									// Forwards capture
									if (board[i+1][j-1] == team && 
										board[i-1][j+1] != team && 
										board[i-1][j+1] != 'E'	&&
										board[i-1][j+1] != 'X') {
											Captures += 1;
										}
									// Backwards capture
									if (i != row_limit-2 && j != 1) {
										if (board[i+1][j-1] == team && 
											board[i+2][j-2] != team && 
											board[i+2][j-2] != 'E'  &&
											board[i+2][j-2] != 'X') {
												Captures += 1;
											}
									}	
									// Check down 
									// Forwards capture
									if (board[i+1][j] == team && 
										board[i-1][j] != team && 
										board[i-1][j] != 'E'  &&
										board[i-1][j] != 'X') {
											Captures += 1;
										}
									// Backwards capture
									if (i != row_limit-2) {
										if (board[i+1][j] == team && 
											board[i+2][j] != team && 
											board[i+2][j] != 'E'  &&
											board[i+2][j] != 'X') {
												Captures += 1;
											}
									}	
									// Check down and to the right 
									// Forwards capture
									if (board[i+1][j+1] == team && 
										board[i-1][j-1] != team && 
										board[i-1][j-1] != 'E' &&
										board[i-1][j-1] != 'X') {
											Captures += 1;
										}
									// Backwards capture
									if ( i != row_limit-2 && 
										j != column_limit-2) {
											if (board[i+1][j+1] == team && 
												board[i+2][j+2] != team && 
												board[i+2][j+2] != 'E'	&&
												board[i+2][j+2] != 'X') {
													Captures += 1;
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
									Captures += 1;
								}
							// Backwards capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] != team && 
									board[i-2][j] != 'E'  &&
									board[i-2][j] != 'X') {
										Captures += 1;
									}
							}
							// Check down 
							// Forwards capture
							if (board[i+1][j] == team &&
								board[i-1][j] != team && 
								board[i-1][j] != 'E'  &&
								board[i-1][j] != 'X') {
									Captures += 1;
								}
							// Backwards capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] != team && 
									board[i+2][j] != 'E'  &&
									board[i+2][j] != 'X') {
										Captures += 1;
									}
							}	
							// Check left 
							// Forwards capture
							if (board[i][j-1] == team && 
								board[i][j+1] != team && 
								board[i][j+1] != 'E'  &&
								board[i][j+1] != 'X') {
									Captures += 1;
								}
							// Backwards capture
							if ( j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] != team && 
									board[i][j-2] != 'E'  &&
									board[i][j-2] != 'X') {
										Captures += 1;
									}
							}	
							// Check right 
							// Forwards capture
							if (board[i][j+1] == team && 
								board[i][j-1] != team && 
								board[i][j-1] != 'E'  &&
								board[i][j-1] != 'X') {
									Captures += 1;
								}
							// Backwards caputre
							if (j != column_limit-2) {
								if (board[i][j+1] == team && 
									board[i][j+2] != team &&
									board[i][j+2] != 'E'  &&
									board[i][j+2] != 'X') {
										Captures += 1;
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
										Captures += 1;
									}
							}
							// Backwards capture
							if (j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] != team && 
									board[i][j-2] != 'E'  &&
									board[i][j-2] != 'X') {
										Captures += 1;
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
											Captures += 1;
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
										Captures += 1;
									}
							}
							// Backwards capture
							if (j != column_limit-2) {
								if (board[i][j+1] == team && 
									board[i][j+2] != team && 
									board[i][j+2] != 'E'  &&
									board[i][j+2] != 'X') {
										Captures += 1;
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
											Captures += 1;
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
										Captures += 1;
									}
							}
							// Backwards capture
							if (j != 1) {
								if (board[i][j-1] == team && 
									board[i][j-2] != team && 
									board[i][j-2] != 'E'  &&
									board[i][j-2] != 'X') {
										Captures += 1;
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
											Captures += 1;
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
										Captures += 1;
									}
							}
							// Backwards capture
							if (j != column_limit-1) {
								if (board[i][j+1] == team && 
									board[i][j+2] != team && 
									board[i][j+2] != 'E'  &&
									board[i][j+2] != 'X') {
										Captures += 1;
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
											Captures += 1;
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
									}
							}
							// Backwards capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] != team && 
									board[i-2][j] != 'E'  &&
									board[i-2][j] != 'X') {
										Captures +=1;
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
									}
							}
							// Backwards capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] != team && 
									board[i+2][j] != 'E'  &&
									board[i+2][j] != 'X') {
										Captures += 1;
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
									}
							}
							// Backwards capture
							if (i != 1) {
								if (board[i-1][j] == team && 
									board[i-2][j] != team && 
									board[i-2][j] != 'E'  &&
									board[i-2][j] != 'X') {
										Captures +=1;
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
									}
							}
							// Backwards capture
							if (i != row_limit-2) {
								if (board[i+1][j] == team && 
									board[i+2][j] != team && 
									board[i+2][j] != 'E' &&
									board[i+2][j] != 'X') {
										Captures += 1;
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
	
	public void capture(char team_moved, int x_ps, int y_ps, int x_zs, int y_zs, int forward) {
		int i = 1;
		if (forward == 1) {
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
	}

	public void move(int x_ps, int y_ps, int x_zs, int y_zs) {
		char team = board[x_ps][y_ps];
		board[x_zs][y_zs] = team;
		board[x_ps][y_ps] = 'E';
	}
	
	public void sacrifice(int x, int y) {
		board[x][y] = 'X';
	}
	
// -----------------------------------------------------------------------------------
// ------------------------------ Main testing ---------------------------------------	
	public static void main(String[]args) {
	Board fanorona = new Board();
	fanorona.display_board();
	System.out.println("White has " + fanorona.white_remaining() + " remaining");
	System.out.println("Black has " + fanorona.black_remaining() + " remaining");
	fanorona.sacrifice(2, 5);
	System.out.println("White currently has " + fanorona.check_for_capture('W') + " captures");
	//fanorona.capture('W', 3, 3, 2, 4, 1);
	//fanorona.capture('W', 3, 4, 2, 4, 1);
	//fanorona.capture('W', 3, 5, 2, 4, 1);
	//fanorona.capture('B', 2, 5, 2, 4, 1);
	//fanorona.capture('B', 0, 5, 1, 4, 1);
	fanorona.capture('B', 1, 4, 2, 4, 1);
	fanorona.display_board();
	Board test = new Board(13,1);
	test.display_board();
	System.out.println("White currently has " + test.check_for_capture('W') + " captures");
	}
//------------------------------------------------------------------------------------
}