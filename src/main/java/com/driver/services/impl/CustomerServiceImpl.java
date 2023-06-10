package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> customer = customerRepository2.findById(customerId);
		if (!customer.isPresent()) return;
	
		customerRepository2.delete(customer.get());
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		
		// Retrieve the list of drivers with available cabs and sort them by driverId
        List<Driver> availableDrivers = driverRepository2.findByCabAvailableOrderByDriverId(true);

        // Check if any drivers are available
        if (availableDrivers.isEmpty()) {
            throw new Exception("No cab available!");
        }

        // Select the driver with the lowest driverId
        Driver selectedDriver = availableDrivers.get(0);

        // Retrieve the associated customer entity
        Customer customer = customerRepository2.findById(customerId)
                .orElseThrow(() -> new Exception("Customer not found!"));

        // Create a new TripBooking instance
        TripBooking tripBooking = new TripBooking();
        tripBooking.setFromLocation(fromLocation);
        tripBooking.setToLocation(toLocation);
        tripBooking.setDistanceInKm(distanceInKm);
        tripBooking.setStatus(TripStatus.CONFIRMED);

        // Associate the driver and customer entities with the TripBooking instance
        tripBooking.setDriver(selectedDriver);
        tripBooking.setCustomer(customer);

        // Save the TripBooking instance
        TripBooking savedTripBooking = tripBookingRepository2.save(tripBooking);

        // Update the availability of the selected driver's cab
        selectedDriver.getCab().setAvailable(false);
        driverRepository2.save(selectedDriver);

        return savedTripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

		// Retrieve the TripBooking entity
        TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();

        // Update the status to indicate cancellation
        tripBooking.setStatus(TripStatus.CANCELED);

        // Save the updated TripBooking entity
        tripBookingRepository2.save(tripBooking);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly

		// Retrieve the TripBooking entity
        TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();

        // Update the status to indicate cancellation
        tripBooking.setStatus(TripStatus.COMPLETED);

        // Save the updated TripBooking entity
        tripBookingRepository2.save(tripBooking);
	}
}
