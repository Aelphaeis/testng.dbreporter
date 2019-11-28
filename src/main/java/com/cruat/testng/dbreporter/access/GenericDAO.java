package com.cruat.testng.dbreporter.access;

import java.util.Objects;

import javax.persistence.EntityManager;

import com.cruat.testng.dbreporter.entities.ReportEntity;

public class GenericDAO<T extends ReportEntity> implements DataAccessObject<T> {
	
	private final EntityManager entityManager;
	private final Class<T> entityClass;
	
	public GenericDAO(EntityManager em, Class<T> cls) {
		this.entityManager = Objects.requireNonNull(em);
		this.entityClass = Objects.requireNonNull(cls);
	}
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	@Override
	public Class<T> getEntityClass() {
		return this.entityClass;
	}
}
