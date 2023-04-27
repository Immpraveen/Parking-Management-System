package com.kpmg.parkingreservation.service;

import java.time.LocalDate;
import java.util.List;
import com.kpmg.parkingreservation.model.Ticket;

/**
 * A service interface for managing parking tickets.
 */
public interface TicketService {

	/**
	 * Saves the given parking ticket.
	 *
	 * @param ticket The parking ticket to be saved.
	 * @return The saved parking ticket.
	 */
	Ticket saveTicket(Ticket ticket);

	/**
	 * Retrieves the parking ticket for the given employee ID.
	 *
	 * @param empId The employee ID to search for.
	 * @return The parking ticket for the given employee ID.
	 */
	Ticket getTicketByempId(int empId);

	/**
	 * Retrieves all parking tickets.
	 *
	 * @return A list of all parking tickets.
	 */
	List<Ticket> getAllTickets();

	/**
	 * Creates a new parking ticket with the given information.
	 *
	 * @param empId         The employee ID for whom the parking spot is being
	 *                      booked.
	 * @param vehicleType   The type of the vehicle.
	 * @param vehicleNumber The number of the vehicle.
	 * @param spotType      The type of the parking spot.
	 * @return The created parking ticket.
	 * @throws Exception If the parking spot is not available or the input is
	 *                   invalid.
	 */
	Ticket makeTicket(int empId, String vehicleType, String vehicleNumber, String spotType) throws Exception;

	/**
	 * Cancels the parking ticket for the given employee ID and parking spot ID.
	 *
	 * @param empId  The employee ID for whom the parking spot was booked.
	 * @param spotId The ID of the parking spot to cancel.
	 */
	void cancelTicket(int empId, int spotId);

	/**
	 * Creates a new parking ticket for an admin with the given information.
	 *
	 * @param empId         The employee ID for whom the parking spot is being
	 *                      booked.
	 * @param vehicleType   The type of the vehicle.
	 * @param vehicleNumber The number of the vehicle.
	 * @param spotType      The type of the parking spot.
	 * @return The created parking ticket.
	 * @throws Exception If the parking spot is not available or the input is
	 *                   invalid.
	 */
	Ticket makeAdminTicket(int empId, String vehicleType, String vehicleNumber, String string) throws Exception;

	// These methods are commented out and may need description if needed in the
	// future

	/**
	 * Generates a monthly report for the given month.
	 *
	 * @param month The month for which the report is to be generated (1-12).
	 * @return A list of parking tickets for the given month.
	 */
	List<Ticket> generateMonthlyReport(int month);

	/**
	 * Generates a quarterly report for the given quarter.
	 *
	 * @param quarter The quarter for which the report is to be generated (1-4).
	 * @return A list of parking tickets for the given quarter.
	 */
	List<Ticket> generateQuarterlyReport(int quarter);

	/**
	 * Generates a yearly report for the given year.
	 *
	 * @param year The year for which the report is to be generated.
	 * @return A list of parking tickets for the given year.
	 */
	List<Ticket> generateYearlyReport(int year);

	Ticket findLatestByEmpIdAndIsCancelledAndDate(int empId, boolean b, LocalDate incidentDate);
}
