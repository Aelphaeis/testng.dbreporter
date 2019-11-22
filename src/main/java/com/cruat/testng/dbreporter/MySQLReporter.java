package com.cruat.testng.dbreporter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import com.cruat.testng.dbreporter.access.GenericDAO;
import com.cruat.testng.dbreporter.common.ReportDatabaseManager;
import com.cruat.testng.dbreporter.entities.TestNGResults;
import com.cruat.testng.dbreporter.utilities.Strings;

public class MySQLReporter implements IReporter {
	
	protected final ReportDatabaseManager dbManager;
	
	public MySQLReporter() {
		dbManager = new ReportDatabaseManager();
	}
	
	@Override
	public void generateReport(List<XmlSuite> xml, List<ISuite> s, String dir) {
		String url = dbManager.getConnectionString();
		if (Strings.isBlank(url)) {
			return;
		}
		dbManager.getLiquibaseRunner().run();
		writeResults(toResults(s));
	}
	
	public void writeResults(TestNGResults insertable) {
		EntityManager manager = dbManager.getEntityManager();
		manager.getTransaction().begin();
		new GenericDAO<>(manager, TestNGResults.class).create(insertable);
		manager.getTransaction().commit();
	}
	
	public TestNGResults toResults(Collection<ISuite> suites) {
		TestNGResults results = new TestNGResults();
		
		int passed = 0;
		int failed = 0;
		int skipped = 0;
		
		for (ISuite suite : suites) {
			// Might need to synchronize this map
			for (ISuiteResult suiteResult : suite.getResults().values()) {
				ITestContext context = suiteResult.getTestContext();
				passed += context.getPassedTests().size();
				failed += context.getFailedTests().size();
				skipped += context.getSkippedTests().size();
			}
		}
		
		List<ITestContext> contexts = suites.stream()
				.map(ISuite::getResults)
				.map(Map::values)
				.flatMap(Collection::stream)
				.map(ISuiteResult::getTestContext)
				.collect(Collectors.toList());
		
		OffsetDateTime start = contexts.stream()
				.map(ITestContext::getStartDate)
				.min(Date::compareTo)
				.map(Date::toInstant)
				.map(p -> p.atOffset(ZoneOffset.UTC))
				.orElseThrow(IllegalArgumentException::new);
		
		OffsetDateTime end = contexts.stream()
				.map(ITestContext::getEndDate)
				.max(Date::compareTo)
				.map(Date::toInstant)
				.map(p -> p.atOffset(ZoneOffset.UTC))
				.orElseThrow(IllegalArgumentException::new);
		
		results.setStartDatetime(start.toInstant().atOffset(ZoneOffset.UTC));
		results.setEndDateTime(end.toInstant().atOffset(ZoneOffset.UTC));
		results.setTotal(passed + failed + skipped);
		results.setSkipped(skipped);
		results.setFailed(failed);
		results.setPassed(passed);
		
		return results;
	}
}
