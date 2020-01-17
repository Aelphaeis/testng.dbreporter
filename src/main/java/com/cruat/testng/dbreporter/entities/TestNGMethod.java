package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;

import org.testng.ITestResult;

public class TestNGMethod implements ReportEntity {
	
	private long id;
	private String status;
	private boolean retried;
	private boolean isConfig;
	private String name;
	private String description;
	private String signature;
	private OffsetDateTime start;
	private OffsetDateTime end;
	
	public TestNGMethod() {
		this(null);
	}
	
	public TestNGMethod(ITestResult itr) {
		throw new UnsupportedOperationException("Not Implemented");
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
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
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	
	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
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
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
