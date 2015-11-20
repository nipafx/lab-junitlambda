package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.Name;
import org.junit.gen5.api.Tag;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestInstance;
import org.junit.gen5.api.TestInstance.Lifecycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.gen5.api.Assertions.assertEquals;

/**
 * It is easy to create annotations that JUnit picks up.
 *
 * These can gather a bunch of other JUnit annotations to make it more convenient to apply them all at once.
 * E.g., they could lead to execution as a test, define a name and a couple of tags.
 */
@TestInstance(Lifecycle.PER_CLASS)
class _6_CustomAnnotations {

	// we use the PER_CLASS lifecycle to assert that all tests are executed

	private static final int EXPECTED_TEST_METHOD_COUNT = 2;
	private int executedTestMethodCount = 0;

	@AfterAll
	void assertAllMethodsExecuted() {
		assertEquals(EXPECTED_TEST_METHOD_COUNT, executedTestMethodCount);
	}

	// TESTS

	@IntegrationTest
	void runsWithCustomAnnotations() {
		// this is run even though 'IntegrationTest' is not defined by JUnit
		executedTestMethodCount++;
	}

	@MetaIntegrationTest
	void runsWithCustomMetaAnnotations() {
		// this is run even though 'MetaIntegrationTest' is not even annotated with '@Test'
		executedTestMethodCount++;
	}

	// OUR ANNOTATIONS

	/**
	 * We define a custom annotation that:
	 * - stands in for '@Test' so that the method gets executed
	 * - gives it a default name
	 * - has the tag "integration" so we can filter by that, e.g. from the command line
	 *
	 * This is very easy because JUnit checks whether the annotations on a class/method are in turn annotated with
	 * known annotations like '@Test'. This is the hamstrung but common way to simulate inheritance of annotations.
	 */
	@Target({ElementType.TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Test
	@Name("Evil integration test \uD83D\uDC79!")
	@Tag("integration")
	public @interface IntegrationTest {

	}

	/**
	 * Adds another tag to '@IntegrationTest'.
	 */
	@Target({ElementType.TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@IntegrationTest
	@Tag("meta")
	public @interface MetaIntegrationTest {

	}

}
