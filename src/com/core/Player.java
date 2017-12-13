package com.core;

import java.util.ArrayList;


class Player{
	private ArrayList<Tile> hand = new ArrayList<Tile>();
	private String name;
	private int score;
	private int handSize;
	private ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>();

	public Player(String name, Tile[] hand){
		this.name = name;
		this.score = 0;
		if (hand != null){
			this.handSize = 0;
			for (int i=0; i<hand.length; i++){
				this.addTileToHand(hand[i]);
			}
		}
	}

	/**
	 * 
	 * @return this players enchantments
	 */
	public ArrayList<Enchantment> getEnchantments(){
		return this.enchantments;
	}
	
	/**
	 * 
	 * @param requiredSize - the required hand size set by the game rules
	 * @return whether or not the player needs his/her hand filled
	 */
	public boolean needToFillHand(int requiredSize){
		if (this.handSize < requiredSize) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param tile - the tile to be removed from the players hand
	 */
	public void removeTileFromHand(Tile tile) {
		for (int i=0; i<hand.size();i++){
			if (hand.get(i).equals(tile)){
				this.hand.remove(i);
				this.handSize--;
				break;
			}
		}
	}
	
	public Enchantment removeEnchantment(Enchantment.EnchantmentType ench){
		Enchantment ret = null;
		for (int i=0;i<enchantments.size();i++){
			if (this.enchantments.get(i).getType() == ench){
				ret = this.enchantments.get(i);
				this.enchantments.remove(i);
			}
		}
		return ret;
	}
	/**
	 * 
	 * @param tile - the tile to be added to the players hand
	 */
	public void addTileToHand(Tile tile) {
		this.hand.add(tile);
		this.handSize++;
	}
	
	/**
	 * 
	 * @return the current hand size of the player
	 */
	public int getHandSize(){
		return this.handSize;
	}
	
	/**
	 * 
	 * @return the tiles in the players hand
	 */
	public ArrayList<Tile> getHand() {
		return this.hand;
	}

	/**
	 * 
	 * @return the name of this player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return the score in the game for this player
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param score - the score to set this players score to
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 
	 * @param enchantment - the enchantment that the playing is receiving do to buying it
	 */
	public void receiveEnchantment(Enchantment enchantment){
		this.setScore(this.getScore() - enchantment.getPrice());
		enchantments.add(enchantment);
		enchantment.setOwner(this);
	}

	public void removeTileFromHand(int index){
		this.hand.remove(index);
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Player)) return false;
		
		Player player = (Player) obj;
		if (player.getName().equals(this.getName())) return true;
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.getName().hashCode();
	}
}