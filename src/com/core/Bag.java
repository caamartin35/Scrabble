package com.core;

import java.util.ArrayList;

import com.core.Tile.TileType;

public class Bag{
	private ArrayList<Tile> tiles;
	private static final int NUM_A = 9;
	private static final int NUM_B = 2;
	private static final int NUM_C = 2;
	private static final int NUM_D = 4;
	private static final int NUM_E = 12;
	private static final int NUM_F = 2;
	private static final int NUM_G = 3;
	private static final int NUM_H = 2;
	private static final int NUM_I = 9;
	private static final int NUM_J = 1;
	private static final int NUM_K = 1;
	private static final int NUM_L = 4;
	private static final int NUM_M = 2;
	private static final int NUM_N = 6;
	private static final int NUM_O = 8;
	private static final int NUM_P = 2;
	private static final int NUM_Q = 1;
	private static final int NUM_R = 6;
	private static final int NUM_S = 4;
	private static final int NUM_T = 6;
	private static final int NUM_U = 4;
	private static final int NUM_V = 2;
	private static final int NUM_W = 2;
	private static final int NUM_X = 1;
	private static final int NUM_Y = 2;
	private static final int NUM_Z = 1;
	private static final int NUM_BLANK = 2;

	//tile VALUES A-Z then BLANK
	private static final int[] VALUES = {1, 4, 4, 2, 1, 4, 3, 3, 1, 10, 5, 2, 4, 2, 1, 4, 10,
		1, 1, 1, 2, 5, 4, 8, 3, 10, 0};
	
	public Bag(){
		initializeBag();
	}
	
	/**
	 * initialize the bag with the tiles
	 */
	private void initializeBag(){
		tiles = new ArrayList<Tile>();
		/*Add all the regular tiles to the bag*/
		addTiles();
	}
	
	/**
	 * add all the tiles manually.
	 * the amounts are based on standard scrabble rules.
	 */
	private void addTiles() {
		int i;
		
		//Add the A tiles
		for (i=0; i<NUM_A; i++){
			Tile aTile = new Tile(VALUES[0], 'a', TileType.TYPE_REGULAR);
			tiles.add(aTile);
		}
		
		//Add the B tiles
		for (i=0; i<NUM_B; i++){
			Tile bTile = new Tile(VALUES[1], 'b', TileType.TYPE_REGULAR);
			tiles.add(bTile);
		}
		
		//Add the C tiles
		for (i=0; i<NUM_C; i++){
			Tile cTile = new Tile(VALUES[2], 'c', TileType.TYPE_REGULAR);
			tiles.add(cTile);
		}
		
		//Add the D tiles
		for (i=0; i<NUM_D; i++){
			Tile dTile = new Tile(VALUES[3], 'd', TileType.TYPE_REGULAR);
			tiles.add(dTile);
		}
		
		//Add the E tiles
		for (i=0; i<NUM_E; i++){
			Tile eTile = new Tile(VALUES[4], 'e', TileType.TYPE_REGULAR);
			tiles.add(eTile);
		}
		
		//Add the F tiles
		for (i=0; i<NUM_F; i++){
			Tile fTile = new Tile(VALUES[5], 'f', TileType.TYPE_REGULAR);
			tiles.add(fTile);
		}
		
		//Add the G tiles
		for (i=0; i<NUM_G; i++){
			Tile gTile = new Tile(VALUES[6], 'g', TileType.TYPE_REGULAR);
			tiles.add(gTile);
		}
		
		//Add the H tiles
		for (i=0; i<NUM_H; i++){
			Tile hTile = new Tile(VALUES[7], 'h', TileType.TYPE_REGULAR);
			tiles.add(hTile);
		}
		
		//Add the I tiles
		for (i=0; i<NUM_I; i++){
			Tile iTile = new Tile(VALUES[8], 'i', TileType.TYPE_REGULAR);
			tiles.add(iTile);
		}
		
		//Add the J tiles
		for (i=0; i<NUM_J; i++){
			Tile jTile = new Tile(VALUES[9], 'j', TileType.TYPE_REGULAR);
			tiles.add(jTile);
		}
		
		//Add the K tiles
		for (i=0; i<NUM_K; i++){
			Tile kTile = new Tile(VALUES[10], 'k', TileType.TYPE_REGULAR);
			tiles.add(kTile);
		}
		
		//Add the L tiles
		for (i=0; i<NUM_L; i++){
			Tile lTile = new Tile(VALUES[11], 'l', TileType.TYPE_REGULAR);
			tiles.add(lTile);
		}
		
		//Add the M tiles
		for (i=0; i<NUM_M; i++){
			Tile mTile = new Tile(VALUES[12], 'm', TileType.TYPE_REGULAR);
			tiles.add(mTile);
		}
		
		//Add the N tiles
		for (i=0; i<NUM_N; i++){
			Tile nTile = new Tile(VALUES[13], 'n', TileType.TYPE_REGULAR);
			tiles.add(nTile);
		}
		
		//Add the O tiles
		for (i=0; i<NUM_O; i++){
			Tile oTile = new Tile(VALUES[14], 'o', TileType.TYPE_REGULAR);
			tiles.add(oTile);
		}
		
		//Add the P tiles
		for (i=0; i<NUM_P; i++){
			Tile pTile = new Tile(VALUES[15], 'p', TileType.TYPE_REGULAR);
			tiles.add(pTile);
		}
		
		//Add the Q tiles
		for (i=0; i<NUM_Q; i++){
			Tile qTile = new Tile(VALUES[16], 'q', TileType.TYPE_REGULAR);
			tiles.add(qTile);
		}
		
		//Add the R tiles
		for (i=0; i<NUM_R; i++){
			Tile rTile = new Tile(VALUES[17], 'r', TileType.TYPE_REGULAR);
			tiles.add(rTile);
		}
		
		//Add the S tiles
		for (i=0; i<NUM_S; i++){
			Tile sTile = new Tile(VALUES[18], 's', TileType.TYPE_REGULAR);
			tiles.add(sTile);
		}
		
		//Add the T tiles
		for (i=0; i<NUM_T; i++){
			Tile tTile = new Tile(VALUES[19], 't', TileType.TYPE_REGULAR);
			tiles.add(tTile);
		}
		//Add the U tiles
		for (i=0; i<NUM_U; i++){
			Tile uTile = new Tile(VALUES[20], 'u', TileType.TYPE_REGULAR);
			tiles.add(uTile);
		}
		
		//Add the V tiles
		for (i=0; i<NUM_V; i++){
			Tile vTile = new Tile(VALUES[21], 'v', TileType.TYPE_REGULAR);
			tiles.add(vTile);
		}
		
		//Add the W tiles
		for (i=0; i<NUM_W; i++){
			Tile wTile = new Tile(VALUES[22], 'w', TileType.TYPE_REGULAR);
			tiles.add(wTile);
		}
		
		//Add the X tiles
		for (i=0; i<NUM_X; i++){
			Tile xTile = new Tile(VALUES[23], 'x', TileType.TYPE_REGULAR);
			tiles.add(xTile);
		}
		
		//Add the Y tiles
		for (i=0; i<NUM_Y; i++){
			Tile yTile = new Tile(VALUES[24], 'y', TileType.TYPE_REGULAR);
			tiles.add(yTile);
		}
		
		//Add the Z tiles
		for (i=0; i<NUM_Z; i++){
			Tile zTile = new Tile(VALUES[25], 'z', TileType.TYPE_REGULAR);
			tiles.add(zTile);
		}
		
		//Add the blank tiles
		for (i=0; i<NUM_BLANK; i++){
			Tile blankTile = new Tile(VALUES[26], '0', TileType.TYPE_BLANK);
			tiles.add(blankTile);
		}
		
	}

	/**
	 * 
	 * @param tiles - to be added to the bag
	 */
	public void addTilesToBag(Tile[] tiles){
		for (int i=0; i<tiles.length; i++){
			this.tiles.add(tiles[i]);
		}
	}
	/**
	 * @return random tile from the bag
	 */
	public Tile getRandomTile(){
		if (this.isEmpty())
			return null;
		else{
			int index = (int) (Math.random()*tiles.size());
			Tile tile = tiles.get(index);
			tiles.remove(index);
			return tile;
		}
	}
	
	public int getNumTiles(){
		return this.tiles.size();
	}
	
	/**
	 * 
	 * @return whether or not the bag is empty
	 */
	public boolean isEmpty(){
		return tiles.isEmpty();
	}
	
	public static int[] getValues() {
		return VALUES;
	}
}