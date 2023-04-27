package com.kpmg.parkingreservation.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpmg.parkingreservation.enums.IncidentType;
import com.kpmg.parkingreservation.enums.UserType;

/**
 * Represents a security incident that has occurred and has been reported.
 */
@Entity
public class SecurityIncident {

	/**
	 * The unique identifier for the incident.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * To prioritize the incidents according to their level.
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private IncidentType incidentType;


	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 * The user who reported the incident.
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id")
	private User user;

	/**
	 * A description of the incident.
	 */
	@Column(length = 1000)
	private String description;

	/**
	 * The ID of the spot where the incident occurred.
	 */
	@Column
	private int spotId;

	/**
	 * The vehicle number associated with the incident.
	 */
	@Column
	private String vehicleNumber;

	/**
	 * The date the incident was reported.
	 */
	@Column
	private LocalDate date;

	/**
	 * The time the incident was reported.
	 */
	@Column
	private LocalTime time;

	/**
	 * The time the individual entered the location.
	 */
	@Column
	private LocalTime entryTime;

	/**
	 * The time the individual left the location.
	 */
	@Column
	private LocalTime exitTime;

	/**
	 * The date the incident occurred.
	 */
	@Column
	private LocalDate incidentDate;

	/**
	 * The status of the incident (e.g. open, closed).
	 */
	@Column
	private boolean status;

	/**
	 * Comments related to the incident.
	 */
	@Column
	private String comments;

	// getters and setters

	/**
	 * Returns the status of the incident.
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * Sets the status of the incident.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Returns any comments related to the incident.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets any comments related to the incident.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Returns the date the incident occurred.
	 */
	public LocalDate getIncidentDate() {
		return incidentDate;
	}

	/**
	 * Sets the date the incident occurred.
	 */
	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 * Returns the unique identifier for the incident.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for the incident.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the user who reported the incident.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user who reported the incident.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the description of the incident.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the incident.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the ID of the spot where the incident occurred.
	 */
	public int getSpotId() {
		return spotId;
	}

	/**
	 * Sets the ID of the spot where the incident occurred.
	 */

	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}

	/**
	 * Returns the license plate number of the vehicle involved in the incident.
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the license plate number of the vehicle involved in the incident.
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Returns the date of the incident.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date of the incident.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Returns the time of the incident.
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Sets the time of the incident.
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
	 * Returns the entry time of the vehicle.
	 */
	public LocalTime getEntryTime() {
		return entryTime;
	}

	/**
	 * Sets the entry time of the vehicle.
	 */
	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * Returns the exit time of the vehicle.
	 */
	public LocalTime getExitTime() {
		return exitTime;
	}

	/**
	 * Sets the exit time of the vehicle.
	 */
	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * Default constructor for the SecurityIncident class.
	 */
	public SecurityIncident() {
		super();
		// TODO Auto-generated constructor stub
	}
}