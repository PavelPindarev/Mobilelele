package com.example.mobilelele.model.dto.binding;

import com.example.mobilelele.model.entity.enums.EngineType;
import com.example.mobilelele.model.entity.enums.TransmissionType;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferUpdateBindingModel {
    private Long id;

    @NotNull
    @PositiveOrZero
    private Double mileage;

    @NotNull
    @Min(100)
    private BigDecimal price;

    @NotNull
    @Min(1930)
    private Integer year;

    @NotBlank
    private String description;

    @NotNull
    private EngineType engine;

    @NotNull
    private TransmissionType transmission;

    @NotBlank
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public OfferUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferUpdateBindingModel setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Double getMileage() {
        return mileage;
    }

    public OfferUpdateBindingModel setMileage(Double mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferUpdateBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferUpdateBindingModel setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }
}
