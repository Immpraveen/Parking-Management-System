package com.kpmg.parkingreservation.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import com.kpmg.parkingreservation.model.SecurityIncident;
import org.springframework.stereotype.Service;

public interface SecurityIncidentService {
    /**
     * Creates a new security incident.
     *
     * @param securityIncident The security incident to create.
     * @return The created SecurityIncident.
     */
    SecurityIncident createSecurityIncident(SecurityIncident securityIncident);
    /**
     * Retrieves all security incidents.
     *
     * @return A list of all SecurityIncident objects.
     */
    List<SecurityIncident> getAllIncidents();
    /**
     * Retrieves security incidents by employee ID.
     *
     * @param empId The employee ID to filter incidents.
     * @return A list of SecurityIncident objects associated with the given employee ID.
     */
    List<SecurityIncident> getIncidentsByEmpId(int empId);
    /**
     * Retrieves a security incident by its ID.
     *
     * @param id The ID of the security incident to retrieve.
     * @return The SecurityIncident with the specified ID, or null if not found.
     */
    SecurityIncident getIncidentById(long id);
    /**
     *  Generates an Excel file containing security incidents.
     *
     * @param incidents The list of security incidents to include in the Excel file.
     * @return A ByteArrayInputStream containing the generated Excel file.
     */
    ByteArrayInputStream generateExcel(List<SecurityIncident> incidents) throws IOException;
}
