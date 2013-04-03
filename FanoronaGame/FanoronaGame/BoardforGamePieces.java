package FanoronaGame;



import java.io.IOException;
import javax.swing.JPanel;

////////////////I TRIED TO CHANGE THIS TO DEAL WITH GAMEPIECES RATHER THAN JUST CHARACTERS... DON'T THINK I GOT ANYWHERE
/////////////////BUT MAYBE IT CAN BE USED? NOT SURE. THIS IS WHY I MADE playingFieldPanel STATIC IN THE FANORONAGAME CLASS//////


// This function returns the number of captures that are
	// available for a given team.
public class BoardforGamePieces extends FanoronaGame {
      JPanel panel;
	MiniMaxTree mmTree = new MiniMaxTree();


 //---------------- This is a resize function for an array --------------------------
	private static Object resize_array (Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0)
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		return newArray; 
	}
//----------------------------------------------------------------------------------
//-------------------------- Private members ---------------------------------------    
	private int row_limit;
	private int column_limit;
	private GamePieces board[][] = new GamePieces[row_limit][column_limit];
     // private JPanel playingFieldPanel;
 

              
//----------------------------------------------------------------------------------
//------------------------------- Constructors -------------------------------------
	//Default constructor
  /**
   *
   * @throws IOException
   */
  public BoardforGamePieces() throws IOException {
     // this.board = FanoronaGame.buttonArray;
      this.panel = playingFieldPanel;
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
					board[i][j] = new GamePieces('B',i,j);
				else if ( i == 2 && 
					(j == 0 || j == 2 || j == 5 || j == 7)) {
					board[i][j] = new GamePieces('B',i,j);
					}
				else
					board[i][j] = new GamePieces('W',i,j);
				}
		board[2][4] = new GamePieces('E',2,4);
	}

	// AI constructor with size
	public BoardforGamePieces(int row, int column) throws IOException {
    //  this.board = FanoronaGame.buttonArray;
      this.panel = playingFieldPanel;
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
							board[i][j] = new GamePieces('B',i,j);
						else if (i == row/2) {
							if (((j % 2) == 0 && j < column/2) 
								|| ((j % 2) == 1 && j > column/2)) {
									board[i][j] = new GamePieces('B',i,j);
							}
							else if (j == column/2)
								board[i][j] = new GamePieces('E',i,j);
							else 
								board[i][j] = new GamePieces('W',i,j);
						}
						else
							board[i][j] = new GamePieces('W',i,j);
					}
				}
			}
		}
	}		
//----------------------------------------------------------------------------------
//---------------------------- Public Functions ------------------------------------
    GamePieces selected;

      
	int upper_row;
	int upper_column;
	
	public int check_for_capture() 
      {
          int[][] dirCaptures; //first >> 0 = fwd; 1 = back; //second >> 0 = up;// 1 = down;// 2 = left; 3 = right; // 4 = top lft; 5 = up right; // 6 = bottm lft; 7 = bottom right;
          dirCaptures[1][8] = 0;
          
          for (int i = 0; i < row_limit; i++) 
          {
              for (int j = 0; j < column_limit; j++) 
              {
                  if (board[i][j].team == selected.checkTeam(i, j))
                  {
                      if ( i > 0 && i < row_limit-1 && j > 0 && j < column_limit-1)
                      {
                          // Check nodes that can move in all directions
                          if ( ((i % 2) == 1 && (j % 2) == 1) || ((i % 2) == 0 && (j % 2) == 0))
                          {
                              // Check up and to the left 
                              // Forwards capture
                              if (board[i-1][j-1].team == selected.checkTeam(i-1, j-1) && (board[i+1][j+1].team != selected.checkTeam(i+1, j+1))) 
                              {
                            	  dirCaptures[0][4] += 1;	
                              }
                              // Backwards capture
                              if (i != 1 && j != 1) 
                              {
                                      if (board[i-1][j-1].team == selected.checkTeam(i-1, j-1) && board[i-2][j-2].team != selected.checkTeam(i-2, j-2))
                                      {
                                    	  dirCaptures[1][4] += 1;
                                      }
                              }	
                              // Check up 
                              // Forwards capture
                              if (board[i-1][j].team == selected.checkTeam(i-1, j) && board[i+1][j].team != selected.checkTeam(i+1, j))
                              {
                            	  dirCaptures[0][0] += 1;
                              }
                              // Backwards capture
                              if (i != 1) 
                              {
                            	  if (board[i-1][j].team == selected.checkTeam(i-1, j) && board[i-2][j].team != selected.checkTeam(i-2, j))
                                      {
                                    	  dirCaptures[1][0] += 1;
                                      }
                              }
                              // Check up and to the right 
                              // Forwards capture
                              if (board[i-1][j+1].team == selected.checkTeam(i-1, j+1) && board[i+1][j-1].team != selected.checkTeam(i+1, j-1)) 
                              {
                            	  dirCaptures[0][5] += 1;
                              }	
                              // Backwards capture
                              if (i != 1 && j != column_limit-2) 
                              {
                            	  if (board[i-1][j+1].team == selected.checkTeam(i-1, j+1) && board[i-2][j+2].team != selected.checkTeam(i-2, j+2)) 
                                      {
                            		  		dirCaptures[1][5] += 1;
                                      }
                              }	
                              // Check left 
                              // Forwards capture
                              if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j+1].team !=  selected.checkTeam(i, j+1)) 
                              {
                            	  dirCaptures[0][2] += 1;
                              }
                              // Backwards capture
                              if (j != 1) 
                              {
                            	  if (board[i][j-1].team == selected.checkTeam(i-1, j+1) && board[i][j-2].team != selected.checkTeam(i, j-2))
                                      {
                                    	  dirCaptures[1][2] += 1;
                                      }
                              }
                              // Check right 
                              // Forwards capture
                              if (board[i][j+1].team == selected.checkTeam(i-1, j+1) && board[i][j-1].team != selected.checkTeam(i, j-1))
                              {
                                  dirCaptures[0][3] += 1;
                              }
                              // Backwards capture
                              if (j != column_limit-2) 
                              {
                                  if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j+2].team != selected.checkTeam(i, j+2)) 
                                  {
                                      dirCaptures[1][3] += 1;
                                  }
                              }	
                              // Check down and to the left 
                              // Forwards capture
                              if (board[i+1][j-1].team == selected.checkTeam(i+1, j-1) && board[i-1][j+1].team != selected.checkTeam(i-1, j+1))
                              {
                                  dirCaptures[0][6] += 1;
                              }
                              // Backwards capture
                              if (i != row_limit-2 && j != 1) 
                              {
                                      if (board[i+1][j-1].team == selected.checkTeam(i+1, j-1) && board[i+2][j-2].team != selected.checkTeam(i+2, j-2))
                                      {
                                          dirCaptures[1][6] += 1;
                                      }
                              }	
                              // Check down 
                              // Forwards capture
                              if (board[i+1][j].team == selected.checkTeam(i+1, j) && board[i-1][j].team != selected.checkTeam(i-1, j))
                              {
                                  dirCaptures[0][1] += 1;
                              }
                              // Backwards capture
                              if (i != row_limit-2) 
                              {
                                  if (board[i+1][j].team == selected.checkTeam(i+1, j) && board[i+2][j].team != selected.checkTeam(i+2, j))
                                  {
                                      dirCaptures[1][1] += 1;
                                  }
                              }	
                              // Check down and to the right 
                              // Forwards capture
                              if (board[i+1][j+1].team == selected.checkTeam(i+1, j+1) && board[i-1][j-1].team != selected.checkTeam(i-1, j-1))
                              {
                                  dirCaptures[0][7] = 1;
                              }
                              // Backwards capture
                              if ( i != row_limit-2 && j != column_limit-2) 
                              {
                                  if (board[i+1][j+1].team == selected.checkTeam(i+1, j+1) && board[i+2][j+2].team != selected.checkTeam(i+2, j+2)) 
                                  {
                                      dirCaptures[1][7] += 1;
                                  }
                              }
                          }
                          // Check nodes that can move up, down, left, and right 	
                          else {
                              // Check up 
                              // Forwards capture
                              if (board[i-1][j].team == selected.checkTeam(i-1, j) && board[i+1][j].team != selected.checkTeam(i+1, j))
                              {
                                  dirCaptures[0][0] += 1;
                              }
                              // Backwards capture
                              if (i != 1) 
                              {
                                  if(board[i-1][j].team == selected.checkTeam(i-1, j) && board[i-2][j].team != selected.checkTeam(i-2, j)) 
                                  {
                                      dirCaptures[1][0] += 1;
                                  }
                              }
                              // Check down 
                              // Forwards capture
                              if (board[i+1][j].team == selected.checkTeam(i+1, j) && board[i-1][j].team != selected.checkTeam(i-1, j))
                              {
                                  dirCaptures[0][1] += 1;
                              }
                              // Backwards capture
                              if (i != row_limit-2)
                              {
                            	  if (board[i+1][j].team == selected.checkTeam(i+1, j) && board[i+2][j].team != selected.checkTeam(i+2, j))
                                      {
                                          dirCaptures[1][1] += 1;
                                      }
                              }	
                              // Check left 
                              // Forwards capture
                              if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j+1].team != selected.checkTeam(i, j+1))
                              {
                                  dirCaptures[0][2] += 1;
                              }
                              // Backwards capture
                              if ( j != 1) 
                              {
                                  if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j-2].team != selected.checkTeam(i, j-2))
                                  {
                                      dirCaptures[1][2] += 1;
                                  }
                              }	
                              // Check right 
                              // Forwards capture
                              if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j-1].team != selected.checkTeam(i, j-1))
                              {
                                  dirCaptures[0][3] += 1;
                              }
                              // Backwards caputre
                              if (j != column_limit-2)
                              {
                                  if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j+2].team != selected.checkTeam(i, j+2))
                                  {
                                      dirCaptures[1][3] += 1;
                                  }
                              }
                          }
                      }
                      // Checks the top edge
                      else if (i == 0) 
                      {
                          if (j > 0) 
                          {
                              // Check left 
                              // Forwards capture
                              if ( j != column_limit-1) 
                              {
                                  if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j+1].team != selected.checkTeam(i, j+1))
                                  {
                                      dirCaptures[0][2] += 1;
                                  }
                              }
                              // Backwards capture
                              if (j != 1) 
                              {
                                  if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j-2].team != selected.checkTeam(i, j-2))
                                  {
                                	  dirCaptures[1][2] += 1;
                                  }
                              }
                              // Check down and to the left
                              // Backwards capture
                              if (row_limit > 1) 
                              {
                                  if ((j % 2) == 0)
                                  {
                                      if (board[i+1][j-1].team == selected.checkTeam(i+1, j-1) && board[i+2][j-2].team != selected.checkTeam(i+2, j-2))

                                      {
                                    	  dirCaptures[1][6] += 1;
                                      }
                                  }
                              }
                          }
                          if (j < column_limit-1) 
                          {
                              // Check right 
                              // Forwards capture
                              if (j != 0) 
                              {
                                  if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j-1].team != selected.checkTeam(i, j-1))
                                  {
                                	  dirCaptures[0][3] += 1;
                                  }
                              }
                                  // Backwards capture
                              if (j != column_limit-2) 
                              {
                                  if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j+2].team != selected.checkTeam(i, j+2)) 
                                  {
                                	  dirCaptures[1][3] += 1;
                                  }
                              }
                              // Check down and to the right
                              // Backwards capture
                              if (row_limit > 1) 
                              {
                                  if ((j % 2) == 0) 
                                  {
                                      if (board[i+1][j+1].team == selected.checkTeam(i+1, j+1) && board[i+2][j+2].team != selected.checkTeam(i+2, j+2))
                                      {
                                    	  dirCaptures[1][7] += 1;
                                      }
                                  }
                              }
                          }
                      }
                      // Checks the bottom edge
                      else if (i == row_limit-1) 
                      {
                          if (j > 0) 
                          {
                              // Check left 
                              // Forwards capture
                              if ( j != column_limit-2) 
                              {
                                  if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j+1].team != selected.checkTeam(i, j+1))
                                  {
                                      dirCaptures[0][2] += 1;
                                  }
                              }
                              // Backwards capture
                              if (j != 1) 
                              {
                                  if (board[i][j-1].team == selected.checkTeam(i, j-1) && board[i][j-2].team != selected.checkTeam(i, j-2))
                                  {
                                      dirCaptures[1][2] += 1;
                                  }
                              }
                              // Check up and to the left
                              // Backwards capture
                              if (row_limit > 1)
                              {
                                  if ((j % 2) == 0)
                                  {
                                      if (board[i-1][j-1].team == selected.checkTeam(i-1, j-1) && board[i-2][j-2].team != selected.checkTeam(i-2, j-2))
                                      {
                                          dirCaptures[1][4] += 1;
                                      }
                                  }
                              }
                          }
                          if (j < column_limit-1) 
                          {
                              // Check right 
                              // Forwards capture
                              if (j != 0)
                              {
                                  if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j-1].team != selected.checkTeam(i, j-1))
                                  {
                                      dirCaptures[0][3] += 1;
                                  }
                              }
                              // Backwards capture
                              if (j != column_limit-1)
                              {
                                  if (board[i][j+1].team == selected.checkTeam(i, j+1) && board[i][j+2].team != selected.checkTeam(i, j+2))
                                  {
                                      dirCaptures[1][3] += 1;
                                  }
                              }
                              // Check up and to the right
                              // Backwards capture
                              if (row_limit > 1) 
                              {
                                  if ((j % 2) == 0) 
                                  {
                                      if (board[i-1][j+1].team == selected.checkTeam(i-1, j+1) && board[i-2][j+2].team != selected.checkTeam(i-2, j+2))
                                      {
                                          dirCaptures[1][5] += 1;
                                      }
                                  }
                              }
                          }
                      }
                      // Checks the left edge
                      else if (j == 0) 
                      {
                          if (i > 0) 
                          {
                              // Check up 
                              // Forwards capture
                              if (i != row_limit-1) 
                              {
                                  if (board[i-1][j].team == selected.checkTeam(i-1, j) && board[i+1][j].team != selected.checkTeam(i+1, j))
                                  {
                                      dirCaptures[0][0] += 1;
                                  }
                              }
                              // Backwards capture
                              if (i != 1)
                              {
                                  if (board[i-1][j].team == selected.checkTeam(i-1, j) && board[i-2][j].team != selected.checkTeam(i-2, j))
                                  {
                                      dirCaptures[1][0] +=1;
                                  }
                              }
                              // Check up and to the right
                              // Backwards capture
                              if (column_limit > 1) 
                              {
                                  if ((i % 2) == 0) 
                                  {
                                      if (board[i-1][j+1].team == selected.checkTeam(i-1, j+1) && board[i-2][j+2].team != selected.checkTeam(i-2, j-2))
                                      {
                                          dirCaptures[1][5] +=1;
                                      }
                                  }
                              }
                          }
                          if (i < row_limit-1) 
                          {
                          // Check down 
                          // Forwards capture
                              if (i != 0) 
                              {
                                  if (board[i+1][j].team == selected.checkTeam(i+1, j) && board[i-1][j].team != selected.checkTeam(i+1, j))
                                  {
                                      dirCaptures[0][1] += 1;
                                  }
                              }
                          // Backwards capture
                              if (i != row_limit-2) 
                              {
                                  if (board[i+1][j].team == selected.checkTeam(i+1, j) && board[i+2][j].team != selected.checkTeam(i+1, j))
                                  {
                                      dirCaptures[1][1] += 1;
                                  }
                              }
                          // Check down and to the right
                          // Backwards capture
                              if (column_limit > 1) 
                              {
                                  if ((i %2 ) == 0) 
                                  {
                                    /*  if (board[i+1][j+1].team ==  && board[i+2][j+2].team != )
                                      {
                                          dirCaptures[1][7] +=1;
                                      }*/
                                  }
                              }
                          }
                      }
                      // Checks right edge
                      else if (j == column_limit-1) 
                      {
                          if (i > 0)
                          {
                          // Check up 
                          // Forwards capture
                              if (i != row_limit-1)
                              {
                                  if (board[i-1][j].team == && board[i+1][j].team != )
                                  {
                                      dirCaptures[0][0] += 1;
                                  }
                              }
                          // Backwards capture
                              if (i != 1)
                              {
                                  if (board[i-1][j].team ==  && board[i-2][j].team != )
                                  {
                                      dirCaptures[1][0] +=1;
                                  }
                              }
                          // Check up and to the left
                          // Backwards capture
                              if (column_limit > 1)
                              {
                                  if ((i % 2) == 0)
                                  {
                                      if (board[i-1][j-1].team ==  && board[i-2][j-2].team != )
                                      {
                                          dirCaptures[1][6] +=1;
                                      }
                                  }
                              }
                          }
                          if (i < row_limit-1)
                          {
                          // Check down 
                          // Forwards capture
                              if (i != 0)
                              {
                                  if (board[i+1][j] == new GamePieces('B') || board[i+1][j] == new GamePieces('W') && 
                                      board[i-1][j] != new GamePieces('B') || board[i-1][j] == new GamePieces('W') && 
                                      board[i-1][j] != new GamePieces('E'))
                                  {
                                      Captures += 1;
                                  }
                              }
                          // Backwards capture
                              if (i != row_limit-2)
                              {
                                  if (board[i+1][j] == new GamePieces('B') || board[i+1][j] == new GamePieces('W') && 
                                      board[i+2][j] != new GamePieces('B') || board[i+2][j] == new GamePieces('W') && 
                                      board[i+2][j] != new GamePieces('E'))
                                  {
                                      Captures += 1;
                                  }
                              }
                          // Check down and to the left
                          // Backwards capture
                              if (column_limit > 1)
                              {
                                  if ((i % 2) == 0)
                                  {
                                      if (board[i+1][j-1] == new GamePieces('B') || board[i+1][j-1] == new GamePieces('W') && 
                                          board[i+2][j-2] != new GamePieces('B') || board[i+2][j-2] == new GamePieces('W') && 
                                          board[i+2][j-2] != new GamePieces('E'))
                                      {
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

	public void capture(GamePieces team_moved, int x_ps, int y_ps, int x_zs, int y_zs, int forward) 
      {
          int i = 1;
          
          if(team_moved == new GamePieces('B'))
              team_moved = new GamePieces('B');
          if(team_moved == new GamePieces('W'))
              team_moved = new GamePieces('W');
      
          if (forward == 1) 
          {
              // Upper left
              if ((x_ps-x_zs) > 0 && (y_ps-y_zs) > 0) 
              {
                  while (x_zs-i >= 0 && y_zs-i >= 0) 
                  {
                      if (board[x_zs-i][y_zs-i] != new GamePieces('E')) 
                      {
                          if (board[x_zs-i][y_zs-i] == team_moved && 
				board[x_zs-i][y_zs-i] != new GamePieces('X')) 
                          {
                              break;
                          }
                          
                          board[x_zs-i][y_zs-i] = new GamePieces('E');
                      }
                      
                      i++;
                  }
              }
              // Upper
              else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) == 0) 
              {
                  while (x_zs-i >= 0) 
                  {
                      if (board[x_zs-i][y_zs] != new GamePieces('E')) 
                      {
                          if (board[x_zs-i][y_zs] == team_moved && 
				board[x_zs-i][y_zs] != new GamePieces('X'))
                          {
                              break;
                          }
                          
                          board[x_zs-i][y_zs] = new GamePieces('E');
                      }
                      
                      i++;
                  }
              }
              // Upper right
              else if ((x_ps-x_zs) > 0 && (y_ps-y_zs) < 0) 
              {
                  while (x_zs-i >= 0 && y_zs+i < column_limit) 
                  {
                      if (board[x_zs-i][y_zs+i] != new GamePieces('E'))
                      {
                          if (board[x_zs-i][y_zs+i] == team_moved && 
				board[x_zs-i][y_zs+i] != new GamePieces('X')) 
                          {
                              break;
                          }
                          
                          board[x_zs-i][y_zs+i] = new GamePieces('E');
                      }
                      
                      i++;
                  }
              }
              // Left
              else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) > 0)
              {
                  while (y_zs-i >= 0)
                  {
                      if (board[x_zs][y_zs-i] != new GamePieces('E'))
                      {
                          if (board[x_zs][y_zs-i] == team_moved && 
                              board[x_zs][y_zs-i] != new GamePieces('X'))
                          {
                              break;
                          }

                          board[x_zs][y_zs-i] = new GamePieces('E');
                      }
                                  
                          i++;
                      }
              }
              // Right
              else if ((x_ps-x_zs) == 0 && (y_ps-y_zs) < 0) 
              {
                  while (y_zs+i < column_limit)
                  {
                      if (board[x_zs][y_zs+i] != new GamePieces('E'))
                      {
                          if (board[x_zs][y_zs+i] == team_moved && 
                              board[x_zs][y_zs+i] != new GamePieces('X'))
                          {
                              break;
                          }

                          board[x_zs][y_zs+i] = new GamePieces('E');

                      }

                      i++;
                  }
              }
              // Down Left
              else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) > 0)
              {
                  while (x_zs+i < row_limit && y_zs-i >= 0)
                  {
                      if (board[x_zs+i][y_zs-i] != new GamePieces('E'))
                      {
                          if (board[x_zs+i][y_zs-i] == team_moved && 
                              board[x_zs+i][y_zs-i] != new GamePieces('X'))
                          {
                              break;
                          }

                          board[x_zs+i][y_zs-i] = new GamePieces('E');
                      }

                      i++;
                  }
              }
              // Down
              else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) == 0) 
              {
                  while (x_zs+i < row_limit) 
                  {
                      if (board[x_zs+i][y_zs] != new GamePieces('E')) 
                      {
                          if (board[x_zs+i][y_zs] == team_moved && 
                              board[x_zs+i][y_zs] != new GamePieces('X')) 
                          {
                              break;
                          }

                          board[x_zs+i][y_zs] = new GamePieces('E');
                      }

                      i++;
                  }
              }
              // Down Right
              else if ((x_ps-x_zs) < 0 && (y_ps-y_zs) < 0) 
              {
                  while (x_zs+i < row_limit && y_zs+i < column_limit) 
                  {
                      if (board[x_zs+i][y_zs+i] != new GamePieces('E'))
                      {
                          if (board[x_zs+i][y_zs+i] == team_moved && 
                              board[x_zs+i][y_zs+i] != new GamePieces('X'))
                          {
                              break;
                          }

                          board[x_zs+i][y_zs+i] = new GamePieces('E');
                      }

                      i++;
                  }
              }
          }
      }

//-----------------------------------------------------------------------------------
//------------------------------ Main testing ---------------------------------------	
	public static void main(String[]args) {
	fanoronagame.Board fanorona = new fanoronagame.Board();
	fanorona.display_board();
	System.out.println("White has " + fanorona.white_remaining() + " remaining");
	System.out.println("Black has " + fanorona.black_remaining() + " remaining");
	System.out.println("White currently has " + fanorona.check_for_capture('W') + " captures");
	//fanorona.capture('W', 3, 3, 2, 4, 1);
	//fanorona.capture('W', 3, 4, 2, 4, 1);
	//fanorona.capture('W', 3, 5, 2, 4, 1);
	//fanorona.capture('B', 2, 5, 2, 4, 1);
	//fanorona.capture('B', 0, 5, 1, 4, 1);
	fanorona.capture('B', 1, 4, 2, 4, 1);
	fanorona.display_board();
	fanoronagame.Board test = new fanoronagame.Board(13,1);
	test.display_board();
	System.out.println("White currently has " + test.check_for_capture('W') + " captures");
	}
}
//------------------------------------------------------------------------------------
