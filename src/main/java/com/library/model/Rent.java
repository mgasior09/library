package com.library.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Rent {
    private Integer id;
    private Customer customer;
    private Date rentDate;
    private Volume volume;
    private Date untilDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }
}