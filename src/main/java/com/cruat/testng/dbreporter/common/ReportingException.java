package com.cruat.testng.dbreporter.common;

public class ReportingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ReportingException(Throwable cause) {
		super(cause);
	}
	
	public ReportingException(String message) {
		super(message);
	}
}
