/**
 * The main class for the Parking Reservation System application.
 * <p>
 * This application uses Spring Boot to manage the backend services for a parking reservation system.
 * </p>
 *
 * @authors Akhil Yadav, Aryan Sinha, Harshita Jain, Praveen Kumar
 * @version 1.0
 * @since [25/04/2023]
 */

package com.kpmg.parkingreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.kpmg.parkingreservation.repository.ParkingLotRepository;
import com.kpmg.parkingreservation.repository.TicketRepository;
import com.kpmg.parkingreservation.repository.UserRepository;

import com.kpmg.parkingreservation.service.ParkingLotServiceImpl;
import com.kpmg.parkingreservation.service.TicketServiceImpl;
import com.kpmg.parkingreservation.service.UserServiceImpl;
import com.kpmg.parkingreservation.model.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.kpmg.parkingreservation")
public class ParkingReservationSystemApplication implements CommandLineRunner {

	/**
	 * The main method of the application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ParkingReservationSystemApplication.class, args);
	}

	/**
	 * The repository for managing user data.
	 */
	@Autowired
	private UserRepository repository;

	/**
	 * The service for managing ticket data.
	 */
	@Autowired
	private TicketServiceImpl ticketService;
	@Autowired
	private TicketRepository ticketRepository;

	/**
	 * The repository for managing parking lot data.
	 */
	@Autowired
	private ParkingLotRepository parkingLotRepository;

	/**
	 * The service for managing parking lot data.
	 */
	@Autowired
	private ParkingLotServiceImpl parkingLotService;

	/**
	 * The method called by the Spring Boot framework on startup.
	 *
	 * @param args command-line arguments
	 * @throws Exception if an error occurs during startup
	 */
	public void run(String... args) throws Exception {

//		ParkingLot p = new ParkingLot("SPECIALLY_ABLED","CAR", false, false);
//		parkingLotService.saveParkingLot(p);
//		ParkingLot p1 = new ParkingLot("SPECIALLY_ABLED","CAR", false, false);
//		parkingLotService.saveParkingLot(p1);
//		ParkingLot p2 = new ParkingLot("EMPLOYEE","CAR", false, false);
//		parkingLotService.saveParkingLot(p2);
//		ParkingLot p3 = new ParkingLot("EMPLOYEE","CAR", false, false);
//		parkingLotService.saveParkingLot(p3);
//		ParkingLot p4 = new ParkingLot("EMPLOYEE","CAR", false, false);
//		parkingLotService.saveParkingLot(p4);
//		ParkingLot p5 = new ParkingLot("EMPLOYEE","BIKE", false, false);
//		parkingLotService.saveParkingLot(p5);
//		ParkingLot p6 = new ParkingLot("EMPLOYEE","BIKE", false, false);
//		parkingLotService.saveParkingLot(p6);
//		ParkingLot p7 = new ParkingLot("EMPLOYEE","CAR", false, false);
//		parkingLotService.saveParkingLot(p7);
//		ParkingLot p8 = new ParkingLot("ADMIN","CAR", false, false);
//		parkingLotService.saveParkingLot(p8);
//		ParkingLot p9 = new ParkingLot("ADMIN","CAR", false, false);
//		parkingLotService.saveParkingLot(p9);
//		ParkingLot p10 = new ParkingLot("ADMIN","CAR", false, false);
//		parkingLotService.saveParkingLot(p10);
//		ParkingLot p11 = new ParkingLot("ADMIN","CAR", false, false);
//		parkingLotService.saveParkingLot(p11);

//		ticketService.start();

	}

}
