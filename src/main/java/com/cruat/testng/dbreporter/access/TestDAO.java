package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGClass;
import com.cruat.testng.dbreporter.entities.TestNGTest;

public class TestDAO extends GenericDAO<TestNGTest>{

	public TestDAO(EntityManager em) {
		super(em, TestNGTest.class);
	}
	
	@Override
	public void create(TestNGTest e) {
		super.create(e);
	
	}
	
}
