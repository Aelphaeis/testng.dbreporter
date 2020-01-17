package com.cruat.testng.dbreporter;

import java.util.List;

import javax.persistence.EntityManager;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import com.cruat.testng.dbreporter.access.ResultDAO;
import com.cruat.testng.dbreporter.common.ReportDatabaseManager;
import com.cruat.testng.dbreporter.entities.TestNGResults;
import com.cruat.testng.dbreporter.utilities.Strings;

public class DatabaseReporter implements IReporter {
	public static final String URL_KEY = "reporting.url";

	private final ReportDatabaseManager dbManager;

	public DatabaseReporter() {
		dbManager = new ReportDatabaseManager(System.getProperty(URL_KEY));
	}

	@Override
	public void generateReport(List<XmlSuite> xml, List<ISuite> s, String dir) {
		if (!Strings.isBlank(dbManager.getConnectionString())) {
			dbManager.getLiquibaseRunner().run();
			writeResults(new TestNGResults(s));
		}
	}

	public void writeResults(TestNGResults insertable) {
		EntityManager manager = dbManager.getEntityManager();
		manager.getTransaction().begin();
		new ResultDAO(manager).create(insertable);
		manager.getTransaction().commit();
	}
}
