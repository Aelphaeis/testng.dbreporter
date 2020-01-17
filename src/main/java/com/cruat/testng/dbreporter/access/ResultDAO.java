package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGResults;
import com.cruat.testng.dbreporter.entities.TestNGSuite;

public class ResultDAO extends GenericDAO<TestNGResults> {

	public ResultDAO(EntityManager em) {
		super(em, TestNGResults.class);
	}
	
	@Override
	public void create(TestNGResults e) {
		super.create(e);

		//Required because of https://hibernate.atlassian.net/browse/HHH-11210
		SuiteDAO suiteDAO = new SuiteDAO(getEntityManager());
		for(TestNGSuite suite : e.getSuites()) {
			suiteDAO.create(suite);
		}
	}
}
