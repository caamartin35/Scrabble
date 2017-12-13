package com.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.core.Enchantment.EnchantmentType;
import com.core.Tile.TileType;
import com.util.Location;

/**
 * NOTE: There is no BoardTest class because this test class achieves 91% coverage of
 * the Board class.
 * 
 * @author Cody
 *
 */
public class GameTest {
	Game mTestGame;
	
	@Before
	public void setUp(){
		mTestGame = new Game();
		mTestGame.addPlayer("Jerry");
		mTestGame.addPlayer("Billy");
		mTestGame.addPlayer("Kramer");
		mTestGame.startGame();
	}
	
	/**
	 * test the numPlayers variable
	 */
	@Test
	public void testNumPlayers() {
		assertEquals(mTestGame.getNumPlayers(), 3);
	}

	/**
	 * test adding and removing to/from the working set
	 */
	@Test
	public void testWorkingSet() {
		mTestGame.addToWorkingSet(new Tile(1, 'A', TileType.TYPE_REGULAR), new Location(1,1));
		assertEquals(mTestGame.getWorkingSetLength(), 1);
		mTestGame.removeFromWorkingSet(new Location(1,2));
		assertEquals(mTestGame.getWorkingSetLength(), 1);
		mTestGame.removeFromWorkingSet(new Location(1,1));
		assertEquals(mTestGame.getWorkingSetLength(), 0);
		mTestGame.addToWorkingSet(new Tile(1, 'A', TileType.TYPE_REGULAR), new Location(1,1));
		mTestGame.addToWorkingSet(new Tile(1, 'A', TileType.TYPE_REGULAR), new Location(1,1));
		mTestGame.clearWorkingSet();
		assertEquals(mTestGame.getWorkingSetLength(), 0);
	}
	
	/**
	 * test adding/removing to/from the swap box
	 */
	@Test
	public void testSwapBox() {
		mTestGame.addToSwapBox(new Tile(1, 'a', TileType.TYPE_REGULAR));
		assertEquals(mTestGame.getSwapBox().size(), 1);
		mTestGame.removeFromSwapBox(new Tile(3, 'b', TileType.TYPE_REGULAR));
		assertEquals(mTestGame.getSwapBox().size(), 1);
		mTestGame.removeFromSwapBox(new Tile(1, 'a', TileType.TYPE_REGULAR));
		assertEquals(mTestGame.getSwapBox().size(), 0);
		mTestGame.addToSwapBox(new Tile(1, 'a', TileType.TYPE_REGULAR));
		mTestGame.addToSwapBox(new Tile(1, 'a', TileType.TYPE_REGULAR));
		mTestGame.clearSwapBox();
		assertEquals(mTestGame.getSwapBox().size(), 0);
	}
	
	/**
	 * test the user choice of pass
	 */
	@Test
	public void testPass(){
		Player current = mTestGame.getCurrentPlayer();
		mTestGame.pass();
		assertFalse(current.equals(mTestGame.getCurrentPlayer()));
		mTestGame.pass();
		mTestGame.addToSwapBox(new Tile(1,'a', TileType.TYPE_REGULAR));
		mTestGame.pass();
		assertTrue(current.equals(mTestGame.getCurrentPlayer()));

	}
	
	/**
	 * test the use choice of swap tiles
	 */
	@Test
	public void testSwapTiles(){
		ArrayList<Tile> hand = mTestGame.getCurrentHand();
		mTestGame.addToSwapBox(hand.get(0));
		Player current = mTestGame.getCurrentPlayer();
		assertEquals(current.getHand().size(), 6);
		mTestGame.swapTiles();
		assertEquals(mTestGame.getSwapBox().size(), 0);
		assertEquals(current.getHandSize(), 7);
	}
	
	/**
	 * test the user choice of play word with the 
	 * buildoff word being on the edge of the played word
	 */
	@Test
	public void testPlayWordEdges(){
		mTestGame.addToWorkingSet(new Tile(1, 'a', TileType.TYPE_REGULAR), new Location(7,5));
		mTestGame.addToWorkingSet(new Tile(3, 'b', TileType.TYPE_REGULAR), new Location(7,4));
		mTestGame.addToWorkingSet(new Tile(2, 'g', TileType.TYPE_REGULAR), new Location(7,6));
		mTestGame.playWord();
		assertEquals(mTestGame.getPlayedWords().size(), 1);
		assertTrue(mTestGame.getPlayedWords().get(0).toString().equals("bag"));
		mTestGame.addToWorkingSet(new Tile(2, 'd', TileType.TYPE_REGULAR), new Location(5,6));
		mTestGame.addToWorkingSet(new Tile(1, 'o', TileType.TYPE_REGULAR), new Location(6,6));
		mTestGame.playWord();
		assertEquals(mTestGame.getPlayedWords().size(), 2);
		assertTrue(mTestGame.getPlayedWords().get(1).toString().equals("dog"));

	}
	
	/**
	 * test the user choice of play word with the 
	 * buildoff word being on the inside of the played word
	 */
	@Test
	public void testPlayWordInside(){
		mTestGame.addToWorkingSet(new Tile(1, 'a', TileType.TYPE_REGULAR), new Location(7,5));
		mTestGame.addToWorkingSet(new Tile(3, 'b', TileType.TYPE_REGULAR), new Location(7,4));
		mTestGame.addToWorkingSet(new Tile(2, 'g', TileType.TYPE_REGULAR), new Location(7,6));
		mTestGame.playWord();
		assertEquals(mTestGame.getPlayedWords().size(), 1);
		assertTrue(mTestGame.getPlayedWords().get(0).toString().equals("bag"));
		mTestGame.addToWorkingSet(new Tile(2, 'g', TileType.TYPE_REGULAR), new Location(6,5));
		mTestGame.addToWorkingSet(new Tile(1, 'e', TileType.TYPE_REGULAR), new Location(9,5));
		mTestGame.addToWorkingSet(new Tile(3, 'm', TileType.TYPE_REGULAR), new Location(8,5));
		mTestGame.playWord();
		assertEquals(mTestGame.getPlayedWords().size(), 2);
		assertTrue(mTestGame.getPlayedWords().get(1).toString().equals("game"));
	}
	
	/**
	 * test playing a word that activates and enchantment
	 */
	@Test
	public void testPlayWordEnchantment(){
		Enchantment loseTurn = new Enchantment(EnchantmentType.LOSE_TURN);
		Player current1, current2;
		
		current1 = mTestGame.getCurrentPlayer();
		current1.setScore(10);
		mTestGame.buyEnchantment(loseTurn);
		
		mTestGame.addToWorkingSet(new Tile(1, 'a', TileType.TYPE_REGULAR), new Location(7,5));
		mTestGame.addToWorkingSet(new Tile(3, 'b', TileType.TYPE_REGULAR), new Location(7,4));
		mTestGame.addToWorkingSet(new Tile(2, 'g', TileType.TYPE_REGULAR), new Location(7,6));
		mTestGame.applyEnchantment(new Location(7,5), loseTurn);
		mTestGame.playWord();
		
		assertEquals(mTestGame.getPlayedWords().size(), 1);
		assertTrue(mTestGame.getPlayedWords().get(0).toString().equals("bag"));
		assertEquals(mTestGame.getPlayedWords().get(0).getValue() + 5, current1.getScore());
		
		current2 = mTestGame.getCurrentPlayer();
		current2.setScore(100);
		mTestGame.addToWorkingSet(new Tile(2, 'g', TileType.TYPE_REGULAR), new Location(6,5));
		mTestGame.addToWorkingSet(new Tile(1, 'e', TileType.TYPE_REGULAR), new Location(9,5));
		mTestGame.addToWorkingSet(new Tile(3, 'm', TileType.TYPE_REGULAR), new Location(8,5));
		mTestGame.playWord();
		assertEquals(current2.getScore(), 100);
		assertEquals(mTestGame.getPlayedWords().size(), 1);
	}
}
