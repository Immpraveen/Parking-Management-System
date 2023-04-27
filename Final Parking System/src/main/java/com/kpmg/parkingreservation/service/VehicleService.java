package com.kpmg.parkingreservation.service;

import com.kpmg.parkingreservation.model.Vehicle;

import java.util.List;

/**
 * This interface defines a set of methods for managing vehicles for an employee
 * in a system.
 */
public interface VehicleService {

	/**
	 * Retrieves a list of all vehicles associated with the given employee ID.
	 *
	 * @param empId the ID of the employee to retrieve vehicles for
	 * @return a list of all vehicles associated with the given employee ID
	 */
	List<Vehicle> getAllVehiclesByEmpId(int empId);

	/**
	 * Associates the given vehicle with the specified employee ID and adds it to
	 * the system.
	 *
	 * @param empId   the ID of the employee to associate the vehicle with
	 * @param vehicle the vehicle to add to the system
	 * @return the newly added vehicle
	 */
	Vehicle addVehicleToUser(int empId, Vehicle vehicle);

	/**
	 * Updates the vehicle with the given ID with the new values specified in the
	 * given vehicle object.
	 *
	 * @param id      the ID of the vehicle to update
	 * @param vehicle the updated vehicle object containing the new values to apply
	 */
	void updateVehicle(Long id, Vehicle vehicle);

	/**
	 * Deletes the vehicle with the given ID from the system.
	 *
	 * @param id the ID of the vehicle to delete
	 */
	void deleteVehicle(Long id);

	/**
	 * Retrieves the vehicle with the given ID from the system.
	 *
	 * @param id the ID of the vehicle to retrieve
	 * @return the vehicle object associated with the given ID, or null if no such
	 *         vehicle exists
	 */
	Vehicle getVehicleById(Long id);
}
