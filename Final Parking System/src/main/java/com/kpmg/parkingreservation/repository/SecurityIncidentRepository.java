package com.kpmg.parkingreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kpmg.parkingreservation.model.SecurityIncident;

/**
 * Spring Data JPA repository for {@link SecurityIncident} entities.
 */
@Repository
public interface SecurityIncidentRepository extends JpaRepository<SecurityIncident, Long> {

	/**
	 * Find a security incident by its primary key.
	 * 
	 * @param id the primary key of the security incident to find
	 * @return the security incident with the given primary key, or null if not
	 *         found
	 */
	SecurityIncident findById(long id);

	/**
	 * Find all security incidents with a status of false.
	 * 
	 * @return a list of all security incidents with a status of false
	 */
	List<SecurityIncident> findAllByStatusFalse();

	List<SecurityIncident> findByUser_EmpId(int empId);
}
