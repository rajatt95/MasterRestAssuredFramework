package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class _BaseTest {

	/*
	 * This is created just to check whether we are able to run the test cases
	 * successfully in parallel mode
	 */

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("STARTING TEST: " + m.getName());
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
	}
}
