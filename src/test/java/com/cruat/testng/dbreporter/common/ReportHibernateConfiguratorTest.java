package com.cruat.testng.dbreporter.common;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ReportHibernateConfiguratorTest{
	
	@Test(expected = NullPointerException.class)
	public void ctor_null_npe() {
		new ReportHibernateConfigurator(null);
	}
	
	@Test
	public void ctor_url_instance() {
		assertNotNull(new ReportHibernateConfigurator(""));
	}
}
