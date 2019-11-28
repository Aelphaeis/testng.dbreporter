package com.cruat.testng.dbreporter.access;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cruat.testng.dbreporter.entities.ReportEntity;

interface DataAccessObject<T extends ReportEntity> {
	EntityManager getEntityManager();
	Class<T> getEntityClass();
	
	default T first(String query, Object...params) {
		List<T> result = query(query, params);
		return result.isEmpty() ? null : result.get(0);
	}

	default T distinct(String query, Object... params) {
		List<T> result = query(query, params);
		if (result.size() > 1) {
			String err = "More than one element meeting criteria";
			throw new DuplicateEntityException(err);
		}
		return result.isEmpty() ? null : result.get(0);
	}

	default List<T> query(String queryString, Object... params) {
		EntityManager manager = getEntityManager();
		TypedQuery<T> q = manager.createQuery(queryString, getEntityClass());
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		return q.getResultList();
	}
	
	default T update(T entity) {
		return getEntityManager().merge(entity);
	}

	default void create(T entity) {
		getEntityManager().persist(entity);
	}

	default void delete(T entity) {
		getEntityManager().remove(entity);
	}
	
	default void delete(Iterable<T> entities) {
		for(T entity : entities) {
			getEntityManager().remove(entity);
		}
	}
}
