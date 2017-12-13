package com.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.core.Enchantment.EnchantmentType;

public class EnchantmentStoreTest {

	/**
	 * there is only one test because it is a very simple series of tests
	 */
	@Test
	public void test() {
		EnchantmentStore eStore = new EnchantmentStore();
		assertEquals(eStore.getEnchantments().size(), 15);
		assertFalse(eStore.isEmpty());
		eStore.removeEnchantment(EnchantmentType.BANKRUPT);
		assertEquals(eStore.getEnchantments().size(), 14);
	}

}
