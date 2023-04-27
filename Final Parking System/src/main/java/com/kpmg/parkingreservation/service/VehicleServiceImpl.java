package com.kpmg.parkingreservation.service;

import com.kpmg.parkingreservation.repository.*;
import com.kpmg.parkingreservation.exception.ResourceNotFoundException;
import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> getAllVehiclesByEmpId(int empId) {
		User user = userRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", empId));
		return user.getVehicles();
	}

	@Override
	public Vehicle addVehicleToUser(int empId, Vehicle vehicle) {
		User user = userRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", empId));
		vehicle.setUser(user);
		return vehicleRepository.save(vehicle);
	}

	@Override
	public void updateVehicle(Long id, Vehicle vehicle) {
		Vehicle existingVehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", id));
		existingVehicle.setVehicleNumber(vehicle.getVehicleNumber());
		existingVehicle.setVehicleType(vehicle.getVehicleType());
		vehicleRepository.save(existingVehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", id));
		vehicleRepository.delete(vehicle);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Vehicle getVehicleById(Long id) {
		return vehicleRepository.getById(id);
	}
}
