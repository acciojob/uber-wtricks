package com.driver.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripBookingId;

    private String fromLocation;

    private String toLocation;

    private int distanceInKm;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    private int bill;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public TripBooking() {}

    public TripBooking(
        int tripBookingId, 
        String fromLocation,
        String toLocation,
        int distanceInKm,
        TripStatus status,
        int bill
    ) {
        this.tripBookingId = tripBookingId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.bill = bill;
        this.status = status;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public int getBill() {
        return bill;
    }

    public String getToLocation() {
        return toLocation;
    }

    public int getTripBookingId() {
        return tripBookingId;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Driver getDriver() {
        return driver;
    }
}