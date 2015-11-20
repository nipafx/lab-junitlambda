package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.Nested;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;

import static org.junit.gen5.api.Assertions.assertEquals;

/**
 * It is now really easy to group test methods into inner classes and have them be executed as well.
 */
@TestInstance(Lifecycle.PER_CLASS)
class _4_InnerClasses {

	// we use the PER_CLASS lifecycle to assert that all tests are executed

	private static final int EXPECTED_TEST_METHOD_COUNT = 2;
	private int executedTestMethodCount = 0;

	@AfterAll
	void assertAllMethodsExecuted() {
		assertEquals(EXPECTED_TEST_METHOD_COUNT, executedTestMethodCount);
	}

	// TESTS

	@Nested
	class SomeInnerClass {

		@Test
		void someTestMethod() {
			executedTestMethodCount++;
		}

	}

	@Nested
	class OtherInnerClass {

		@Test
		void someTestMethod() {
			executedTestMethodCount++;
		}

	}

	@Nested
	static class StaticClass {

		@Test
		public void doesNotRunTestMethodsInStaticInnerClasses() {
			// the documentation says "Notice that only non-static inner classes can serve as contexts."
			// but this runs anyway... strange
			throw new AssertionError("This method should not be run!");
		}

	}


}
