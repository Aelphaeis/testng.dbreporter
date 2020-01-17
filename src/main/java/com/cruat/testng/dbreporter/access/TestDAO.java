package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGClass;
import com.cruat.testng.dbreporter.entities.TestNGTest;

public class TestDAO extends GenericDAO<TestNGTest> {
	
	public TestDAO(EntityManager em) {
		super(em, TestNGTest.class);
	}
	
	@Override
	public void create(TestNGTest e) {
		super.create(e);

		//Required because https://hibernate.atlassian.net/browse/HHH-11210
		//prevents us from using cascade persistence. 
		ClassDAO classDAO = new ClassDAO(getEntityManager());
		for (TestNGClass c : e.getClasses()) {
			classDAO.create(c);
		}
	}
}
