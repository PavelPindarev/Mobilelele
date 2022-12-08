package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.dto.brand.BrandSummaryView;
import com.example.mobilelele.model.dto.model.ModelDTO;
import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.repository.BrandRepository;
import com.example.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void initializeBrand() {
        if (brandRepository.count() == 0) {
            Brand brand = new Brand().setName("Ford");
            brandRepository.save(brand);
        }
    }

    @Override
    public List<BrandSummaryView> getAllBrands() {
        return brandRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    private BrandSummaryView map(Brand brand) {
        List<ModelDTO> modelDTOS = brand.getModels()
                .stream()
                .map(model -> mapper.map(model, ModelDTO.class)).toList();

        return new BrandSummaryView()
                .setModels(modelDTOS)
                .setName(brand.getName());

    }
}
