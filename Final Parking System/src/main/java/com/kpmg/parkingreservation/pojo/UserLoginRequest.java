package com.kpmg.parkingreservation.pojo;

/**
 * A class representing a user login request, which includes a username and
 * password.
 */
public class UserLoginRequest {
	/**
	 * The username provided by the user for login.
	 */
	private String userName;
	/**
	 * The password provided by the user for login.
	 */
	private String password;

	/**
	 * Gets the username of the user.
	 * 
	 * @return The username of the user.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the username of the user.
	 * 
	 * @param userName The username of the user.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password The password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
