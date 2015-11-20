package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;

import static org.junit.gen5.api.Assertions.assertEquals;

/**
 * Classes and methods are also executed if they only have default visibility, which reduces verbosity of tests.
 */
@TestInstance(Lifecycle.PER_CLASS)
class _3_Visibility {

	// we use the PER_CLASS lifecycle to assert that all methods are executed;

	private static final int EXPECTED_TEST_METHOD_COUNT = 2;
	private int executedTestMethodCount = Integer.MIN_VALUE;

	@BeforeAll
	void initializeCounter() {
		// note that if this would not run (because it is not public), the test would fail
		executedTestMethodCount = 0;
	}

	@AfterAll
	public void assertAllMethodsExecuted() {
		// this method is public because we need it to execute
		// and we already know that public methods to get executed
		assertEquals(EXPECTED_TEST_METHOD_COUNT, executedTestMethodCount);
	}

	// TESTS

	@Test
	public void runsPublicTestMethods() {
		executedTestMethodCount++;
	}

	@Test
	void runsPackageVisibleTestMethods() {
		executedTestMethodCount++;
	}

	@Test
	private void doesNotRunPrivateTestMethods() {
		throw new AssertionError("This method should not be run!");
	}

}
