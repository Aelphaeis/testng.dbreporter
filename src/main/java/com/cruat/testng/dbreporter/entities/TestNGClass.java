package com.cruat.testng.dbreporter.entities;

import org.testng.ITestResult;

public class TestNGClass implements ReportEntity {
	
	public static final String DEFAULT_PACKAGE_NAME = "[default]";
	
	private String name;
	private String pkgName;
	
	private TestNGTest context;
	public TestNGClass() { }
	
	public TestNGClass(ITestResult result) {
		this();
		
		String classname = result.getTestClass().getName();
		int dotIndex = classname.lastIndexOf('.');
		
		if(dotIndex > -1) {
			this.name = classname.substring(dotIndex + 1);
			this.pkgName = classname.substring(0, dotIndex);
		}
		else {
			this.name = classname;
			this.pkgName = DEFAULT_PACKAGE_NAME;
		}
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
	 * @return the pkgName
	 */
	public String getPkgName() {
		return pkgName;
	}
	
	/**
	 * @param pkgName
	 *            the pkgName to set
	 */
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	
	/**
	 * @return the context
	 */
	public TestNGTest getContext() {
		return context;
	}

	
	/**
	 * @param context the context to set
	 */
	public void setContext(TestNGTest context) {
		this.context = context;
	}
	
}
