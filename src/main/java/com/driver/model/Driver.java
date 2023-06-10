package com.driver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int driverId;

    private String mobile;

    private String password;

    @OneToOne
    @JoinColumn
    private Cab cab;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    ArrayList<TripBooking> tripBookingList = new ArrayList<>();

    public Driver() {}

    public Driver(int driverId, String mobile, String password, Cab cab) {
        this.driverId = driverId;
        this.mobile = mobile;
        this.password = password;
        this.cab = cab;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public Cab getCab() {
        return cab;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}