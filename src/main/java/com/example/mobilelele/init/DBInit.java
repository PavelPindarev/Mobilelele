package com.example.mobilelele.init;

import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.ModelService;
import com.example.mobilelele.service.OfferService;
import com.example.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final OfferService offerService;
    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public DBInit(UserService userService, OfferService offerService, ModelService modelService, BrandService brandService) {
        this.userService = userService;
        this.offerService = offerService;
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        brandService.initializeBrand();
        modelService.initializeModels();
        offerService.initializeOffers();
    }

}
