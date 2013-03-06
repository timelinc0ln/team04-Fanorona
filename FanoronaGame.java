package team04.fanorona;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

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
    }
    
    public void newPaint(Graphics g) { //After I figure out how to make the Initial display go away..
        g.drawRect(100, 800, 300, 500);
        
        repaint();
    }
    
    
    public static void main(String[] args) {
        new FanoronaGame();
    }
}
