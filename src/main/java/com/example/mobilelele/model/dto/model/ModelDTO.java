package com.example.mobilelele.model.dto.model;

import com.example.mobilelele.model.enums.CategoryType;


public class ModelDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private CategoryType category;
    private Integer startYear;
    private Integer endYear;

    public Long getId() {
        return id;
    }

    public ModelDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryType getCategory() {
        return category;
    }

    public ModelDTO setCategory(CategoryType category) {
        this.category = category;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelDTO setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelDTO setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }
}
