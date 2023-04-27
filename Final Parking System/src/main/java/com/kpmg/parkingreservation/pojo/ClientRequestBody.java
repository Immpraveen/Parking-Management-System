package com.kpmg.parkingreservation.pojo;

/**
 * This class represents a request body sent by a client, containing information
 * about an employee and their vehicle.
 */
public class ClientRequestBody {
	private int empId;
	private String vehicleType;
	private String vehicleNumber;
	private String userName;

	/**
	 * Gets the name of the user who is making the request.
	 *
	 * @return The user's name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the name of the user who is making the request.
	 *
	 * @param userName The user's name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the type of the employee's vehicle.
	 *
	 * @return The vehicle type.
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of the employee's vehicle.
	 *
	 * @param vehicleType The vehicle type.
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Gets the registration number of the employee's vehicle.
	 *
	 * @return The vehicle registration number.
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the registration number of the employee's vehicle.
	 *
	 * @param vehicleNumber The vehicle registration number.
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Gets the ID of the employee.
	 *
	 * @return The employee ID.
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Sets the ID of the employee.
	 *
	 * @param empId The employee ID.
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

}
