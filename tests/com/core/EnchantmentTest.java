package com.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.core.Enchantment.EnchantmentType;
import com.core.Tile.TileType;
import com.util.Location;

public class EnchantmentTest {
	Enchantment mTestEnch1;
	Enchantment mTestEnch2;
	Enchantment mTestEnch3;
	Game mTestGame;
	Player mTestPlayer;
	
	/** Called before each test case method. */
    @Before
    public void setUp() throws Exception {
        // Start each test case method with a brand new AvlTreeSet object.
       mTestEnch1 = new Enchantment(EnchantmentType.BANKRUPT);
       mTestEnch2 = new Enchantment(EnchantmentType.LOSE_TURN);
       mTestEnch3 = new Enchantment(EnchantmentType.TAKE_WORD);
       mTestGame = new Game();
       Tile[] tiles = {new Tile(10, 'C', TileType.TYPE_REGULAR)};
       Location[] locs = {new Location(0,0)};
       mTestPlayer = new Player("T", null);
       mTestPlayer.setScore(100);
       mTestEnch1.setOwner(mTestPlayer);
       mTestGame.setCurrentWord(new Word(tiles, locs, mTestPlayer, 10));
       mTestGame.setCurrentPlayer(mTestPlayer);
    }

    /** Called after each test case method. */
    @After
    public void tearDown() throws Exception {
        // Don't need to do anything here.
    }

    
    /** Tests the basics of the Enchantment Class. */
    @Test
    public void testBasics() {
    	Player testPlayer = new Player("T", null);
    	assertEquals(mTestEnch1.getType(), EnchantmentType.BANKRUPT);
    	assertTrue(mTestEnch1.getOwner().equals(testPlayer));
    	assertEquals(mTestEnch1.getPrice(), 5);
    	assertFalse(mTestEnch1.isActivated());
    }
    
    /**
     * tests the executing of the enchantment type bankrupt
     */
    @Test
    public void testExecuteBankrupt(){
    	Player enchOwner = new Player("TheOwner", null);
    	
    	//test bankrupt
    	mTestEnch1.execute(mTestGame, enchOwner);
    	assertEquals(mTestGame.getCurrentPlayer().getScore(), 0);
    }
    
    /**
     * test the execution of the enchantment type lose turn
     */
    @Test
    public void testExecuteLoseTurn(){
    	Player enchOwner = new Player("TheOwner", null);
    	
    	//test lose turn
    	mTestEnch2.execute(mTestGame, enchOwner);
    	assertEquals(mTestGame.getCurrentWord().getOwner(), null);
    }
    
    /**
     * test the execution of the enchantment type take word
     */
    @Test
    public void testExecuteTakeWord(){
    	Player enchOwner = new Player("TheOwner", null);
    	
    	//test lose turn
    	mTestEnch3.execute(mTestGame, enchOwner);
    	assertTrue(mTestGame.getCurrentWord().getOwner().equals(enchOwner));
    }
}
