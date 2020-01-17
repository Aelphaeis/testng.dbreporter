package com.cruat.testng.dbreporter.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.testng.ITestResult;

import com.cruat.testng.dbreporter.utilities.ResultComparator;

public class TestNGClass implements ReportEntity {
	
	public static final String DEFAULT_PACKAGE_NAME = "[default]";
	
	private String name;
	private String pkgName;
	
	private TestNGTest context;
	private List<TestNGMethod> methods;
	
	public TestNGClass() { 
		this.methods = new ArrayList<>();
	}
	
	public TestNGClass(Entry<String, List<ITestResult>> e) {
		this();
		
		String classname = e.getKey();
		int dotIndex = classname.lastIndexOf('.');
		
		if(dotIndex > -1) {
			this.name = classname.substring(dotIndex + 1);
			this.pkgName = classname.substring(0, dotIndex);
		}
		else {
			this.name = classname;
			this.pkgName = DEFAULT_PACKAGE_NAME;
		}
		
		e.getValue().sort(ResultComparator.INSTANCE);
		for(ITestResult result : e.getValue()) {
			TestNGMethod method = new TestNGMethod(result);
			method.setGroup(this);
			methods.add(method);
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

	
	/**
	 * @return the methods
	 */
	public List<TestNGMethod> getTestMethods() {
		return methods;
	}

	
	/**
	 * @param methods the methods to set
	 */
	public void setTestMethods(List<TestNGMethod> testMethods) {
		this.methods = testMethods;
	}
	
}
