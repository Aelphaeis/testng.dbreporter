package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.cruat.testng.dbreporter.utilities.ResultComparator;

@Entity
@Table(name = "testng_tests")
public class TestNGTest implements ReportEntity {
	
	private long id;
	private String name;
	private OffsetDateTime start;
	private OffsetDateTime end;
	
	private TestNGSuite suite;
	private List<TestNGClass> classes;
	
	
	public TestNGTest() {
		this.classes = new ArrayList<>();
	}
	
	public TestNGTest(ITestContext c) {
		this();
		
		this.name = c.getName();
		this.end = c.getEndDate().toInstant().atOffset(ZoneOffset.UTC);
		this.start = c.getStartDate().toInstant().atOffset(ZoneOffset.UTC);
		
		List<ITestResult> aggregation  = new ArrayList<>();
		aggregation.addAll(sort(c.getPassedTests()));
		aggregation.addAll(sort(c.getFailedTests()));
		aggregation.addAll(sort(c.getSkippedTests()));
		aggregation.addAll(sort(c.getPassedConfigurations()));
		aggregation.addAll(sort(c.getSkippedConfigurations()));
		aggregation.addAll(sort(c.getFailedConfigurations()));
		aggregation.addAll(sort(c.getFailedButWithinSuccessPercentageTests()));
		
		
		Map<String, List<ITestResult>> results = aggregation.stream()
			.collect(Collectors.groupingBy(TestNGTest::resolveName));
			
		for(Entry<String, List<ITestResult>> result : results.entrySet()) {
			TestNGClass cls = new TestNGClass(result);
			cls.setContext(this);
			classes.add(cls);
		}
	}
	
	private static String resolveName(ITestResult result) {
		return result.getTestClass().getName();
	}
	
	private static List<ITestResult> sort(IResultMap in) {
		return Stream.of(in)
				.map(IResultMap::getAllResults)
				.flatMap(Set::stream)
				.sorted(ResultComparator.INSTANCE)
				.collect(Collectors.toList());
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
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the start
	 */
	@Column(name = "start")
	public OffsetDateTime getStart() {
		return start;
	}
	
	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(OffsetDateTime start) {
		this.start = start;
	}
	
	/**
	 * @return the end
	 */
	@Column(name = "end")
	public OffsetDateTime getEnd() {
		return end;
	}
	
	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(OffsetDateTime end) {
		this.end = end;
	}

	
	/**
	 * @return the suite
	 */
	@ManyToOne(targetEntity = TestNGSuite.class)
	@JoinColumn(name = "testng_suite_id", referencedColumnName = "id")
	public TestNGSuite getSuite() {
		return suite;
	}

	
	/**
	 * @param suite the suite to set
	 */
	public void setSuite(TestNGSuite suite) {
		this.suite = suite;
	}

	
	/**
	 * @return the classes
	 */
	@OneToMany(mappedBy = "context")
	public List<TestNGClass> getClasses() {
		return classes;
	}

	
	/**
	 * @param classes the classes to set
	 */
	public void setClasses(List<TestNGClass> classes) {
		this.classes = classes;
	}
}
