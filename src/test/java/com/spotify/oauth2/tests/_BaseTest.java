package com.spotify.oauth2.tests;

import com.spotify.oauth2.listeners.AnnotationTransformer;
import com.spotify.oauth2.listeners.ListenerClass;
import com.spotify.oauth2.listeners.MethodInterceptor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(value = {AnnotationTransformer.class,
		ListenerClass.class,
		MethodInterceptor.class})
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
