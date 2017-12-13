package com.core;

import java.util.ArrayList;

import com.core.Enchantment.EnchantmentType;
import com.util.Location;


public class Game{
	private final int BOARD_WIDTH = 15;
	private final int BOARD_HEIGHT = 15;
	private final int REQUIRED_HAND_SIZE = 7;
	private Board board;
	private Bag bag;
	private Dictionary dict;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private Word currentWord;
	private ArrayList<Tile> swapBox = new ArrayList<Tile>();
	private EnchantmentStore eStore;
	private int numPlayers;
	private boolean isStarted;
	private boolean ended;
	
	public Game(){
		initializeGame();
	}
	
	public void initializeGame(){
		board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		bag = new Bag();
		dict = new Dictionary();
		eStore = new EnchantmentStore();
		isStarted = false;
		ended = false;
		numPlayers = 0;
		players.clear();
		swapBox.clear();
	}
	
	public void startGame() {
		currentPlayer = players.get(0);
		currentWord = null;
		numPlayers = players.size();
		isStarted = true;
	}
	
	public boolean isLocationFilled(Location loc){
		return this.board.isFilled(loc);
	}
	public boolean isValidLocation(Location loc) {
		return this.board.isValidLocation(loc);
	}
	
	public Tile getTile(Location loc){
		return this.board.getTile(loc);
	}
	
	public void endGame(){
		this.isStarted = false;
		this.ended = true;
	}
	
	public boolean isEnded(){
		return this.ended;
	}
	public void reset(){
		initializeGame();
	}
	
	public boolean isStarted(){
		return this.isStarted;
	}
	
	public String getWinnerName(){
		Player winner = players.get(0);
		for (int i=0; i<players.size();i++){
			if (players.get(i).getScore() > winner.getScore()){
				winner = players.get(i);
			}
		}
		return winner.getName();
	}
	
	public int getNumPlayers(){
		return this.numPlayers;
	}
	
	public boolean gameIsDone() {
		if (this.bag.isEmpty()) return true;
		return false;
		
	}
	
	public ArrayList<Enchantment> getEnchantments(){
		return this.eStore.getEnchantments();
	}
	
	public boolean buyEnchantment(Enchantment enchantment){
		System.out.println("BUYING ENCHANTMENT");
		if (currentPlayer.getScore() < enchantment.getPrice())
			return false;
		eStore.removeEnchantment(enchantment.getType());
		currentPlayer.receiveEnchantment(enchantment);
		return true;
	}
	
	public ArrayList<Enchantment> getCurrentPlayerEnchs(){
		return currentPlayer.getEnchantments();
	}
	public void addPlayer(String name){
		this.players.add(new Player(name, generateHand()));
		this.numPlayers++;
	}
	/**
	 * add a tile temporarily to the board at location loc,
	 * also adds the boardspace to the current boardspaces
	 * so when a word is played, those boardspaces represent the word,
	 * returns true if it added it, false if it didnt 
	 */
	public boolean addToWorkingSet(Tile tile, Location loc){
		return this.board.addToWorkingSet(tile, loc);
	}
	
	/**
	 * remove tile at location loc from the current working set as well as the 
	 * boarspaces temporary tile
	 */
	public void removeFromWorkingSet(Location loc){
		this.board.removeFromWorkingSet(loc);
	}
	
	public boolean boardIsEmpty(){
		return board.hasNoWords();
	}
	
	public int getWorkingSetLength(){
		return this.board.getWorkingSetLength();
	}
	/**
	 * clear the current working set all together.
	 */
	public void clearWorkingSet(){
		this.board.clearWorkingSet();
	}
	/**
	 * adds a tile to the swapbox, a box displayed when a user decides to swap tiles
	 * 
	 */
	public void addToSwapBox(Tile tile){
		swapBox.add(tile);
	}
	
	public void clearSwapBox(){
		swapBox.clear();
	}
	
	public void clearSwapBoxToHand(){
		for (int i=0; i< swapBox.size(); i++){
			currentPlayer.addTileToHand(swapBox.get(i));
		}
		clearSwapBox();
	}
	
	public void removeFromSwapBox(Tile tile){
		swapBox.remove(tile);
	}
	
	public ArrayList<Tile> getSwapBox(){
		return this.swapBox;
	}
	
	public String[] getPlayerNames(){
		String[] ret = new String[this.players.size()];
		for (int i=0; i<ret.length;i++){
			ret[i] = players.get(i).getName();
		}
		return ret;
	}
	
	public String getCurrentPlayerName(){
		return this.currentPlayer.getName();
	}
	
	public int[] getPlayerScores(){
		int[] ret = new int[this.players.size()];
		for (int i=0; i<ret.length;i++){
			ret[i] = players.get(i).getScore();
		}
		return ret;
	}
	
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}
	
	public int getBoardWidth(){
		return this.BOARD_WIDTH;
	}
	
	public int getBoardHeight(){
		return this.BOARD_HEIGHT;
	}
	public void removeFromCurrentHand(Tile tile){
		this.currentPlayer.removeTileFromHand(tile);
	}
	
	public void addToCurrentHand(Tile tile){
		this.currentPlayer.addTileToHand(tile);
	}
	
	public void setCurrentPlayer(Player player){
		this.currentPlayer = player;
	}
	
	public Word getCurrentWord(){
		return this.currentWord;
	}
	
	public void setCurrentWord(Word word){
		this.currentWord = word;
	}
	
	public ArrayList<Word> getPlayedWords(){
		return this.board.getWords();
	}
	
	public ArrayList<Tile> getCurrentHand(){
		return currentPlayer.getHand();
	}
	
	public void applyEnchantment(Location loc, Enchantment enchantment){
		board.applyEnchantment(loc, enchantment);
	}
	
	public int getCurrentPlayerBankrupts(){
		int sum = 0;
		ArrayList<Enchantment> enchs = this.getCurrentPlayerEnchs();
		
		for (int i=0; i< enchs.size();i++){
			if (enchs.get(i).getType() == EnchantmentType.BANKRUPT)
				sum++;
		}
		return sum;
	}
	
	public int getCurrentPlayerLoseTurns(){
		int sum = 0;
		ArrayList<Enchantment> enchs = this.getCurrentPlayerEnchs();
		
		for (int i=0; i< enchs.size();i++){
			if (enchs.get(i).getType() == EnchantmentType.LOSE_TURN)
				sum++;
		}
		return sum;
	}
	
	public int getCurrentPlayerTakeWords(){
		int sum = 0;
		ArrayList<Enchantment> enchs = this.getCurrentPlayerEnchs();
		
		for (int i=0; i< enchs.size();i++){
			if (enchs.get(i).getType() == EnchantmentType.TAKE_WORD)
				sum++;
		}
		return sum;
	}
	
	public Enchantment removeEnchFromCurrentPlayer(EnchantmentType type){
		Enchantment ret = this.currentPlayer.removeEnchantment(type);
		return ret;
	}
	
	/**
	 * the user passed the turn
	 */
	public void pass(){
		clearSwapBoxToHand();
		endTurn();
	}
	
	/**
	 * the user is playing a word on the board
	 * 
	 */
	public void playWord(){
		Player enchantmentOwner;
		Word words[] = board.createWordsWithWorkingSet(currentPlayer);
		boolean notValid = false;
		int accumValue = 0;
		
		if (this.boardIsEmpty() && words != null){
			Location[] locs = words[0].getTileLocations();
			Location centerLoc = new Location (BOARD_WIDTH/2, BOARD_HEIGHT/2);
			boolean onCenter = false;
			for (int i=0; i<locs.length;i++){
				if (locs[i].equals(centerLoc))
					onCenter = true;
			}
			if (!onCenter) words = null;
		}
		
		if (words != null){
			for (int i=0; i<words.length;i++){
				accumValue += words[i].getValue();
				if (!wordIsValid(words[i]))
					notValid = true;
			}
			if (!notValid){
				this.setCurrentWord(words[0]);
				this.currentWord.resetValue(accumValue);
			}
			else{
				System.out.println("Word not found in Dictionary!");
				return;
			}
		}
		else{
			System.out.println("Word not a valid word!"); //TODO: do a better error statement
			return;
		}
		
		//execute an enchantment if there is one
		for (int i=0; i<currentWord.length();i++){
			
			if (currentWord.getTiles()[i].getEnchantment() != null && 
					(enchantmentOwner = currentWord.getTiles()[i].getEnchantment().getOwner()) != currentWord.getOwner() ){
				currentWord.getTiles()[i].getEnchantment().execute(this, enchantmentOwner);
			}
			
		}
		if (currentWord.getOwner() != null){
			board.addWord(currentWord);
			currentWord.getOwner().setScore(currentWord.getOwner().getScore() + currentWord.getValue());
		}
		
		endTurn();
		
	}
	
	/**
	 * the user is swapping out tiles for new ones
	 * NOTE: all we need to do here is add the swapbox tiles to the bag. When the
	 * swapbox tiles were added to the swapbox, they were automatically removed from the hand,
	 * then the endTurn method will refill the hand for us with random tiles from the bag.
	 * 
	 */
	public void swapTiles(){
		Object[] objects = swapBox.toArray();
		Tile[] tiles = new Tile[objects.length];
		
		for (int i=0; i<objects.length;i++){
			tiles[i] = (Tile) objects[i];
		}
		
		bag.addTilesToBag(tiles);
		endTurn();
	}

	public int[] getTileValues(){
		return Bag.getValues();
	}
	
	private void switchCurrentPlayer(){
		for (int i=0; i<players.size();i++){
			if (currentPlayer.equals(players.get(i))) {
				if (i == players.size() - 1)
					currentPlayer = players.get(0);
				else
					currentPlayer = players.get(i+1);
				break;
			}
		}
	}
	
	public int getNumTilesInBag(){
		return bag.getNumTiles();
	}
	
	public int getNumBankrupts(){
		return eStore.getNumBankrupts();
	}
	
	public int getNumLoseTurns(){
		return eStore.getNumLoseTurns();
	}
	
	public int getNumTakeWords(){
		return eStore.getNumTakeWords();
	}
	
	/**
	 * 
	 * @return true if the current word is a valid word in the dictionary
	 */
	private boolean wordIsValid(Word currentWord){
		int wordLength = currentWord.length();
		char[] array = new char[wordLength];
		for (int i=0; i<wordLength;i++){
			array[i] = currentWord.getTiles()[i].getLetter();
		}
		return this.dict.wordIsValid(new String(array));
	}

	/**
	 * ends the current players turn by refilling the players hand if needed,
	 * clearing the swap box if needed, clearing the current word if needed,
	 * and swtiching the current player to the other player.
	 */
	private void endTurn(){
		refillHand();
		clearSwapBox();
		clearWorkingSet();
		switchCurrentPlayer();
	}
	
	private void refillHand(){
		while (currentPlayer.needToFillHand(REQUIRED_HAND_SIZE) && !bag.isEmpty())
			currentPlayer.addTileToHand(bag.getRandomTile());
	}

	private Tile[] generateHand(){
		Tile[] hand = new Tile[REQUIRED_HAND_SIZE];
		for (int i=0;i<hand.length;i++){
			hand[i] = bag.getRandomTile();
		}
		return hand;
	}

	public boolean playerExists(String text) {
		for (int i=0; i<players.size();i++){
			if (players.get(i).getName().equals(text)){
				return true;
			}
		}
		return false;
	}
}