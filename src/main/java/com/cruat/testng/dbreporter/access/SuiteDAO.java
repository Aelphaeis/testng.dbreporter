package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGSuite;

public class SuiteDAO extends GenericDAO<TestNGSuite> {
	
	public SuiteDAO(EntityManager em) {
		super(em, TestNGSuite.class);
	}
}
