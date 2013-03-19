import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;


public class FanoronaGame extends JPanel {
    public class AL extends KeyAdapter {
        public void keyPessed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
        }
        public void keyReleased(KeyEvent e) {
            
        }
    }
    
    class clearWindow implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
           //getRootPane().getContentPane().removeAll();
        }
    }

    public FanoronaGame() { //create the window
        JPanel board = new JPanel();
        board.setSize(900,500);
        JButton startButton = new JButton("Start Game");
        JPanel buttonCenter = new JPanel();
        buttonCenter.add(startButton);
        add(buttonCenter, BorderLayout.NORTH);
        startButton.setVisible(true);
        buttonCenter.setVisible(true);
        startButton.addActionListener(new clearWindow() );
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Fanorona Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FanoronaGame newGame = new FanoronaGame();
        frame.setContentPane(newGame);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,500);
    }



     public void paint(Graphics g) { //paint graphics to screen
         g.setColor(Color.red);
         g.drawString("Fanorona Game", 400, 250);
         g.setColor(Color.blue);
         g.drawString("Created by: Megan Kerins, Matt Hacker, and Patrick Casey", 300, 280);
         g.setColor(Color.black);
         g.drawString("Click Here to Start the Game", 360, 30);
     }
     
    

     
     class Panels extends JPanel {

     private JPanel intro, game;
     private JButton startgame,newgame; 

     public Panels()
     {
         createPanel();
         addPanel();
     }

     private void createPanel()
     {
         intro = new JPanel();
         startgame = new JButton("Start Game");
         startgame.addActionListener(new addButtonListener() );

         game = new JPanel();
         newgame = new JButton("New Game");

     }

     private void addPanel()
     {
         intro.add(startgame);
         game.add(newgame);

         add(intro);

     }

     class addButtonListener implements ActionListener
     {
         public void actionPerformed(ActionEvent ae) 
         {
             getRootPane().getContentPane().removeAll();
             add(game);

             repaint();
         }
     }
     }

//    class ButtonAction {
//    	  public ButtonAction() {
//    	    JFrame frame = new JFrame("Fanorona Game");
//    	    frame.setSize(900, 500);
//    	    frame.setResizable(false);
//    	    frame.setVisible(true);
//    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	    JButton button = new JButton("Start New Game");
//
//    	    ActionListener actionListener = new ActionListener(){
//    	      public void actionPerformed(ActionEvent actionEvent) {
//    	    	  Panels game = new Panels();
//    	    	  game.add(game);
//    	      
//    	    }
//    
  /*  public void newPaint(Graphics g) { //After I figure out how to make the Initial display go away..
        g.clearRect(0, 0, 900, 500 );
        g.setColor(Color.black);
        g.drawRect(100, 800, 300, 500);
        g.setColor(Color.black);
        g.drawLine(100, 800, 300, 500);
    }
    
    public void mainMenu(){
        JFrame menu = new JFrame();
        JButton start = new JButton("New Game");
       }
    
    
    public static void main(String[] args) {
     new FanoronaGame();
     FanoronaGame.Panels.addButtonListener intro = null;
	intro.addButtonListener();
    }*/
    	
	public static void main(String args[])
	{
        new FanoronaGame();
		FanoronaGame.createGUI();
	}
}
    
