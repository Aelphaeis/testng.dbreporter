package com.cruat.testng.dbreporter.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.MySQL8Dialect;

import com.cruat.testng.dbreporter.entities.ReportEntity;
import com.cruat.testng.dbreporter.entities.TestNGResults;

class ReportHibernateConfigurator{
	
	private static final String DIALECT = getDialect();
	private final String url;
	
	public ReportHibernateConfigurator(String url) {
		this.url = Objects.requireNonNull(url);
	}
	
	public EntityManagerFactory buildFactory() {
		Configuration conf = new Configuration();
		for (Class<? extends ReportEntity> cls : getAnnotated()) {
			conf.addAnnotatedClass(cls);
		}
		
		return conf
			.setProperty(AvailableSettings.DIALECT, DIALECT)
			.setProperty(AvailableSettings.AUTOCOMMIT, "true")
			.setProperty(AvailableSettings.URL, url)
			.buildSessionFactory();
	}
	
	public static List<Class<? extends ReportEntity>> getAnnotated() {
		List<Class<? extends ReportEntity>> annotated = new ArrayList<>();
		annotated.add(TestNGResults.class);
		return annotated;
	}
	
	private static String getDialect() {
		return MySQL8Dialect.class.getName();
	}
}
