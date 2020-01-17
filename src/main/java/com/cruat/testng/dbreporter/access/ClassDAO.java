package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGClass;

public class ClassDAO extends GenericDAO<TestNGClass>{

	public ClassDAO(EntityManager em) {
		super(em, TestNGClass.class);
	}
	
	@Override
	public void create(TestNGClass e) {
		super.create(e);
		
		//Required because https://hibernate.atlassian.net/browse/HHH-11210
		//prevents us from using cascade persistence. 
		
		MethodDAO methodDAO = new MethodDAO(getEntityManager());
		for(int i =  0; i < e.getTestMethods().size(); i++) {
			//Enhanced for loop cannot be used here due to concurrency issues.
			methodDAO.create(e.getTestMethods().get(i));
		}
		
	}
}
