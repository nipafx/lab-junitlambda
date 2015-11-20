package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assumptions.assumeFalse;
import static org.junit.gen5.api.Assumptions.assumeTrue;
import static org.junit.gen5.api.Assumptions.assumingThat;

/**
 * Assumptions can be used to dynamically abort tests.
 */
@TestInstance(Lifecycle.PER_CLASS)
class _5_Assumptions {

	// we use the PER_CLASS lifecycle to assert that all tests are executed

	private static final int EXPECTED_TEST_METHOD_COUNT = 2;
	private int executedTestMethodCount = 0;

	@AfterAll
	void assertAllMethodsExecuted() {
		assertEquals(EXPECTED_TEST_METHOD_COUNT, executedTestMethodCount);
	}

	// TESTS

	@Test
	void assumeTrue_true() {
		assumeTrue(true);
		executedTestMethodCount++;
	}

	@Test
	void assumeFalse_true() {
		assumeFalse(true);
		String message = "If you can see this, 'assumeFalse(true)' passed, which it obviously shouldn't.";
		throw new AssertionError(message);
	}

	@Test
	void assumeThat_trueAndFalse() {
		assumingThat(true, () -> executedTestMethodCount++);
		assumingThat(false, () -> {
			String message = "If you can see this, 'assumeFalse(true)' passed, which it obviously shouldn't.";
			throw new AssertionError(message);
		});
	}

}
