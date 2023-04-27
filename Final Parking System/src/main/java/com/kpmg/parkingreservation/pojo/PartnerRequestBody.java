package com.kpmg.parkingreservation.pojo;

/**
 * This class represents a request body object that is sent as part of an HTTP
 * request to a server. It contains information about an employee and their
 * associated vehicle.
 */
public class PartnerRequestBody {
	/**
	 * The ID of the employee associated with this request.
	 */
	private int empId;
	/**
	 * The type of vehicle associated with the employee.
	 */
	private String vehicleType;
	/**
	 * The number of the vehicle associated with the employee.
	 */
	private String vehicleNumber;
	/**
	 * The name of the user who is making the request.
	 */
	private String userName;

	/**
	 * Returns the name of the user who is making the request.
	 *
	 * @return the name of the user who is making the request
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the name of the user who is making the request.
	 *
	 * @param userName the name of the user who is making the request
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Returns the type of vehicle associated with the employee.
	 *
	 * @return the type of vehicle associated with the employee
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of vehicle associated with the employee.
	 *
	 * @param vehicleType the type of vehicle associated with the employee
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Returns the number of the vehicle associated with the employee.
	 *
	 * @return the number of the vehicle associated with the employee
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Sets the number of the vehicle associated with the employee.
	 *
	 * @param vehicleNumber the number of the vehicle associated with the employee
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * Returns the ID of the employee associated with this request.
	 *
	 * @return the ID of the employee associated with this request
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Sets the ID of the employee associated with this request.
	 *
	 * @param empId the ID of the employee associated with this request
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

}
