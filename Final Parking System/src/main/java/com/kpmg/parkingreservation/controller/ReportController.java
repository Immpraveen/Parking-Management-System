package com.kpmg.parkingreservation.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.kpmg.parkingreservation.util.ReportHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/reports")
public class ReportController {
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
		List<Ticket> monthlyReport = ticketService.generateMonthlyReport(month);
		Workbook workbook = ReportHelper.generateTicketReport(monthlyReport, "Monthly Report");

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=monthly_report.xlsx");
		workbook.write(response.getOutputStream());
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
		List<Ticket> quarterlyReport = ticketService.generateQuarterlyReport(quarter);
		Workbook workbook = ReportHelper.generateTicketReport(quarterlyReport, "Quarterly Report");

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=quarterly_report.xlsx");
		workbook.write(response.getOutputStream());
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
		List<Ticket> yearlyReport = ticketService.generateYearlyReport(year);
		Workbook workbook = ReportHelper.generateTicketReport(yearlyReport, "Yearly Report");

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=yearly_report.xlsx");
		workbook.write(response.getOutputStream());
		workbook.close();
		response.flushBuffer();
	}

}
