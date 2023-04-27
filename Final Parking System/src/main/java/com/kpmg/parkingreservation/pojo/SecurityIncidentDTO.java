package com.kpmg.parkingreservation.pojo;

import java.time.LocalDate;

import com.kpmg.parkingreservation.enums.IncidentType;

/**
 * This class represents a data transfer object (DTO) for security incidents. It
 * contains a description of the incident and the date on which it occurred.
 */
public class SecurityIncidentDTO {

	private String description;
	private LocalDate incidentDate;
	private IncidentType incidentType;

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 * This class represents a data transfer object (DTO) for security incidents. It
	 * contains a description of the incident and the date on which it occurred.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter method for the incident date.
	 *
	 * @return The date on which the security incident occurred.
	 */
	public LocalDate getIncidentDate() {
		return incidentDate;
	}

	/**
	 * Setter method for the incident date.
	 *
	 * @param incidentDate The date on which the security incident occurred.
	 */
	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 * Setter method for the incident description.
	 *
	 * @param description The description of the security incident.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Default constructor for the SecurityIncidentDTO class.
	 */
	public SecurityIncidentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns a string representation of the SecurityIncidentDTO object.
	 *
	 * @return A string representation of the SecurityIncidentDTO object.
	 */
	@Override
	public String toString() {
		return "SecurityIncidentDTO [description=" + description + ", incidentDate=" + incidentDate + "]";
	}

}
