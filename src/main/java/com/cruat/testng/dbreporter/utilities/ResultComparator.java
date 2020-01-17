package com.cruat.testng.dbreporter.utilities;

import java.util.Comparator;

import org.testng.ITestResult;

public class ITestResultComparator implements Comparator<ITestResult> {
	
	@Override
	public int compare(ITestResult a, ITestResult b) {
		return (int) (a.getStartMillis() - b.getStartMillis());
	}
	
}
