package com.kpmg.parkingreservation.pojo;

import com.kpmg.parkingreservation.enums.UserType;
import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.model.Vehicle;
import java.util.*;

/**
 * Represents a DTO (Data Transfer Object) for a user and their associated
 * vehicles.
 */
public class UserVehicleDTO {

	/**
	 * The employee ID of the user.
	 */
	private int empId;

	/**
	 * The username of the user.
	 */
	private String username;

	/**
	 * The type of user (e.g. regular, admin).
	 */
	private UserType userType;

	/**
	 * A list of DTOs representing the user's vehicles.
	 */
	private List<VehicleDTO> vehicles = new ArrayList<>();

	/**
	 * Default constructor for the DTO.
	 */
	public UserVehicleDTO() {
		super();
	}

	/**
	 * Constructor that takes a User object and initializes the DTO with its
	 * properties.
	 * 
	 * @param user The User object to convert to a DTO.
	 */
	public UserVehicleDTO(User user) {
		this.empId = user.getEmpId();
		this.username = user.getUsername();
		this.userType = user.getUserType();

		for (Vehicle vehicle : user.getVehicles()) {
			this.vehicles.add(new VehicleDTO(vehicle));
		}
	}

	/**
	 * Returns the employee ID of the user.
	 * 
	 * @return The employee ID of the user.
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Sets the employee ID of the user.
	 * 
	 * @param empId The new employee ID to set.
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
	 * @param username The new username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @param userType The new user type to set.
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * Returns a list of DTOs representing the user's vehicles.
	 * 
	 * @return A list of DTOs representing the user's vehicles.
	 */
	public List<VehicleDTO> getVehicles() {
		return vehicles;
	}

	/**
	 * Sets the list of DTOs representing the user's vehicles.
	 * 
	 * @param vehicles The new list of DTOs to set.
	 */
	public void setVehicles(List<VehicleDTO> vehicles) {
		this.vehicles = vehicles;
	}
}
