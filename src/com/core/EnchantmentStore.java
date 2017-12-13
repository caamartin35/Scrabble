package com.core;

import java.util.ArrayList;

class EnchantmentStore{
	private int numBankrupt = 5;
	private int numLoseTurn = 5;
	private int numTakeWord = 5;
	private ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>();
	
	public EnchantmentStore(){
		for (int i=0;i<numBankrupt;i++){
			enchantments.add(new Enchantment(Enchantment.EnchantmentType.BANKRUPT));
		}
		for (int i=0;i<numLoseTurn;i++){
			enchantments.add(new Enchantment(Enchantment.EnchantmentType.LOSE_TURN));
		}
		for (int i=0;i<numTakeWord;i++){
			enchantments.add(new Enchantment(Enchantment.EnchantmentType.TAKE_WORD));
		}
	}
	
	/**
	 * 
	 * @return a list of enchantments in the store
	 */
	public ArrayList<Enchantment> getEnchantments(){
		return this.enchantments;
	}
	
	/**
	 * 
	 * @return whether or not the store is empty
	 */
	public boolean isEmpty(){
		return this.enchantments.isEmpty();
	}
	
	/**
	 * 
	 * @param type - the type of enchantment to be removed from the store
	 */
	public void removeEnchantment(Enchantment.EnchantmentType type){
		for (int i=0; i<enchantments.size();i++){
			if (enchantments.get(i).getType() == type){
				if (enchantments.get(i).getType() == Enchantment.EnchantmentType.BANKRUPT)
					numBankrupt--;
				if (enchantments.get(i).getType() == Enchantment.EnchantmentType.LOSE_TURN)
					numLoseTurn--;
				if (enchantments.get(i).getType() == Enchantment.EnchantmentType.TAKE_WORD)
					numTakeWord--;
				enchantments.remove(i);
				System.out.println("REMOVED ENCHANTMENT");
				break;
			}
		}
	}
	
	public int getNumBankrupts(){
		return numBankrupt;
	}
	
	public int getNumLoseTurns(){
		return numLoseTurn;
	}
	
	public int getNumTakeWords(){
		return numTakeWord;
	}
		
}