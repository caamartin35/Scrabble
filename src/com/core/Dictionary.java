package com.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Dictionary{
	HashMap<String, Integer> words = new HashMap<String, Integer>();
	
 	public Dictionary(){
		loadDict("accepted_words.txt");
	}
	
	/**
	 * load the dictionary with valid words
	 * 
	 * @param fileName
	 */
	private void loadDict(String fileName){
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(fileName));
		    String text = null;

		    while ((text = reader.readLine()) != null) {
		       words.put(text, hashFunction(text));
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param word - key to hash
	 * @return hash value for the key
	 */
	private int hashFunction(String word){
		int hash=7;
		for (int i=0; i < word.length(); i++) {
		    hash = hash*31+word.charAt(i);
		}
		return hash;
	}
	
	/**
	 * checks if word is a valid word in the dictionary
	 * @param word - word to check
	 * @return whether word is contained in the dictionary or not
	 */
	public boolean wordIsValid(String word){
		if (words.containsKey(word)) return true;
		else return false;
	}
}