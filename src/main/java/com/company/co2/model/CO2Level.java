package com.company.co2.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CO2_LEVELS")
public class CO2Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Level")
    private String level;

    @CreationTimestamp
    @Column(name = "DetectionDate", updatable = false)
    private Timestamp detectionDate;

    @Column(name = "District")
    private String district;

    @Column(name = "City")
    private String city;


    public CO2Level() {

    }

    public CO2Level(String level, Timestamp detectionDate, String district, String city) {
        this.level = level;
        this.detectionDate = detectionDate;
        this.district = district;
        this.city = city;
    }

    public CO2Level(String level, String district, String city) {
        this.level = level;
        this.detectionDate = new Timestamp(new java.util.Date().getTime());
        this.district = district;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Timestamp getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Timestamp detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CO2Level{");
        sb.append("id=").append(id);
        sb.append(", level='").append(level).append('\'');
        sb.append(", detectionDate=").append(detectionDate);
        sb.append(", district='").append(district).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}