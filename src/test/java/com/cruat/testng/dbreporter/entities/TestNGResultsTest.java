package com.cruat.testng.dbreporter.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
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

public class TestNGResultsTest {

	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	private ReportDatabaseManager dbmanager;
	private String connString;
	private EntityManager em;

	private GenericDAO<TestNGResults> dao;

	@Before
	public void setup() {
		String location = folder.getRoot().toString().replace("\\", "/");
		connString = String.format(CONN_STR_TEMPLATE, location);
		assumeTrue(JDBC.isValidURL(connString));

		dbmanager = new ReportDatabaseManager(connString);
		dbmanager.getLiquibaseRunner().run();
		em = dbmanager.getEntityManager();
		dao = new GenericDAO<>(em, TestNGResults.class);
	}

	@Test
	public void ctor_mappings_OutputMatchesInput() {
		final String query = "from TestNGResults where id=?0";
		TestNGResults expected = new TestNGResults();
		expected.setStartDatetime(OffsetDateTime.now());
		expected.setEndDateTime(OffsetDateTime.now());
		expected.setSkipped(1);
		expected.setPassed(2);
		expected.setFailed(3);
		expected.setTotal(6);

		dao.getEntityManager().getTransaction().begin();
		dao.create(expected);
		dao.getEntityManager().getTransaction().commit();

		assumeThat(expected.getId(), not(0));

		TestNGResults r = dao.distinct(query, expected.getId());

		assertThat(r, notNullValue());
		assertThat(r.getTotal(), equalTo(expected.getTotal()));
		assertThat(r.getFailed(), equalTo(expected.getFailed()));
		assertThat(r.getPassed(), equalTo(expected.getPassed()));
		assertThat(r.getSkipped(), equalTo(expected.getSkipped()));
		assertThat(r.getEndDateTime(), equalTo(expected.getEndDateTime()));
		assertThat(r.getStartDatetime(), equalTo(expected.getStartDatetime()));
	}
}
