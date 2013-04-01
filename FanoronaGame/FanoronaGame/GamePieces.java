package FanoronaGame;


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GamePieces extends JButton {
	Color c;
	int x, y;

	public GamePieces(char color, int x, int y) {
		// get the color of the piece based on the data array
		if (color == 'B') {
			c = Color.black;
		} else if (color == 'W') {
			c = Color.white;
		} else if (color == 'E') {
			c = new Color(110,110,110);
			setContentAreaFilled(false);
			setBorderPainted(false);
		} else {
			color = 'X';
			c = Color.gray; //sacrifice piece, light grey color means blocked
		}
		this.x = x;
		this.y = y;
		// These statements enlarge the button so that it
		// becomes a circle rather than an oval.
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);

		// This call causes the JButton not to paint
		// the background.
		// This allows us to paint a round background.
		setContentAreaFilled(false);
	}

	// Paint the round background and label.
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			// You might want to make the highlight color
			// a property of the GamePieces class.
			g.setColor(Color.red);
		} else {
			g.setColor(getBackground()) ;
		}
		g.fillOval(10, 10,  getWidth()-10, getWidth()-10);
		
		// This call will paint the label and the
		// focus rectangle.
		super.paintComponent(g);
	}

	// Paint the border of the button using a simple stroke.
	@Override
	protected void paintBorder(Graphics g) {
		g.drawOval(10, 10, getWidth()-10, getWidth()-10);
		//g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight()); // very
		// ghetto grid solution
	}
	
	public void setColor(Color clr) {
		this.setBackground(clr);
	}
	
	GamePieces source = null;
   	GamePieces target = null;
	
	public Color getColor() { //returns what c is set to. So the actual color (black, white, 110, or grey)
            return this.c;
        }
        
         public int getXPos()
         {
         	int xpos;
    		xpos = this.x;
    		return xpos;
    	}
    
    	public int getYPos()
    	{
    		int ypos;
    		ypos = this.y;
    		return ypos;
    	}
     
    
    
     public boolean legalMove(GamePieces t) { //source piece calls it
    	 if(this.getXPos() == t.getXPos() || this.getXPos()+1 == t.getXPos()+1 || this.getXPos()-1 == t.getXPos()-1
    			 && this.getYPos() == t.getYPos() || this.getYPos()+1 == t.getYPos()+1 || this.getYPos()-1 == t.getYPos()-1)
    	 	return true;
    	 else
    		return false;
     }

//	// Hit detection.
	Shape shape;

	public boolean contains(int x, int y) {
		// If the button has changed size,
		// make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}

	//public String pieceLoc(GamePieces g[][]) { TRYING TO MAKE A FUCTION RETURN A STRING OF X AND Y GIVEN A GAMEPIECES OBJ
    	//   if(FanoronaGame.)
    	//}
