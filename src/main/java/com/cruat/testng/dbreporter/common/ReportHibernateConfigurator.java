package com.cruat.testng.dbreporter.common;

import java.util.Objects;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.MySQL8Dialect;

import com.cruat.testng.dbreporter.entities.TestNGResults;

class ReportHibernateConfigurator {
	
	private static final String DIALECT = getDialect();
	private final String url;
	
	public ReportHibernateConfigurator(String url) {
		this.url = Objects.requireNonNull(url);
	}
	
	public EntityManagerFactory buildFactory() {
		return new Configuration()
				.addAnnotatedClass(TestNGResults.class)
				.setProperty(AvailableSettings.DIALECT, DIALECT)
				.setProperty(AvailableSettings.AUTOCOMMIT, "true")
				.setProperty(AvailableSettings.URL, this.url)
				.buildSessionFactory();
	}
	
	private static String getDialect() {
		return MySQL8Dialect.class.getName();
	}
}
