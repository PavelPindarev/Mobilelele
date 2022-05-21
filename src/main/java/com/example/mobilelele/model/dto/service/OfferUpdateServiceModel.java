package com.example.mobilelele.model.dto.service;

import com.example.mobilelele.model.entity.enums.EngineType;
import com.example.mobilelele.model.entity.enums.TransmissionType;

import java.math.BigDecimal;

public class OfferUpdateServiceModel {
    private Long id;

    private String description;

    private EngineType engine;

    private String imageUrl;

    private double mileage;

    private BigDecimal price;

    private TransmissionType transmission;

    private int year;

    public Long getId() {
        return id;
    }

    public OfferUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferUpdateServiceModel setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public double getMileage() {
        return mileage;
    }

    public OfferUpdateServiceModel setMileage(double mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferUpdateServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferUpdateServiceModel setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferUpdateServiceModel setYear(int year) {
        this.year = year;
        return this;
    }
}
