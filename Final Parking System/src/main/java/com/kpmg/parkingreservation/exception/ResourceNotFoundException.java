package com.kpmg.parkingreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is used to handle the scenario where a requested resource is not
 * found. It extends the RuntimeException class.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ResourceNotFoundException with the specified detail message,
	 * a string indicating the type of resource that was not found, and an employee
	 * ID.
	 * 
	 * @param message the detail message
	 * @param string  a string indicating the type of resource that was not found
	 * @param empId   the employee ID
	 */
	public ResourceNotFoundException(String message, String string, int empId) {
		super(message);
	}

	/**
	 * Constructs a new ResourceNotFoundException with the specified detail message
	 * and cause.
	 * 
	 * @param message the detail message
	 * @param cause   the cause of the exception
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new ResourceNotFoundException with the specified detail message,
	 * a string indicating the type of resource that was not found, and an ID.
	 * 
	 * @param message the detail message
	 * @param string  a string indicating the type of resource that was not found
	 * @param id      the ID of the resource that was not found
	 */
	public ResourceNotFoundException(String message, String string, Long id) {
		// TODO Auto-generated constructor stub
	}
}
