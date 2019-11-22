package com.cruat.testng.dbreporter.common;

import org.junit.Test;

public class ReportLiquibaseRunnerTest {
	
	@Test(expected=NullPointerException.class)
	public void ctor_nullConnection_NPE() {
		new ReportLiquibaseRunner(null, "");
	}
}
