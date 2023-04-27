package com.kpmg.parkingreservation.repository;

import com.kpmg.parkingreservation.model.ParkingLot;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for managing parking lots.
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

	/**
	 * Finds a parking lot with the specified booking and occupancy status and
	 * vehicle type.
	 *
	 * @param isBooked    Whether the parking lot is booked or not.
	 * @param isOccupied  Whether the parking lot is occupied or not.
	 * @param vehicleType The type of vehicle for which the parking lot is intended.
	 * @return The first matching parking lot, or null if none is found.
	 */
	ParkingLot findByIsBookedAndIsOccupiedAndVehicleType(boolean isBooked, boolean isOccupied, String vehicleType);

	/**
	 * Finds the first available parking spot of the specified vehicle and spot
	 * types.
	 *
	 * @param vehicleType The type of vehicle for which the parking spot is
	 *                    intended.
	 * @param spotType    The type of parking spot required.
	 * @param isBooked    Whether the parking spot is already booked or not.
	 * @return The first available parking lot matching the criteria, or null if
	 *         none is found.
	 */
	ParkingLot findFirstByVehicleTypeAndSpotTypeAndIsBookedOrderBySpotId(String vehicleType, String spotType,
			boolean isBooked);

	/**
	 * Finds a parking lot by its unique spot ID.
	 *
	 * @param spotId The unique ID of the parking spot.
	 * @return The matching parking lot, or null if none is found.
	 */
	ParkingLot findBySpotId(int spotId);
}
