package com.kpmg.parkingreservation.pojo;

import com.kpmg.parkingreservation.enums.UserType;

/**
 * Represents a login response containing information about the authenticated
 * user.
 */
public class LoginResponse {

	/** The employee ID of the authenticated user. */
	private long empId;
	/** The type of user (e.g. admin, manager, employee). */
	private UserType type;
	/** Indicates whether the user was authenticated successfully. */
	private boolean isAuth;

	/**
	 * Constructs a new LoginResponse object with the given employee ID, user type,
	 * and authentication status.
	 * 
	 * @param empId    the employee ID of the authenticated user
	 * @param userType the type of user (e.g. admin, manager, employee)
	 * @param isAuth   whether the user was authenticated successfully
	 */
	public LoginResponse(int empId, UserType userType, boolean isAuth) {
		this.empId = empId;
		this.type = userType;
		this.isAuth = isAuth;
	}

	/**
	 * Returns the employee ID of the authenticated user.
	 * 
	 * @return the employee ID of the authenticated user
	 */
	public long getEmpId() {
		return empId;
	}

	/**
	 * Returns the type of user (e.g. admin, manager, employee).
	 * 
	 * @return the type of user
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * Returns whether the user was authenticated successfully.
	 * 
	 * @return true if the user was authenticated successfully, false otherwise
	 */
	public boolean isAuth() {
		return isAuth;
	}

}
