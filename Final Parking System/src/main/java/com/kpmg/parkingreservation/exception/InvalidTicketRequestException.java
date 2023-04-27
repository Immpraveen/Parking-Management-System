package com.kpmg.parkingreservation.exception;

/**
 * This class defines a custom exception called InvalidTicketRequestException.
 * This exception can be thrown when a ticket request is invalid for some
 * reason.
 */
public class InvalidTicketRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidTicketRequestException with the specified detail
	 * message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the
	 *                getMessage() method)
	 */
	public InvalidTicketRequestException(String message) {
		super(message);
	}
}
