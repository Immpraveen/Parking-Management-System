package com.kpmg.parkingreservation.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.kpmg.parkingreservation.service.SecurityIncidentService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kpmg.parkingreservation.model.SecurityIncident;
import com.kpmg.parkingreservation.repository.SecurityIncidentRepository;

/**
 * The SecurityIncidentService class provides methods to manage security
 * incidents and associated tickets.
 */
@Service
public class SecurityIncidentServiceImpl implements SecurityIncidentService{

	/**
	 * Repository for storing and retrieving security incidents.
	 */
	@Autowired
	private SecurityIncidentRepository securityIncidentRepository;

	/**
	 * Repository for storing and retrieving tickets associated with security
	 * incidents.
	 */

	/**
	 * Creates a new security incident in the repository.
	 * 
	 * @param securityIncident the security incident to create
	 * @return the created security incident, including any generated IDs or
	 *         timestamps
	 */
	public SecurityIncident createSecurityIncident(SecurityIncident securityIncident) {
		return securityIncidentRepository.save(securityIncident);
	}

	/**
	 * Retrieves all security incidents from the repository.
	 * 
	 * @return a list of all security incidents in the repository
	 */
	public List<SecurityIncident> getAllIncidents() {
		return securityIncidentRepository.findAll();
	}

	/**
	 * Retrieves security incidents associated with a specific employee ID.
	 *
	 * @param empId the employee ID to filter incidents
	 * @return a list of security incidents associated with the given employee ID
	 */
	public List<SecurityIncident> getIncidentsByEmpId(int empId) {
		return securityIncidentRepository.findByUser_EmpId(empId);
	}

	/**
	 * Retrieves a security incident by its ID.
	 *
	 * @param id the ID of the security incident to retrieve
	 * @return the security incident with the specified ID, or null if not found
	 */
	public SecurityIncident getIncidentById(long id) {
		return securityIncidentRepository.findById(id);
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
	public ByteArrayInputStream generateExcel(List<SecurityIncident> incidents) throws IOException {
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
