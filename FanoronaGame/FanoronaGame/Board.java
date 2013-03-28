package FanoronaGame;

class Board {
	private char board[][] = new char[5][9];

	public Board() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 9; j++) {
				if (i == 0 || i == 1)
					board[i][j] = 'B';
				else if (i == 2 && (j == 0 || j == 2 || j == 5 || j == 7))
					board[i][j] = 'B';
				else
					board[i][j] = 'W';
			}
		board[2][4] = 'E';
	}

	public char game_piece(int i, int j) {
		return board[i][j];
	}

	public int white_remaining() {
		int total = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 'W')
					total += 1;
			}
		}
		return total;
	}

	public int black_remaining() {
		int total = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 'B')
					total += 1;
			}
		}
		return total;
	}

	public void display_board() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();
		}
	}

	public int check_for_capture(char team) {
		int Captures = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 'E') {
					if (i > 0 && i < 4 && j > 0 && j < 8) {
						if (((i % 2) == 1 && (j % 2) == 1)
								|| ((i % 2) == 0 && (j % 2) == 0)) {
							// Check nodes that can move in all directions

							// Check up and to the left
							// Forwards capture
							if (board[i - 1][j - 1] == team
									&& board[i + 1][j + 1] != team
									&& board[i + 1][j + 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 1 && j != 1) {
								if (board[i - 1][j - 1] == team
										&& board[i - 2][j - 2] != team
										&& board[i - 2][j - 2] != 'E')
									Captures += 1;
							}

							// Check up
							// Forwards capture
							if (board[i - 1][j] == team
									&& board[i + 1][j] != team
									&& board[i + 1][j] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 1) {
								if (board[i][j - 1] == team
										&& board[i][j - 2] != team
										&& board[i][j - 2] != 'E')
									Captures += 1;
							}

							// Check up and to the right
							// Forwards capture
							if (board[i - 1][j + 1] == team
									&& board[i + 1][j - 1] != team
									&& board[i + 1][j - 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 1 && j != 7) {
								if (board[i - 1][j + 1] == team
										&& board[i - 2][j + 2] != team
										&& board[i - 2][j + 2] != 'E')
									Captures += 1;
							}

							// Check left
							// Forwards capture
							if (board[i][j - 1] == team
									&& board[i][j + 1] != team
									&& board[i][j + 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (j != 1) {
								if (board[i][j - 1] == team
										&& board[i][j - 2] != team
										&& board[i][j - 2] != 'E')
									Captures += 1;
							}

							// Check right
							// forward capture
							if (board[i][j + 1] == team
									&& board[i][j - 1] != team
									&& board[i][j - 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (j != 7) {
								if (board[i][j + 1] == team
										&& board[i][j + 2] != team
										&& board[i][j + 2] != 'E')
									Captures += 1;
							}

							// Check down and to the left
							// forward capture
							if (board[i + 1][j - 1] == team
									&& board[i - 1][j + 1] != team
									&& board[i - 1][j + 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 3 && j != 1) {
								if (board[i + 1][j - 1] == team
										&& board[i + 2][j - 2] != team
										&& board[i + 2][j - 2] != 'E')
									Captures += 1;
							}

							// Check down
							// Forwards capture
							if (board[i + 1][j] == team
									&& board[i - 1][j] != team
									&& board[i - 1][j] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 3) {
								if (board[i + 1][j] == team
										&& board[i + 2][j] != team
										&& board[i + 2][j] != 'E')
									Captures += 1;
							}

							// Check down and to the right
							// Forwards capture
							if (board[i + 1][j + 1] == team
									&& board[i - 1][j - 1] != team
									&& board[i - 1][j - 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 3 && j != 7) {
								if (board[i + 1][j + 1] == team
										&& board[i + 2][j + 2] != team
										&& board[i + 2][j + 2] != 'E')
									Captures += 1;
							}
						} else {
							// Check nodes that can move up, down, left, and
							// right

							// Check up
							// Forwards capture
							if (board[i - 1][j] == team
									&& board[i + 1][j] != team
									&& board[i + 1][j] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 1) {
								if (board[i - 1][j] == team
										&& board[i - 2][j] != team
										&& board[i - 2][j] != 'E')
									Captures += 1;
							}

							// Check down
							// Forwards capture
							if (board[i + 1][j] == team
									&& board[i - 1][j] != team
									&& board[i - 1][j] != 'E')
								Captures += 1;

							// Backwards capture
							if (i != 3) {
								if (board[i + 1][j] == team
										&& board[i + 2][j] != team
										&& board[i + 2][j] != 'E')
									Captures += 1;
							}

							// Check left
							// Forwards capture
							if (board[i][j - 1] == team
									&& board[i][j + 1] != team
									&& board[i][j + 1] != 'E')
								Captures += 1;

							// Backwards capture
							if (j != 1) {
								if (board[i][j - 1] == team
										&& board[i][j - 2] != team
										&& board[i][j - 2] != 'E')
									Captures += 1;
							}

							// Check right
							// Forwards capture
							if (board[i][j + 1] == team
									&& board[i][j - 1] != team
									&& board[i][j - 1] != 'E')
								Captures += 1;

							// Backwards caputre
							if (j != 7) {
								if (board[i][j + 1] == team
										&& board[i][j + 2] != team
										&& board[i][j + 2] != 'E')
									Captures += 1;
							}
						}
					} else if (i == 0) {
						// Checks the top edge
						if (j > 0) {
							// Check left
							// Forwards capture
							if (j != 8) {
								if (board[i][j - 1] == team
										&& board[i][j + 1] != team
										&& board[i][j + 1] != 'E')
									Captures += 1;
							}

							// Backwards capture
							if (j != 1) {
								if (board[i][j - 1] == team
										&& board[i][j - 2] != team
										&& board[i][j - 2] != 'E')
									Captures += 1;
							}

							// Check down and to the left
							// Backwards capture
							if ((j % 2) == 0) {
								if (board[i + 1][j - 1] == team
										&& board[i + 2][j - 2] != team
										&& board[i + 2][j - 2] != 'E')
									Captures += 1;
							}

						}
						if (j < 8) {
							// Check right
							// Forwards capture
							if (j != 0) {
								if (board[i][j + 1] == team
										&& board[i][j - 1] != team
										&& board[i][j - 1] != 'E')
									Captures += 1;
							}

							// Backwards capture
							if (j != 7) {
								if (board[i][j + 1] == team
										&& board[i][j + 2] != team
										&& board[i][j + 2] != 'E')
									Captures += 1;
							}

							// Check down and to the right
							// Backwards capture
							if ((j % 2) == 0) {
								if (board[i + 1][j + 1] == team
										&& board[i + 2][j + 2] != team
										&& board[i + 2][j + 2] != 'E')
									Captures += 1;
							}
						}
					} else if (i == 4) {
						// Checks the bottom edge
						if (j > 0) {
							// Check left
							// Forwards capture
							if (j != 8) {
								if (board[i][j - 1] == team
										&& board[i][j + 1] != team
										&& board[i][j + 1] != 'E')
									Captures += 1;
							}

							// Backwards capture
							if (j != 1) {
								if (board[i][j - 1] == team
										&& board[i][j - 2] != team
										&& board[i][j - 2] != 'E')
									Captures += 1;
							}

							// Check up and to the left
							// Backwards capture
							if ((j % 2) == 0) {
								if (board[i - 1][j - 1] == team
										&& board[i - 2][j - 2] != team
										&& board[i - 2][j - 2] != 'E')
									Captures += 1;
							}

						}
						if (j < 8) {
							// Check right
							// Forwards capture
							if (j != 0) {
								if (board[i][j + 1] == team
										&& board[i][j - 1] != team
										&& board[i][j - 1] != 'E')
									Captures += 1;
							}

							// Backwards capture
							if (j != 7) {
								if (board[i][j + 1] == team
										&& board[i][j + 2] != team
										&& board[i][j + 2] != 'E')
									Captures += 1;
							}

							// Check up and to the right
							// Backwards capture
							if ((j % 2) == 0) {
								if (board[i - 1][j + 1] == team
										&& board[i - 2][j + 2] != team
										&& board[i - 2][j + 2] != 'E')
									Captures += 1;
							}
						}
					} else if (j == 0) {
						// Checks the left edge
						if (i > 0) {
							// Check up
							// Forwards capture
							if (i != 4) {
								if (board[i - 1][j] == team
										&& board[i + 1][j] != team
										&& board[i + 1][j] != 'E')
									Captures += 1;
							}
							// Backwards capture
							if (i != 1) {
								if (board[i - 1][j] == team
										&& board[i - 2][j] != team
										&& board[i - 2][j] != 'E')
									Captures += 1;
							}

							// Check up and to the right
							// Backwards capture
							if ((i % 2) == 0) {
								if (board[i - 1][j + 1] == team
										&& board[i - 2][j + 2] != team
										&& board[-+2][j + 2] != 'E')
									Captures += 1;
							}
						}

						if (i < 4) {
							// Check down
							// Forwards capture
							if (i != 0) {
								if (board[i + 1][j] == team
										&& board[i - 1][j] != team
										&& board[i - 1][j] != 'E')
									Captures += 1;
							}
							// Backwards capture
							if (i != 3) {
								if (board[i + 1][j] == team
										&& board[i + 2][j] != team
										&& board[i + 2][j] != 'E')
									Captures += 1;
							}
							// Check down and to the right
							// Backwards capture
							if ((i % 2) == 0) {
								if (board[i + 1][j + 1] == team
										&& board[i + 2][j + 2] != team
										&& board[i + 2][j + 2] != 'E')
									Captures += 1;
							}
						}
					} else if (j == 8) {
						// Checks right edge
						if (i > 0) {
							// Check up
							// Forwards capture
							if (i != 4) {
								if (board[i - 1][j] == team
										&& board[i + 1][j] != team
										&& board[i + 1][j] != 'E')
									Captures += 1;
							}
							// Backwards capture
							if (i != 1) {
								if (board[i - 1][j] == team
										&& board[i - 2][j] != team
										&& board[i - 2][j] != 'E')
									Captures += 1;
							}

							// Check up and to the left
							// Backwards capture
							if ((i % 2) == 0) {
								if (board[i - 1][j - 1] == team
										&& board[i - 2][j - 2] != team
										&& board[-+2][j - 2] != 'E')
									Captures += 1;
							}
						}

						if (i < 4) {
							// Check down
							// Forwards capture
							if (i != 0) {
								if (board[i + 1][j] == team
										&& board[i - 1][j] != team
										&& board[i - 1][j] != 'E')
									Captures += 1;
							}
							// Backwards capture
							if (i != 3) {
								if (board[i + 1][j] == team
										&& board[i + 2][j] != team
										&& board[i + 2][j] != 'E')
									Captures += 1;
							}
							// Check down and to the left
							// Backwards capture
							if ((i % 2) == 0) {
								if (board[i + 1][j - 1] == team
										&& board[i + 2][j - 2] != team
										&& board[i + 2][j - 2] != 'E')
									Captures += 1;
							}
						}
					}
				}
			}
		}
		return Captures;
	}

	public static void main(String[] args) {
		Board fanorona = new Board();
		fanorona.display_board();
		System.out.println("White has " + fanorona.white_remaining()
				+ " remaining");
		System.out.println("Black has " + fanorona.black_remaining()
				+ " remaining");
		System.out.println("White currently has "
				+ fanorona.check_for_capture('W') + " captures");
	}
}
