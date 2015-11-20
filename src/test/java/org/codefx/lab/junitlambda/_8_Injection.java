package org.codefx.lab.junitlambda;

import org.codefx.lab.junitlambda._8_Injection.EMailParameter;
import org.codefx.lab.junitlambda._8_Injection.ServerParameterResolver;
import org.junit.gen5.api.AfterEach;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.TestName;
import org.junit.gen5.api.extension.ExtendWith;
import org.junit.gen5.api.extension.MethodParameterResolver;
import org.junit.gen5.api.extension.ParameterResolutionException;
import org.junit.gen5.api.extension.TestExecutionContext;
import org.junit.gen5.commons.util.AnnotationUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

import static org.codefx.lab.junitlambda.Console.print;
import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertTrue;

/**
 * Test methods can now have parameters.
 *
 * JUnit will try to find a resolver among the  the '@ExtendWith' classes (and fail the test if it doesn't succeed),
 * which is then responsible to create a value for the parameter.
 */
@ExtendWith({EMailParameter.Resolver.class, ServerParameterResolver.class})
class _8_Injection {

	// TESTS

	@Test
	void injectsTestName(@TestName String testName) {
		// '@TestName' comes with JUnit.
		assertEquals("injectsTestName", testName);
	}

	@AfterEach
	void outputTestName(@TestName String name) {
		// injection also works for 'BeforeEach' and 'AfterEach' methods
		print("Current test: %s", name);
	}

	@Test
	void injectsServer(Server server) {
		int statusCode = server.sendRequest("gimme!");
		assertEquals(200, statusCode);
	}

	@Test
	void injectsEMailAddress(@EMailParameter String eMail) {
		assertTrue(eMail.contains("@"));
	}

	// CUSTOM PARAMETER HANDLERS AND ANNOTATIONS

	/**
	 * We can define a parameter handler for _all_ parameters of a certain type.
	 *
	 * This one resolves all parameters of type 'Server'.
	 */
	public static class ServerParameterResolver implements MethodParameterResolver {

		@Override
		public boolean supports(Parameter parameter) {
			return parameter.getType().equals(Server.class);
		}

		@Override
		public Object resolve(Parameter parameter, TestExecutionContext testExecutionContext)
				throws ParameterResolutionException {
			return new Server("http://codefx.org");
		}
	}

	/**
	 * We can use a custom annotation to have resolve parameters of ubiquitous types, in this case 'String'.
	 * Our resolver is then only used if this annotation is present on the parameter.
	 */
	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface EMailParameter {

		// the annotation itself does nothing special

		/**
		 * The resolver is responsible to check for the annotation's presence and create a value.
		 */
		class Resolver implements MethodParameterResolver {

			@Override
			public boolean supports(Parameter parameter) {
				return parameter.getType().equals(String.class)
						&& AnnotationUtils.isAnnotated(parameter, EMailParameter.class);
			}

			@Override
			public Object resolve(Parameter parameter, TestExecutionContext testExecutionContext)
					throws ParameterResolutionException {
				return "nipa@codefx.org";
			}
		}

	}

}
