package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;

import static org.junit.gen5.api.Assertions.assertEquals;

/**
 * Note the annotation {@code @TestInstance(Lifecycle.PER_CLASS)}, which leads to having a fixed instance of this class
 * for all contained tests. This allows to have state across tests. (Why do we want this?)
 */
@TestInstance(Lifecycle.PER_CLASS)
public class _2_PerClassLifecycle {

	/**
	 * There are {@link #oneMethod()} and {@link #otherMethod()}, so the value is 2.
	 */
	private static final int EXPECTED_TEST_METHOD_COUNT = 2;

	/**
	 * Is incremented by every test method.
	 */
	private int executedTestMethodCount = Integer.MIN_VALUE;

	/*
	 * Note that the following @[Before|After]All methods are _not_ static!
	 * They don't have to be because this test class has a lifecycle PER_CLASS.
	 */

	@BeforeAll
	public void initializeCounter() {
		executedTestMethodCount = 0;
	}

	@AfterAll
	public void assertAllMethodsExecuted() {
		assertEquals(EXPECTED_TEST_METHOD_COUNT, executedTestMethodCount);
	}

	@Test
	public void oneMethod() {
		executedTestMethodCount++;
	}

	@Test
	public void otherMethod() {
		executedTestMethodCount++;
	}

}
