package de.timroes.axmlrpc;

/**
 * Will be thrown when a call to the server times out. The timeout can be 
 * set via {@link XMLRPCClient#setTimeout(int)}.
 * 
 * @author Tim Roes <mail@timroes.de>
 */
public class XMLRPCTimeoutException extends XMLRPCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	XMLRPCTimeoutException(String ex) {
		super(ex);
	}

}