

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;

public class FanoronaGame extends JFrame {
    
    public class AL extends KeyAdapter {
        public void keyPessed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
        }
        public void keyReleased(KeyEvent e) {
            
        }
    }
    

    public FanoronaGame() { //create the window
        setTitle("Team04-Fanorona");
        setSize(900, 500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) { //paint graphics to screen
        g.setColor(Color.red);
        g.drawString("Fanorona Game", 400, 250);
        g.setColor(Color.blue);
        g.drawString("Created by: Megan Kerins, Matt Hacker, and Patrick Casey", 300, 280);
        //g.mainMenu();
    }
    
    public void newPaint(Graphics g) { //After I figure out how to make the Initial display go away..
        g.clearRect(0, 0, getWidth(), getHeight() );
        g.drawRect(100, 800, 300, 500);
       // g.drawLine();
        repaint();
    }
    
    /** Adds a "New Game" button to the startup screen
    *   Removes Text fields when pressed
    *   Proceeds to launch
    */
    public void mainMenu(){
    	JFrame menu = new JFrame();
    	JButton start = new JButton("New Game");
    }
    
    
    public static void main(String[] args) {
        new FanoronaGame();
    }
}
