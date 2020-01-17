package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.stream.LongStream;

import org.testng.ITestResult;

public class TestNGMethod implements ReportEntity {
	
	private long id;
	private ITestResultStatus status;
	private boolean retried;
	private boolean isConfig;
	private String name;
	private String instanceName;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	
	private TestNGClass group;
	
	public TestNGMethod() {
	}
	
	public TestNGMethod(ITestResult itr) {
		this();
		
		status = ITestResultStatus.valueOf(itr.getStatus());
		isConfig = !itr.getMethod().isTest();
		name = itr.getMethod().getMethodName();
		instanceName = itr.getInstanceName();
		description = itr.getMethod().getDescription();
		retried = itr.wasRetried();
		
		LongStream.of(itr.getStartMillis())
				.mapToObj(Date::new)
				.map(Date::toInstant)
				.map(p -> p.atOffset(ZoneOffset.UTC))
				.findFirst()
				.ifPresent(this::setStart);
		
		LongStream.of(itr.getEndMillis())
				.mapToObj(Date::new)
				.map(Date::toInstant)
				.map(p -> p.atOffset(ZoneOffset.UTC))
				.findFirst()
				.ifPresent(this::setEnd);
	}
	
	/**
	 * @return the status
	 */
	public ITestResultStatus getStatus() {
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ITestResultStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the retried
	 */
	public boolean isRetried() {
		return retried;
	}
	
	/**
	 * @param retried
	 *            the retried to set
	 */
	public void setRetried(boolean retried) {
		this.retried = retried;
	}
	
	/**
	 * @return the isConfig
	 */
	public boolean isConfig() {
		return isConfig;
	}
	
	/**
	 * @param isConfig
	 *            the isConfig to set
	 */
	public void setConfig(boolean isConfig) {
		this.isConfig = isConfig;
	}
	
	/**
	 * @return the name
	 */
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the group
	 */
	public TestNGClass getGroup() {
		return group;
	}
	
	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(TestNGClass group) {
		this.group = group;
	}
	
	/**
	 * @return the instanceName
	 */
	public String getInstanceName() {
		return instanceName;
	}
	
	/**
	 * @param instanceName
	 *            the instanceName to set
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
}
