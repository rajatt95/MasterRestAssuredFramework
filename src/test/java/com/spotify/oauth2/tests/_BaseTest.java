/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: REST Assured API Automation from scratch + Framework + CI (https://www.udemy.com/course/rest-assured-api-automation/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package com.spotify.oauth2.tests;

import com.spotify.oauth2.listeners.AnnotationTransformer;
import com.spotify.oauth2.listeners.ListenerClass;
import com.spotify.oauth2.listeners.MethodInterceptor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(value = {
		/*
		 * Any TestNG listeners can be loaded by @Listeners, except
		 * IAnnotationTransformer --> AnnotationTransformer.class can't be loaded.
		 * Testng need to know IAnnotationTransformer earlier.
		 */
		/*
		 * Issue: https://github.com/cbeust/testng/issues/446
		 */
		/* AnnotationTransformer.class, */
		ListenerClass.class, MethodInterceptor.class })
public class _BaseTest {

	/*
	 * This is created just to check whether we are able to run the test cases
	 * successfully in parallel mode
	 */

	@BeforeMethod
	public void beforeMethod(Method method) {
		System.out.println("STARTING TEST: " + method.getName());
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
	}
}
