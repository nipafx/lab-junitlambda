package org.codefx.lab.junitlambda;

import org.junit.gen5.api.AfterAll;
import org.junit.gen5.api.AfterEach;
import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Disabled;
import org.junit.gen5.api.Name;
import org.junit.gen5.api.Test;

import static org.codefx.lab.junitlambda.Console.print;

/**
 * How to set up, execute and tear down tests.
 */
@Name("JUnit Lambda: Basic Feature Test")
public class _0_BasicFeatures {

	// SET UP, TEAR DOWN

	@BeforeAll
	public static void setUpTestClass() {
		print("Executed once, before all tests in this class.");
	}

	@BeforeEach
	public void setUpTestMethod() {
		print("Executed before each test in this class.");
	}

	@AfterEach
	public void tearDownTestMethod() {
		print("Executed after each test in this class.");
	}

	@AfterAll
	public static void tearDownTestClass() {
		print("Executed once, after all tests in this class.");
	}

	// TESTS

	public void test_doesNotRunNotAnnotatedMethods() {
		// the method is named 'test...' to lure JUnit into running it old school style
		throw new AssertionError("This method should not be run!");
	}

	@Test
	public void runsAnAnnotatedMethod() {
		print("Hello World!");
	}

	@Test
	@Name("test name annotation")
	public void showsName() {
		print("This test should have a nice name.");
	}

	@Disabled
	@Test
	public void disabledTestsAreNotRun() {
		// '@Disabled' is the new '@Ignored'
		throw new AssertionError("This method should not be run!");
	}

}
