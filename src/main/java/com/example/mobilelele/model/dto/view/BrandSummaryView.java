package com.example.mobilelele.model.dto.view;

import com.example.mobilelele.model.entity.Model;

import java.util.Set;

public class BrandSummaryView {
    private String name;

    private Set<Model> models;

    public String getName() {
        return name;
    }

    public BrandSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Model> getModels() {
        return models;
    }

    public BrandSummaryView setModels(Set<Model> models) {
        this.models = models;
        return this;
    }
}
