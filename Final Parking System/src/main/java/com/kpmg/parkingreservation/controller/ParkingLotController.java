package com.kpmg.parkingreservation.controller;

import com.kpmg.parkingreservation.model.ParkingLot;
import com.kpmg.parkingreservation.service.ParkingLotServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This controller class handles requests related to parking lots.
 */
@CrossOrigin
@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {
	/**
	 * 
	 * Autowired instance of ParkingLotServiceImpl for handling parking lot related
	 * services.
	 */
	@Autowired
	private ParkingLotServiceImpl parkingLotService;

	/**
	 * 
	 * Retrieves all parking lots available.
	 * 
	 * @return A list of ParkingLot objects.
	 */
	@Operation(summary = "returns a list of all the parking lots available")
	@GetMapping
	public List<ParkingLot> getAllParkingLots() {
		return parkingLotService.getAllSpots();
	}

	/**
	 * 
	 * Retrieves a specific parking lot by its ID.
	 * 
	 * @param spotId The ID of the parking lot to retrieve.
	 * @return ResponseEntity with a ParkingLot object if found, or
	 *         HttpStatus.NOT_FOUND if not found.
	 */
	@Operation(summary = "method returns a specific parking lot by its ID")
	@GetMapping("/{id}")
	public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable("id") int spotId) {
		Optional<ParkingLot> parkingLot = parkingLotService.getSpotById(spotId);
		if (parkingLot.isPresent()) {
			return new ResponseEntity<>(parkingLot.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
