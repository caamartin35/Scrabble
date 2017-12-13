package com.core;

public class Enchantment{
	private final static int PRICE = 5;
	private EnchantmentType type;
	private boolean isActivated;
	private Player owner;
	
	public Enchantment(EnchantmentType type){
		this.type = type;
		this.isActivated = false;
		this.owner = null;
	}
	
	public enum EnchantmentType{
		BANKRUPT,
		LOSE_TURN,
		TAKE_WORD
	}
	
	/**
	 * 
	 * @return the type of enchantment
	 */
	public EnchantmentType getType(){
		return this.type;
	}
	
	/**
	 * 
	 * @param owner - the owner that this enchantments owner shall be set to
	 */
	public void setOwner(Player owner){
		this.owner = owner;
	}
	
	/**
	 * 
	 * @return the price of this enchantment
	 */
	public int getPrice(){
		return this.PRICE;
	}
	
	/**
	 * 
	 * @return the owner of this enchantment
	 */
	public Player getOwner(){
		return this.owner;
	}
	
	public String getOwnerName(){
		return this.owner.getName();
	}
	/**
	 * 
	 * @return whether or not this enchantment is activated
	 */
	public boolean isActivated(){
		return this.isActivated;
	}
	
	/**
	 * 
	 * @param game - the current game going on
	 * @param enchantmentOwner - the owner of the enchantment that is executing
	 */
	public void execute(Game game, Player enchantmentOwner){
		switch (this.type){
		case BANKRUPT:
			this.bankrupt(game);
			break;
		case LOSE_TURN:
			this.loseTurn(game, enchantmentOwner);
			break;
		case TAKE_WORD:
			this.takeWord(game, enchantmentOwner);
			break;
		}
		this.isActivated = true;
	}

	/**
	 * takes the word being played by the current player ofthe game and gives
	 * it to the owner of the enchantment being activated
	 * 
	 * @param game - the current game going on
	 * @param enchantmentOwner - the owner of the enchantment being executed
	 */
	private void takeWord(Game game, Player enchantmentOwner) {
		game.getCurrentWord().switchOwner(enchantmentOwner);
	}

	/**
	 * makes the current player lose his/her turn by nullifying the ownership
	 * of the current word
	 * 
	 * @param game - the current game going on
	 * @param enchantmentOwner - owner of the enchantment being executed
	 */
	private void loseTurn(Game game, Player enchantmentOwner) {
		game.getCurrentWord().switchOwner(null);
	}

	/**
	 * bankrupts the player who activated this enchantment
	 * 
	 * @param game - the game that is being played
	 */
	private void bankrupt(Game game) {
		game.getCurrentPlayer().setScore(0);
	}
	
}