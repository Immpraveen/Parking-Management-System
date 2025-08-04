package com.kpmg.parkingreservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kpmg.parkingreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.model.Vehicle;
import com.kpmg.parkingreservation.dto.request.UserRegistration;
import com.kpmg.parkingreservation.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authManager;

	@Override
	public void addRegistration(UserRegistration registration) {
		User user = new User();
		user.setUsername(registration.getUsername());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setUserType(registration.getUserType());
		Vehicle vehicle = new Vehicle(user, registration.getVehicleNumber(), registration.getVehicleType());
		List<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(vehicle);
		user.setVehicles(vehicleList);
		repository.save(user);
	}

	@Override
	public User getByUsername(String username){
		return repository.findByUsername(username);
	}

	@Override
	public User getUserById(int empId) {
		return repository.findById(empId).orElse(null);
	}

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}
}
