package com.kpmg.parkingreservation.pojo;

import com.kpmg.parkingreservation.enums.UserType;
import com.kpmg.parkingreservation.enums.VehicleType;

/**
 * The UserRegistration class represents a user registration entity, which
 * contains information about the user's identity, vehicle, and login
 * credentials.
 */
public class UserRegistration {

	/** The ID of the user. */
	private int empId;

	/** The username of the user. */
	private String username;

	/** The password of the user. */
	private String password;

	/** The type of user (e.g. admin, employee, etc.) */
	private UserType userType;

	/** The type of vehicle associated with the user. */
	private VehicleType vehicleType;

	/** The registration number of the user's vehicle. */
	private String vehicleNumber;

	/**
	 * Returns the ID of the user.
	 * 
	 * @return The ID of the user.
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Sets the ID of the user.
	 * 
	 * @param empId The ID of the user.
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * Returns the username of the user.
	 * 
	 * @return The username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 * 
	 * @param username The username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the password of the user.
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

	/**
	 * Returns the type of user.
	 * 
	 * @return The type of user.
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Sets the type of user.
	 * 
	 * @param userType The type of user.
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * Returns the type of vehicle associated with the user.
	 * 
	 * @return The type of vehicle associated with the user.
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of vehicle associated with the user.
	 * 
	 * @param vehicleType The type of vehicle associated with the user.
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Returns the registration number of the user's vehicle.
	 * 
	 * @return The registration number of the user's vehicle.
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the registration number of the user's vehicle.
	 * 
	 * @param vehicleNumber The registration number of the user's vehicle.
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Returns a string representation of the object's state.
	 * 
	 * @return A string representation of the object's state.
	 */
	@Override
	public String toString() {
		return "UserRegistration [empId=" + empId + ", username=" + username + ", password=" + password + ", userType="
				+ userType + ", vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber + "]";
	}

	/**
	 * Default constructor for the UserRegistration class.
	 */
	public UserRegistration() {
		super();

	}

}
