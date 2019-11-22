package com.cruat.testng.dbreporter.common;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ReportHibernateConfiguratorTest{
	
	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";
	private String connString;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Before
	public void setup() {
		String location = folder.getRoot().toString().replace("\\", "/");
		connString = String.format(CONN_STR_TEMPLATE, location);
	}
	
	@Test(expected = NullPointerException.class)
	public void ctor_null_npe() {
		new ReportHibernateConfigurator(null);
	}
	
	@Test
	public void ctor_url_instance() {
		assertNotNull(new ReportHibernateConfigurator(""));
	}
	
	@Test
	public void buildFactory_validConnectionString_correctlyBound() {
		EntityManagerFactory f = new ReportHibernateConfigurator(connString)
				.buildFactory();
		assertNotNull(f);
	}
}
