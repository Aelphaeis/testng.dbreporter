package com.cruat.testng.dbreporter.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManagerFactory;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import com.cruat.testng.dbreporter.entities.ReportEntity;
import com.cruat.testng.dbreporter.entities.TestNGResults;
import com.cruat.testng.dbreporter.entities.TestNGSuite;

class ReportHibernateConfigurator{
	
	private final String url;
	
	public ReportHibernateConfigurator(String url) {
		this.url = Objects.requireNonNull(url);
	}
	
	public EntityManagerFactory buildFactory() {
		Configuration conf = new Configuration()
				.setProperty(AvailableSettings.AUTOCOMMIT, "true")
				.setProperty(AvailableSettings.URL, url);
		
		for (Class<? extends ReportEntity> cls : getAnnotated()) {
			conf.addAnnotatedClass(cls);
		}
		
		StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties())
				.build();
		
		return conf.buildSessionFactory(reg);
		
	}
	
	public static List<Class<? extends ReportEntity>> getAnnotated() {
		List<Class<? extends ReportEntity>> annotated = new ArrayList<>();
		annotated.add(TestNGResults.class);
		annotated.add(TestNGSuite.class);
		return annotated;
	}
	
}
