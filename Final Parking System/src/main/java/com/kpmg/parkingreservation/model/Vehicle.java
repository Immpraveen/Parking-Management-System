package com.kpmg.parkingreservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kpmg.parkingreservation.enums.VehicleType;

/**
 * This class represents a vehicle owned by a user.
 */
@Entity
public class Vehicle {

	/**
	 * The unique identifier of the vehicle.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The user who owns the vehicle.
	 */
	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false)
	private User user;

	/**
	 * The unique number of the vehicle.
	 */
	@Column(unique = true)
	private String vehicleNumber;

	/**
	 * The type of the vehicle.
	 */
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	/**
	 * Get the ID of the vehicle.
	 * 
	 * @return The ID of the vehicle.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the ID of the vehicle.
	 * 
	 * @param id The ID of the vehicle.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the user who owns the vehicle.
	 * 
	 * @return The user who owns the vehicle.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Set the user who owns the vehicle.
	 * 
	 * @param user The user who owns the vehicle.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the vehicle number.
	 * 
	 * @return The vehicle number.
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Set the vehicle number.
	 * 
	 * @param vehicleNumber The vehicle number.
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Get the type of the vehicle.
	 * 
	 * @return The type of the vehicle.
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Set the type of the vehicle.
	 * 
	 * @param vehicleType The type of the vehicle.
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Create a new Vehicle object with the given user, vehicle number, and vehicle
	 * type.
	 * 
	 * @param user          The user who owns the vehicle.
	 * @param vehicleNumber The unique number of the vehicle.
	 * @param vehicleType   The type of the vehicle.
	 */
	public Vehicle(User user, String vehicleNumber, VehicleType vehicleType) {
		super();
		this.user = user;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}

	/**
	 * Create a new Vehicle object.
	 */
	public Vehicle() {
		super();
	}
}
