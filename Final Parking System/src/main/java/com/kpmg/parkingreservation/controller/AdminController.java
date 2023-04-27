package com.kpmg.parkingreservation.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kpmg.parkingreservation.exception.InvalidTicketRequestException;
import com.kpmg.parkingreservation.model.ParkingLot;
import com.kpmg.parkingreservation.model.Ticket;
import com.kpmg.parkingreservation.pojo.AdminTicketRequest;
import com.kpmg.parkingreservation.pojo.ClientRequestBody;
import com.kpmg.parkingreservation.pojo.OtherRequestBody;
import com.kpmg.parkingreservation.pojo.PartnerRequestBody;
import com.kpmg.parkingreservation.repository.ParkingLotRepository;
import com.kpmg.parkingreservation.repository.TicketRepository;
import com.kpmg.parkingreservation.resources.ConstantUtils;
import com.kpmg.parkingreservation.resources.EmailUtil;
//import com.kpmg.parkingreservation.service.ParkingLotService;
import com.kpmg.parkingreservation.service.TicketServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This class is the controller for the Admin-related endpoints in the parking
 * management system. It contains methods for creating parking tickets for
 * different types of users, marking the entry and exit of vehicles in parking
 * spots, and cancelling tickets.
 */
@CrossOrigin
@RestController
@RequestMapping("/Admin")
public class AdminController {
	/**
	 * Service layer implementation for the Ticket-related functionalities.
	 */
	@Autowired
	private TicketServiceImpl ticketService;
	/**
	 * Repository for storing and retrieving Ticket entities.
	 */
	@Autowired
	private TicketRepository ticketRepository;
	/**
	 * Repository for storing and retrieving ParkingLot entities.
	 */
	@Autowired
	private ParkingLotRepository parkingLotRepository;

	/**
	 * Endpoint for creating a parking ticket for an employee.
	 * 
	 * @param ticketRequest the request body containing the necessary details for
	 *                      the ticket creation
	 * @return a ResponseEntity containing the created Ticket object and a CREATED
	 *         status if successful, or an InvalidTicketRequestException if the
	 *         request is invalid.
	 * @throws Exception if an error occurs during the ticket creation process.
	 */
	@Operation(summary = " allows admin to create a parking ticket for an employee ")
	@PostMapping("/employeeticket")
	public ResponseEntity<Ticket> bookEmployeeParkingSpot(@RequestBody AdminTicketRequest ticketRequest)
			throws Exception {
		System.out.println(ticketRequest);
		Ticket ticket = ticketService.makeAdminTicketEmp(ticketRequest.getEmpId(), ticketRequest.getVehicleType(),
				ticketRequest.getVehicleNumber(), ticketRequest.getSpotType());

		if (ticket != null) {

			return new ResponseEntity<>(ticket, HttpStatus.CREATED);
		} else {
			throw new InvalidTicketRequestException("Invalid ticket request");
		}

	}

	/**
	 * Endpoint for creating a parking ticket for a specially-abled person.
	 * 
	 * @param ticketRequest the request body containing the necessary details for
	 *                      the ticket creation
	 * @return a ResponseEntity containing the created Ticket object and a CREATED
	 *         status if successful, or an InvalidTicketRequestException if the
	 *         request is invalid.
	 * @throws Exception if an error occurs during the ticket creation process.
	 */
	@Operation(summary = " allows admin to create a parking ticket for a specially-abled person ")
	@PostMapping("/speciallyabledticket")
	public ResponseEntity<Ticket> bookSpeciallyAbledParkingSpot(@RequestBody AdminTicketRequest ticketRequest)
			throws Exception {
		Ticket ticket = ticketService.makeAdminTicketEmp(ticketRequest.getEmpId(), ticketRequest.getVehicleType(),
				ticketRequest.getVehicleNumber(), ticketRequest.getSpotType());
		if (ticket != null) {
			return new ResponseEntity<>(ticket, HttpStatus.CREATED);
		} else {
			throw new InvalidTicketRequestException("Invalid ticket request");
		}
	}

	/**
	 * Endpoint for creating a parking ticket for a partner.
	 * 
	 * @param request the request body containing the necessary details for the
	 *                ticket creation
	 * @return a ResponseEntity containing the created Ticket object and a CREATED
	 *         status if successful, or an InvalidTicketRequestException if the
	 *         request is invalid.
	 * @throws Exception if an error occurs during the ticket creation process.
	 */
	@Operation(summary = " allows admin to create a parking ticket for a partner")
	@PostMapping("/partnerticket")
	public ResponseEntity<Ticket> bookPartnerSpot(@RequestBody PartnerRequestBody request) throws Exception {
		Ticket ticket = ticketService.makeAdminTicket(request.getEmpId(), request.getVehicleType(),
				request.getVehicleNumber(), "ADMIN");
		if (ticket != null) {
			EmailUtil.sendEmail(request.getUserName(), ConstantUtils.resDone,
					"reservation done for " + ticket.toString());
			return new ResponseEntity<>(ticket, HttpStatus.CREATED);
		} else {
			throw new InvalidTicketRequestException("Invalid ticket request");
		}
	}

	/**
	 * Endpoint for creating a parking ticket for a client.
	 * 
	 * @param request the request body containing the necessary details for the
	 *                ticket creation
	 * @return a ResponseEntity containing the created Ticket object and a CREATED
	 *         status if successful, or an InvalidTicketRequestException if the
	 *         request is invalid.
	 * @throws Exception if an error occurs during the ticket creation process.
	 */
	@Operation(summary = " allows admin to create a parking ticket for a client")
	@PostMapping("/clientticket")
	public ResponseEntity<Ticket> bookClientSpot(@RequestBody ClientRequestBody request) throws Exception {
		Ticket ticket = ticketService.makeAdminTicket(request.getEmpId(), request.getVehicleType(),
				request.getVehicleNumber(), "ADMIN");
		if (ticket != null) {
			EmailUtil.sendEmail(request.getUserName(), ConstantUtils.resDone,
					"reservation done for " + ticket.toString());
			return new ResponseEntity<>(ticket, HttpStatus.CREATED);
		} else {
			throw new InvalidTicketRequestException("Invalid ticket request");
		}
	}

	/**
	 * Endpoint for creating a parking ticket for any non-registered person.
	 * 
	 * @param request the request body containing the necessary details for the
	 *                ticket creation
	 * @return a ResponseEntity containing the created Ticket object and a CREATED
	 *         status if successful, or an InvalidTicketRequestException if the
	 *         request is invalid.
	 * @throws Exception if an error occurs during the ticket creation process.
	 */
	@Operation(summary = " allows admin to create a parking ticket for any non-registered person ")
	@PostMapping("/otherticket")
	public ResponseEntity<Ticket> bookOtherSpot(@RequestBody OtherRequestBody request) throws Exception {
		Ticket ticket = ticketService.makeAdminTicket(request.getEmpId(), request.getVehicleType(),
				request.getVehicleNumber(), "ADMIN");
		if (ticket != null) {
			EmailUtil.sendEmail(request.getUserName(), ConstantUtils.resDone,
					"reservation done for " + ticket.toString());
			return new ResponseEntity<>(ticket, HttpStatus.CREATED);
		} else {
			throw new InvalidTicketRequestException("Invalid ticket request");
		}
	}

	/**
	 * Controller method that allows an admin to mark the entry of an employee from
	 * a parking spot.
	 * 
	 * @param empId  The ID of the employee whose parking spot is being marked as
	 *               occupied.
	 * @param spotId The ID of the parking spot being marked as occupied.
	 * @return A ResponseEntity object containing a String indicating the success or
	 *         failure of the operation.
	 */
	@Operation(summary = " allows admin to mark the entry of the employee")
	@PutMapping("/isoccupiedtrue")
	public ResponseEntity<String> ToggleOccupied(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "spotId") int spotId) {
		Ticket ticket = new Ticket();
		ticket = ticketRepository.findByDateAndIsCancelledAndSpotIdAndEmpId(LocalDate.now(), false, spotId,empId );
		// find the parking lot with the given spotId
		ParkingLot parkingLot = parkingLotRepository.findBySpotId(spotId);
		if (parkingLot == null) {
			return ResponseEntity.badRequest().body("Invalid spotId");
		}

		// check if the parking spot is already occupied
		if (parkingLot.isOccupied()) {
			return ResponseEntity.badRequest().body("Parking spot is already occupied");
		}

		// update the parking lot's isOccupied field to true
		parkingLot.setOccupied(true);
		ticket.setEntryTime(LocalTime.now());
//	    ticket.setExitTime(LocalTime.now());

		// save the changes to the database
		parkingLotRepository.save(parkingLot);
		ticketRepository.save(ticket);

		return ResponseEntity.ok(" updated successfully");

	}

	/**
	 * Controller method that allows an admin to mark the exit of an employee from a
	 * parking spot.
	 * 
	 * @param empId  The ID of the employee whose parking spot is being marked as
	 *               unoccupied.
	 * @param spotId The ID of the parking spot being marked as unoccupied.
	 * @return A ResponseEntity object containing a String indicating the success or
	 *         failure of the operation.
	 */
	@Operation(summary = " allows admin to mark the exit of the employee")
	@PutMapping("/isoccupiedfalse")
	public ResponseEntity<String> Occupied(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "spotId") int spotId) {
		Ticket ticket = new Ticket();
		ticket = ticketRepository.findByDateAndIsCancelledAndSpotIdAndEmpId(LocalDate.now(), false, spotId,empId);
		// find the parking lot with the given spotId
		ParkingLot parkingLot = parkingLotRepository.findBySpotId(spotId);
		if (parkingLot == null || ticket == null) {
			return ResponseEntity.badRequest().body("No record found to update");
		}

		// check if the parking spot is already occupied
		if (parkingLot.isOccupied()) {
			parkingLot.setOccupied(false);
			ticket.setExitTime(LocalTime.now());
			ticket.setCancelled(true);

		}

		parkingLotRepository.save(parkingLot);

		return ResponseEntity.ok(" updated successfully");

	}

	/**
	 * Controller method that cancels a parking ticket for the given employee and
	 * spot IDs.
	 * 
	 * @param empId  The ID of the employee whose ticket is being cancelled.
	 * @param spotId The ID of the parking spot associated with the ticket being
	 *               cancelled.
	 * @return A ResponseEntity object containing a String indicating the success or
	 *         failure of the operation.
	 */
	@Operation(summary = "cancels a Ticket object by employee ID and spot ID")
	@PutMapping("/cancel")
	public ResponseEntity<String> cancelTicket(@RequestParam(value = "empId") int empId,
			@RequestParam(value = "spotId") int spotId) {
		ticketService.cancelTicket(empId, spotId);
		return ResponseEntity.ok("Ticket canceled successfully");
	}

}
