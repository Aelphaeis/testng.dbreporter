package com.cruat.testng.dbreporter.access;

import static org.hamcrest.CoreMatchers.equalTo;
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

import com.cruat.testng.dbreporter.common.ReportDatabaseManager;
import com.cruat.testng.dbreporter.entities.TestNGResults;

public class GenericDAOTest {
	
	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";
	
	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();
	@Rule
	public final ErrorCollector collector = new ErrorCollector();
	
	private ReportDatabaseManager dbmanager;
	private String connString;
	private EntityManager em;
	
	private GenericDAO<TestNGResults> dao;
	
	@Before
	public void setup() {
		String location = folder.getRoot().toString();
		connString = String.format(CONN_STR_TEMPLATE, location);
		assumeTrue(JDBC.isValidURL(connString));
		
		dbmanager = new ReportDatabaseManager(connString);
		dbmanager.getLiquibaseRunner().run();
		em = dbmanager.getEntityManager();
		dao = new GenericDAO<>(em, TestNGResults.class);
		
		
		dao.getEntityManager().getTransaction().begin();

		for (int i = 1; i <= 3; i++) {
			TestNGResults results = new TestNGResults();
			results.setStartDatetime(OffsetDateTime.now());
			results.setEndDateTime(OffsetDateTime.now());
			results.setSkipped(i);
			results.setPassed(i);
			results.setFailed(i);
			results.setTotal(i);
			dao.create(results);
		}
		
		dao.getEntityManager().getTransaction().commit();

	}
	
	@Test
	public void first_notAnElement_null() {
		assertNull(dao.first("from TestNGResults where id=?0", 4L));
	}
	
	@Test
	public void first_firstElement_firstElement() {
		TestNGResults r = dao.first("from TestNGResults where id=?0", 1L);
		assertThat(r, notNullValue());
		assertThat(r.getTotal(), equalTo(1));
		assertThat(r.getFailed(), equalTo(1));
		assertThat(r.getPassed(), equalTo(1));
		assertThat(r.getSkipped(), equalTo(1));
	}
	
	@Test(expected = DuplicateEntityException.class)
	public void distinct_allAfterfirst_firstElement() {
		dao.distinct("from TestNGResults where id > 1");
	}
	
	@Test
	public void distinct_firstElement_firstElement() {
		TestNGResults r = dao.distinct("from TestNGResults where id=?0", 1L);
		assertThat(r, notNullValue());
		assertThat(r.getTotal(), equalTo(1));
		assertThat(r.getFailed(), equalTo(1));
		assertThat(r.getPassed(), equalTo(1));
		assertThat(r.getSkipped(), equalTo(1));
	}
	
	@Test
	public void distinct_notAnElement_null() {
		assertNull(dao.distinct("from TestNGResults where id=?0", 4L));
	}
	
}
