package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;

/**
 * 
 * modeled after org.testng.ISuiteResult;
 * 
 * @author morain
 *
 */
public class TestNGTest implements ReportEntity {
	
	private long id;
	private int name;
	private OffsetDateTime start;
	private OffsetDateTime end;
	
	List<TestNGClass> classes;
	
	public TestNGTest() {
		this.classes = new ArrayList<>();
	}
	
	public TestNGTest(ITestContext c) {
		throw new UnsupportedOperationException("Not Implemented");
	}
	
	/**
	 * @return the id
	 */
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
	public int getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(int name) {
		this.name = name;
	}
	
	/**
	 * @return the start
	 */
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
}
