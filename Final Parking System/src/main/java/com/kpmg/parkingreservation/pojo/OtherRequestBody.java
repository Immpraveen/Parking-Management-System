package com.kpmg.parkingreservation.pojo;

/**
 * OtherRequestBody is a Java class that represents a request body with employee
 * and vehicle details.
 */
public class OtherRequestBody {
	/**
	 * The ID of the employee.
	 */
	private int empId;
	/**
	 * The name of the user.
	 */
	private String userName;

	/**
	 * Get the name of the user.
	 * 
	 * @return The name of the user.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the name of the user.
	 * 
	 * @param userName The name of the user.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the ID of the employee.
	 * 
	 * @return The ID of the employee.
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * Set the ID of the employee.
	 * 
	 * @param empId The ID of the employee.
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * The type of the vehicle.
	 */
	private String vehicleType;
	/**
	 * The number of the vehicle.
	 */
	private String vehicleNumber;

	/**
	 * Get the type of the vehicle.
	 * 
	 * @return The type of the vehicle.
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * Set the type of the vehicle.
	 * 
	 * @param vehicleType The type of the vehicle.
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Get the number of the vehicle.
	 * 
	 * @return The number of the vehicle.
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * Set the number of the vehicle.
	 * 
	 * @param vehicleNumber The number of the vehicle.
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

}
