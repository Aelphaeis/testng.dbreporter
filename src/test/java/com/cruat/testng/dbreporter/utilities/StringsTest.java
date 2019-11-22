package com.cruat.testng.dbreporter.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringsTest{
	
	@Test
	public void isBlank() {
		assertTrue(Strings.isBlank(null));
		assertTrue(Strings.isBlank(""));
		assertTrue(Strings.isBlank(" "));
		assertFalse(Strings.isBlank("foo"));
		assertFalse(Strings.isBlank("  foo  "));
	}
}
