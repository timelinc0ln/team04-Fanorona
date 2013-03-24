package fanoronagame;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 
import java.util.*; 

public class FanoronaGame implements ActionListener {
	JFrame window = new JFrame("Fanorona Game");

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
	GamePieces buttonArray[] = new GamePieces[45];

	// Panels for Graphic interface
	JPanel	newGamePanel = new JPanel(),
			northPanel = new JPanel(),
			southPanel = new JPanel(),
			topPanel = new JPanel(),
			bottomPanel = new JPanel(),
			playingFieldPanel = new JPanel();

	JLabel gameTitle = new JLabel("Fanorona");
	JTextArea text = new JTextArea();

	// set window size and default color
	final int windowX = 900, windowY = 500, color = 190; 

	public FanoronaGame() {
		
		// Game window formatting
		window.setSize(windowX, windowY);
		window.setLocation(400, 400);
		window.setResizable(false);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Panel properties
		newGamePanel.setLayout(new GridLayout(2, 1, 2, 10));
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		southPanel.setLayout( new FlowLayout(FlowLayout.CENTER));
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		northPanel.setBackground(new Color(color-20, color-20, color-20));
		southPanel.setBackground(new Color(color, color, color));
		bottomPanel.setBackground(new Color(color, color, color));
		topPanel.setBackground(new Color(color, color, color));

		// Create Menu Bars
		mainMenu.add(newGame);
		mainMenu.add(about);
		mainMenu.add(instructions);
		mainMenu.add(exit);

		newGamePanel.add(pve);

		// Add action listeners
		newGame.addActionListener(this);
		exit.addActionListener(this);
		instructions.addActionListener(this);
		about.addActionListener(this);
		back.addActionListener(this);
		pve.addActionListener(this);

		//Game board setup
		playingFieldPanel.setLayout(new GridLayout(5, 9, 2, 2));
		playingFieldPanel.setBackground(Color.GRAY);
		for (int i = 0; i < 18; i++) {
			buttonArray[i] = new GamePieces(Color.black);
			buttonArray[i].setBackground(new Color(0, 0, 0));
			buttonArray[i].addActionListener(this);
			playingFieldPanel.add(buttonArray[i]);
			buttonArray[i].setVisible(true);
		}
		
		buttonArray[18] = new GamePieces(Color.white);
		buttonArray[18].setBackground(new Color(255, 255, 255));
		buttonArray[18].addActionListener(this);
		playingFieldPanel.add(buttonArray[18]);
		buttonArray[18].setVisible(true);
		
		buttonArray[19] = new GamePieces(Color.black);
		buttonArray[19].setBackground(new Color(0, 0, 0));
		buttonArray[19].addActionListener(this);
		playingFieldPanel.add(buttonArray[19]);
		buttonArray[19].setVisible(true);
		
		buttonArray[20] = new GamePieces(Color.white);
		buttonArray[20].setBackground(new Color(255, 255, 255));
		buttonArray[20].addActionListener(this);
		playingFieldPanel.add(buttonArray[20]);
		buttonArray[20].setVisible(true);
		
		buttonArray[21] = new GamePieces(Color.black);
		buttonArray[21].setBackground(new Color(0, 0, 0));
		buttonArray[21].addActionListener(this);
		playingFieldPanel.add(buttonArray[21]);
		buttonArray[21].setVisible(true);
		
		
		buttonArray[22] = new GamePieces(Color.GRAY);
		playingFieldPanel.add(buttonArray[22]);
		buttonArray[22].setVisible(false);
		
	
		buttonArray[23] = new GamePieces(Color.white);
		buttonArray[23].setBackground(new Color(255, 255, 255));
		buttonArray[23].addActionListener(this);
		playingFieldPanel.add(buttonArray[23]);
		buttonArray[23].setVisible(true);
		
		buttonArray[24] = new GamePieces(Color.black);
		buttonArray[24].setBackground(new Color(0, 0, 0));
		buttonArray[24].addActionListener(this);
		playingFieldPanel.add(buttonArray[24]);
		buttonArray[24].setVisible(true);
		
		buttonArray[25] = new GamePieces(Color.white);
		buttonArray[25].setBackground(new Color(255, 255, 255));
		buttonArray[25].addActionListener(this);
		playingFieldPanel.add(buttonArray[25]);
		buttonArray[25].setVisible(true);
		
		buttonArray[26] = new GamePieces(Color.black);
		buttonArray[26].setBackground(new Color(0, 0, 0));
		buttonArray[26].addActionListener(this);
		playingFieldPanel.add(buttonArray[26]);
		buttonArray[26].setVisible(true);
		
		
		for (int i = 27; i < 45; i++) {
			buttonArray[i] = new GamePieces(Color.white);
			buttonArray[i].setBackground(new Color(255, 255, 255));
			buttonArray[i].addActionListener(this);
			playingFieldPanel.add(buttonArray[i]);
			buttonArray[i].setVisible(true);
		}

		playingFieldPanel.setVisible(true);

		
		northPanel.add(mainMenu);
		southPanel.add(gameTitle);

		window.add(northPanel, BorderLayout.NORTH);
		window.add(southPanel, BorderLayout.CENTER);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent click) {
		Object actionSource = click.getSource();
		if (actionSource == newGame) {
			int option = askMessage("Are you prepared to start a new game vs CPU?" +
				" WARNING: All current progress will be lost.\n", 
				"Quit Game?", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				int playOption = JOptionPane.showOptionDialog(newGamePanel, "Select your difficulty", "Difficulty Level",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if(playOption == JOptionPane.OK_OPTION){
					window.add(playingFieldPanel, BorderLayout.CENTER);
					window.setVisible(true);
				}
				else if(playOption == JOptionPane.NO_OPTION){
					window.add(playingFieldPanel, BorderLayout.CENTER);
					window.setVisible(true);
				}
				else if(playOption == JOptionPane.CANCEL_OPTION){
					window.add(playingFieldPanel, BorderLayout.CENTER);
					window.setVisible(true);
				}
				
			}
		}
		else if (actionSource == exit) {
			int option = askMessage("Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION); 
			if(option == JOptionPane.YES_OPTION) 
				System.exit(0);
		}
		else if (actionSource == about) {
			JOptionPane.showMessageDialog(null, "This Game was created by Megan Kernis, Patrick Casey, and Matt Hacker.\n" +
					"Current Version: 0.1\n" +
					"Team 04, CSCE 315-501\n",
					"About", JOptionPane.ERROR_MESSAGE);
		}
		else if (actionSource == instructions) {
			JOptionPane.showMessageDialog(null, "Move your piece toward or way from an enemy piece to capture it.\n" +
					"You may only move a piece that will cause the capture of an enemy piece.\n" +
					"The Game will continue as long as there are valid move to be made.\n" +
					"More instructions to follow...", "Instructions", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int askMessage(String message, String title, int option) { 
		return JOptionPane.showConfirmDialog(null, message, title, option); 
	} 

	public static void main(String[] args) {
		 new FanoronaGame();
	}
}