package com.cruat.testng.dbreporter.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ReportDatabaseManager {
	public static final String URL_KEY = "reporting.url";
	public static final String CHANGELOG = "changelog/changelog.xml";
	
	private final String connectionString;
	
	public ReportDatabaseManager() {
		connectionString = System.getProperty(URL_KEY);
	}
	
	public String getConnectionString() {
		return connectionString;
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			throw new ReportingException(e);
		}
	}
	
	public ReportLiquibaseRunner getLiquibaseRunner() {
		return new ReportLiquibaseRunner(getConnection(), CHANGELOG);
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return new ReportHibernateConfigurator(connectionString).buildFactory();
	}
	
	public EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
}
