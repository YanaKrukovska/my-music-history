package com.ritacle.mhistory.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(updatable = false, nullable = false, length = 2)
    private String countryCode;


    public Country() {
    }

    public Country(String fullName, String countryCode) {
        this.fullName = fullName;
        this.countryCode = countryCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country: id = " + id + ", full name = " + fullName + ", countryCode = " + countryCode;
    }
}


