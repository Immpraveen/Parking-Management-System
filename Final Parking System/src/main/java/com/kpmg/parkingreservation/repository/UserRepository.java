package com.kpmg.parkingreservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kpmg.parkingreservation.model.User;

/**
 * This interface represents a repository for managing User objects.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/**
	 * Finds a User by their username and password.
	 * 
	 * @param username The username of the User to find.
	 * @param password The password of the User to find.
	 * @return The User object if found, null otherwise.
	 */
	public User findByUsernameAndPassword(String username, String password);

	/**
	 * Finds a User by their username.
	 * 
	 * @param username The username of the User to find.
	 * @return The User object if found, null otherwise.
	 */
	public User findByUsername(String username);

	/**
	 * Finds a User by their employee ID.
	 * 
	 * @param empId The employee ID of the User to find.
	 * @return The User object if found, null otherwise.
	 */
	public User findByEmpId(int empId);
}
