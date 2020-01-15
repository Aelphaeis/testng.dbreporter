package com.cruat.testng.dbreporter.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.time.OffsetDateTime;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TemporaryFolder;
import org.sqlite.JDBC;

import com.cruat.testng.dbreporter.access.GenericDAO;
import com.cruat.testng.dbreporter.common.ReportDatabaseManager;

public class TestNGSuiteTest {
	
	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";
	
	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();
	@Rule
	public final ErrorCollector collector = new ErrorCollector();
	
	private ReportDatabaseManager dbmanager;
	private String connString;
	private EntityManager em;
	
	private GenericDAO<TestNGResults> results;
	private GenericDAO<TestNGSuite> suites;
	
	@Before
	public void setup() {
		String location = folder.getRoot().toString();
		connString = String.format(CONN_STR_TEMPLATE, location);
		assumeTrue(JDBC.isValidURL(connString));
		
		dbmanager = new ReportDatabaseManager(connString);
		dbmanager.getLiquibaseRunner().run();
		em = dbmanager.getEntityManager();
		results = new GenericDAO<>(em, TestNGResults.class);
		suites = new GenericDAO<>(em, TestNGSuite.class);
	}
	
	@Test
	public void ctor_mapping_outputMatchesInput() {
		final String query = "from TestNGSuite s where s.name = ?0";
		TestNGSuite expected = new TestNGSuite();
		expected.setName("test");
		expected.setResult(new TestNGResults());
		expected.setEndDateTime(OffsetDateTime.now());
		expected.setStartDatetime(OffsetDateTime.now());
		expected.getResult().setEndDateTime(OffsetDateTime.now());
		expected.getResult().setStartDatetime(OffsetDateTime.now());

		
		results.getEntityManager().getTransaction().begin();
		results.create(expected.getResult());
		results.getEntityManager().getTransaction().commit();
		
		assertThat(expected.getId(), is(not(0L)));
		
		TestNGSuite r = suites.distinct(query, "test");

		assertThat(r, notNullValue());
		assertThat(r.getName(), equalTo("test"));
		assertThat(r.getEndDateTime(), equalTo(expected.getEndDateTime()));
		assertThat(r.getStartDatetime(), equalTo(expected.getStartDatetime()));

	}
	
}
