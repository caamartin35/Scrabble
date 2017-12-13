package com.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryTest {

	/**
	 * theres is only one test because it is a very simple series of tests
	 */
	@Test
	public void test() {
		Dictionary dict = new Dictionary();
		assertTrue(dict.wordIsValid("abashed"));
		assertFalse(dict.wordIsValid("aadf"));
	}

}
