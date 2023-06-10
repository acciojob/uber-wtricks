package com.driver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int perKmRate;

    private boolean available;

    @OneToOne(mappedBy = "cab")
    private Driver driverId;

    public Cab() {}

    public Cab(int id, int perKmRate, boolean available, Driver driver) {
        this.id = id;
        this.perKmRate = perKmRate;
        this.available = available;
        this.driverId = driver;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public int getId() {
        return id;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }
}