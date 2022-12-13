package com.example.mobilelele.model.dto.offer;

import com.example.mobilelele.model.enums.EngineType;
import com.example.mobilelele.model.enums.TransmissionType;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferUpdateOrAddBindingModel {
    @NotNull
    @Min(1)
    private Long id;

    @NotNull
    private EngineType engine;

    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    @NotNull
    private Integer mileage;

    @Min(1900)
    @NotNull
    private Integer year;

    @NotEmpty
    private String description;

    @NotNull
    private TransmissionType transmission;

    @NotEmpty
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public OfferUpdateOrAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferUpdateOrAddBindingModel setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateOrAddBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateOrAddBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateOrAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferUpdateOrAddBindingModel setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateOrAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferUpdateOrAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
