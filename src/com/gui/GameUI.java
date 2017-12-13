package com.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import com.core.Game;
import com.core.Enchantment;
import com.core.Tile;
import com.util.Location;
import com.util.Util;
/**
 * This class represents an element in the GUI for rabbit world. This panel
 * draws the actual world, and also controls running the rabbit world
 * appropriately.
 */
@SuppressWarnings("serial")
public class GameUI extends JLayeredPane implements MouseMotionListener, MouseListener{

	Game game;
	
	private final static int FRAME_WIDTH = 600;
	private final static int FRAME_HEIGHT = 720;
	private final static int PADDING = 10;
	
	//Current selected letter
	private Tile selected;
		
	//current selected enchantment
	private Enchantment selectedE;
	
	//current mouse click down location
	private int clickDownX, clickDownY;
	private int offsetX, offsetY;
	
	//panels
	private BoardPanel bp;
	private PlayerPanel pp;
	private PickNamePanel pnp;
	private BlankTileChoicePanel btcp;
	private SwappingPanel sp;
	private EstorePanel ep;
	private JLayeredPane layeredPane;
	
	// Load all the icons
	private final BufferedImage aTile;
	private final BufferedImage bTile;
	private final BufferedImage cTile;
	private final BufferedImage dTile;
	private final BufferedImage eTile;
	private final BufferedImage fTile;
	private final BufferedImage gTile;
	private final BufferedImage hTile;
	private final BufferedImage iTile;
	private final BufferedImage jTile;
	private final BufferedImage kTile;
	private final BufferedImage lTile;
	private final BufferedImage mTile;
	private final BufferedImage nTile;
	private final BufferedImage oTile;
	private final BufferedImage pTile;
	private final BufferedImage qTile;
	private final BufferedImage rTile;
	private final BufferedImage sTile;
	private final BufferedImage tTile;
	private final BufferedImage uTile;
	private final BufferedImage vTile;
	private final BufferedImage wTile;
	private final BufferedImage xTile;
	private final BufferedImage yTile;
	private final BufferedImage zTile;
	private final BufferedImage blankTile;
	private final BufferedImage enchantmentImage;

	private boolean buying;
	private boolean swapping;
	private boolean enchanting;
	private boolean choosing;
	private boolean picking;
	
	private boolean error;
	private String errorMessage;
	
	//Tile width
	private final static int TILE_WIDTH = 40;
	private final static int TILE_HEIGHT = 40;

	
	public GameUI() throws IOException {
		this.game = new Game();
		selected = null;
		selectedE = null;
		clickDownX = 0;
		clickDownY = 0;
		swapping = false;
		buying = false;
		enchanting = false;
		error = false;
		choosing = false;
		picking = false;
		
		this.setPreferredSize(new Dimension(FRAME_WIDTH-PADDING, FRAME_HEIGHT-PADDING));
		this.setLayout(new BorderLayout(0,0));
		
		//add the panels
		layeredPane = new JLayeredPane();
		bp = new BoardPanel(game, this);
		pp = new PlayerPanel(game, this);
		pnp = new PickNamePanel(game, this);
		btcp = new BlankTileChoicePanel(game, this);
		sp = new SwappingPanel(game, this);
		ep = new EstorePanel(game, this);
		
		btcp.setVisible(false);
		pnp.setVisible(false);
		sp.setVisible(false);
		ep.setVisible(false);
		
		layeredPane.setPreferredSize(new Dimension(600, 720));
		
		bp.setBounds(0, 0, 600, 600);
		pp.setBounds(0, 600, 600, 120);
		pnp.setBounds(150, 300, 300, 40);
		btcp.setBounds(150, 300, 300, 40);
		sp.setBounds(74, 530, 382, 70);
		ep.setBounds(150, 100, 300, 420);
		
		layeredPane.add(bp, new Integer(0));
		layeredPane.add(pp, new Integer(0));
		layeredPane.add(pnp, new Integer(1));
		layeredPane.add(btcp, new Integer(1));
		layeredPane.add(sp, new Integer(1));
		layeredPane.add(ep, new Integer(1));
		
		layeredPane.setVisible(true);
		

		this.add(layeredPane);
		
		this.addMouseListener(bp);
		this.addMouseListener(pp);
		this.addMouseListener(sp);
		this.addMouseMotionListener(this);
		
		//load all the images
		this.aTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/aTile.jpg")), 20);
		this.bTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/bTile.jpg")), 20);
		this.cTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/cTile.jpg")), 20);
		this.dTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/dTile.jpg")), 20);
		this.eTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/eTile.jpg")), 20);
		this.fTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/fTile.jpg")), 20);
		this.gTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/gTile.jpg")), 20);
		this.hTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/hTile.jpg")), 20);
		this.iTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/iTile.jpg")), 20);
		this.jTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/jTile.jpg")), 20);
		this.kTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/kTile.jpg")), 20);
		this.lTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/lTile.jpg")), 20);
		this.mTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/mTile.jpg")), 20);
		this.nTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/nTile.jpg")), 20);
		this.oTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/oTile.jpg")), 20);
		this.pTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/pTile.jpg")), 20);
		this.qTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/qTile.jpg")), 20);
		this.rTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/rTile.jpg")), 20);
		this.sTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/sTile.jpg")), 20);
		this.tTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/tTile.jpg")), 20);
		this.uTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/uTile.jpg")), 20);
		this.vTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/vTile.jpg")), 20);
		this.wTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/wTile.jpg")), 20);
		this.xTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/xTile.jpg")), 20);
		this.yTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/yTile.jpg")), 20);
		this.zTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/zTile.jpg")), 20);
		this.blankTile = Util.makeRoundedCorner(ImageIO.read(new File("icons/blankTile.jpg")), 20);
		this.enchantmentImage = Util.makeRoundedCorner(ImageIO.read(new File("icons/enchantment.jpg")), 20);
	}

	/**
	 * 
	 * @param tileCoords coordinates of the tile
	 * @param clickCoords coordinates of the click
	 * @return true if the click was on the tile, false if it was not
	 */
	public boolean isOnTile(int[] tileCoords, int[] clickCoords){
		if (clickCoords[0] >= tileCoords[0] && clickCoords[1] >= tileCoords[1]){
			if (clickCoords[0] <= tileCoords[0] + TILE_WIDTH && clickCoords[1] <= tileCoords[1] + TILE_HEIGHT){
				return true;
			}
		}
		return false;
	}
	
	public void repaintAll(){
		bp.repaint();
		pp.repaint();
		pnp.repaint();
		btcp.repaint();
		sp.repaint();
		this.repaint();
	}
	
	public BufferedImage findProperImage(char c) {
		switch(c){
		case 'a': return aTile;
		case 'b': return bTile;
		case 'c': return cTile;
		case 'd': return dTile;
		case 'e': return eTile;
		case 'f': return fTile;
		case 'g': return gTile;
		case 'h': return hTile;
		case 'i': return iTile;
		case 'j': return jTile;
		case 'k': return kTile;
		case 'l': return lTile;
		case 'm': return mTile;
		case 'n': return nTile;
		case 'o': return oTile;
		case 'p': return pTile;
		case 'q': return qTile;
		case 'r': return rTile;
		case 's': return sTile;
		case 't': return tTile;
		case 'u': return uTile;
		case 'v': return vTile;
		case 'w': return wTile;
		case 'x': return xTile;
		case 'y': return yTile;
		case 'z': return zTile;
		case '0':return blankTile;
		}
		return null;
	}
	
	public BufferedImage getEnchantmentImage(){
		return this.enchantmentImage;
	}
	
	public int getFrameWidth(){
		return FRAME_WIDTH;
	}
	
	public int getFrameHeight(){
		return FRAME_HEIGHT;
	}
	
	public Tile getSelected(){
		return this.selected;
	}
	
	public void setSelected(Tile tile){
		this.selected = tile;
	}
	
	public Enchantment getSelectedE(){
		return this.selectedE;
	}
	
	public void setSelectedE(Enchantment ench){
		this.selectedE = ench;
	}
	
	public void setOffsetX(int offsetX){
		this.offsetX = offsetX;
	}
	
	public void setOffsetY(int offsetY){
		this.offsetY = offsetY;
	}
	
	public int getOffsetX(){
		return this.offsetX;
	}
	
	public int getOffsetY(){
		return this.offsetY;
	}
	
	public void setClickDownX(int clickDownX){
		this.clickDownX = clickDownX;
	}
	
	public void setClickDownY(int clickDownY){
		this.clickDownY = clickDownY;
	}
	
	public int getClickDownX(){
		return this.clickDownX;
	}
	
	public int getClickDownY(){
		return this.clickDownY;
	}

	public void setSwapping(boolean swapping){
		if (!swapping){
			sp.setVisible(false);
			game.clearSwapBoxToHand();
		}
		else{
			sp.setVisible(true);
		}
		this.swapping = swapping;
	}
	
	public boolean getSwapping(){
		return this.swapping;
	}
	
	public void setEnchanting(boolean enchanting){
		if (!enchanting){
			selectedE = null;
		}
		this.enchanting = enchanting;
	}
	
	public boolean isEnchanting(){
		return this.enchanting;
	}
	
	public void setBuying(boolean buying){
		if (!buying){
			ep.setVisible(false);
		}
		else {
			ep.setVisible(true);
		}
		this.buying = buying;
	}
	
	public boolean isBuying(){
		return this.buying;
	}
	
	public void setError(boolean error, String message){
		this.error = error;
		this.errorMessage = message;
	}
	
	public boolean getError(){
		return this.error;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public void setChoosing(boolean choosing){
		btcp.setVisible(choosing);
		this.choosing = choosing;
	}
	
	public boolean getChoosing(){
		return this.choosing;
	}
	
	public void saveRelease(Location loc){
		btcp.saveReleaseLoc(loc);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (game.isStarted()){
			clickDownX = e.getX() - offsetX;
			clickDownY = e.getY() - offsetY;
		}
		repaintAll();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (game.isStarted()){
			pnp.setVisible(false);
		}
		if (game.gameIsDone()){
			game.endGame();
			this.btcp.setVisible(false);
			this.ep.setVisible(false);
			this.pnp.setVisible(false);
			this.sp.setVisible(false);
		}
	}
	/**
	 * The programs entry point to have a nice UI.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame f = new JFrame("Scrabble");
				GameUI gui = null;
				try {
					gui = new GameUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				f.add(gui);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.pack();
				f.setResizable(false);
				f.setVisible(true);
			}
		});
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
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
	public void mousePressed(MouseEvent arg0) {
		if (game.isEnded()){
			this.repaintAll();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setPicking(boolean b) {
		pnp.setVisible(b);
		this.picking = b;
	}
}