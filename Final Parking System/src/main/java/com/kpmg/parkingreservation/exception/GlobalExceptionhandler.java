package com.kpmg.parkingreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class serves as a global exception handler for the Spring Boot
 * application.
 */
@RestControllerAdvice

public class GlobalExceptionhandler {
	/**
	 * This method handles the exception thrown when a ticket is not found.
	 *
	 * @param ex The exception that was thrown.
	 * @return A ResponseEntity with a message indicating that the ticket was not
	 *         found, and a status of NOT_FOUND.
	 */
	@ExceptionHandler(value = { TicketNotFoundException.class })
	public ResponseEntity<Object> handleTicketNotFoundException(TicketNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * This method handles the exception thrown when an invalid request is made for
	 * a ticket.
	 *
	 * @param ex The exception that was thrown.
	 * @return A ResponseEntity with a message indicating that the request was
	 *         invalid, and a status of BAD_REQUEST.
	 */
	@ExceptionHandler(value = { InvalidTicketRequestException.class })
	public ResponseEntity<Object> handleInvalidTicketRequestException(InvalidTicketRequestException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles all other exceptions that are not caught by the previous
	 * two methods.
	 *
	 * @param ex The exception that was thrown.
	 * @return A ResponseEntity with a message indicating that an internal server
	 *         error occurred, and a status of INTERNAL_SERVER_ERROR.
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
