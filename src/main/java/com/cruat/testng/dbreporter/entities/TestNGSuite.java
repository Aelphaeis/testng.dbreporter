package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

@Entity
@Table(name = "testng_suites")
public class TestNGSuite implements ReportEntity {
	
	private long id;
	private String name;
	private OffsetDateTime startDatetime;
	private OffsetDateTime endDateTime;
	
	private TestNGResults result;
	private List<TestNGTest> testResults;
	
	public TestNGSuite() { 
		testResults = new ArrayList<>();
	}
	
	public TestNGSuite(ISuite suite) {
		this();
		
		this.name = suite.getName();
		
		List<ITestContext> contexts = Stream.of(suite)
				.map(ISuite::getResults)
				.map(Map::values)
				.flatMap(Collection::stream)
				.map(ISuiteResult::getTestContext)
				.collect(Collectors.toList());
		
		startDatetime = contexts.stream()
				.map(ITestContext::getStartDate)
				.min(Date::compareTo)
				.map(Date::toInstant)
				.map(p -> p.atOffset(ZoneOffset.UTC))
				.orElseThrow(IllegalArgumentException::new);
		
		endDateTime = contexts.stream()
				.map(ITestContext::getEndDate)
				.max(Date::compareTo)
				.map(Date::toInstant)
				.map(p -> p.atOffset(ZoneOffset.UTC))
				.orElseThrow(IllegalArgumentException::new);
		
	}
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the startDatetime
	 */
	@Column(name = "start")
	public OffsetDateTime getStartDatetime() {
		return startDatetime;
	}
	
	/**
	 * @param startDatetime the startDatetime to set
	 */
	public void setStartDatetime(OffsetDateTime startDatetime) {
		this.startDatetime = startDatetime;
	}
	
	/**
	 * @return the endDateTime
	 */
	@Column(name = "end")
	public OffsetDateTime getEndDateTime() {
		return endDateTime;
	}
	
	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(OffsetDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	/**
	 * @return the resultId
	 */
	
	@ManyToOne(targetEntity = TestNGResults.class)
	@JoinColumn(name = "testng_results_id", referencedColumnName = "id")
	public TestNGResults getResult() {
		return result;
	}
	
	/**
	 * @param resultId the resultId to set
	 */
	public void setResult(TestNGResults result) {
		result.getSuites().add(this);
		this.result = result;
	}

	
	/**
	 * @return the testResults
	 */
	@Transient
	public List<TestNGTest> getTestResults() {
		return testResults;
	}

	
	/**
	 * @param testResults the testResults to set
	 */
	public void setTestResults(List<TestNGTest> testResults) {
		this.testResults = testResults;
	}
}
