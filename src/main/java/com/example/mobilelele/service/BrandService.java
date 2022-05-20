package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.view.BrandSummaryView;
import com.example.mobilelele.model.entity.Brand;

import java.util.List;

public interface BrandService {
    void initializeBrand();

    List<BrandSummaryView> getAllBrands();
}
