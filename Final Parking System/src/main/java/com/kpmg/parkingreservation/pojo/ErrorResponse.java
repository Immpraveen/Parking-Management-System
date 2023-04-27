package com.kpmg.parkingreservation.pojo;

/**
 * Represents an error response that can be returned by an API endpoint.
 */
public class ErrorResponse {
	/**
	 * The error message that provides information about the error that occurred.
	 */
	private String message;
	/**
	 * Indicates whether the error is related to authentication.
	 */
	private boolean isAuth;

	/**
	 * Returns whether the error is related to authentication.
	 *
	 * @return true if the error is related to authentication, false otherwise.
	 */
	public boolean isAuth() {
		return isAuth;
	}

	/**
	 * Constructs an ErrorResponse object with the specified error message.
	 *
	 * @param message the error message that provides information about the error
	 *                that occurred.
	 */
	public ErrorResponse(String message) {
		this.message = message;
	}

	/**
	 * Returns the error message that provides information about the error that
	 * occurred.
	 *
	 * @return the error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the error message to the specified value.
	 *
	 * @param message the new error message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
