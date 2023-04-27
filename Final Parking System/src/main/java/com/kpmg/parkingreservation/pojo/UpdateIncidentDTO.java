package com.kpmg.parkingreservation.pojo;

/**
 * The UpdateIncidentDTO class represents a data transfer object that holds
 * information about an incident to be updated. This class has three private
 * instance variables: id, comments, and status.
 */
public class UpdateIncidentDTO {

	/**
	 * The unique identifier of the incident to be updated.
	 */
	private long id;
	/**
	 * The comments to be added to the incident.
	 */
	private String comments;

	/**
	 * The status of the incident to be updated.
	 */
	private boolean status;

	/**
	 * Getter method for the comments variable.
	 * 
	 * @return the comments of the incident.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Setter method for the comments variable.
	 * 
	 * @param comments the comments to be added to the incident.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Getter method for the status variable.
	 * 
	 * @return the status of the incident.
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * Setter method for the status variable.
	 * 
	 * @param status the status of the incident to be updated.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Getter method for the id variable.
	 * 
	 * @return the unique identifier of the incident to be updated.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter method for the id variable.
	 * 
	 * @param id the unique identifier of the incident to be updated.
	 */
	public void setId(long id) {
		this.id = id;
	}

}
