package com.core;

import com.util.Location;

class Word{
	private final Location[] tileLocs;
	private final Tile[] tiles;
	private Player owner;
	private int value;
	
	public Word(Tile[] tiles, Location[] tileLocs, Player owner, int value){
		this.tileLocs = tileLocs;
		this.tiles = tiles;
		this.owner = owner;
		this.value = value;
	}
	
	/**
	 * 
	 * @return the tiles that make this word
	 */
	public Tile[] getTiles(){
		return this.tiles;
	}
	
	/**
	 * 
	 * @return the locations of the tiles that make this word
	 */
	public Location[] getTileLocations(){
		return this.tileLocs;
	}
	
	/**
	 * 
	 * @return the value of the word
	 */
	public int getValue(){
		return this.value;
	}
	/**
	 * 
	 * @param value - new value to set the word to
	 */
	public void resetValue(int value){
		this.value = value;
	}
	
	/**
	 * 
	 * @return the owner of this word
	 */
	public Player getOwner(){
		return this.owner;
	}
	
	/**
	 * 
	 * @param player - the new owner of this word
	 */
	public void switchOwner(Player player){
		this.owner = player;
	}
	
	/**
	 * 
	 * @return the lenght of this word
	 */
	public int length(){
		return this.tiles.length;
	}
	
	/**
	 * returns the string representation of this word
	 */
	public String toString(){
		char[] chars = new char[tiles.length];
		for (int i=0; i<chars.length;i++){
			chars[i] = tiles[i].getLetter();
		}
		return new String(chars);
		
	}
}