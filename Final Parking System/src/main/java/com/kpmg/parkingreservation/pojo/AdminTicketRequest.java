package com.kpmg.parkingreservation.pojo;

/**
 * This class represents a ticket request made by an employee for a parking
 * spot.
 */
public class AdminTicketRequest {
	/**
	 * The ID of the employee making the ticket request.
	 */
	private int empId;
	/**
	 * The type of vehicle for which the ticket request is made.
	 */
	private String vehicleType;
	/**
	 * The number of the vehicle for which the ticket request is made.
	 */
	private String vehicleNumber;
	/**
	 * The type of parking spot requested.
	 */
	private String spotType;

	/**
	 * Returns the ID of the employee making the ticket request.
	 *
	 * @return the ID of the employee
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Sets the ID of the employee making the ticket request.
	 *
	 * @param empId the ID of the employee
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * Returns the type of vehicle for which the ticket request is made.
	 *
	 * @return the type of vehicle
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of vehicle for which the ticket request is made.
	 *
	 * @param vehicleType the type of vehicle
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Returns the number of the vehicle for which the ticket request is made.
	 *
	 * @return the vehicle number
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the number of the vehicle for which the ticket request is made.
	 *
	 * @param vehicleNumber the vehicle number
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Returns the type of parking spot requested.
	 *
	 * @return the spot type
	 */
	public String getSpotType() {
		return spotType;
	}

	/**
	 * Sets the type of parking spot requested.
	 *
	 * @param spotType the spot type
	 */
	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		return "TicketRequest [empId=" + empId + ", vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber
				+ ", spotType=" + spotType + "]";
	}

}
