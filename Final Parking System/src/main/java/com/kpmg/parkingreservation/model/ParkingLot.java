package com.kpmg.parkingreservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

//import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents a parking lot entity and uses JPA annotations to map to a database
 * table.
 */
@Entity
public class ParkingLot {

	/**
	 * The unique identifier for the parking spot.
	 */
	@Id
	@GeneratedValue(generator = "spot_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "spot_seq", sequenceName = "spot_sequence", initialValue = 1, allocationSize = 1)
	private int spotId;

	/**
	 * The type of parking spot, e.g. "compact" or "large".
	 */
	@Column
	private String spotType;

	/**
	 * The type of vehicle that can park in the spot, e.g. "car" or "motorcycle".
	 */
	@Column
	private String vehicleType;

	/**
	 * Whether the parking spot is currently reserved.
	 */
	@Column
	private boolean isBooked;

	/**
	 * Whether the parking spot is currently occupied.
	 */
	@Column
	private boolean isOccupied;

	/**
	 * Overrides the default toString() method to print the ParkingLot object in a
	 * readable format.
	 * 
	 * @return A string representation of the ParkingLot object.
	 */
	@Override
	public String toString() {
		return "ParkingLot [spotId=" + spotId + ", spotType=" + spotType + ", vehicleType=" + vehicleType
				+ ", isBooked=" + isBooked + ", isOccupied=" + isOccupied + "]";
	}

	/**
	 * Gets the unique identifier for the parking spot.
	 * 
	 * @return The spot ID.
	 */
	public int getSpotId() {
		return spotId;
	}

	/**
	 * Sets the unique identifier for the parking spot.
	 * 
	 * @param spotId The new spot ID.
	 */
	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}

	/**
	 * Gets the type of parking spot.
	 * 
	 * @return The spot type.
	 */
	public String getSpotType() {
		return spotType;
	}

	/**
	 * Sets the type of parking spot.
	 * 
	 * @param spotType The new spot type.
	 */
	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}

	/**
	 * Gets the type of vehicle that can park in the spot.
	 * 
	 * @return The vehicle type.
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * Sets the type of vehicle that can park in the spot.
	 * 
	 * @param vehicleType The new vehicle type.
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Returns whether the parking spot is currently reserved.
	 * 
	 * @return True if the spot is reserved, false otherwise.
	 */
	public boolean isBooked() {
		return isBooked;
	}

	/**
	 * Sets whether the parking spot is currently reserved.
	 * 
	 * @param isBooked True if the spot is reserved, false otherwise.
	 */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	/**
	 * Returns whether the parking spot is currently occupied.
	 * 
	 * @return True if the spot is occupied, false otherwise.
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Sets whether the parking spot is currently occupied.
	 * 
	 * @param isOccupied True if the spot is occupied, false otherwise.
	 */

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	/**
	 * 
	 * A constructor for the ParkingLot class that initializes all instance
	 * variables.
	 * 
	 * @param spotType    The type of parking spot.
	 * @param vehicleType The type of vehicle that can park in the spot.
	 * @param isBooked    Whether the spot is currently booked.
	 * @param isOccupied  Whether the spot is currently occupied.
	 */
	// A constructor with all arguments for the ParkingLot class
	public ParkingLot(String spotType, String vehicleType, boolean isBooked, boolean isOccupied) {
		super();
		this.spotType = spotType;
		this.vehicleType = vehicleType;
		this.isBooked = isBooked;
		this.isOccupied = isOccupied;
	}

	/**
	 * 
	 * A default constructor for the ParkingLot class.
	 */
	// A default constructor for the ParkingLot class
	public ParkingLot() {
		super();
		// TODO Auto-generated constructor stub
	}

}
