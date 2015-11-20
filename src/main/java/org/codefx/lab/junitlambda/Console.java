package org.codefx.lab.junitlambda;

import static java.lang.String.format;

public class Console {

	public static void print(String message, Object... args) {
		System.out.println(format("\t >> " + message, args));
	}

}
