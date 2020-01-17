package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGClass;

public class ClassDAO extends GenericDAO<TestNGClass>{

	public ClassDAO(EntityManager em) {
		super(em, TestNGClass.class);
	}
	
}
