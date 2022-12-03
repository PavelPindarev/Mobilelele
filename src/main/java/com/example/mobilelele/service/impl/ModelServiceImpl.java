package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.enums.CategoryType;
import com.example.mobilelele.repository.BrandRepository;
import com.example.mobilelele.repository.ModelRepository;
import com.example.mobilelele.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeModels() {
        if (modelRepository.count() == 0) {
            Brand ford = brandRepository.findByName("Ford")
                    .orElseThrow(IllegalArgumentException::new);

            Model fiesta = new Model();
            fiesta
                    .setName("Fiesta")
                    .setCategory(CategoryType.CAR)
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1920px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
                    .setStartYear(1976)
                    .setBrand(ford);

            Model escort = new Model();
            escort
                    .setName("Escort")
                    .setCategory(CategoryType.CAR)
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg")
                    .setStartYear(1967)
                    .setEndYear(2004)
                    .setBrand(ford);

            modelRepository.saveAll(List.of(fiesta, escort));
        }
    }
}