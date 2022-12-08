package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.brand.BrandSummaryView;

import java.util.List;

public interface BrandService {
    void initializeBrand();

    List<BrandSummaryView> getAllBrands();
}
