package com.kpmg.parkingreservation.controller;

import com.kpmg.parkingreservation.model.Vehicle;
import com.kpmg.parkingreservation.pojo.VehicleDTO;
import com.kpmg.parkingreservation.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * This class is a RESTful controller that handles HTTP requests related to
 * vehicles.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	/**
	 * Retrieves all vehicles associated with a given employee ID.
	 *
	 * @param empId the employee ID for which to retrieve vehicles
	 * @return a ResponseEntity containing a list of VehicleDTO objects and an HTTP
	 *         status code
	 */
	@Operation(summary = "Retrieves all vehicles associated with a given employee ID")
	@GetMapping("/{empId}")
	public ResponseEntity<List<VehicleDTO>> getAllVehiclesByEmpId(@PathVariable int empId) {
		List<Vehicle> vehicles = vehicleService.getAllVehiclesByEmpId(empId);
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			vehicleDTOs.add(new VehicleDTO(vehicle));
		}
		return ResponseEntity.ok(vehicleDTOs);
	}

	/**
	 * Adds a new vehicle to a user (employee) with the provided employee ID.
	 *
	 * @param empId      the employee ID to which to add the vehicle
	 * @param vehicleDTO the VehicleDTO object containing the details of the new
	 *                   vehicle
	 * @return a ResponseEntity containing the added VehicleDTO object and an HTTP
	 *         status code
	 */
	@Operation(summary = "Adds a new vehicle to a user (employee) with the provided employee ID")
	@PostMapping("/{empId}")
	public ResponseEntity<VehicleDTO> addVehicleToUser(@PathVariable int empId, @RequestBody VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
		vehicle.setVehicleType(vehicleDTO.getVehicleType());
		Vehicle addedVehicle = vehicleService.addVehicleToUser(empId, vehicle);
		VehicleDTO addedVehicleDTO = new VehicleDTO(addedVehicle);
		return ResponseEntity.ok(addedVehicleDTO);
	}

	/**
	 * Updates the details of a vehicle with the given ID.
	 *
	 * @param id         the ID of the vehicle to update
	 * @param vehicleDTO the VehicleDTO object containing the updated vehicle
	 *                   details
	 * @return a ResponseEntity with an HTTP status code indicating success or
	 *         failure
	 */
	@Operation(summary = "Updates the details of a vehicle with the given ID")
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
		vehicle.setVehicleType(vehicleDTO.getVehicleType());
		vehicleService.updateVehicle(id, vehicle);
		return ResponseEntity.ok().build();
	}

	/**
	 * Deletes a vehicle with the given ID.
	 *
	 * @param id the ID of the vehicle to delete
	 * @return a ResponseEntity with an HTTP status code indicating success or
	 *         failure
	 */
	@Operation(summary = "Deletes a vehicle with the given ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
		vehicleService.deleteVehicle(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Retrieves a vehicle with the given VehicleID.
	 *
	 * @param id the ID of the vehicle to retrieve
	 * @return a ResponseEntity containing the retrieved VehicleDTO object and an
	 *         HTTP status code
	 */

	@Operation(summary = "Retrieves a vehicle with the given VehicleID")
	@GetMapping("vehicleid/{id}")

	public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {

		Vehicle vehicle = vehicleService.getVehicleById(id);

		if (vehicle == null) {

			return ResponseEntity.notFound().build();

		}

		VehicleDTO vehicleDTO = new VehicleDTO(vehicle);

		return ResponseEntity.ok(vehicleDTO);

	}
}
