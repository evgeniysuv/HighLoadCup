package com.esuvorov.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "LOCATIONS")
public class Location {
    @Id
    @Column(name = "LOCATION_ID")
    private Long id;

    @Column(name = "DISTANCE")
    private Long distance;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "COUNTRY")
    private String country;
}