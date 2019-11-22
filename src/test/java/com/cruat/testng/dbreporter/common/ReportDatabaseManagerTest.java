package com.cruat.testng.dbreporter.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ReportDatabaseManagerTest{
	
	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	private String connString;
	
	@Before
	public void setup() {
		String location = folder.getRoot().toString().replace("\\", "/");
		connString = String.format(CONN_STR_TEMPLATE, location);
	}
	
	@Test
	public void ctor_connectionString_noNulls() {
		ReportDatabaseManager rdm = new ReportDatabaseManager(connString);
		assertNotNull(rdm.getEntityManagerFactory());
		assertNotNull(rdm.getEntityManager());
		assertNotNull(rdm.getLiquibaseRunner());
		assertNotNull(rdm.getConnection());
		assertEquals(connString, rdm.getConnectionString());
	}
	
	@Test(expected=ReportingException.class)
	public void getConnection_invalidString_reportingException() {
		ReportDatabaseManager rdm = new ReportDatabaseManager("");
		rdm.getConnection();
	}
}
