package com.core;

import java.util.ArrayList;
import com.util.Direction;
import com.util.Location;
import com.util.SpaceType;


class Board{
	private BoardSpace[] multiplySpaces = new BoardSpace[60];
	private BoardSpace[][] spaces;
	private WordFactory wf = new WordFactory();
	private ArrayList<BoardSpace> workingSet;
	private ArrayList<Word> words;
	private int width, height;
	
	public Board(int width, int height){
		this.width = width;
		this.height = height;
		spaces = new BoardSpace[width][height];
		words = new ArrayList<Word>();
		workingSet = new ArrayList<BoardSpace>();
		loadMultiplySpaces();
		
		//initialize all the board spaces
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				Location loc = new Location(i, j);
				
				//see if our current board space is in list of multiply board spaces
				for (int k=0; k<multiplySpaces.length; k++){
					if (loc.equals(multiplySpaces[k].getLocation())){
						spaces[i][j] = multiplySpaces[k];
						break;
					}
				}
				
				//if it was not in the list of multiply board spaces, just make it a regular board space
				if (spaces[i][j] == null){
					spaces[i][j] = new BoardSpace(new Location(i, j), SpaceType.NORMAL);
				}
				
			}
		}
	}

	/**
	 * load all the multiply spaces in to an array manually to then 
	 * load them into the board. 
	 */
	private void loadMultiplySpaces() {
		
		//load all double letter spaces
		multiplySpaces[0] = new BoardSpace(new Location(1, 2), SpaceType.DOUBLE_LETTER);
		multiplySpaces[1] = new BoardSpace(new Location(1, 12), SpaceType.DOUBLE_LETTER);
		multiplySpaces[2] = new BoardSpace(new Location(2, 1), SpaceType.DOUBLE_LETTER);
		multiplySpaces[3] = new BoardSpace(new Location(2, 4), SpaceType.DOUBLE_LETTER);
		multiplySpaces[4] = new BoardSpace(new Location(2, 10), SpaceType.DOUBLE_LETTER);
		multiplySpaces[5] = new BoardSpace(new Location(2, 13), SpaceType.DOUBLE_LETTER);
		multiplySpaces[6] = new BoardSpace(new Location(4, 2), SpaceType.DOUBLE_LETTER);
		multiplySpaces[7] = new BoardSpace(new Location(4, 6), SpaceType.DOUBLE_LETTER);
		multiplySpaces[8] = new BoardSpace(new Location(4, 8), SpaceType.DOUBLE_LETTER);
		multiplySpaces[9] = new BoardSpace(new Location(4, 12), SpaceType.DOUBLE_LETTER);
		multiplySpaces[10] = new BoardSpace(new Location(6, 4), SpaceType.DOUBLE_LETTER);
		multiplySpaces[11] = new BoardSpace(new Location(6, 10), SpaceType.DOUBLE_LETTER);
		multiplySpaces[12] = new BoardSpace(new Location(8, 4), SpaceType.DOUBLE_LETTER);
		multiplySpaces[13] = new BoardSpace(new Location(8, 10), SpaceType.DOUBLE_LETTER);
		multiplySpaces[14] = new BoardSpace(new Location(10, 2), SpaceType.DOUBLE_LETTER);
		multiplySpaces[15] = new BoardSpace(new Location(10, 6), SpaceType.DOUBLE_LETTER);
		multiplySpaces[16] = new BoardSpace(new Location(10, 8), SpaceType.DOUBLE_LETTER);
		multiplySpaces[17] = new BoardSpace(new Location(10, 12), SpaceType.DOUBLE_LETTER);
		multiplySpaces[18] = new BoardSpace(new Location(12, 1), SpaceType.DOUBLE_LETTER);
		multiplySpaces[19] = new BoardSpace(new Location(12, 4), SpaceType.DOUBLE_LETTER);
		multiplySpaces[20] = new BoardSpace(new Location(12, 10), SpaceType.DOUBLE_LETTER);
		multiplySpaces[21] = new BoardSpace(new Location(12, 13), SpaceType.DOUBLE_LETTER);
		multiplySpaces[22] = new BoardSpace(new Location(13, 2), SpaceType.DOUBLE_LETTER);
		multiplySpaces[23] = new BoardSpace(new Location(13, 12), SpaceType.DOUBLE_LETTER);
		
		//add double word spaces
		multiplySpaces[24] = new BoardSpace(new Location(1, 5), SpaceType.DOUBLE_WORD);
		multiplySpaces[25] = new BoardSpace(new Location(1, 9), SpaceType.DOUBLE_WORD);
		multiplySpaces[26] = new BoardSpace(new Location(3, 7), SpaceType.DOUBLE_WORD);
		multiplySpaces[27] = new BoardSpace(new Location(5, 1), SpaceType.DOUBLE_WORD);
		multiplySpaces[28] = new BoardSpace(new Location(5, 13), SpaceType.DOUBLE_WORD);
		multiplySpaces[29] = new BoardSpace(new Location(7, 3), SpaceType.DOUBLE_WORD);
		multiplySpaces[30] = new BoardSpace(new Location(7, 11), SpaceType.DOUBLE_WORD);
		multiplySpaces[31] = new BoardSpace(new Location(9, 1), SpaceType.DOUBLE_WORD);
		multiplySpaces[32] = new BoardSpace(new Location(9, 13), SpaceType.DOUBLE_WORD);
		multiplySpaces[33] = new BoardSpace(new Location(11, 7), SpaceType.DOUBLE_WORD);
		multiplySpaces[34] = new BoardSpace(new Location(13, 5), SpaceType.DOUBLE_WORD);
		multiplySpaces[35] = new BoardSpace(new Location(13, 9), SpaceType.DOUBLE_WORD);
		
		//add triple letter spaces
		multiplySpaces[36] = new BoardSpace(new Location(0, 6), SpaceType.TRIPLE_LETTER);
		multiplySpaces[37] = new BoardSpace(new Location(0, 8), SpaceType.TRIPLE_LETTER);
		multiplySpaces[38] = new BoardSpace(new Location(3, 3), SpaceType.TRIPLE_LETTER);
		multiplySpaces[39] = new BoardSpace(new Location(3, 11), SpaceType.TRIPLE_LETTER);
		multiplySpaces[40] = new BoardSpace(new Location(5, 5), SpaceType.TRIPLE_LETTER);
		multiplySpaces[41] = new BoardSpace(new Location(5, 9), SpaceType.TRIPLE_LETTER);
		multiplySpaces[42] = new BoardSpace(new Location(6, 0), SpaceType.TRIPLE_LETTER);
		multiplySpaces[43] = new BoardSpace(new Location(6, 14), SpaceType.TRIPLE_LETTER);
		multiplySpaces[44] = new BoardSpace(new Location(8, 0), SpaceType.TRIPLE_LETTER);
		multiplySpaces[45] = new BoardSpace(new Location(8, 14), SpaceType.TRIPLE_LETTER);
		multiplySpaces[46] = new BoardSpace(new Location(9, 5), SpaceType.TRIPLE_LETTER);
		multiplySpaces[47] = new BoardSpace(new Location(9, 9), SpaceType.TRIPLE_LETTER);
		multiplySpaces[48] = new BoardSpace(new Location(11, 3), SpaceType.TRIPLE_LETTER);
		multiplySpaces[49] = new BoardSpace(new Location(11, 11), SpaceType.TRIPLE_LETTER);
		multiplySpaces[50] = new BoardSpace(new Location(14, 6), SpaceType.TRIPLE_LETTER);
		multiplySpaces[51] = new BoardSpace(new Location(14, 8), SpaceType.TRIPLE_LETTER);
		
		//add triple word spaces
		multiplySpaces[52] = new BoardSpace(new Location(0, 3), SpaceType.TRIPLE_WORD);
		multiplySpaces[53] = new BoardSpace(new Location(0, 11), SpaceType.TRIPLE_WORD);
		multiplySpaces[54] = new BoardSpace(new Location(3, 0), SpaceType.TRIPLE_WORD);
		multiplySpaces[55] = new BoardSpace(new Location(3, 14), SpaceType.TRIPLE_WORD);
		multiplySpaces[56] = new BoardSpace(new Location(11, 0), SpaceType.TRIPLE_WORD);
		multiplySpaces[57] = new BoardSpace(new Location(11, 14), SpaceType.TRIPLE_WORD);
		multiplySpaces[58] = new BoardSpace(new Location(14, 3), SpaceType.TRIPLE_WORD);
		multiplySpaces[59] = new BoardSpace(new Location(14, 11), SpaceType.TRIPLE_WORD);
	}

	/**
	 * 
	 * @param tile - tile to add to the working set
	 * @param loc - location to add the tile in the working set on the board
	 * @return whether or not the tile was successfully added
	 */
	public boolean addToWorkingSet(Tile tile, Location loc){
		BoardSpace bs = getBoardSpace(loc);
		if (!bs.isFilled()){
			bs.setTile(tile);
			workingSet.add(bs);
			return true;
		}
		else 
			return false;
	}

	/**
	 * 
	 * @return the size of the current working set
	 */
	public int getWorkingSetLength(){
		return this.workingSet.size();
	}
	
	/**
	 * 
	 * @param loc - location of tile to remove from the working set
	 */
	public void removeFromWorkingSet(Location loc){
		BoardSpace bs = getBoardSpace(loc);
		bs.setTile(null);
		workingSet.remove(bs);
	}

	/**
	 * clear the whole working set
	 */
	public void clearWorkingSet(){
		for (int i=0; i<workingSet.size();i++){
			if (!workingSet.get(i).isFilled())
				workingSet.get(i).setTile(null);
		}
		workingSet.clear();
	}
	
	/**
	 * 
	 * @return all the words that have been played on the board
	 */
	public ArrayList<Word> getWords(){
		return this.words;
	}
	
	/**
	 * 
	 * @return the width of the board
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * 
	 * @return the height of the board
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * 
	 * @param loc - location on the board of which to get the space type
	 * @return the space type of that location on the board
	 */
	public SpaceType getSpaceType(Location loc){
		return getBoardSpace(loc).getSpaceType();
	}
	
	/**
	 * 
	 * @param loc - location on the board to see if valid
	 * @return if the location is valid or not
	 */
	public boolean isValidLocation(Location loc) {
		if (loc.getX() < 0 || loc.getY() < 0) return false;
		if (loc.getX() >= width || loc.getY() >= height) return false;
		return true;
	}
	
	/**
	 * 
	 * @param owner - owner of the word we are creating with the working set
	 * @return the word that was created
	 */
	public Word[] createWordsWithWorkingSet(Player owner){
		return wf.createWordsFromWorkingSet(owner);	
	}
	
	public Tile getTile(Location loc){
		BoardSpace bs = getBoardSpace(loc);
		return bs.getTile();
	}
	
	public boolean hasNoWords(){
		return words.isEmpty();
	}
	/**
	 * 
	 * @param word - word to be added to the list of words on the board
	 */
	public void addWord(Word word){
		BoardSpace bs;
		words.add(word);
		
		for (int i=0; i<word.length();i++){
			bs = getBoardSpace(word.getTileLocations()[i]);
			bs.fillSpace();
		}
	}
	
	public boolean isFilled(Location loc){
		BoardSpace bs = getBoardSpace(loc);
		return bs.isFilled();
	}
	/**
	 * 
	 * @param loc - location on the board to get the boardSpace object of
	 * @return the boardspace object associated with that location
	 */
	private BoardSpace getBoardSpace(Location loc){
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				if (loc.equals(spaces[i][j].getLocation()))
					return spaces[i][j];
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param loc - location to apply the enchantment to
	 * @param enchantment - the enchantment to apply
	 */
	public void applyEnchantment(Location loc, Enchantment enchantment) {
		BoardSpace bs = getBoardSpace(loc);
		bs.getTile().setEnchantment(enchantment);
		
	}
	private class BoardSpace{
		private Tile tile;
		private final SpaceType type;
		private final Location location;
		private boolean isFilled;
		
		public BoardSpace(Location location, SpaceType type){
			this.location = location;
			this.type = type;
			this.tile = null;
			this.isFilled = false;
		}
		
		/**
		 * 
		 * @return the location of this boardspace
		 */
		public Location getLocation(){
			return this.location;
		}
		
		/**
		 * 
		 * @return whether or not the boardspace has a permanent word on it
		 */
		public boolean isFilled(){
			return this.isFilled;
		}
		
		/**
		 * fill in the space so as to have a permanent word on it
		 */
		public void fillSpace(){
			this.isFilled = true;
		}
		
		@Override
		public boolean equals(Object obj){
			if (!(obj instanceof BoardSpace)) return false;
			
			BoardSpace bs = (BoardSpace) obj;
			if (bs.getLocation().equals(this.getLocation())) return true;
			return false;
		}
		
		@Override
		public int hashCode(){
			return location.hashCode();
		}
		
		/**
		 * 
		 * @param tile - tile to set the boardspace tile to
		 */
		public void setTile(Tile tile){
			this.tile = tile;
		}
		
		/**
		 * 
		 * @return the tile of this boardspace
		 */
		public Tile getTile(){
			return this.tile;
		}
		
		/**
		 * 
		 * @return the space type of this board space (REGULAR, DOUBLE_WORD, etc.)
		 */
		public SpaceType getSpaceType(){
			return this.type;
		}
	}
	
	/**
	 * This is a class that has the sole purpose of determine if the tiles placed on the board make a
	 * legitimate word that we can pass to the dictionary. It always produces a Word object.
	 * @author Cody
	 *
	 */
	private class WordFactory{
		/**
		 * 
		 * @param owner - owner of the word being created
		 * @return the word that was created, or null if the tiles were not in an
		 * acceptable orientation.
		 */
		private Word[] createWordsFromWorkingSet(Player owner){
			Direction dir = null;
			BoardSpace[] reorderedSpaces;
			BoardSpace[] associatedSpaces;
			BoardSpace[] finalSpaces;
			Word finalWord;
			Word[] crossWords1, crossWords2;
			Word[] crossWords;
			ArrayList<Word> crossWordsList = new ArrayList<Word>();
			Word[] wordsToCheck;
			Tile[] tiles;
			Location[] locations;
			int value;
			
			if (workingSet.size() == 0) return null;
			
			else if (workingSet.size() > 1){
				//get the dimension of the word
				if ((dir = getDimension()) == null)
					return null;
			
				//reorder the spaces to be in order
				reorderedSpaces = reorderSpaces(dir);
				
				//check to make sure the distance between each letter is 2 or less
				if (!checkDistances(reorderedSpaces))
					return null;
				
				//get all crossWords to the word
				crossWords = getCrossWords(reorderedSpaces, dir, owner);
			}
			
			else {
				reorderedSpaces = reorderSpaces(Direction.SOUTH);
				crossWords1 = getCrossWords(reorderedSpaces, Direction.SOUTH, owner);
				crossWords2 = getCrossWords(reorderedSpaces, Direction.EAST, owner);
				crossWords = new Word[crossWords1.length + crossWords2.length];
				for (int i=0;i<crossWords1.length;i++){
					crossWords[i] = crossWords1[i];
				}
				for (int i=0; i<crossWords2.length;i++){
					crossWords[i+crossWords1.length] = crossWords2[i];
				}
			}
			
			
			//add any letters of words that we are building off of
			if ((finalSpaces = addBuildoffLetters(reorderedSpaces, dir)) == null)
				return null;
			
			
			tiles = new Tile[finalSpaces.length];
			locations = new Location[finalSpaces.length];
			
			//generate tiles and locations to construct the word
			for (int i=0;i<finalSpaces.length;i++){
				tiles[i] = finalSpaces[i].getTile();
				locations[i] = finalSpaces[i].getLocation();
			}
			
			//compute the value of each word
			value = computeWordValue(finalSpaces);
			
			finalWord = new Word(tiles, locations, owner, value);
			
			for (int i=0;i<crossWords.length;i++){
				if (crossWords[i].length() > 1)
					crossWordsList.add(crossWords[i]);
			}
			
			if (workingSet.size() > 1){
				wordsToCheck = new Word[crossWordsList.size() + 1];
				wordsToCheck[0] = finalWord;
				for (int i=1; i<wordsToCheck.length;i++){
					wordsToCheck[i] = crossWordsList.get(i-1);
				}
			}
			else {
				wordsToCheck = new Word[crossWordsList.size()];
				for (int i=0; i<wordsToCheck.length;i++){
					wordsToCheck[i] = crossWordsList.get(i);
				}
			}
			
			System.out.println("PRINTING WORDS TO CHECK!!");
			for (int i=0; i<wordsToCheck.length;i++){
				for (int j=0; j<wordsToCheck[i].length();j++){
					System.out.print(wordsToCheck[i].getTiles()[j].getLetter());
				}
				System.out.println("");
			}
			
			return wordsToCheck;
			
		}
		

		private Word[] getCrossWords(BoardSpace[] finalSpaces, Direction dir, Player owner) {
			Word[] words = new Word[finalSpaces.length];
			BoardSpace[] spacesPtr;
			BoardSpace[] newSpaces;
			Tile[] tiles;
			Location[] locs;
			int value;
			
			if (dir == Direction.SOUTH){
				for (int i=0; i<finalSpaces.length;i++){
					spacesPtr = new BoardSpace[1];
					spacesPtr[0] = finalSpaces[i];
					
					newSpaces = checkEast(spacesPtr);
					while (newSpaces.length > spacesPtr.length){
						spacesPtr = newSpaces;
						newSpaces = checkEast(spacesPtr);
					}
					
					newSpaces = checkWest(spacesPtr);
					while(newSpaces.length > spacesPtr.length){
						spacesPtr = newSpaces;
						newSpaces = checkWest(spacesPtr);
					}
					
					tiles = new Tile[spacesPtr.length];
					locs = new Location[spacesPtr.length];
					for (int j=0; j<spacesPtr.length;j++){
						tiles[j] = spacesPtr[j].getTile();
						locs[j] = spacesPtr[j].getLocation();
					}
					
					value = computeWordValue(spacesPtr);
					words[i] = new Word(tiles, locs, owner, value);
				}
			}
			else if (dir == Direction.EAST){
				for (int i=0; i<finalSpaces.length;i++){
					spacesPtr = new BoardSpace[1];
					spacesPtr[0] = finalSpaces[i];
				
					newSpaces = checkNorth(spacesPtr);
					while (newSpaces.length > spacesPtr.length){
						spacesPtr = newSpaces;
						newSpaces = checkNorth(spacesPtr);
					}
					
					newSpaces = checkSouth(spacesPtr);
					while(newSpaces.length > spacesPtr.length){
						spacesPtr = newSpaces;
						newSpaces = checkSouth(spacesPtr);
					}
					
					tiles = new Tile[spacesPtr.length];
					locs = new Location[spacesPtr.length];
					for (int j=0; j<spacesPtr.length;j++){
						tiles[j] = spacesPtr[j].getTile();
						locs[j] = spacesPtr[j].getLocation();
					}
					
					value = computeWordValue(spacesPtr);
					words[i] = new Word(tiles, locs, owner, value);
				}
			}
			return words;
		}


		/**
		 * 
		 * @param spaces - the spaces that represent the reordered working set based on
		 * 					position
		 * @param wordDir - the direction that this particular word is going (SOUTH, EAST)
		 * @return the spaces with any previously laid word spaces that we are building off of
		 * 			added.
		 */
		private BoardSpace[] addBuildoffLetters(BoardSpace[] spaces, Direction wordDir) {
			BoardSpace[] spacesPtr;
			BoardSpace[] newSpaces;
			
			spacesPtr = spaces;
			if (wordDir == Direction.SOUTH){
				newSpaces = checkNorth(spacesPtr);
				while(newSpaces.length > spacesPtr.length){
					spacesPtr = newSpaces;
					newSpaces = checkNorth(spacesPtr);
				}
				newSpaces = checkSouth(spacesPtr);
				while(newSpaces.length > spacesPtr.length){
					spacesPtr = newSpaces;
					newSpaces = checkSouth(spacesPtr);
				}
			}
			else if (wordDir == Direction.EAST){
				newSpaces = checkEast(spacesPtr);
				while(newSpaces.length > spacesPtr.length){
					spacesPtr = newSpaces;
					newSpaces = checkEast(spacesPtr);
				}
				newSpaces = checkWest(spacesPtr);
				while(newSpaces.length > spacesPtr.length){
					spacesPtr = newSpaces;
					newSpaces = checkWest(spacesPtr);
				}
			}
			
			spacesPtr = checkInside(spacesPtr, wordDir);
			if (spacesPtr.length == spaces.length && !hasNoWords()) return null;
			
			return spacesPtr;
		}

		/**
		 * 
		 * @param spacesPtr - pointer to the spaces that represent our partially built word
		 * @param wordDir - direction of the partially built word
		 * @return a partially built word with any word's letters we are building off of added
		 */
		private BoardSpace[] checkInside(BoardSpace[] spacesPtr, Direction wordDir) {
			BoardSpace[] ret;
			Location loc1, loc2;
			ArrayList<BoardSpace> newSpaces = new ArrayList<BoardSpace>();
			ArrayList<Location> locsToAdd = new ArrayList<Location>();
			
			if (wordDir == Direction.SOUTH){
				for (int i=0;i<spacesPtr.length-1;i++){
					loc1 = spacesPtr[i].getLocation();
					loc2 = spacesPtr[i+1].getLocation();
					newSpaces.add(spacesPtr[i]);
					
					if (loc1.distanceTo(loc2) == 2){
						newSpaces.add(getBoardSpace(new Location(loc1.getX(), loc1.getY() + 1)));
					}
				}
				newSpaces.add(spacesPtr[spacesPtr.length-1]);
			}
			else if (wordDir == Direction.EAST){
				for (int i=0;i<spacesPtr.length-1;i++){
					loc1 = spacesPtr[i].getLocation();
					loc2 = spacesPtr[i+1].getLocation();
					newSpaces.add(spacesPtr[i]);
					
					if (loc1.distanceTo(loc2) == 2){
						newSpaces.add(getBoardSpace(new Location(loc1.getX() + 1, loc1.getY())));
					}
				}
				newSpaces.add(spacesPtr[spacesPtr.length-1]);
				
			}
			ret = new BoardSpace[newSpaces.size()];
			for (int i=0; i<ret.length;i++){
				ret[i] = newSpaces.get(i);
			}
			return ret;
		}

		/**
		 * 
		 * @param spaces - spaces that represent our partially built word
		 * @return spaces with any word's letters on the west side that we are building off of
		 * added
		 */
		private BoardSpace[] checkWest(BoardSpace[] spaces) {
			Location left, leftOf;
			BoardSpace[] ret;
			left = spaces[0].getLocation();
			leftOf = new Location(left.getX() - 1, left.getY());
			
			
			if (isValidLocation(leftOf) && getBoardSpace(leftOf).isFilled()){
				ret = new BoardSpace[spaces.length+1];
				ret[0] = getBoardSpace(leftOf);
				for (int i=1; i<ret.length;i++){
					ret[i] = spaces[i-1];
				}
				return ret;
			}
			else 
				return spaces;
		}

		/**
		 * 
		 * @param spaces - spaces that represent our partially built word
		 * @return spaces with any word's letters on the east side that we are building off of
		 * added
		 */
		private BoardSpace[] checkEast(BoardSpace[] spaces) {
			Location right, rightOf;
			BoardSpace[] ret;
			right = spaces[spaces.length-1].getLocation();
			rightOf = new Location(right.getX() + 1, right.getY());
			
			if (isValidLocation(rightOf) && getBoardSpace(rightOf).isFilled()){
				ret = new BoardSpace[spaces.length+1];
				for (int i=0; i<ret.length-1;i++){
					ret[i] = spaces[i];
				}
				ret[ret.length-1] = getBoardSpace(rightOf);
				return ret;
			}
			else 
				return spaces;
		}

		/**
		 * 
		 * @param spaces - spaces that represent our partially built word
		 * @return spaces with any word's letters on the south side that we are building off of
		 * added
		 */
		private BoardSpace[] checkSouth(BoardSpace[] spaces) {
			Location bottom, below;
			BoardSpace[] ret;
			bottom = spaces[spaces.length-1].getLocation();
			below = new Location(bottom.getX(), bottom.getY() + 1);
			
			if (isValidLocation(below) && getBoardSpace(below).isFilled()){
				ret = new BoardSpace[spaces.length+1];
				for (int i=0; i<ret.length-1;i++){
					ret[i] = spaces[i];
				}
				ret[ret.length-1] = getBoardSpace(below);
				return ret;
			}
			else 
				return spaces;
		}

		/**
		 * 
		 * @param spaces - spaces that represent our partially built word
		 * @return spaces with any word's letters on the north side that we are building off of
		 * added
		 */
		private BoardSpace[] checkNorth(BoardSpace[] spaces) {
			Location top, above;
			BoardSpace[] ret;
			top = spaces[0].getLocation();
			above = new Location(top.getX(), top.getY() - 1);
			
			//see if we have another tile above
			if (isValidLocation(above) && getBoardSpace(above).isFilled()){
				ret = new BoardSpace[spaces.length+1];
				ret[0] = getBoardSpace(above);
				for (int i=1; i<ret.length;i++){
					ret[i] = spaces[i-1];
				}
				return ret;
			}
			else
				return spaces;
		}

		/**
		 * 
		 * @param reorderedSpaces - spaces that represent our partially built word
		 * @return whether or not the spaces abide by the distances rules (no consecutive
		 * spaces can be more than distance 2 apart.)
		 */
		private boolean checkDistances(BoardSpace[] reorderedSpaces) {
			Location loc1, loc2;
			
			for (int i=0; i<reorderedSpaces.length-1;i++){
				loc1 = reorderedSpaces[i].getLocation();
				loc2 = reorderedSpaces[i+1].getLocation();
				
				if (loc1.distanceTo(loc2) > 2) return false;
			}
			return true;
		}
		
		/**
		 * 
		 * @return dimension that our word must be going, null if tiles are places in more than one
		 * dimension
		 */
		private Direction getDimension(){
			Location loc1, loc2;
			Direction[] acceptableDirs = new Direction[2];
			ArrayList<BoardSpace> spaces = new ArrayList<BoardSpace>();
			
			if (workingSet.size() > 0)
				spaces.add(workingSet.get(0));
			
			//check for tiles being in different directions
			for (int i=0; i<workingSet.size()-1; i++){
				loc1 = workingSet.get(i).getLocation();
				loc2 = workingSet.get(i+1).getLocation();
				spaces.add(workingSet.get(i));
				
				if (loc1.dirTo(loc2) == null) return null;
				
				if (i==0){
					if (loc1.dirTo(loc2) == Direction.NORTH || loc1.dirTo(loc2) == Direction.SOUTH){
						acceptableDirs[0] = Direction.SOUTH;
						acceptableDirs[1] = Direction.NORTH;
					}
					else{
						acceptableDirs[0] = Direction.EAST;
						acceptableDirs[1] = Direction.WEST;
					}
				}
				else {
					if (loc1.dirTo(loc2) != acceptableDirs[0] && loc1.dirTo(loc2) != acceptableDirs[1])
						return null;
				}
			}
			
			return acceptableDirs[0];
		}
		
		/**
		 * 
		 * @param dir - dimension our word is expected to be going in
		 * @return new array that is the old array reordered by their location
		 */
		private BoardSpace[] reorderSpaces(Direction dir){
			BoardSpace[] reorderedSpaces = null;
			//all tiles are either up/down or left/right, 
			//now make the tiles in order by their x or y,
			//then check where the letter(s) that we are borrowing letters comes from
			if (dir == Direction.SOUTH){
				reorderedSpaces = buildNorthToSouth(workingSet);
				
			}
			else if (dir == Direction.EAST){
				reorderedSpaces = buildWestToEast(workingSet);
			}
			
			return reorderedSpaces;
		}
		
		/**
		 * 
		 * @param spaces - spaces that represent our full word
		 * @return the value of the word based on multiply spaces as well
		 */
		private int computeWordValue(BoardSpace[] spaces){
			int sum = 0;
			Tile currentTile;
			SpaceType currentType;
			boolean isDoubleWord = false;
			boolean isTripleWord = false;
			
			for (int i=0; i<spaces.length; i++){
				currentTile = spaces[i].getTile();
				currentType = spaces[i].getSpaceType();
				
				if (currentType == SpaceType.DOUBLE_LETTER){
					sum += currentTile.getValue()*2;
				}
				else if (currentType == SpaceType.TRIPLE_LETTER){
					sum += currentTile.getValue()*3;
				}
				else{
					sum += currentTile.getValue();
					if (currentType == SpaceType.DOUBLE_WORD) isDoubleWord = true;
					else if (currentType == SpaceType.TRIPLE_WORD) isTripleWord = true;
				}
			}
			
			if (isDoubleWord) sum *= 2;
			if (isTripleWord) sum *= 3;
			
			return sum;
		}
		
		/**
		 * 
		 * @param spaces - spaces that represent our partially built word
		 * @return reordered spaces that go West to East
		 */
		private BoardSpace[] buildWestToEast(ArrayList<BoardSpace> spaces) {
			ArrayList<BoardSpace> newSpaces = new ArrayList<BoardSpace>();
			BoardSpace[] ret = new BoardSpace[spaces.size()];
			BoardSpace currentSpace, leftMost;
			
			for (int i=0; i<spaces.size();i++){
				newSpaces.add(spaces.get(i));
			}
			for (int i=0; i<ret.length;i++){
				
				leftMost = newSpaces.get(0);
				for (int j=0; j<newSpaces.size();j++){
					currentSpace = newSpaces.get(j);
					if (currentSpace.getLocation().getX() < leftMost.getLocation().getX())
						leftMost = currentSpace;
				}
				ret[i] = leftMost;
				newSpaces.remove(leftMost);
			}
			return ret;
		}

		/**
		 * 
		 * @param spaces - spaces that represent our partially built word
		 * @return reordered spaces that go North to South
		 */
		private BoardSpace[] buildNorthToSouth(ArrayList<BoardSpace> spaces) {
			ArrayList<BoardSpace> newSpaces = new ArrayList<BoardSpace>();
			BoardSpace[] ret = new BoardSpace[spaces.size()];
			BoardSpace currentSpace, topMost;
			
			for (int i=0; i<spaces.size();i++){
				newSpaces.add(spaces.get(i));
			}
			for (int i=0; i<ret.length;i++){
				
				topMost = newSpaces.get(0);
				for (int j=0; j<newSpaces.size();j++){
					currentSpace = newSpaces.get(j);
					if (currentSpace.getLocation().getY() < topMost.getLocation().getY())
						topMost = currentSpace;
				}
				ret[i] = topMost;
				newSpaces.remove(topMost);
			}
			return ret;
		}
	}
	
}