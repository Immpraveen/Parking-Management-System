package com.kpmg.parkingreservation.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 * 
 * This class represents a persistent entity called "Ticket".
 * 
 * It is used to store and retrieve information about parking tickets in a
 * database.
 */
@Entity
@Table(name = "Ticket")
public class Ticket {

	@Override
	public String toString() {
		return "Ticket [vehicleType=" + vehicleType + ", vehicleNumber=" + vehicleNumber + ", spotId=" + spotId
				+ ", empId=" + empId + "]";
	}

	/**
	 * 
	 * The ID of the ticket, used as primary key in the database
	 */
	@Id
	@GeneratedValue(generator = "Ticket_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "Ticket_seq", sequenceName = "Ticket_sequence", initialValue = 1, allocationSize = 1)
	private int Id;
	/**
	 * 
	 * The type of vehicle associated with the ticket
	 */
	@Column
	private String vehicleType;
	/**
	 * 
	 * The number of the vehicle associated with the ticket
	 */
	@Column
	private String vehicleNumber;
	/**
	 * 
	 * The date when the ticket was created
	 */
	@Column
	@CreationTimestamp
	private LocalDate date;
	/**
	 * 
	 * The time when the ticket was created
	 */
	@Column(name = "reservation_time")
	@CreationTimestamp
	private LocalTime time;
	/**
	 * 
	 * The time when the vehicle entered the parking spot
	 */
	@Column
	private LocalTime entryTime = LocalTime.of(0, 0, 0);
	/**
	 * 
	 * The time when the vehicle left the parking spot
	 */
	@Column
	private LocalTime exitTime = LocalTime.of(0, 0, 0);
	/**
	 * 
	 * The ID of the parking spot associated with the ticket
	 */
	@Column
	private int spotId;
	/**
	 * 
	 * The ID of the employee who created the ticket
	 */
	@Column
	private int empId;
	/**
	 * 
	 * Indicates whether the ticket has been cancelled or not
	 */
	@Column()
	private boolean isCancelled;

	/**
	 * 
	 * Returns the ID of the ticket
	 */
	public int getId() {
		return Id;
	}

	/**
	 * 
	 * Sets the ID of the ticket
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * 
	 * Returns the type of vehicle associated with the ticket
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * 
	 * Sets the type of vehicle associated with the ticket
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * 
	 * Returns the number of the vehicle associated with the ticket
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * 
	 * Sets the number of the vehicle associated with the ticket
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * 
	 * Returns the ID of the parking spot associated with the ticket
	 */
	public int getSpotId() {
		return spotId;
	}

	/**
	 * 
	 * Sets the ID of the parking spot associated with the ticket
	 */
	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}

	/**
	 * 
	 * Returns the ID of the employee who created the ticket
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * 
	 * Sets the ID of the employee who created the ticket
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * 
	 * Returns whether the ticket has been cancelled or not
	 */
	public boolean isCancelled() {
		return isCancelled;
	}

	/**
	 * Sets the {@code isCancelled} flag to indicate whether the ticket has been
	 * cancelled.
	 * 
	 * @param isCancelled whether the ticket has been cancelled
	 */
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	/**
	 * Gets the time at which the ticket was issued.
	 * 
	 * @return the time at which the ticket was issued
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Sets the time at which the ticket was issued.
	 * 
	 * @param time the time at which the ticket was issued
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
	 * Gets the time at which the vehicle entered the parking lot.
	 * 
	 * @return the time at which the vehicle entered the parking lot
	 */
	public LocalTime getEntryTime() {
		return entryTime;
	}

	/**
	 * Sets the time at which the vehicle entered the parking lot.
	 * 
	 * @param entryTime the time at which the vehicle entered the parking lot
	 */
	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * Gets the time at which the vehicle exited the parking lot.
	 * 
	 * @return the time at which the vehicle exited the parking lot
	 */
	public LocalTime getExitTime() {
		return exitTime;
	}

	/**
	 * Sets the time at which the vehicle exited the parking lot.
	 * 
	 * @param exitTime the time at which the vehicle exited the parking lot
	 */
	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * Sets the date on which the ticket was issued.
	 * 
	 * @param date the date on which the ticket was issued
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Gets the date on which the ticket was issued.
	 * 
	 * @return the date on which the ticket was issued
	 */
	public LocalDate getDate() {
		return date;
	}
}
