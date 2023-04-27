package com.kpmg.parkingreservation.exception;

/**
 * The TicketNotFoundException class defines a custom exception that can be
 * thrown when a ticket is not found in a system. This exception extends the
 * RuntimeException class, making it an unchecked exception that does not need
 * to be explicitly caught or declared in the method signature.
 */
public class TicketNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new TicketNotFoundException with the specified error message.
	 * 
	 * @param message The error message to be displayed when the exception is
	 *                thrown.
	 */
	public TicketNotFoundException(String message) {
		super(message);
	}
}
