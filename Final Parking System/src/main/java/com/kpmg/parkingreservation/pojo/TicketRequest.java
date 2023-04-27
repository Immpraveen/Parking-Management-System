package com.kpmg.parkingreservation.pojo;

/**
 * Represents a request for a parking ticket, including employee ID, vehicle
 * type, vehicle number, and spot type.
 */
public class TicketRequest {
	/**
	 * The ID of the employee making the ticket request.
	 */
	private int empId;
	/**
	 * The type of vehicle for which a parking spot is being requested.
	 */
	private String vehicleType;

	/**
	 * The number of the vehicle for which a parking spot is being requested.
	 */
	private String vehicleNumber;
	/**
	 * The type of parking spot being requested (e.g. "regular", "premium",
	 * "handicapped", etc.).
	 */
	private String spotType;

//    private ZonedDateTime startTime;
	/**
	 * Returns the ID of the employee making the ticket request.
	 * 
	 * @return the employee ID
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Sets the ID of the employee making the ticket request.
	 * 
	 * @param empId the employee ID
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * Returns the type of vehicle for which a parking spot is being requested.
	 * 
	 * @return the vehicle type
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of vehicle for which a parking spot is being requested.
	 * 
	 * @param vehicleType the vehicle type
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Returns the number of the vehicle for which a parking spot is being
	 * requested.
	 * 
	 * @return the vehicle number
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the number of the vehicle for which a parking spot is being requested.
	 * 
	 * @param vehicleNumber the vehicle number
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Returns the type of parking spot being requested.
	 * 
	 * @return the spot type
	 */
	public String getSpotType() {
		return spotType;
	}

	/**
	 * Sets the type of parking spot being requested.
	 * 
	 * @param spotType the spot type
	 */
	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}

	/**
	 * Returns a string representation of the object's state, including employee ID,
	 * vehicle type, vehicle number, and spot type.
	 * 
	 * @return a string representation of the object's state
	 */
	@Override
	public String toString() {
		return "TicketRequest [empId=" + empId + ", vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber
				+ ", spotType=" + spotType + "]";
	}

}
