package com.cruat.testng.dbreporter;

import org.junit.Test;
import org.testng.TestNG;

public class MySQLReporterTest {
	
	private static final String PROTO = "src/test/resources/prototype-test.xml";
	
	@Test
	public void test() {
		TestNG.main(new String[] { PROTO });
	}
}
