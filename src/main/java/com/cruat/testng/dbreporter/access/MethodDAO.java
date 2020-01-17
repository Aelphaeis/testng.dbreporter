package com.cruat.testng.dbreporter.access;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.TestNGMethod;

public class MethodDAO extends GenericDAO<TestNGMethod>{

	public MethodDAO(EntityManager em) {
		super(em, TestNGMethod.class);
	}
}
