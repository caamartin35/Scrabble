package com.core;

/**
 * an abstract tile class to describe the tiles.
 * @author Cody
 *
 */
public class Tile{
	private int value;
	private char letter;
	private TileType type;
	private Enchantment enchantment;
	
	public Tile(int value, char letter, TileType type){
		this.value = value;
		this.letter = letter;
		this.type = type;
		this.enchantment = null;
	}
	
	public enum TileType{
		TYPE_REGULAR,
		TYPE_BLANK
	}
	
	/**
	 * 
	 * @return the enchantment associated with this tile
	 */
	public Enchantment getEnchantment(){
		return this.enchantment;
	}
	
	/**
	 * 
	 * @param enchantment - the enchantment to associate this tile with
	 */
	public void setEnchantment(Enchantment enchantment){
		this.enchantment = enchantment;
	}
	
	/**
	 * 
	 * @return the type of tile this is
	 */
	public TileType getType(){
		return this.type;
	}
	
	/**
	 * 
	 * @return the value of the tile
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * 
	 * @return the letter of this tile
	 */
	public char getLetter(){
		return this.letter;
	}
	
	public void setLetter(char letter){
		this.letter = letter;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Tile)) return false;
		
		Tile tile = (Tile) obj;
		if (tile.getLetter() == this.getLetter() && tile.getValue() == this.getValue()) return true;
		return false;
	}
	
	@Override
	public int hashCode(){
		return value + 31*getLetter();
	}
	
}