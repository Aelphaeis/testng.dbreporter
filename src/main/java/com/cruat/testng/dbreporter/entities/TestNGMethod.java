package com.cruat.testng.dbreporter.entities;

import org.testng.ITestResult;

public class TestNGMethod {
	
	private String status;
	private boolean retried;
	
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
}
