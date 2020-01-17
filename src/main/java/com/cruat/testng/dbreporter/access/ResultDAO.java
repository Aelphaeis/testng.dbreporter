package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGResults;

public class ResultDAO extends GenericDAO<TestNGResults> {

	public ResultDAO(EntityManager em) {
		super(em, TestNGResults.class);
	}
	
	@Override
	public void create(TestNGResults e) {
		super.create(e);

		//Required because https://hibernate.atlassian.net/browse/HHH-11210
		//prevents us from using cascade persistence. 
		SuiteDAO suiteDAO = new SuiteDAO(getEntityManager());
		for(int i =  0; i < e.getSuites().size(); i++) {
			//Enhanced for loop cannot be used here due to concurrency issues.
			suiteDAO.create(e.getSuites().get(i));
		}
	}
}
