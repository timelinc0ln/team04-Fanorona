package FanoronaGame1;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Meg
 */
public class Window extends JFrame {
    
    JFrame window = new Window("Fanorona Game");

	// Game Menu variables
	JMenuBar mainMenu = new JMenuBar();
	JMenuItem newGame = new JMenuItem("New Game"), 
				about = new JMenuItem("About"),
				instructions = new JMenuItem("Instructions"),
				exit = new JMenuItem("Exit");
	//New Game buttons
	Object[] options = {"Easy", "Medium", "Hard"};

	// Buttons for Menus
	JButton pve = new JButton("Player vs CPU"),
			back = new JButton("exit");
	GamePieces buttonArray[][] = new GamePieces[9][5];

	//Action for buttons
	GamePieces clickedButton = new GamePieces(Color.red);
	
	// Panels for Graphic interface
	JPanel	newGamePanel = new JPanel(),
			northPanel = new JPanel(),
			southPanel = new JPanel(),
			topPanel = new JPanel(),
			bottomPanel = new JPanel(),
			playingFieldPanel = new Board();

	JLabel gameTitle = new JLabel("Fanorona");
	JTextArea text = new JTextArea();

	// set window size and default color
	final int windowX = 900, windowY = 500, color = 190; 
    
    MouseListener arg0;
    
    public Window(String w){
    // Game window formatting
		window.setSize(windowX, windowY);
		window.setLocation(400, 400);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addMouseListener(arg0);
    }
    
        /**
     *
     * @param e
     */

    public void mousePressed(MouseEvent e){
            int mouse_x = e.getX();
            int mouse_y = e.getY();
            
            System.out.println("x:" + mouse_x + " y:" + mouse_y);
        }
        
        
        
//	public void mouseClicked(MouseEvent e) {
//		if(clickedButton == null) {
//			//first button clicked, remember it
//			clickedButton = (GamePieces)e.getSource();
//                        System.out.println(e.getPoint());
//		}
//		else {
//			//second button, so do the 'move'
//			GamePieces secondButton = (GamePieces)e.getSource();
//			
//			
//			//domove(clickedButton, secondButton);
//			
//			//clear clicked button so new buttons can fill the position
//			clickedButton = null;
//		}
//	}
	
        public void mouseEntered(MouseEvent e) {}

                public void mouseExited(MouseEvent e) {}
        
        
        //public void mousePressed(MouseEvent e) {
        
       // }

        public void mouseReleased(MouseEvent e) {}
	
	/*boolean chosenPiece = false;
	GamePieces buttonChosen[] = new GamePieces[2];
	
	public void buttonMove(ActionEvent clicked) {
		Object actionSource = clicked.getSource();
		if(actionSource == buttonArray[0]) {
			if(chosenPiece == false)  {
				chosenPiece = true;
				buttonChosen[0] = buttonArray[0];
			}
			else if(chosenPiece == true) { //source piece is chosen, set this to destination piece
				buttonChosen[1] = buttonArray[0];
				//now need to move buttonChosen[1] to [0] spot and re-assign colors of deleted pieces
				
				buttonChosen[0].setBackground(new Color(220, 220, 220));
				buttonChosen[1].setBackground(new Color(255, 255, 255));
			
			}
		}
		}*/
        
}