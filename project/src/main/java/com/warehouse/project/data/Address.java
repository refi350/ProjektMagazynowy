package com.warehouse.project.data;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "street_name", nullable = false)
    private String streetName;
    @Column(name = "house_number", nullable = false)
    private String houseNumber;
    @Column(name = "local_number")
    private int localNumber;
    private String place;
    private String code;

    @OneToOne(mappedBy = "address")
    private Warehouse warehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address() {
    }

    public Address(String streetName, String houseNumber, int localNumber, String place, String code) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.localNumber = localNumber;
        this.place = place;
        this.code = code;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(int localNumber) {
        this.localNumber = localNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
