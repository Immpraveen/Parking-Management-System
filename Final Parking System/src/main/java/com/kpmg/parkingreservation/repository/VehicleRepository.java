package com.kpmg.parkingreservation.repository;

import com.kpmg.parkingreservation.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface defines a Spring Data JPA repository for the Vehicle entity.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	/**
	 * This method finds a list of vehicles associated with the user that has the
	 * specified employee ID.
	 * 
	 * @param empId the employee ID of the user
	 * @return a list of vehicles associated with the user
	 */
	List<Vehicle> findByUser_EmpId(int empId);
}