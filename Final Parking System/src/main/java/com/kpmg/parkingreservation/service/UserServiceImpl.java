package com.kpmg.parkingreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.model.Vehicle;
import com.kpmg.parkingreservation.pojo.UserRegistration;
import com.kpmg.parkingreservation.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
//    @Autowired
//	private UserRegistrationService userService;
	// private CrudRepository<User, Integer> userRepository;

//    @Override
//    public List<UserRegistration> getAllRegistrations() {
//        return (List<UserRegistration>) repository.findAll();
//    }
//
//    @Override
//    public UserRegistration getRegistrationById(int empId) {
//        return repository.findById(empId).orElse(null);
//    }

	@Override
	public void addRegistration(UserRegistration registration) {
		User user = new User(registration.getUsername(), registration.getPassword(), registration.getUserType());
		Vehicle vehicle = new Vehicle(user, registration.getVehicleNumber(), registration.getVehicleType());
		List<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(vehicle);
		user.setVehicles(vehicleList);
		repository.save(user);
	}

	@Override
	public User getRegistrationByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Object getRegistrationByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.findByUsername(username);
	}

//	@Override
//	public User findByuserName(String username) {
//		// TODO Auto-generated method stub
//		return repository.findByUsername(username);
//	}
	@Override

	public User findByuserName(String userName) {

		return repository.findByUsername(userName);

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
