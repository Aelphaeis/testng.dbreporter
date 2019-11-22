package com.cruat.testng.dbreporter.access;

import java.util.Objects;

import javax.persistence.EntityManager;

public class GenericDAO<T> implements DataAccessObject<T> {
	
	private final EntityManager entityManager;
	private final Class<T> entityClass;
	
	public GenericDAO(EntityManager em, Class<T> cls) {
		this.entityManager = Objects.requireNonNull(em);
		this.entityClass = Objects.requireNonNull(cls);
	}
	
	@Override
	public EntityManager geEntityManager() {
		return this.entityManager;
	}
	
	@Override
	public Class<T> getEntityClass() {
		return this.entityClass;
	}
}
