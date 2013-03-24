package fanoronagame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;



class BoardBackground extends JPanel  {
	
		protected void paintComponent(Graphics g) {
			int x = getWidth();
			int y = getHeight();
			g.setColor(Color.black);
			g.fillRect(0, 0, x, y);
			Graphics2D g2 = (Graphics2D) g;
			int w = x/10;
			int h = y/10;
			g.setColor(Color.cyan);
			g2.setStroke(new BasicStroke(1));
			
			for (int i = 1; i < 10; i++) {
				g2.drawLine(i*w, 0, i*w, y);
			}
			for (int i = 1; i < 10; i++) {
				g2.drawLine(0, i*h, x, i*h);
			}
			
/*			g2.setColor(Color.red);
			double rowH = getHeight() / 10.0;

			for (int i = 1; i < 10; i++) {
				Line2D line = new Line2D.Double(0.0, (double) i * rowH,
				(double) getWidth(), (double) i * rowH);
				g2.draw(line);				
			}*/
		}		
	}


/*Probably in FanoronaGame..:

BufferedImage myImage = ImageIO.load(...);
playingFieldPanel.setContentPane(new ImagePanel(myImage));

*/