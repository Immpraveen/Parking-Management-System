package com.kpmg.parkingreservation.service;

import com.kpmg.parkingreservation.model.ParkingLot;
import com.kpmg.parkingreservation.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	@Autowired
	private ParkingLotRepository parkingLotRepository;

	public List<ParkingLot> getAllSpots() {
		return parkingLotRepository.findAll();
	}

	public Optional<ParkingLot> getSpotById(int spotId) {
		return parkingLotRepository.findById(spotId);
	}

	public ParkingLot saveParkingLot(ParkingLot parkingLot) {
		return parkingLotRepository.save(parkingLot);
	}
}
