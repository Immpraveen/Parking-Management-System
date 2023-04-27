package com.kpmg.parkingreservation.service;

import java.util.List;
import java.util.Optional;

import com.kpmg.parkingreservation.model.ParkingLot;

/**
 * This interface represents a service for managing parking lots.
 */
public interface ParkingLotService {

	/**
	 * Retrieves a list of all parking spots in the parking lot.
	 * 
	 * @return A list of all parking spots in the parking lot.
	 */
	List<ParkingLot> getAllSpots();

	/**
	 * Retrieves a parking spot by its ID.
	 * 
	 * @param spotId The ID of the parking spot to retrieve.
	 * @return An Optional object containing the parking spot with the specified ID,
	 *         or empty if not found.
	 */
	Optional<ParkingLot> getSpotById(int spotId);

	/**
	 * Saves a parking lot to the data store.
	 * 
	 * @param parkingLot The parking lot to save.
	 * @return The saved parking lot object.
	 */
	ParkingLot saveParkingLot(ParkingLot parkingLot);

}
