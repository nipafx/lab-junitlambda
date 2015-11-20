package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.Condition;
import org.junit.gen5.api.Conditional;
import org.junit.gen5.api.Disabled;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;
import org.junit.gen5.api.extension.TestExecutionContext;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertFalse;
import static org.junit.gen5.api.Assertions.assertTrue;

/**
 * With {@link Condition} JUnit provides a way to define annotations that can be used to dynamically decide whether
 * tests are run or skipped.
 */
@TestInstance(Lifecycle.PER_CLASS)
class _7_Conditionals {

	// we use the PER_CLASS lifecycle to assert that all tests are executed

	// note that even though two tests increase the counter, they should never run both
	private static final int EXPECTED_TEST_METHOD_COUNT = 1;
	private int executedTestMethodCount = 0;

	@AfterAll
	void assertAllMethodsExecuted() {
		assertEquals(EXPECTED_TEST_METHOD_COUNT, executedTestMethodCount);
	}

	// TESTS

	@Test
	@Disabled
	void doesNotRunDisabledTests() {
		throw new AssertionError("This method should not be run!");
	}

	@Test
	@FridayAfternoon.DontRun
	void neverRunsOnFridayAfternoon() {
		executedTestMethodCount++;
		assertFalse(FridayAfternoon.isIt());
	}

	@Test
	@FridayAfternoon.Run
	void onlyRunsOnFridayAfternoon() {
		executedTestMethodCount++;
		assertTrue(FridayAfternoon.isIt());
	}

	// OUR CONDITIONS

	/**
	 * Contains a check whether it is Friday afternoon and two annotations that run or skip tests depending on
	 * whether it is.
	 */
	static class FridayAfternoon {

		static boolean isIt() {
			LocalDateTime now = LocalDateTime.now();
			return now.getDayOfWeek() == DayOfWeek.FRIDAY
					&& 13 <= now.getHour() && now.getHour() <= 18;
		}

		/**
		 * Runs always unless it is Friday afternoon.
		 */
		@Target({ElementType.TYPE, ElementType.METHOD})
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		@Conditional({DontRun.FridayCondition.class})
		@interface DontRun {

			class FridayCondition implements Condition {

				@Override
				public Result evaluate(TestExecutionContext testExecutionContext) {
					return isIt()
							? Result.failure("It's Friday afternoon!")
							: Result.success("Just a regular day...");
				}
			}

		}

		/**
		 * Skips always unless it is Friday afternoon.
		 */
		@Target({ElementType.TYPE, ElementType.METHOD})
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		@Conditional({Run.FridayCondition.class})
		@interface Run {

			class FridayCondition implements Condition {

				@Override
				public Result evaluate(TestExecutionContext testExecutionContext) {
					return isIt()
							? Result.success("It's Friday afternoon!")
							: Result.failure("Just a regular day...");
				}
			}

		}
	}

}
