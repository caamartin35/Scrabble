package com.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.core.Game;
import com.core.Tile;
import com.core.Tile.TileType;
import com.util.Location;

@SuppressWarnings("serial")
class BoardPanel extends JPanel implements MouseListener{
	
	//cell width
	private final int cellWidth;
	private final int cellHeight;
	private final BufferedImage board;
	private Game game;
	private GameUI gui;
	
	public BoardPanel(Game game, GameUI gui) throws IOException{
		this.game = game;
		this.gui = gui;
		Dimension dim = new Dimension(600,600);
		this.setPreferredSize(dim);
		this.setBorder(null);
		this.board = ImageIO.read(new File("icons/board.jpg"));
		cellWidth = gui.getFrameWidth()/this.game.getBoardWidth();
		cellHeight = gui.getFrameWidth()/this.game.getBoardWidth();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
		
		if (game.isStarted()){
			displayBoardTiles(g);
			displaySelectedTile(g);
		}
	}

	public boolean isReleaseOnBoardValid(int[] releaseCoords){
		Location loc = coordsToLoc(releaseCoords);
		if (game.isValidLocation(loc) && !game.isLocationFilled(loc) && game.getTile(loc) ==  null){
			return true;
		}
		return false;
	}
	
	private boolean isClickOnBoard(int[] clickCoords) {
		return game.isValidLocation(coordsToLoc(clickCoords));
	}
	
	private void displayBoardTiles(Graphics g) {
		Tile tile;
		int[] coords;
		Location loc;
		for (int i=0; i<this.game.getBoardWidth();i++){
			for (int j=0; j<this.game.getBoardHeight();j++){
				loc = new Location(i,j);
				tile = game.getTile(loc);
				coords = locToCoords(loc);
				if (tile != null){
					if ((tile.getEnchantment() != null && tile.getEnchantment().getOwnerName().equals(game.getCurrentPlayerName())) && 
							!tile.getEnchantment().isActivated())
						g.drawImage(gui.getEnchantmentImage(), coords[0], coords[1], null);
					else
						g.drawImage(gui.findProperImage(tile.getLetter()), coords[0], coords[1], null);
				}
			}
		}
		
	}
	
	private void displaySelectedTile(Graphics g) {
		if (gui.getSelected() != null){
			
			BufferedImage letterToDraw = gui.findProperImage(gui.getSelected().getLetter());
			g.drawImage(letterToDraw, gui.getClickDownX(), gui.getClickDownY(), null);
		}
	}
	
	private Location coordsToLoc(int[] coords) {
		int[] locProps = new int[2];
		locProps[0] = coords[0]/cellWidth;
		locProps[1] = coords[1]/cellHeight;
		return new Location(locProps[0], locProps[1]);
	}

	private int[] locToCoords(Location loc){
		int[] ret = new int[2];
		ret[0] = loc.getX()*cellWidth;
		ret[1] = loc.getY()*cellHeight;
		return ret;
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
		clickCoords[0] = e.getX();
		clickCoords[1] = e.getY();
		if (game.isStarted() && !gui.getSwapping() && !gui.isBuying()){
			if (isClickOnBoard(clickCoords)){
				Tile tile;
				int[] cornerCoords = locToCoords(coordsToLoc(clickCoords));
				if (gui.isEnchanting()){
					if (game.getTile(coordsToLoc(clickCoords)) != null){
						game.applyEnchantment(coordsToLoc(clickCoords), gui.getSelectedE());
						gui.setEnchanting(false);
					}
					else 
						gui.setError(true, "Cannot place enchantment there");
				}
				gui.setOffsetX(clickCoords[0] - cornerCoords[0]);
				gui.setOffsetY(clickCoords[1] - cornerCoords[1]);
				gui.setClickDownX(clickCoords[0] - gui.getOffsetX());
				gui.setClickDownY(clickCoords[1] - gui.getOffsetY());
				if ((tile = game.getTile(coordsToLoc(clickCoords))) != null && !game.isLocationFilled(coordsToLoc(clickCoords))){
					game.removeFromWorkingSet(coordsToLoc(clickCoords));
					gui.setSelected(tile);
				}
			}
		}
		gui.repaintAll();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int[] releaseCoords = new int[2];
		releaseCoords[0] = e.getX();
		releaseCoords[1] = e.getY();
		if (game.isStarted() && !gui.getSwapping() && !gui.isBuying() && !gui.isEnchanting()){
			if (!isReleaseOnBoardValid(releaseCoords)){
				if (gui.getSelected() != null){
					if (gui.getSelected().getType() == TileType.TYPE_BLANK){
						gui.getSelected().setLetter('0');
						gui.getSelected().setValue(0);
					}
					game.addToCurrentHand(gui.getSelected());
				}
			}
			else {
				if (gui.getSelected() != null){
					if (gui.getSelected().getType() == TileType.TYPE_BLANK){
						gui.setChoosing(true);
						gui.saveRelease((coordsToLoc(releaseCoords)));
					}
					else{
						game.addToWorkingSet(gui.getSelected(), coordsToLoc(releaseCoords));
					}
					
				}
			}
			gui.setSelected(null);
		}
		gui.repaintAll();
		
	}
	
}