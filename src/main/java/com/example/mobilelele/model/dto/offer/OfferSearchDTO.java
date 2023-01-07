package com.example.mobilelele.model.dto.offer;

import java.util.Objects;

public class OfferSearchDTO {
    private String model;
    private Integer minPrice;
    private Integer maxPrice;

    public String getModel() {
        return model;
    }

    public OfferSearchDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public OfferSearchDTO setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public OfferSearchDTO setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public boolean isEmpty() {
        return (model == null || model.isEmpty())
               && maxPrice == null
               && minPrice == null;
    }

    @Override
    public String toString() {
        return "OfferSearchDTO{" +
               "model='" + model + '\'' +
               ", minPrice=" + minPrice +
               ", maxPrice=" + maxPrice +
               '}';
    }
}
