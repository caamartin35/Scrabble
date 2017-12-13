package com.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.core.Tile.TileType;

public class BagTest {

	/**
	 * there is only one test because it is a very simply series of tests
	 */
	@Test
	public void test() {
		Bag bag = new Bag();
		assertNotNull(bag.getRandomTile());
		assertFalse(bag.isEmpty());
		while(!bag.isEmpty()){
			bag.getRandomTile();
		}
		Tile[] tiles = {new Tile(10, 'c', TileType.TYPE_REGULAR)};
		bag.addTilesToBag(tiles);
		Tile testTile = new Tile(10, 'c', TileType.TYPE_REGULAR);
		assertTrue(bag.getRandomTile().equals(testTile));
		assertEquals(bag.getRandomTile(), null);
	}

}
