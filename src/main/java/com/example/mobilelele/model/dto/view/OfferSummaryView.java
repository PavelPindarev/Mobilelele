package com.example.mobilelele.model.dto.view;

import com.example.mobilelele.model.entity.enums.EngineType;
import com.example.mobilelele.model.entity.enums.TransmissionType;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferSummaryView {
    private Long id;

    private String description;

    private EngineType engine;

    private String imageUrl;

    private double mileage;

    private BigDecimal price;

    private TransmissionType transmission;

    private int year;

    private String model;

    private String brand;

    private Instant created;

    private Instant modified;

    private String seller;

    public String getDescription() {
        return description;
    }

    public OfferSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferSummaryView setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferSummaryView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferSummaryView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummaryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public double getMileage() {
        return mileage;
    }

    public OfferSummaryView setMileage(double mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferSummaryView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferSummaryView setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferSummaryView setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferSummaryView setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferSummaryView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferSummaryView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public OfferSummaryView setSeller(String seller) {
        this.seller = seller;
        return this;
    }
}
