package com.kpmg.parkingreservation.service;

import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.pojo.UserRegistration;

/**
 * This interface defines methods for registering and retrieving users.
 */
public interface UserService {

	/**
	 * Adds a user registration to the system.
	 *
	 * @param registration the user registration to add
	 */
	public void addRegistration(UserRegistration registration);

	/**
	 * Retrieves a user by their username and password.
	 *
	 * @param username the username of the user to retrieve
	 * @param password the password of the user to retrieve
	 * @return the user with the matching username and password, or null if not
	 *         found
	 */
	public User getRegistrationByUsernameAndPassword(String username, String password);

	/**
	 * Retrieves a user by their username.
	 *
	 * @param username the username of the user to retrieve
	 * @return the user with the matching username, or null if not found
	 */
	public Object getRegistrationByUsername(String username);

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param empId the ID of the user to retrieve
	 * @return the user with the matching ID, or null if not found
	 */
	public User getUserById(int empId);

	/**
	 * Retrieves a user by their username.
	 *
	 * @param userName the username of the user to retrieve
	 * @return the user with the matching username, or null if not found
	 */
	public User findByuserName(String userName);

	/**
	 * Saves a user to the system.
	 *
	 * @param user the user to save
	 * @return the saved user
	 */
	public User saveUser(User user);
}
