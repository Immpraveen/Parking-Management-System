package com.kpmg.parkingreservation.pojo;

import com.kpmg.parkingreservation.enums.VehicleType;
import com.kpmg.parkingreservation.model.Vehicle;

/**
 * A class representing a vehicle data transfer object (DTO). A DTO is an object
 * that carries data between processes, typically between a client and server in
 * a distributed system.
 */
public class VehicleDTO {

	/**
	 * The ID of the vehicle.
	 */
	private Long id;

	/**
	 * The vehicle number, typically the license plate number.
	 */
	private String vehicleNumber;

	/**
	 * The type of vehicle, represented as a VehicleType object.
	 */
	private VehicleType vehicleType;

	/**
	 * Default constructor.
	 */
	public VehicleDTO() {
		super();
	}

	/**
	 * Constructor that creates a new VehicleDTO object based on an existing Vehicle
	 * object.
	 * 
	 * @param vehicle The Vehicle object to use as the basis for the new DTO.
	 */
	public VehicleDTO(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.vehicleNumber = vehicle.getVehicleNumber();
		this.vehicleType = vehicle.getVehicleType();
	}

	/**
	 * Gets the ID of the vehicle.
	 * 
	 * @return The ID of the vehicle.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the vehicle.
	 * 
	 * @param id The ID of the vehicle.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the vehicle number.
	 * 
	 * @return The vehicle number.
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the vehicle number.
	 * 
	 * @param vehicleNumber The vehicle number.
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Gets the type of vehicle.
	 * 
	 * @return The type of vehicle, represented as a VehicleType object.
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of vehicle.
	 * 
	 * @param vehicleType The type of vehicle, represented as a VehicleType object.
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}
