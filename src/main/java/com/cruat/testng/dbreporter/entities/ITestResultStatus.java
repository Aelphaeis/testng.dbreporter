package com.cruat.testng.dbreporter.entities;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ITestResultStatus {
	
	SUCCESS(1),
	FAILURE(2),
	SKIP(3),
	SUCCESS_PERCENTAGE_FAILURE(4);
	
	private final int value;
	
	private ITestResultStatus(int value) {
		this.value = value;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	public static ITestResultStatus valueOf(int i) {
		return Arrays.stream(ITestResultStatus.values())
				.filter(p -> p.getValue() == i)
				.findAny()
				.orElseThrow(NoSuchElementException::new);
	}
}
