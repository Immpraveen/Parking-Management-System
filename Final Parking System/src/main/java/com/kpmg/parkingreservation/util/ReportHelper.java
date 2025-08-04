package com.kpmg.parkingreservation.util;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kpmg.parkingreservation.model.Ticket;

public class ReportHelper {

    public static Workbook generateTicketReport(List<Ticket> tickets, String sheetName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Ticket ID");
        headerRow.createCell(1).setCellValue("Emp Id");
        headerRow.createCell(2).setCellValue("Date");
        headerRow.createCell(3).setCellValue("Vehicle Number");
        headerRow.createCell(4).setCellValue("Spot Id");
        headerRow.createCell(5).setCellValue("Vehicle Type");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(ticket.getId());
            row.createCell(1).setCellValue(ticket.getEmpId());
            row.createCell(2).setCellValue(ticket.getDate().format(formatter));
            row.createCell(3).setCellValue(ticket.getVehicleNumber());
            row.createCell(4).setCellValue(ticket.getSpotId());
            row.createCell(5).setCellValue(ticket.getVehicleType());
        }
        return workbook;
    }
}
