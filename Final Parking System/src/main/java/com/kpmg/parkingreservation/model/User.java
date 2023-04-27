package com.kpmg.parkingreservation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.kpmg.parkingreservation.enums.UserType;

/**
 * Represents a user in the system.
 */
@Entity
public class User {

	/**
	 * The user's employee ID.
	 */
	@Id
	@GeneratedValue(generator = "user_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1)
	private int empId;

	/**
	 * The user's username. This value must be unique.
	 */
	@Column(nullable = false, unique = true)
	private String username;

	/**
	 * The user's password.
	 */
	@Column(nullable = false)
	private String password;

	/**
	 * The type of user (e.g. admin, employee, etc.).
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	/**
	 * The list of vehicles associated with this user.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Vehicle> vehicles = new ArrayList<>();

	/**
	 * The list of security incidents associated with this user.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<SecurityIncident> securityIncident = new ArrayList<>();

	/**
	 * Default constructor for the User class.
	 */
	public User() {
		super();
	}

	public List<SecurityIncident> getSecurityIncident() {
		return securityIncident;
	}

	public void setSecurityIncident(List<SecurityIncident> securityIncident) {
		this.securityIncident = securityIncident;
	}

	/**
	 * Constructor for the User class.
	 *
	 * @param username The user's username.
	 * @param password The user's password.
	 * @param userType The type of user.
	 */
	public User(String username, String password, UserType userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	/**
	 * Get the user's employee ID.
	 *
	 * @return The employee ID.
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Set the user's employee ID.
	 *
	 * @param empId The employee ID.
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * Get the user's username.
	 *
	 * @return The username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the user's username.
	 *
	 * @param username The username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the user's password.
	 *
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the user's password.
	 *
	 * @param password The password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the user's type.
	 *
	 * @return The user type.
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the user's type.
	 *
	 * @param userType The user type.
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * Get the list of vehicles associated with this user.
	 *
	 * @return The list of vehicles.
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * Set the list of vehicles associated with this user.
	 *
	 * @param vehicles The list of vehicles.
	 */

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * 
	 * Constructs a new User with the given employee ID.
	 * 
	 * @param empId the employee ID of the User to be created
	 */
	public User(int empId) {
		this.empId = empId;
	}

}