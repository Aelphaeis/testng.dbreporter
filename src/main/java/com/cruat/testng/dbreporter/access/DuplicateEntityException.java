package com.cruat.testng.dbreporter.access;

import com.cruat.testng.dbreporter.common.ReportingException;

public class DuplicateEntityException extends ReportingException {
	
	private static final long serialVersionUID = 1L;
	
	public DuplicateEntityException(String message) {
		super(message);
	}
}
