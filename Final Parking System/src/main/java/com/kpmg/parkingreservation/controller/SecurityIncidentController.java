package com.kpmg.parkingreservation.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.kpmg.parkingreservation.security.context.UserContext;
import com.kpmg.parkingreservation.service.SecurityIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kpmg.parkingreservation.model.SecurityIncident;
import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.dto.request.SecurityIncidentDTO;
import com.kpmg.parkingreservation.dto.request.UpdateIncidentDTO;
import com.kpmg.parkingreservation.service.TicketService;
import com.kpmg.parkingreservation.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This class represents the Controller for managing Security Incident reports.
 * It contains methods for creating, resolving, and downloading reports as well
 * as viewing all existing reports. It is annotated with @CrossOrigin to allow
 * cross-origin requests, and @RestController to indicate that it is a RESTful
 * service. It is mapped to "/securityincidents" for all of its API endpoints.
 */

@RestController
@RequestMapping("/securityincidents")
public class SecurityIncidentController {
	/**
	 * An instance of the UserService to be used for retrieving User objects.
	 */
	@Autowired
	private UserService userService;
	/**
	 * An instance of the SecurityIncidentService to be used for managing
	 * SecurityIncident objects.
	 */
	@Autowired
	private SecurityIncidentService securityIncidentService;
	/**
	 * An instance of the TicketRepository to be used for retrieving Ticket objects
	 * from the database.
	 */
	@Autowired
	private TicketService ticketservice;
	/**
	 * An instance of the UserContext to be used for retrieving the current user's
	 * employee ID.
	 */
	@Autowired
	private UserContext userContext;

	/**
	 * This method allows a user to generate a new Security Incident report. It is
	 * mapped to "/report" with a POST method. It requires an employee ID and a
	 * SecurityIncidentDTO object in the request body. It returns a ResponseEntity
	 * with the created SecurityIncident object and an HTTP status code indicating
	 * whether the request was successful.
	 */
	@Operation(summary = "allows the user to generate security incident report ")
	@PostMapping("/report")
	public ResponseEntity<SecurityIncident> createSecurityIncident(@RequestBody SecurityIncidentDTO securityIncidentDTO) {
		Integer empId = userContext.getEmpId(); // Get employee ID from UserContext
		User user = userService.getUserById(empId);
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		SecurityIncident securityIncident = new SecurityIncident();
		securityIncident.setUser(user);
		securityIncident.setDescription(securityIncidentDTO.getDescription());
		securityIncident.setSpotId((ticketservice
				.findLatestByEmpIdAndIsCancelledAndDate(empId, false, securityIncidentDTO.getIncidentDate())
				.getSpotId()));
		securityIncident.setVehicleNumber((ticketservice
				.findLatestByEmpIdAndIsCancelledAndDate(empId, false, securityIncidentDTO.getIncidentDate())
				.getVehicleNumber()));
		securityIncident.setDate(LocalDate.now());
		securityIncident.setTime(LocalTime.now());
		securityIncident.setIncidentType(securityIncidentDTO.getIncidentType());

		securityIncident.setEntryTime((ticketservice
				.findLatestByEmpIdAndIsCancelledAndDate(empId, false, securityIncidentDTO.getIncidentDate())
				.getEntryTime()));
		securityIncident.setExitTime((ticketservice
				.findLatestByEmpIdAndIsCancelledAndDate(empId, false, securityIncidentDTO.getIncidentDate())
				.getExitTime()));
		securityIncident.setIncidentDate(securityIncidentDTO.getIncidentDate());
		SecurityIncident createdSecurityIncident = securityIncidentService.createSecurityIncident(securityIncident);

		return ResponseEntity.ok(createdSecurityIncident);

	}

	/**
	 * This method allows a user to view all raised Security Incident reports. It
	 * is mapped to "/activeincidents" with a GET method. It returns a ResponseEntity
	 * with a List of raised SecurityIncident objects and an HTTP status code
	 * indicating whether the request was successful.
	 */
	@Operation(summary = "allows the users to view all security incident raised by them ")
	@GetMapping("/activeincidents")
	public ResponseEntity<?> getIncidents() {
		Integer empId = userContext.getEmpId();
		List<SecurityIncident> incidents = securityIncidentService.getIncidentsByEmpId(empId);

		if (incidents.isEmpty()) {
			return new ResponseEntity<>("No records found ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(incidents, HttpStatus.OK);
	}

	/**
	 * This method allows an admin to download an Excel file containing all existing
	 * Security Incident reports. It is mapped to "/download" with a GET method. It
	 * returns a ResponseEntity with an InputStreamResource containing the Excel
	 * file, an HTTP header with the file name, and an HTTP status code indicating
	 * whether the request was successful.
	 * 
	 * @throws IOException if there is an error generating the Excel file.
	 */
	@Operation(summary = "allows the admin to download security incident report ")
	@GetMapping("/download")
	public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
		List<SecurityIncident> incidents = securityIncidentService.getAllIncidents();
		ByteArrayInputStream in = securityIncidentService.generateExcel(incidents);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=security_incidents.xlsx");

		return ResponseEntity.ok().headers(headers)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(new InputStreamResource(in));
	}

	/**
	 * This method allows an admin to resolve a Security Incident report by updating
	 * its status and adding comments. It is mapped to "/resolve" with a POST
	 * method. It requires an UpdateIncidentDTO object in the request body. It
	 * returns a ResponseEntity with the resolved SecurityIncident object and an
	 * HTTP status code indicating whether the request was successful.
	 */
	@Operation(summary = "allows the admin to resolve a security incident report ")
	@PostMapping("/resolve")
	public ResponseEntity<SecurityIncident> resolveSecurityIncident(@RequestBody UpdateIncidentDTO updateIncidentDTO) {
		SecurityIncident securityIncident = securityIncidentService.getIncidentById(updateIncidentDTO.getId());
		if (securityIncident == null) {
			return ResponseEntity.badRequest().build();
		}
		securityIncident.setStatus(updateIncidentDTO.isStatus());

		securityIncident.setComments(updateIncidentDTO.getComments());
		SecurityIncident resolvedSecurityIncident = securityIncidentService.createSecurityIncident(securityIncident);

		return ResponseEntity.ok(resolvedSecurityIncident);
	}

	/**
	 * This method allows an admin to view all existing Security Incident reports.
	 * It is mapped to "/show-ticket" with a GET method. It returns a ResponseEntity
	 * with a List of all SecurityIncident objects and an HTTP status code
	 * indicating whether the request was successful.
	 */
	@Operation(summary = "allows the admin to view all security incident report ")
	@GetMapping("/show-ticket")
	public ResponseEntity<?> getActiveTickets() {
		List<SecurityIncident> incidents;
		incidents = securityIncidentService.getAllIncidents();

		if (incidents.isEmpty()) {
			return new ResponseEntity<>("No records found ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(incidents, HttpStatus.OK);
	}

}
