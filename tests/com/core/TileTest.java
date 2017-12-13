package com.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.core.Tile.TileType;

public class TileTest {
	
	/**
	 * only one test because the series of testing is simple
	 */
	@Test
	public void test() {
		Tile tile = new Tile(10, 'C', TileType.TYPE_REGULAR);
		Tile tile2 = new Tile(0, '0', TileType.TYPE_BLANK);
		Enchantment ench = new Enchantment(Enchantment.EnchantmentType.BANKRUPT);
		tile.setEnchantment(ench);
		assertNotNull(tile.getEnchantment());
		assertEquals(tile2.getEnchantment(), null);
		assertFalse(tile.equals(tile2));
		assertEquals(tile.getType(), TileType.TYPE_REGULAR);
		assertEquals(tile.getLetter(), 'C');
		assertEquals(tile.getValue(), 10);
	}

}
