package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGSuite;
import com.cruat.testng.dbreporter.entities.TestNGTest;

public class SuiteDAO extends GenericDAO<TestNGSuite> {
	
	public SuiteDAO(EntityManager em) {
		super(em, TestNGSuite.class);
	}
	
	@Override
	public void create(TestNGSuite e) {
		super.create(e);

		//Required because https://hibernate.atlassian.net/browse/HHH-11210
		//prevents us from using cascade persistence. 
		TestDAO testDAO = new TestDAO(getEntityManager());
		for(TestNGTest c : e.getContexts()) {
			testDAO.create(c);
		}
	}
	
}
