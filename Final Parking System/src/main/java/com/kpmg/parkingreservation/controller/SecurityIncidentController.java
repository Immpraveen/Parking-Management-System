package com.kpmg.parkingreservation.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kpmg.parkingreservation.model.SecurityIncident;
import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.pojo.SecurityIncidentDTO;
import com.kpmg.parkingreservation.pojo.UpdateIncidentDTO;
import com.kpmg.parkingreservation.repository.SecurityIncidentRepository;
import com.kpmg.parkingreservation.service.SecurityIncidentService;
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
@CrossOrigin
@RestController
@RequestMapping("/securityincidents")
public class SecurityIncidentController {
	/**
	 * An instance of the SecurityIncidentService to be used for creating and
	 * retrieving SecurityIncident objects.
	 */
	@Autowired
	private SecurityIncidentService securityIncidentService;
	/**
	 * An instance of the UserService to be used for retrieving User objects.
	 */
	@Autowired
	private UserService userService;
	/**
	 * An instance of the SecurityIncidentRepository to be used for retrieving and
	 * modifying SecurityIncident objects from the database.
	 */
	@Autowired
	private SecurityIncidentRepository sic;
	/**
	 * An instance of the TicketRepository to be used for retrieving Ticket objects
	 * from the database.
	 */
	@Autowired
	private TicketService ticketservice;

	/**
	 * This method allows a user to generate a new Security Incident report. It is
	 * mapped to "/report" with a POST method. It requires an employee ID and a
	 * SecurityIncidentDTO object in the request body. It returns a ResponseEntity
	 * with the created SecurityIncident object and an HTTP status code indicating
	 * whether the request was successful.
	 */
	@Operation(summary = "allows the user to generate security incident report ")
	@PostMapping("/report")
	public ResponseEntity<SecurityIncident> createSecurityIncident(@RequestParam int empId,
			@RequestBody SecurityIncidentDTO securityIncidentDTO) {
		System.out.println(securityIncidentDTO);
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
	 * This method allows an user to view all raised Security Incident reports. It
	 * is mapped to "/activeincident" with a GET method. It returns a ResponseEntity
	 * with a List of raised SecurityIncident objects and an HTTP status code
	 * indicating whether the request was successful.
	 */
	@Operation(summary = "allows the users to view all security incident raised by them ")
	@GetMapping("/activeincident")
	public ResponseEntity<?> getIncidents(@RequestParam int empId) {
		List<SecurityIncident> incidents;
		incidents = sic.findByUser_EmpId(empId);

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
		List<SecurityIncident> incidents = sic.findAll();
		ByteArrayInputStream in = generateExcel(incidents);

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
		SecurityIncident securityIncident = sic.findById(updateIncidentDTO.getId());
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
		incidents = sic.findAll();

		if (incidents.isEmpty()) {
			return new ResponseEntity<>("No records found ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(incidents, HttpStatus.OK);
	}

	/**
	 * This method generates an Excel file containing information about a list of
	 * SecurityIncidents.
	 *
	 * @param incidents a List of SecurityIncident objects to include in the Excel
	 *                  file.
	 * @return a ByteArrayInputStream containing the generated Excel file.
	 * @throws IOException if there is an error writing the Excel file.
	 */
	private ByteArrayInputStream generateExcel(List<SecurityIncident> incidents) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Security Incidents");
		int rowNum = 0;
		createHeaderRow(sheet, rowNum++);

		for (SecurityIncident incident : incidents) {
			Row row = sheet.createRow(rowNum++);
			createIncidentRow(row, incident);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();

		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	/**
	 * This method creates the header row for the Security Incidents sheet.
	 *
	 * @param sheet  the XSSFSheet object to add the header row to.
	 * @param rowNum the row number to add the header row to.
	 */
	private void createHeaderRow(XSSFSheet sheet, int rowNum) {
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("spotId");
		row.createCell(2).setCellValue("Employee ID");
		row.createCell(3).setCellValue("Description");
		row.createCell(4).setCellValue("vehicleNumber");
		row.createCell(5).setCellValue("Date");
		row.createCell(6).setCellValue("Time");
		row.createCell(7).setCellValue("EntryTime");
		row.createCell(8).setCellValue("ExitTime");
		row.createCell(9).setCellValue("Status");
		row.createCell(10).setCellValue("Comments");
	}

	/**
	 * This method creates a row within the Security Incidents sheet for a given
	 * SecurityIncident object.
	 *
	 * @param row      the Row object to add the incident data to.
	 * @param incident the SecurityIncident object containing the data to add to the
	 *                 row.
	 */
	private void createIncidentRow(Row row, SecurityIncident incident) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		// LocalTime time = LocalTime.parse(incident.getTime(), formatter);
		row.createCell(0).setCellValue(incident.getId());
		row.createCell(1).setCellValue(incident.getSpotId());
		row.createCell(2).setCellValue(incident.getUser().getEmpId());
		row.createCell(3).setCellValue(incident.getDescription());
		row.createCell(4).setCellValue(incident.getVehicleNumber());
		row.createCell(5).setCellValue(incident.getDate().format(formatter));
		row.createCell(6).setCellValue(incident.getTime().format(formatter2));
		row.createCell(7).setCellValue(incident.getEntryTime().format(formatter2));
		row.createCell(8).setCellValue(incident.getExitTime().format(formatter2));
		row.createCell(9).setCellValue(incident.isStatus());
		row.createCell(10).setCellValue(incident.getComments());

	}

}
