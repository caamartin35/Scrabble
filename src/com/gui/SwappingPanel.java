package com.gui;

import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.core.Game;
import com.core.Tile;

@SuppressWarnings("serial")
class SwappingPanel extends JPanel implements ActionListener, MouseListener{
	
	Game game;
	GameUI gui;
	private final BufferedImage emptyHand;
	private final static int TRANSLATE_X = 74;
	private final static int TRANSLATE_Y = 530;
	JLabel label;
	JButton button;
	
	//coordinates of the tiles in the hand
	int[][] swapBoxCoords;
	
	public SwappingPanel(final Game game, final GameUI gui) throws IOException{
		this.game = game;
		this.gui = gui;
		loadSwapBoxCoords();
		this.emptyHand = ImageIO.read(new File("icons/emptyHand.jpg"));
		label = new JLabel("Drag Tiles into this box to swap");
		button = new JButton("Swap");
		this.setLayout(null);
		label.setPreferredSize(new Dimension(50,50));
		label.setBounds(110, 0, 200, 20);
		button.setBounds(0, 20, 70, 50);
		this.add(label);
		this.add(button);
		button.addActionListener(this);
		
		
	}
	
	private void displaySelectedTile(Graphics g) {
		if (gui.getSelected() != null){
			
			BufferedImage letterToDraw = gui.findProperImage(gui.getSelected().getLetter());
			g.drawImage(letterToDraw, gui.getClickDownX() - TRANSLATE_X, gui.getClickDownY() - TRANSLATE_Y, null);
		}
	}
	
	private void loadSwapBoxCoords(){
		int [][] swapBoxCoords = 
				{{72, 24},
				{117, 24},
				{161, 24},
				{205, 24},
				{250, 24},
				{295, 24},
				{340, 24}};
		this.swapBoxCoords = swapBoxCoords;
	}
	
	private void displaySwapBox(Graphics g) {
		ArrayList<Tile> swapBox = game.getSwapBox();
		BufferedImage letterToDraw;
		for (int i=0; i<swapBox.size();i++){
			letterToDraw = gui.findProperImage(swapBox.get(i).getLetter());
			g.drawImage(letterToDraw, swapBoxCoords[i][0], swapBoxCoords[i][1], null);
		}
		
	}
	
	public boolean isClickOnSwapBox(int[] clickCoords){
		for (int i=0; i<game.getSwapBox().size();i++){
			if (gui.isOnTile(swapBoxCoords[i], clickCoords)){
				System.out.println("PICKING UP TILE");
				gui.setOffsetX(clickCoords[0] - swapBoxCoords[i][0]);
				gui.setOffsetY(clickCoords[1] - swapBoxCoords[i][1]);
				gui.setClickDownX(clickCoords[0] - gui.getOffsetX() + TRANSLATE_X);
				gui.setClickDownY(clickCoords[1] - gui.getOffsetY() + TRANSLATE_Y);
				gui.setSelected(game.getSwapBox().get(i));
				return true;
			}
		}
		return false;
	}
	
	public boolean isReleaseOnSwapBox(int[] clickCoords){
		for (int i=0; i<7;i++){
			if (gui.isOnTile(swapBoxCoords[i], clickCoords)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (game.isStarted() && gui.getSwapping()){
			g.drawImage(emptyHand, 70, 20,null);
			displaySwapBox(g);
			displaySelectedTile(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int[] clickCoords = new int[2];
		clickCoords[0] = e.getX() - TRANSLATE_X;
		clickCoords[1] = e.getY() - TRANSLATE_Y;
		
		if (game.isStarted()){
			if (isClickOnSwapBox(clickCoords) && gui.getSwapping()){
				game.removeFromSwapBox(gui.getSelected());
			}
		}
		gui.repaintAll();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int[] releaseCoords = new int[2];
		releaseCoords[0] = e.getX() - TRANSLATE_X;
		releaseCoords[1] = e.getY() - TRANSLATE_Y;
		
		if (game.isStarted()){
			if (isReleaseOnSwapBox(releaseCoords) && gui.getSwapping()){
				game.addToSwapBox(gui.getSelected());
				gui.setSelected(null);
			}
			else if (gui.getSwapping()){
				if (gui.getSelected() != null){
					game.addToCurrentHand(gui.getSelected());
					gui.setSelected(null);
				}
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.swapTiles();
		gui.setSwapping(false);
		gui.repaintAll();
	}
}