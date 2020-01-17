package com.cruat.testng.dbreporter.utilities;

import java.util.Comparator;

import org.testng.ITestResult;

public class ResultComparator implements Comparator<ITestResult> {
	
	public static final ResultComparator INSTANCE = new ResultComparator();
	

	private ResultComparator() { } 
	
	@Override
	public int compare(ITestResult a, ITestResult b) {
		return (int) (a.getStartMillis() - b.getStartMillis());
	}
	
}
