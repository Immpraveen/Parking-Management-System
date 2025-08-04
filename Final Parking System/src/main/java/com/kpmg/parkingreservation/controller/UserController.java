package com.kpmg.parkingreservation.controller;

import com.kpmg.parkingreservation.dto.common.ErrorResponse;
import com.kpmg.parkingreservation.dto.request.UserLoginRequest;
import com.kpmg.parkingreservation.dto.request.UserRegistration;
import com.kpmg.parkingreservation.dto.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for handling user-related HTTP requests
 */

@RestController
@RequestMapping
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * HTTP POST method for user registration
	 * 
	 * @param registration A UserRegistration object containing registration data
	 * @return A ResponseEntity with HTTP status code 201 (Created)
	 */
	@Operation(summary = "Allows the users to register")
	@PostMapping("/registrations")
	public ResponseEntity<?> addRegistration(@RequestBody UserRegistration registration) {
		userService.addRegistration(registration);
//		EmailUtil.sendEmail(registration.getUsername(), ConstantUtils.regDone, "Registration Successful!");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

//	/**
//	 * HTTP POST method for user login
//	 *
//	 * @param loginRequest A UserLoginRequest object containing login data
//	 * @return A ResponseEntity with a LoginResponse object containing user data and
//	 *         a boolean indicating successful login
//	 */
//	@Operation(summary = "Allows the users to login ")
//	@PostMapping("/login")
//
//	public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
//		String userName = loginRequest.getUserName();
//		String password = loginRequest.getPassword();
//		User user = new User(userName, password);
//		// Check if user exists
//		if (userService.verifyUser(user)){
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//					.body(new ErrorResponse("Invalid username or password"));
//		}
//
//		// Check if password is correct
//		if (!passwordEncoder.matches(password, user.getPassword())) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//					.body(new ErrorResponse("Invalid username or password"));
//		}
//
//		// Successful login
//		LoginResponse response = new LoginResponse(user.getEmpId(), user.getUserType(), true);
//		return ResponseEntity.ok(response);
//
//	}
}
