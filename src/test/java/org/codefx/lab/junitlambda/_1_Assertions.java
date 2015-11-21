package org.codefx.lab.junitlambda;

import org.junit.gen5.api.Test;

import java.io.IOException;
import java.util.function.BooleanSupplier;

import static org.codefx.lab.junitlambda.Console.print;
import static org.junit.gen5.api.Assertions.assertAll;
import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertFalse;
import static org.junit.gen5.api.Assertions.assertNotEquals;
import static org.junit.gen5.api.Assertions.assertNotNull;
import static org.junit.gen5.api.Assertions.assertNotSame;
import static org.junit.gen5.api.Assertions.assertNull;
import static org.junit.gen5.api.Assertions.assertSame;
import static org.junit.gen5.api.Assertions.assertTrue;
import static org.junit.gen5.api.Assertions.expectThrows;

public class _1_Assertions {

	private static final boolean CATCH_GROUP_ASSERTION_FAILURE_MESSAGE = true;

	@Test
	public void boringAssertions() {
		String mango = "Mango";

		// as usual: expected, actual
		assertEquals("Mango", mango);
		assertNotEquals("Banana", mango);
		assertSame(mango, mango);
		assertNotSame(new String(mango), mango);

		assertNull(null);
		assertNotNull(mango);
		assertFalse(false);
		assertTrue(true);
	}

	@Test
	public void interestingAssertions() {
		String mango = "Mango";

		// message comes last
		assertEquals("Mango", mango, "Y U no equal?!");

		// message can be created lazily
		assertEquals("Mango", mango, () -> "Expensive string, creation deferred until needed.");

		// for 'assert[True|False]' it is possible to directly test a supplier that exists somewhere in the code
		BooleanSupplier existingBooleanSupplier = () -> true;
		assertTrue(existingBooleanSupplier);
	}

	@Test
	public void exceptionAssertions() {
		IOException exception = expectThrows(
				IOException.class,
				() -> {
					throw new IOException("Something bad happened");
				});
		assertTrue(exception.getMessage().contains("Something bad"));
	}

	@Test
	public void groupedAssertions() {
		try {
			assertAll("Multiplication",
					() -> assertEquals(15, 3 * 5, "3 x 5 = 15"),
					// this fails on purpose to see what the message looks like
					() -> assertEquals(15, 5 + 3, "5 x 3 = 15")
			);
		} catch (AssertionError e) {
			if (CATCH_GROUP_ASSERTION_FAILURE_MESSAGE)
				print("Assertion failed - message starts in next line:\n" + e.getMessage());
			else
				throw e;
		}
	}

}
