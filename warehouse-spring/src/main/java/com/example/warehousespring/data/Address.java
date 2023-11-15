package com.example.warehousespring.data;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "street_name", nullable = false)
    private String street_name;
    @Column(name = "house_number", nullable = false)
    private String house_number;
    @Column(name = "local_number")
    private int local_number;
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
        this.street_name = streetName;
        this.house_number = houseNumber;
        this.local_number = localNumber;
        this.place = place;
        this.code = code;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String streetName) {
        this.street_name = streetName;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String houseNumber) {
        this.house_number = houseNumber;
    }

    public int getLocal_number() {
        return local_number;
    }

    public void setLocal_number(int localNumber) {
        this.local_number = localNumber;
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
