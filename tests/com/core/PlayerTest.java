package com.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player mTestPlayer;
	
	@Before
	public void setUp(){
		Tile[] hand = {new Tile(1, 'A', Tile.TileType.TYPE_REGULAR),
				new Tile(1, 'A', Tile.TileType.TYPE_REGULAR),
				new Tile(3, 'B', Tile.TileType.TYPE_REGULAR),
				new Tile(3, 'B', Tile.TileType.TYPE_REGULAR),
				new Tile(3, 'C', Tile.TileType.TYPE_REGULAR),
				new Tile(3, 'C', Tile.TileType.TYPE_REGULAR)};
		mTestPlayer = new Player("Test", hand);
	}
	
	/**
	 * test the hand size
	 */
	@Test
	public void testHandSize() {
		assertEquals(mTestPlayer.getHandSize(), 6);
	}

	/**
	 * test the need to fill function for player hand
	 */
	@Test
	public void testNeedToFill() {
		assertTrue(mTestPlayer.needToFillHand(7));
		mTestPlayer.addTileToHand(new Tile(1, 'A', Tile.TileType.TYPE_REGULAR));
		assertFalse(mTestPlayer.needToFillHand(7));
	}
	
	/**
	 * test receiving enchantments
	 */
	@Test
	public void testEnchantments() {
		mTestPlayer.receiveEnchantment(new Enchantment(Enchantment.EnchantmentType.BANKRUPT));
		assertNotNull(mTestPlayer.getEnchantments());
	}
	
	/**
	 * test removing a tile from the players hand
	 */
	@Test
	public void testRemoveTile(){
		mTestPlayer.removeTileFromHand(new Tile(1, 'A', Tile.TileType.TYPE_REGULAR));
		assertEquals(mTestPlayer.getHandSize(), 5);
		mTestPlayer.removeTileFromHand(new Tile(1, 'A', Tile.TileType.TYPE_REGULAR));
		assertEquals(mTestPlayer.getHandSize(), 4);
		mTestPlayer.removeTileFromHand(new Tile(1, 'A', Tile.TileType.TYPE_REGULAR));
		assertEquals(mTestPlayer.getHandSize(), 4);
		assertEquals(mTestPlayer.getHand().size(), mTestPlayer.getHandSize());
	}
	
	/**
	 * test the get name function
	 */
	@Test
	public void testGetName(){
		Player test = new Player("Test", null);
		assertTrue(mTestPlayer.getName().equals("Test"));
		assertTrue(mTestPlayer.equals(test));
	}
	
}
