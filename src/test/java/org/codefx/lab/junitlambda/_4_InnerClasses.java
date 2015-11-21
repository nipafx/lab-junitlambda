package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.Nested;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;

import static org.codefx.lab.junitlambda.Console.print;
import static org.junit.gen5.api.Assertions.assertEquals;

/**
 * It is now really easy to group test methods into inner classes and have them be executed as well.
 */
@TestInstance(Lifecycle.PER_CLASS)
class _4_InnerClasses {

	@Test
	void nothing() {
		// this empty test is (currently?) required so that 'InnerClass' gets picked up end its tests executed
	}

	// TESTS

	@Nested
	class InnerClass {

		@Test
		void someTestMethod() {
			print("Greetings from a nested test class.");
		}

	}

	@Nested
	static class StaticClass {

		@Test
		void someTestMethod() {
			// the documentation says "Notice that only non-static inner classes can serve as contexts.";
			// this runs anyway but it is no nested test class
			print("Greetings from an inner test class.");
		}

	}

	class UnannotatedInnerClass {

		@Test
		void someTestMethod() {
			throw new AssertionError("This method does not run.");
		}

	}

	static class UnannotatedStaticClass {

		@Test
		void someTestMethod() {
			print("Greetings from an inner test class that is not even annotated.");
		}

	}

}
