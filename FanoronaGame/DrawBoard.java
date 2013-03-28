package FanoronaGame;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public final class DrawBoard extends JPanel {

	static Graphics g;

	
	@Override
    public boolean isOptimizedDrawingEnabled()
    {
      return false;
    }
	
	static public BufferedImage image;
	
			    public DrawBoard() {
			       try {                
			          image = ImageIO.read(new File("H:/csce315/killme/FanoronaGame/src/FanoronaGame/board.jpg"));
			       } catch (IOException ex) {
			            System.out.println("\nCould not load file\n");
			       }
			    }
			    
			   
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(image, 20, 20, this); 
			    }

//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(image, 0, 0, this); 
//		g.drawRect(50, 75, 600, 400);
//		g.setColor(Color.black);
//
//		// lines going down
//		g.drawLine(125, 75, 125, 475);
//		g.drawLine(200, 75, 200, 475);
//		g.drawLine(275, 75, 275, 475);
//		g.drawLine(350, 75, 350, 475);
//		g.drawLine(425, 75, 425, 475);
//		g.drawLine(500, 75, 500, 475);
//		g.drawLine(575, 75, 575, 475);
//		g.drawLine(650, 75, 650, 475);
//
//		// lines horizontally
//		g.drawLine(50, 175, 650, 175);
//		g.drawLine(50, 275, 650, 275);
//		g.drawLine(50, 375, 650, 375);
//		g.drawLine(50, 475, 650, 475);
//
//		// diagonal lines
//		g.drawLine(50, 75, 350, 475);
//		g.drawLine(350, 475, 650, 75);
//		g.drawLine(50, 275, 200, 475);
//		g.drawLine(50, 475, 350, 75);
//		g.drawLine(50, 275, 200, 75);
//		g.drawLine(650, 475, 350, 75);
//		g.drawLine(650, 475, 350, 75);
//		g.drawLine(500, 475, 200, 75);
//		g.drawLine(200, 475, 500, 75);
//		g.drawLine(650, 275, 500, 75);
//		g.drawLine(650, 275, 500, 475);
//	}


}