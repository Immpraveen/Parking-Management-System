package com.kpmg.parkingreservation.controller;

import com.kpmg.parkingreservation.model.Vehicle;
import com.kpmg.parkingreservation.dto.request.VehicleDTO;
import com.kpmg.parkingreservation.security.context.UserContext;
import com.kpmg.parkingreservation.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is a RESTful controller that handles HTTP requests related to
 * vehicles.
 */

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private UserContext userContext;

	/**
	 * Retrieves all vehicles associated with a given employee ID.
	 *
	 * @return a ResponseEntity containing a list of VehicleDTO objects and an HTTP
	 * status code
	 */
	@Operation(summary = "Retrieves all vehicles associated with a given employee ID")
	@GetMapping
	public ResponseEntity<List<VehicleDTO>> getAllVehiclesByEmpId() {
		Integer empId = userContext.getEmpId(); // Get employee ID from UserContext
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
	 * @param vehicleDTO the VehicleDTO object containing the details of the new vehicle
	 * @return a ResponseEntity containing the added VehicleDTO object and an HTTP
	 * status code
	 */
	@Operation(summary = "Adds a new vehicle to a user (employee) with the provided employee ID")
	@PostMapping
	public ResponseEntity<VehicleDTO> addVehicleToUser(@RequestBody @Valid VehicleDTO vehicleDTO) {
		Integer empId = userContext.getEmpId(); // Get employee ID from UserContext
		Vehicle vehicle = new Vehicle(vehicleDTO.getVehicleNumber(), vehicleDTO.getVehicleType());
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
	@PutMapping("/update/{id}")
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
	@GetMapping("vehicleId/{id}")

	public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {

		Vehicle vehicle = vehicleService.getVehicleById(id);

		if (vehicle == null) {

			return ResponseEntity.notFound().build();

		}

		VehicleDTO vehicleDTO = new VehicleDTO(vehicle);

		return ResponseEntity.ok(vehicleDTO);

	}
}
