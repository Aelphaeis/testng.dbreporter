package com.cruat.testng.dbreporter.entities;

public class TestNGClass {
	
	private String name;
	private String pkgName;
	
	public TestNGClass() {
		throw new UnsupportedOperationException("Not Implemented");
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
	
}
