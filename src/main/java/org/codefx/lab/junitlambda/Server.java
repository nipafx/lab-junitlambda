package org.codefx.lab.junitlambda;

import static java.util.Objects.requireNonNull;
import static org.codefx.lab.junitlambda.Console.print;

/**
 * Highly complicated server interface with complex initialisation.
 */
public class Server {

	private final String url;

	public Server(String url) {
		this.url = requireNonNull(url, "The argument 'url' must not be null.");
	}

	public int sendRequest(String request) {
		print("Sending '%s' to '%s'...", request, url);
		return 200;
	}

}
