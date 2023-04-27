package com.kpmg.parkingreservation.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpmg.parkingreservation.model.Ticket;

import com.kpmg.parkingreservation.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This class represents the REST endpoints for generating and downloading
 * various types of reports. It uses the Spring framework annotations to handle
 * HTTP requests and responses, and the Apache POI library to generate Excel
 * spreadsheets.
 */
@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportControllers {
	/**
	 * The TicketService is an Autowired dependency that provides access to the
	 * ticket data.
	 */
	@Autowired
	private TicketService ticketService;

	/**
	 * This method generates a list of monthly tickets and returns it as a JSON
	 * response.
	 * 
	 * @param month The month for which to generate the report.
	 * @return A List of Ticket objects representing the monthly report.
	 */
	@Operation(summary = "displays a list of monthly tickets")
	@GetMapping("/monthly/{month}")
	public List<Ticket> generateMonthlyReport(@PathVariable int month) {
		return ticketService.generateMonthlyReport(month);
	}

	/**
	 * This method generates a list of quarterly tickets and returns it as a JSON
	 * response.
	 * 
	 * @param quarter The quarter for which to generate the report.
	 * @return A List of Ticket objects representing the quarterly report.
	 */
	@Operation(summary = "displays a list of quaterly tickets")
	@GetMapping("/quarterly/{quarter}")
	public List<Ticket> generateQuarterlyReport(@PathVariable int quarter) {
		return ticketService.generateQuarterlyReport(quarter);
	}

	/**
	 * This method generates a list of monthly tickets and downloads it as an Excel
	 * file.
	 * 
	 * @param month    The month for which to generate the report.
	 * @param response The HTTP response object for writing the Excel file.
	 * @throws IOException if there is an error in writing the Excel file to the
	 *                     response output stream.
	 */
	@Operation(summary = "downloads a list of monthly tickets")
	@GetMapping("/downloadMonthlyReport/{month}")
	public void downloadMonthlyReport(@PathVariable int month, HttpServletResponse response) throws IOException {
		// Fetch data from your repository or service to populate the monthly report
		List<Ticket> monthlyReport = ticketService.generateMonthlyReport(month);

		// Create a new Excel workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a new sheet in the workbook
		Sheet sheet = workbook.createSheet("Monthly Report");

		// Create a header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Ticket ID");
		headerRow.createCell(1).setCellValue("Emp Id");
		headerRow.createCell(2).setCellValue("Date");
		headerRow.createCell(3).setCellValue("Vehicle Number");
		headerRow.createCell(4).setCellValue("Spot Id");
		headerRow.createCell(5).setCellValue("Vehicle Type");

		// Populate the rows with data from the Ticket objects
		for (int i = 0; i < monthlyReport.size(); i++) {
			Ticket ticket = monthlyReport.get(i);
			Row row = sheet.createRow(i + 1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			row.createCell(0).setCellValue(ticket.getId());
			row.createCell(1).setCellValue(ticket.getEmpId());
			row.createCell(2).setCellValue(ticket.getDate().format(formatter));
			row.createCell(3).setCellValue(ticket.getVehicleNumber());
			row.createCell(4).setCellValue(ticket.getSpotId());
			row.createCell(5).setCellValue(ticket.getVehicleType());

		}

		// Set the response headers for downloading the Excel file
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=monthly_report.xlsx");

		// Write the workbook to the response output stream
		workbook.write(response.getOutputStream());

		// Close the workbook and flush the response output stream
		workbook.close();
		response.flushBuffer();
	}

	/**
	 * Endpoint that generates and downloads a quarterly report in Excel format.
	 *
	 * @param quarter  the quarter number for which the report should be generated
	 * @param response the HTTP servlet response object used for downloading the
	 *                 file
	 * @throws IOException if there is an I/O error while generating or downloading
	 *                     the report
	 */
	@Operation(summary = "downloads a list of quaterly tickets")
	@GetMapping("/downloadQuarterlyReport/{quarter}")
	public void downloadQuarterlyReport(@PathVariable int quarter, HttpServletResponse response) throws IOException {
		// Fetch data from your repository or service to populate the quarterly report
		List<Ticket> quarterlyReport = ticketService.generateQuarterlyReport(quarter);

		// Create a new Excel workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a new sheet in the workbook
		Sheet sheet = workbook.createSheet("Quarterly Report");

		// Create a header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Ticket ID");
		headerRow.createCell(1).setCellValue("Emp Id");
		headerRow.createCell(2).setCellValue("Date");
		headerRow.createCell(3).setCellValue("Vehicle Number");
		headerRow.createCell(4).setCellValue("Spot Id");
		headerRow.createCell(5).setCellValue("Vehicle Type");

		// Populate the rows with data from the Ticket objects
		for (int i = 0; i < quarterlyReport.size(); i++) {
			Ticket ticket = quarterlyReport.get(i);
			Row row = sheet.createRow(i + 1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			row.createCell(0).setCellValue(ticket.getId());
			row.createCell(1).setCellValue(ticket.getEmpId());
			row.createCell(2).setCellValue(ticket.getDate().format(formatter));
			row.createCell(3).setCellValue(ticket.getVehicleNumber());
			row.createCell(4).setCellValue(ticket.getSpotId());
			row.createCell(5).setCellValue(ticket.getVehicleType());

		}

		// Set the response headers for downloading the Excel file
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=quarterly_report.xlsx");

		// Write the workbook to the response output stream
		workbook.write(response.getOutputStream());

		// Close the workbook and flush the response output stream
		workbook.close();
		response.flushBuffer();
	}

	/**
	 * Endpoint that generates and downloads a yearly report in Excel format.
	 *
	 * @param year     the year for which the report should be generated
	 * @param response the HTTP servlet response object used for downloading the
	 *                 file
	 * @throws IOException if there is an I/O error while generating or downloading
	 *                     the report
	 */
	@Operation(summary = "downloads a list of yearly tickets")
	@GetMapping("/downloadYearlyReport/{year}")
	public void downloadYearlyReport(@PathVariable int year, HttpServletResponse response) throws IOException {
		// Fetch data from your repository or service to populate the yearly report
		List<Ticket> yearlyReport = ticketService.generateYearlyReport(year);

		// Create a new Excel workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a new sheet in the workbook
		Sheet sheet = workbook.createSheet("Yearly Report");

		// Create a header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Ticket ID");
		headerRow.createCell(1).setCellValue("Emp Id");
		headerRow.createCell(2).setCellValue("Date");
		headerRow.createCell(3).setCellValue("Vehicle Number");
		headerRow.createCell(4).setCellValue("Spot Id");
		headerRow.createCell(5).setCellValue("Vehicle Type");

		// Populate the rows with data from the Ticket objects
		for (int i = 0; i < yearlyReport.size(); i++) {
			Ticket ticket = yearlyReport.get(i);
			Row row = sheet.createRow(i + 1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			row.createCell(0).setCellValue(ticket.getId());
			row.createCell(1).setCellValue(ticket.getEmpId());
			row.createCell(2).setCellValue(ticket.getDate().format(formatter));
			row.createCell(3).setCellValue(ticket.getVehicleNumber());
			row.createCell(4).setCellValue(ticket.getSpotId());
			row.createCell(5).setCellValue(ticket.getVehicleType());
		}

		// Set the response headers for downloading the Excel file
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=yearly_report.xlsx");

		// Write the workbook to the response output stream
		workbook.write(response.getOutputStream());

		// Close the workbook and flush the response output stream
		workbook.close();
		response.flushBuffer();
	}

}
