package com.kpmg.parkingreservation.controller;

import java.util.List;

import com.kpmg.parkingreservation.security.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kpmg.parkingreservation.exception.InvalidTicketRequestException;
import com.kpmg.parkingreservation.exception.TicketNotFoundException;
import com.kpmg.parkingreservation.model.Ticket;
import com.kpmg.parkingreservation.dto.request.TicketRequest;
import com.kpmg.parkingreservation.service.impl.TicketServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This class represents a controller for handling Ticket objects in a parking
 * management system.
 */

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	/**
	 * An instance of TicketServiceImpl for accessing Ticket objects in the
	 * database.
	 */
	@Autowired
	private TicketServiceImpl ticketService;
	@Autowired
	private UserContext userContext;

	/**
	 * Retrieves a Ticket object by employee ID.
	 *
	 * @return A ResponseEntity containing the Ticket object and an HTTP status
	 *         code.
	 * @throws TicketNotFoundException If the Ticket with the given employee ID is
	 *                                 not found.
	 */
	@Operation(summary = "retrieves a Ticket object by employee ID")
	@GetMapping("/current")
	public ResponseEntity<Ticket> getTicketByempId() {
		Integer empId = userContext.getEmpId(); // Get employee ID from UserContext
		Ticket ticket = ticketService.getTicketByempId(empId);
		if (ticket != null) {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		} else {
			throw new TicketNotFoundException("Ticket with empid " + empId + " not found");
		}
	}

	/**
	 * Retrieves a list of all Ticket objects.
	 * 
	 * @return A ResponseEntity containing a list of Ticket objects and an HTTP
	 *         status code.
	 */
	@Operation(summary = "retrieves a list of all Ticket objects")
	@GetMapping("/all")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		List<Ticket> tickets = ticketService.getAllTicketsOrderByIddesc();
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	/**
	 * Creates a new Ticket object with the given input parameters and adds it to
	 * the database.
	 * 
	 * @param ticketRequest An object containing the employee ID, vehicle type,
	 *                      vehicle number, and spot type for the new Ticket.
	 * @return A ResponseEntity containing the newly created Ticket object and an
	 *         HTTP status code.
	 * @throws InvalidTicketRequestException If the input parameters for the new
	 *                                       Ticket are invalid.
	 */
	@Operation(summary = "creates a new Ticket object by taking input parameters and adds it to the database.")
	@PostMapping
	public ResponseEntity<Ticket> makeTicket(@RequestBody TicketRequest ticketRequest) throws Exception {

		Ticket ticket = ticketService.makeTicket(ticketRequest.getEmpId(), ticketRequest.getVehicleType(),
				ticketRequest.getVehicleNumber(), ticketRequest.getSpotType());
		if (ticket != null) {
			return new ResponseEntity<>(ticket, HttpStatus.CREATED);
		} else {
			throw new InvalidTicketRequestException("Invalid ticket request");
		}
	}

	/**
	 * Cancels a Ticket object with the given employee ID and spot ID.
	 *
	 * @param spotId The ID of the spot associated with the Ticket to cancel.
	 * @return A ResponseEntity containing a success message and an HTTP status
	 *         code.
	 */
	@Operation(summary = "cancels a Ticket object by employee ID and spot ID")
	@PutMapping("/cancel")
	public ResponseEntity<String> cancelTicket(@RequestParam(value = "spotId") int spotId) {
		Integer empId = userContext.getEmpId(); // Get employee ID from UserContext
		ticketService.cancelTicket(empId, spotId);
		return ResponseEntity.ok("Ticket canceled successfully");
	}

	/**
	 * Handles exceptions thrown when a Ticket is not found.
	 * 
	 * @param ex The TicketNotFoundException to handle.
	 * @return A ResponseEntity containing an error message and an HTTP status code.
	 */
	@ExceptionHandler(value = { TicketNotFoundException.class })
	public ResponseEntity<Object> handleTicketNotFoundException(TicketNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles exceptions thrown when a Ticket is Invalid.
	 * 
	 * @param ex The InvalidTicketRequestException to handle.
	 * @return A ResponseEntity containing an error message and an HTTP status code.
	 */
	@ExceptionHandler(value = { InvalidTicketRequestException.class })
	public ResponseEntity<Object> handleInvalidTicketRequestException(InvalidTicketRequestException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles exceptions thrown when any other Exception is present.
	 * 
	 * @param ex The handleOtherExceptions to handle.
	 * @return A ResponseEntity containing an error message and an HTTP status code.
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
