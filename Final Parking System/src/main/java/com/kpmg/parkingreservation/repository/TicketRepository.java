package com.kpmg.parkingreservation.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kpmg.parkingreservation.model.Ticket;

/**
 * 
 * A repository interface for managing {@link Ticket} entities in a database.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	/**
	 * 
	 * Returns the ticket for a given vehicle type and vehicle number.
	 * 
	 * @param vehicleType   the type of the vehicle
	 * @param vehicleNumber the number of the vehicle
	 * @return the ticket for the specified vehicle, or null if not found
	 */
	Ticket findByVehicleTypeAndVehicleNumber(String vehicleType, String vehicleNumber);

	/**
	 * 
	 * Returns the ticket for a given date and employee ID.
	 * 
	 * @param date  the date of the ticket
	 * @param empId the ID of the employee
	 * @return the ticket for the specified date and employee ID, or null if not
	 *         found
	 */
	Ticket findByDateAndEmpId(LocalDate date, int empId);

	/**
	 * 
	 * Returns the latest ticket for a given employee ID.
	 * 
	 * @param empId the ID of the employee
	 * @return the latest ticket for the specified employee ID, or null if not found
	 */
	Ticket findLatestTicketByempId(int empId);

	/**
	 * 
	 * Returns the first ticket for a given date, employee ID, and cancellation
	 * status, ordered by time in descending order.
	 * 
	 * @param localDate the date of the ticket
	 * @param empId     the ID of the employee
	 * @param b         the cancellation status of the ticket
	 * @return the first ticket for the specified date, employee ID, and
	 *         cancellation status, or null if not found
	 */
	Ticket findFirstByDateAndEmpIdAndIsCancelledOrderByTimeDesc(LocalDate localDate, int empId, boolean b);

	/**
	 * 
	 * Returns the ticket for a given date, employee ID, and cancellation status.
	 * 
	 * @param now   the date of the ticket
	 * @param empId the ID of the employee
	 * @param b     the cancellation status of the ticket
	 * @return the ticket for the specified date, employee ID, and cancellation
	 *         status, or null if not found
	 */
	Ticket findByDateAndEmpIdAndIsCancelled(LocalDate now, int empId, boolean b);

	/**
	 * 
	 * Returns the latest ticket for a given employee ID and cancellation status.
	 * 
	 * @param empId the ID of the employee
	 * @param b     the cancellation status of the ticket
	 * @return the latest ticket for the specified employee ID and cancellation
	 *         status, or null if not found
	 */
	Ticket findLatestTicketByempIdAndIsCancelled(int empId, boolean b);

	/**
	 * 
	 * Returns all tickets for a given date and later.
	 * 
	 * @param date the starting date of the tickets
	 * @return a list of all tickets for the specified date and later
	 */
	List<Ticket> findByDateAfter(LocalDate date);

	/**
	 * 
	 * Returns the ticket for a given date and spot ID.
	 * 
	 * @param now    the date of the ticket
	 * @param spotId the ID of the parking spot
	 * @return the ticket for the specified date and spot ID, or null if not found
	 */
	Ticket findByDateAndSpotId(LocalDate now, int spotId);

	/**
	 * Finds the latest ticket for the specified employee on the given date.
	 * 
	 * @param empId        the ID of the employee to search for
	 * @param incidentDate the date to search for
	 * @return the latest ticket found, or null if none was found
	 */
	Ticket findLatestByEmpIdAndDate(int empId, LocalDate incidentDate);

	/**
	 * Finds all tickets for the specified quarter of the year.
	 * 
	 * @param quarter the quarter to search for (1-4)
	 * @return a list of all tickets found
	 */
	@Query("SELECT t FROM Ticket t WHERE QUARTER(t.date) = :quarter")
	List<Ticket> findByQuarter(@Param("quarter") int quarter);

	/**
	 * Finds all tickets for the specified month of the year.
	 * 
	 * @param month the month to search for (1-12)
	 * @return a list of all tickets found
	 */
	@Query("SELECT t FROM Ticket t WHERE MONTH(t.date) = :month")
	List<Ticket> findByMonth(@Param("month") int month);

	/**
	 * Finds all tickets for the specified year.
	 * 
	 * @param year the year to search for
	 * @return a list of all tickets found
	 */
	@Query("SELECT t FROM Ticket t WHERE YEAR(t.date) = :year")
	List<Ticket> findByYear(@Param("year") int year);

	/**
	 * Finds the latest ticket for the specified employee on the given date, ordered
	 * by time.
	 * 
	 * @param empId        the ID of the employee to search for
	 * @param incidentDate the date to search for
	 * @return the latest ticket found, or null if none was found
	 */
	Ticket findLatestByEmpIdAndDateOrderByTime(int empId, LocalDate incidentDate);

	/**
	 * Finds all tickets with a time before the specified time and an entry time
	 * between the specified times.
	 * 
	 * @param oneMinuteAgo the time to search for tickets before
	 * @param of           the end time of the search window
	 * @return a list of all tickets found
	 */
	List<Ticket> findByTimeBeforeAndEntryTime(LocalTime oneMinuteAgo, LocalTime of);

	Ticket findLatestByEmpIdAndIsCancelledAndDate(int empId, boolean b, LocalDate incidentDate);

	Ticket findByDateAndIsCancelledAndSpotId(LocalDate now, boolean b, int spotId);

	Ticket findByDateAndIsCancelledAndSpotIdAndEmpId(LocalDate now, boolean b, int spotId, int empId);
}
