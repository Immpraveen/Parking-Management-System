package com.kpmg.parkingreservation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kpmg.parkingreservation.model.SecurityIncident;
import com.kpmg.parkingreservation.repository.SecurityIncidentRepository;

/**
 * The SecurityIncidentService class provides methods to manage security
 * incidents and associated tickets.
 */
@Service
public class SecurityIncidentService {

	/**
	 * Repository for storing and retrieving security incidents.
	 */
	@Autowired
	private SecurityIncidentRepository securityIncidentRepository;

	/**
	 * Repository for storing and retrieving tickets associated with security
	 * incidents.
	 */

	/**
	 * Creates a new security incident in the repository.
	 * 
	 * @param securityIncident the security incident to create
	 * @return the created security incident, including any generated IDs or
	 *         timestamps
	 */
	public SecurityIncident createSecurityIncident(SecurityIncident securityIncident) {
		return securityIncidentRepository.save(securityIncident);
	}

	/**
	 * Retrieves all security incidents from the repository.
	 * 
	 * @return a list of all security incidents in the repository
	 */
	public List<SecurityIncident> getAllIncidents() {
		// TODO: Implement this method
		return null;
	}
}
