package com.cruat.testng.dbreporter.common;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Table;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sqlite.JDBC;

public class ReportLiquibaseRunnerTest{
	
	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";
	private static final String CHANGELOG = ReportDatabaseManager.CHANGELOG;
	private String connectionString;
	
	private ReportLiquibaseRunner manager;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Before
	public void setup() {
		String location = folder.getRoot().toString().replace("\\", "/");
		connectionString = String.format(CONN_STR_TEMPLATE, location);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void ctor_nullConnection_NPE() {
		new ReportLiquibaseRunner(null, "");
	}
	
	@Test(expected = NullPointerException.class)
	public void ctor_nullChangeLog_NPE() throws SQLException {
		assumeTrue(JDBC.isValidURL(connectionString));
		try (Connection c = DriverManager.getConnection(connectionString)) {
			new ReportLiquibaseRunner(c, null);
		}
	}
	
	@Test
	public void ctor_goodInputs_init() throws SQLException {
		assumeTrue(JDBC.isValidURL(connectionString));
		try (Connection c = DriverManager.getConnection(connectionString)) {
			assertNotNull(new ReportLiquibaseRunner(c, CHANGELOG));
		}
	}
	
	@Test(expected = ReportingException.class)
	public void run_badChangeLog_reportingException() throws SQLException{
		assumeTrue(JDBC.isValidURL(connectionString));
		try (Connection c = DriverManager.getConnection(connectionString)) {
			manager = new ReportLiquibaseRunner(c, "");
			manager.run();
		}
	}
	
	@Test
	public void run_validConnectionAndInput_databaseCreated()
			throws SQLException {
		assumeTrue(JDBC.isValidURL(connectionString));
		try (Connection c = DriverManager.getConnection(connectionString)) {
			manager = new ReportLiquibaseRunner(c, CHANGELOG);
			manager.run();
			
			File dbFile = new File(folder.getRoot(), "test.db");
			assertTrue("database file does not exist", dbFile.exists());
			
			String[] expected = getAnnotatedTables();
			List<String> results = getTableList(c);
			assertThat(results, hasItems(expected));
		}
	}
	
	private static List<String> getTableList(Connection c) throws SQLException {
		List<String> tables = new ArrayList<>();
		ResultSet rs = c.getMetaData().getTables(null, null, null, null);
		while (rs.next()) {
			tables.add(rs.getString("TABLE_NAME"));
		}
		return tables;
	}
	
	private static String[] getAnnotatedTables() {
		return ReportHibernateConfigurator.getAnnotated()
				.stream()
				.map(p -> p.getAnnotation(Table.class))
				.map(Table::name)
				.collect(Collectors.toList())
				.toArray(new String[0]);
	}
}
