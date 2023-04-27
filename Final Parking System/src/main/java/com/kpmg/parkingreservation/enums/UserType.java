package com.kpmg.parkingreservation.enums;

/**
 * This enumeration represents the different types of users that can be
 * associated with a parking reservation system.
 */
public enum UserType {
	/**
	 * An employee who is authorized to make parking reservations.
	 */
	EMPLOYEE,
	/**
	 * An administrator who has additional privileges, such as the ability to manage
	 * parking lots and users.
	 */
	ADMIN,
	/**
	 * A specially-abled user who requires special parking accommodations.
	 */
	SPECIALLY_ABLED
}
