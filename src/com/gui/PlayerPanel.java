package com.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.core.Enchantment;
import com.core.Enchantment.EnchantmentType;
import com.core.Game;
import com.core.Tile;

@SuppressWarnings("serial")
class PlayerPanel extends JPanel implements MouseListener{

	private final Game game;
	private final GameUI gui;
	private final BufferedImage emptyHand;
	private final BufferedImage sidingLeft;
	private final BufferedImage sidingRight;
	private final static int TRANSLATE = 600;
	
	//buttons and text fields
	private JButton startGame;
	private JButton resetGame;
	private JButton addPlayer;
	private JButton playWord;
	private JButton swapTiles;
	private JButton pass;
	private JButton buy;
	private JButton bankrupt;
	private JButton loseTurn;
	private JButton takeWord;
	
	//coordinates of the tiles in the hand
	int[][] handCoords;
		
	public PlayerPanel(final Game game, final GameUI gui) throws IOException{
		this.game = game;
		this.gui = gui;
		loadHandCoords();
		Dimension dim = new Dimension(600,120);
		this.setPreferredSize(dim);
		this.setLayout(null);
		this.emptyHand = ImageIO.read(new File("icons/emptyHand.jpg"));
		this.sidingLeft = ImageIO.read(new File("icons/sidingLeft.jpg"));
		this.sidingRight = ImageIO.read(new File("icons/sidingRight.jpg"));
		
		//initialize the start game button
		startGame = new JButton("Start Game");
		startGame.setVisible(true);
		startGame.setLayout(null);
		startGame.setBounds(480, 2, 100, 50);
		startGame.setForeground(new Color(50, 60, 60));
		startGame.setBackground(new Color(217, 221, 220));
		this.add(startGame);
		
		// add functionality to the startgame button.
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getNumPlayers() >= 2){
					game.startGame();
					gui.repaintAll();
				}
				else {
					gui.setError(true, "Not Enough Players");
					gui.repaintAll();
				}
			}
		});
		
		//initialize the add player button
		addPlayer = new JButton("Add Player");
		addPlayer.setVisible(true);
		addPlayer.setLayout(null);
		addPlayer.setBounds(20, 2, 100, 50);
		addPlayer.setForeground(new Color(50, 60, 60));
		addPlayer.setBackground(new Color(217, 221, 220));
		this.add(addPlayer);
		
		// add functionality to the step button.
		addPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getNumPlayers() < 7){
					gui.setPicking(true);
					gui.repaintAll();
				}
				else {
					gui.setError(true, "Too Many Players");
					gui.repaintAll();
				}
			}
		});
				
		//initialize the play word button
		playWord = new JButton("Play Word");
		playWord.setVisible(false);
		playWord.setLayout(null);
		playWord.setBounds(145, 60, 100, 40);
		playWord.setForeground(new Color(50, 60, 60));
		playWord.setBackground(new Color(217, 221, 220));
		this.add(playWord);
		
		// add functionality to the step button.
		playWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!gui.getChoosing()){
					game.playWord();
					gui.repaintAll();
					gui.setBuying(false);
				}
			}
		});
		
		//initialize the swap tiles button
		swapTiles = new JButton("Swap");
		swapTiles.setVisible(false);
		swapTiles.setLayout(null);
		swapTiles.setBounds(250, 60, 100, 40);
		swapTiles.setForeground(new Color(50, 60, 60));
		swapTiles.setBackground(new Color(217, 221, 220));
		this.add(swapTiles);
		
		// add functionality to the step button.
		swapTiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!gui.getChoosing()){
					if (gui.getSwapping()){
						swapTiles.setText("Swap");
						gui.setSwapping(false);
					}
					else{
						swapTiles.setText("Quit Swap");
						gui.setBuying(false);
						gui.setSwapping(true);
					}
					gui.repaintAll();
				}
			}
		});
		
		//initialize the pass button
		pass = new JButton("Pass Turn");
		pass.setVisible(false);
		pass.setLayout(null);
		pass.setBounds(355, 60, 100, 40);
		pass.setForeground(new Color(50, 60, 60));
		pass.setBackground(new Color(217, 221, 220));
		this.add(pass);
		
		// add functionality to the step button.
		pass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!gui.getChoosing()){
					game.pass();
					gui.setSwapping(false);
					gui.setBuying(false);
					gui.repaintAll();
				}
			}
		});
		
		//initialize the buy button
		buy = new JButton("Estore");
		buy.setVisible(false);
		buy.setLayout(null);
		buy.setBounds(480, 10, 100, 30);
		buy.setForeground(new Color(50, 60, 60));
		buy.setBackground(new Color(217, 221, 220));
		this.add(buy);
		
		// add functionality to the buy button.
		buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!gui.getChoosing()){
					if (gui.isBuying()){
						buy.setText("Estore");
						gui.setBuying(false);
					}
					else {
						buy.setText("Quit Estore");
						gui.setBuying(true);
					}
				}
			}
		});
		
		bankrupt = new JButton("Bankrupts");
		bankrupt.setVisible(false);
		bankrupt.setLayout(null);
		bankrupt.setBounds(465, 50, 110, 20);
		bankrupt.setForeground(new Color(50, 60, 60));
		bankrupt.setBackground(new Color(217, 221, 220));
		this.add(bankrupt);
		
		bankrupt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getCurrentPlayerBankrupts() > 0 && !gui.getChoosing() && !gui.getSwapping()){
					Enchantment ench = game.removeEnchFromCurrentPlayer(EnchantmentType.BANKRUPT);
					gui.setSelectedE(ench);
					gui.setEnchanting(true);
				}
				gui.repaintAll();
			}
		});
		
		loseTurn = new JButton("Lose Turns");
		loseTurn.setVisible(false);
		loseTurn.setLayout(null);
		loseTurn.setBounds(465, 70, 110, 20);
		loseTurn.setForeground(new Color(50, 60, 60));
		loseTurn.setBackground(new Color(217, 221, 220));
		this.add(loseTurn);
		
		loseTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getCurrentPlayerLoseTurns() > 0 && !gui.getChoosing() && !gui.getSwapping()){
					Enchantment ench = game.removeEnchFromCurrentPlayer(EnchantmentType.LOSE_TURN);
					gui.setSelectedE(ench);
					gui.setEnchanting(true);
				}
				gui.repaintAll();
			}
		});
		
		takeWord = new JButton("Take Words");
		takeWord.setVisible(false);
		takeWord.setLayout(null);
		takeWord.setBounds(465, 90, 110, 20);
		takeWord.setForeground(new Color(50, 60, 60));
		takeWord.setBackground(new Color(217, 221, 220));
		this.add(takeWord);
		
		takeWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (game.getCurrentPlayerTakeWords() > 0 && !gui.getChoosing() && !gui.getSwapping()){
					Enchantment ench = game.removeEnchFromCurrentPlayer(EnchantmentType.TAKE_WORD);
					gui.setSelectedE(ench);
					gui.setEnchanting(true);
				}
				gui.repaintAll();
			}
		});
		
		//initialize the start game button
		resetGame = new JButton("Reset");
		resetGame.setVisible(false);
		resetGame.setLayout(null);
		resetGame.setBounds(255, 60, 100, 40);
		takeWord.setForeground(new Color(50, 60, 60));
		takeWord.setBackground(new Color(217, 221, 220));
		this.add(resetGame);
		
		// add functionality to the resetgame button.
		resetGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				game.reset();
				gui.repaintAll();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(emptyHand, 144, 0, null);
		g.drawImage(sidingLeft, 0, 0, 145, 50, null);
		g.drawImage(sidingRight, 455, 0, 145, 50, null);
		g.setColor(new Color(107,141, 178));
		g.fillRect(0, 50, 600, 70);
		
		if (game.isStarted()){
			startGame.setVisible(false);
			addPlayer.setVisible(false);
			playWord.setVisible(true);
			swapTiles.setVisible(true);
			pass.setVisible(true);
			buy.setVisible(true);
			bankrupt.setVisible(true);
			loseTurn.setVisible(true);
			takeWord.setVisible(true);
			displayPlayers(g);
			displayNumTiles(g);
			displayEnchantmentNums(g);
			displayHand(g);
			displaySelectedTile(g);
		}
		else{
			startGame.setVisible(true);
			addPlayer.setVisible(true);
			playWord.setVisible(false);
			swapTiles.setVisible(false);
			pass.setVisible(false);
			buy.setVisible(false);
			bankrupt.setVisible(false);
			loseTurn.setVisible(false);
			takeWord.setVisible(false);
			if (game.isEnded()){
				displayWinner(g);
				resetGame.setVisible(true);
			}
			else
				resetGame.setVisible(false);
				
		}
		
		if (gui.getError()){
			displayError(g, gui.getErrorMessage());
		}
			
	}

	private void displayError(Graphics g, String errorMessage) {
		g.setColor(Color.RED);
		g.drawString(errorMessage, 245, 115);
		gui.setError(false, null);
		
	}

	private void displayHand(Graphics g) {
		ArrayList<Tile> currentHand = game.getCurrentHand();
		BufferedImage letterToDraw;
		for (int i=0; i<currentHand.size();i++){
			letterToDraw = gui.findProperImage(currentHand.get(i).getLetter());
			g.drawImage(letterToDraw, handCoords[i][0], handCoords[i][1], null);
		}
		
	}
	
	public boolean isClickOnHand(int[] clickCoords){
		for (int i=0; i<game.getCurrentHand().size();i++){
			if (gui.isOnTile(handCoords[i], clickCoords)){
				System.out.println("PICKING UP TILE");
				gui.setOffsetX(clickCoords[0] - handCoords[i][0]);
				gui.setOffsetY(clickCoords[1] - handCoords[i][1]);
				gui.setClickDownX(clickCoords[0] - gui.getOffsetX());
				gui.setClickDownY(clickCoords[1] - gui.getOffsetY() + TRANSLATE);
				gui.setSelected(game.getCurrentHand().get(i));
				return true;
			}
		}
		return false;
	}
	
	private void displaySelectedTile(Graphics g) {
		if (gui.getSelected() != null){
			
			BufferedImage letterToDraw = gui.findProperImage(gui.getSelected().getLetter());
			g.drawImage(letterToDraw, gui.getClickDownX(), gui.getClickDownY() - TRANSLATE, null);
		}
	}
	
	private void loadHandCoords(){
		int [][] handCoords = 
				{{146, 4},
				{191, 4},
				{235, 4},
				{279, 4},
				{324, 4},
				{369, 4},
				{414, 4}};
		this.handCoords = handCoords;
	}
	
	private void displayPlayers(Graphics g) {
		String[] names = game.getPlayerNames();
		int[] scores = game.getPlayerScores();
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		g.setColor(new Color(217,221,220));
		g.fillRect(5, 5, 125, 98);
		g.setColor(Color.BLACK);
		g.drawRect(5,5,125,98);
		for (int i=0; i<scores.length;i++){
			if (names[i].equals(game.getCurrentPlayerName())){
				g.setColor(new Color(220,150,10));
				g.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
			}
			else {
				g.setColor(new Color(0,0,10));
				g.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
			}
			g.drawString(names[i] + ":", 15, 20 + i*13);
			g.drawString(" " + scores[i], 110, 20 + i*13);
		}
	}

	private void displayWinner(Graphics g){
		System.out.println("DISPLAYING WINNER");
		String winner = game.getWinnerName();
		g.setColor(Color.WHITE);
		g.drawString("Winner is " + winner, 260, 115);
	}
	
	private void displayEnchantmentNums(Graphics g) {
		int numBankrupts = game.getCurrentPlayerBankrupts();
		int numTakeWords = game.getCurrentPlayerTakeWords();
		int numLoseTurns = game.getCurrentPlayerLoseTurns();
		
		g.setColor(new Color(217, 221, 220));
		g.drawString(": " + numBankrupts, 575, 65);
		g.drawString(": " + numLoseTurns, 575, 85);
		g.drawString(": " + numTakeWords, 575, 105);
	}
	
	private void displayNumTiles(Graphics g){
		int numTiles = game.getNumTilesInBag();
		g.setColor(new Color(50,70,80));
		g.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
		g.drawString("Tiles Remaining: " + numTiles, 15, 115);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int[] clickCoords = new int[2];
		clickCoords[0] = e.getX();
		clickCoords[1] = e.getY() - TRANSLATE;
		if (game.isStarted() && !gui.isBuying() && !gui.isEnchanting()){
			if (isClickOnHand(clickCoords)){
				game.removeFromCurrentHand(gui.getSelected());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
}