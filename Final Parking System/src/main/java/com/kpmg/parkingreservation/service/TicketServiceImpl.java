package com.kpmg.parkingreservation.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kpmg.parkingreservation.model.ParkingLot;
import com.kpmg.parkingreservation.model.Ticket;
import com.kpmg.parkingreservation.model.User;
import com.kpmg.parkingreservation.repository.ParkingLotRepository;
import com.kpmg.parkingreservation.repository.TicketRepository;
import com.kpmg.parkingreservation.repository.UserRepository;
import com.kpmg.parkingreservation.resources.ConstantUtils;
import com.kpmg.parkingreservation.resources.EmailUtil;

@Service
public class TicketServiceImpl implements TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	private static final long CANCELLATION_PERIOD_MINUTES = 1;

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private ParkingLotRepository parkinglotrepository;
	@Autowired
	private UserRepository userRepository;

	public Ticket saveTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	public Ticket getTicketByempId(int empId) {
		return ticketRepository.findLatestTicketByempIdAndIsCancelled(empId, false);
	}

	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	public Ticket makeTicket(int empId, String vehicleType, String vehicleNumber, String spotType) throws Exception {
		ParkingLot parkinglot = new ParkingLot();
		Ticket ticket = new Ticket();
		ticket = ticketRepository.findByDateAndEmpIdAndIsCancelled(LocalDate.now(), empId, false);
		if (ticket == null) {
			parkinglot = parkinglotrepository.findFirstByVehicleTypeAndSpotTypeAndIsBookedOrderBySpotId(vehicleType,
					spotType, false);
			if (parkinglot == null) {

				throw new Exception("Spot is not available.");
			}

			parkinglot.setBooked(true);
			parkinglotrepository.save(parkinglot);

			Ticket t2 = new Ticket();
			t2.setEmpId(empId);
			t2.setVehicleType(vehicleType);
			t2.setVehicleNumber(vehicleNumber);
			t2.setSpotId(parkinglot.getSpotId());
			User user = new User();
			user = userRepository.findByEmpId(empId);
			EmailUtil.sendEmail(user.getUsername(), ConstantUtils.resDone, "reservation done for " + t2.toString());
			return ticketRepository.save(t2);
		} else if (!parkinglotrepository.findBySpotId(ticket.getSpotId()).isBooked()) {

			Ticket t1 = new Ticket();
			t1.setEmpId(empId);
			t1.setVehicleType(vehicleType);
			t1.setVehicleNumber(vehicleNumber);
			t1.setSpotId(ticket.getSpotId());
			parkinglot = parkinglotrepository.findBySpotId(ticket.getSpotId());
			parkinglot.setBooked(true);
			parkinglotrepository.save(parkinglot);

			logger.info("Reservation for employee with ID {} updated for spot ID {}", empId, ticket.getSpotId());
			return ticketRepository.save(t1);
		} else {
			logger.info("Reservation for employee with ID {} already exists for today", empId);
			return null;
		}

	}

	public void cancelTicket(int empId, int spotId) {
		Ticket ticket = new Ticket();
		ticket = ticketRepository.findFirstByDateAndEmpIdAndIsCancelledOrderByTimeDesc(LocalDate.now(), empId, false);
		ParkingLot parkinglot = new ParkingLot();
		parkinglot = parkinglotrepository.findBySpotId(spotId);
		parkinglot.setBooked(false);
		ticket.setCancelled(true);
		parkinglotrepository.save(parkinglot);
		User user = new User();
		user = userRepository.findByEmpId(empId);
		EmailUtil.sendEmail(user.getUsername(), ConstantUtils.resCancel,
				"reservation Cancelled for " + ticket.toString());
		ticketRepository.save(ticket);
		logger.info("Reservation for employee with ID {} and spot ID {} cancelled", empId, spotId);
	}

	public Ticket makeAdminTicket(int empId, String vehicleType, String vehicleNumber, String spotType)
			throws Exception {
		ParkingLot parkinglot = new ParkingLot();
		Ticket ticket = new Ticket();
		ticket = ticketRepository.findByDateAndEmpIdAndIsCancelled(LocalDate.now(), empId, false);
		if (ticket == null) {
			parkinglot = parkinglotrepository.findFirstByVehicleTypeAndSpotTypeAndIsBookedOrderBySpotId(vehicleType,
					spotType, false);
			if (parkinglot == null) {

				throw new Exception("Spot is not available.");
			}

			parkinglot.setBooked(true);
			parkinglotrepository.save(parkinglot);

			Ticket t2 = new Ticket();
			t2.setEmpId(empId);
			t2.setVehicleType(vehicleType);
			t2.setVehicleNumber(vehicleNumber);
			t2.setSpotId(parkinglot.getSpotId());

			return ticketRepository.save(t2);
		} else if (!parkinglotrepository.findBySpotId(ticket.getSpotId()).isBooked()) {

			Ticket t1 = new Ticket();
			t1.setEmpId(empId);
			t1.setVehicleType(vehicleType);
			t1.setVehicleNumber(vehicleNumber);
			t1.setSpotId(ticket.getSpotId());
			parkinglot = parkinglotrepository.findBySpotId(ticket.getSpotId());
			parkinglot.setBooked(true);
			parkinglotrepository.save(parkinglot);

			logger.info("Reservation for employee with ID {} updated for spot ID {}", empId, ticket.getSpotId());
			return ticketRepository.save(t1);
		} else {
			logger.info("Reservation for employee with ID {} already exists for today", empId);
			return null;
		}
	}

	public List<Ticket> generateMonthlyReport(int month) {
		return ticketRepository.findByMonth(month);
	}

	public List<Ticket> generateQuarterlyReport(int quarter) {
		return ticketRepository.findByQuarter(quarter);
	}

	public List<Ticket> generateYearlyReport(int year) {
		return ticketRepository.findByYear(year);
	}

	public void start() {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(this::cancelExpiredTickets, 1, CANCELLATION_PERIOD_MINUTES, TimeUnit.MINUTES);
	}

	public void cancelExpiredTickets() {
		ZoneId istZone = ZoneId.of("Asia/Kolkata");
		LocalTime oneMinuteAgo = LocalTime.now(istZone).minusMinutes(1);

		// Query the database for all tickets created before 1 minute ago that have not
		// been redeemed
		List<Ticket> expiredTickets = ticketRepository.findByTimeBeforeAndEntryTime(oneMinuteAgo,
				LocalTime.of(0, 0, 0));
		// Batch process the expired tickets
		int batchSize = 100; // Specify your desired batch size here
		IntStream.range(0, expiredTickets.size()).boxed().collect(Collectors.groupingBy(index -> index / batchSize))
				.forEach((batchIndex, batch) -> {
					List<Ticket> batchTickets = batch.stream().map(expiredTickets::get).collect(Collectors.toList());
					batchTickets.forEach(ticket -> {
						ticket.setCancelled(true);
						ticketRepository.save(ticket);
					});
					// Delay for a short period of time (e.g. 100-200 milliseconds) between batches
					// to avoid overwhelming the system
					try {
						Thread.sleep(100); // Specify your desired delay time here
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
	}

	public Ticket makeAdminTicketEmp(int empId, String vehicleType, String vehicleNumber, String spotType)
			throws Exception {
		ParkingLot parkinglot = new ParkingLot();
		Ticket ticket = new Ticket();
		ticket = ticketRepository.findByDateAndEmpIdAndIsCancelled(LocalDate.now(), empId, false);
		if (ticket == null) {
			parkinglot = parkinglotrepository.findFirstByVehicleTypeAndSpotTypeAndIsBookedOrderBySpotId(vehicleType,
					spotType, false);
			if (parkinglot == null) {

				throw new Exception("Spot is not available.");
			}

			parkinglot.setBooked(true);
			parkinglotrepository.save(parkinglot);

			Ticket t2 = new Ticket();
			t2.setEmpId(empId);
			t2.setVehicleType(vehicleType);
			t2.setVehicleNumber(vehicleNumber);
			t2.setSpotId(parkinglot.getSpotId());
			User user = new User();
			user = userRepository.findByEmpId(empId);
			EmailUtil.sendEmail(user.getUsername(), ConstantUtils.resDone, "reservation done for " + t2.toString());
			return ticketRepository.save(t2);
		} else if (!parkinglotrepository.findBySpotId(ticket.getSpotId()).isBooked()) {

			Ticket t1 = new Ticket();
			t1.setEmpId(empId);
			t1.setVehicleType(vehicleType);
			t1.setVehicleNumber(vehicleNumber);
			t1.setSpotId(ticket.getSpotId());
			parkinglot = parkinglotrepository.findBySpotId(ticket.getSpotId());
			parkinglot.setBooked(true);
			parkinglotrepository.save(parkinglot);
			User user = userRepository.findByEmpId(empId);
			parkinglotrepository.save(parkinglot);
			logger.info("Reservation for employee with ID {} updated for spot ID {}", empId, ticket.getSpotId());
			EmailUtil.sendEmail(user.getUsername(), ConstantUtils.resDone, "reservation done for " + t1.toString());
			return ticketRepository.save(t1);
		} else {
			logger.info("Reservation for employee with ID {} already exists for today", empId);
			return null;
		}
	}

	public List<Ticket> getAllTicketsOrderByIddesc() {
		return ticketRepository.findAll();
	}

	public Ticket findLatestByEmpIdAndIsCancelledAndDate(int empId, boolean b, LocalDate incidentDate) {
		List<Ticket> tickets = getAllTickets(); // replace this with your actual implementation to get the list of all
												// tickets

		Optional<Ticket> optionalTicket = tickets.stream().filter(t -> t.getEmpId() == empId)
				.filter(t -> t.isCancelled() == b).filter(t -> t.getDate().equals(incidentDate))
				.sorted(Comparator.comparing(Ticket::getDate).reversed()).findFirst();

		if (optionalTicket.isPresent()) {
			return optionalTicket.get();
		} else {
			return null;
		}
	}
}
