package com.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.core.Tile.TileType;
import com.util.Location;

public class WordTest {
	Word word;
	
	@Before
	public void setUp(){
		Tile[] tiles = {new Tile(1, 'a', TileType.TYPE_REGULAR)};
		Location[] locs = {new Location(1,1)};
		Player owner = new Player("Owner", tiles);
		word = new Word(tiles, locs, owner, 1);
	}
	
	/**
	 * only one test because the series of testing is simple.
	 */
	@Test
	public void test() {
		assertEquals(word.getTiles().length, 1);
		assertEquals(word.getTileLocations().length, 1);
		assertEquals(word.getValue(), 1);
		assertTrue(word.getOwner().equals(new Player("Owner", null)));
		word.switchOwner(new Player("newOwner", null));
		assertTrue(word.getOwner().equals(new Player("newOwner", null)));
		assertTrue(word.toString().equals("a"));
	}

}
