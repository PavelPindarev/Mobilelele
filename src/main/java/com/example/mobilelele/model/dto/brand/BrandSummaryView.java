package com.example.mobilelele.model.dto.brand;

import com.example.mobilelele.model.dto.model.ModelDTO;
import com.example.mobilelele.model.entity.Model;

import java.util.List;
import java.util.Set;

public class BrandSummaryView {
    private String name;

    private List<ModelDTO> models;

    public String getName() {
        return name;
    }

    public BrandSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public BrandSummaryView setModels(List<ModelDTO> models) {
        this.models = models;
        return this;
    }
}
