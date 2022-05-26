package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.dto.service.OfferUpdateServiceModel;
import com.example.mobilelele.model.dto.view.OfferSummaryView;
import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.entity.enums.EngineType;
import com.example.mobilelele.model.entity.enums.TransmissionType;
import com.example.mobilelele.repository.ModelRepository;
import com.example.mobilelele.repository.OfferRepository;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.service.OfferService;
import com.example.mobilelele.web.NotFoundObjectException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    private final ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void initializeOffers() {

        if (offerRepository.count() == 0) {
            Offer offer1 = new Offer()
                    .setModel(modelRepository.findById(1L).orElse(null))
                    .setEngine(EngineType.GASOLINE)
                    .setTransmission(TransmissionType.MANUAL)
                    .setMileage(22500)
                    .setPrice(BigDecimal.valueOf(14300))
                    .setYear(2019)
                    .setDescription("Used, but well services and in good condition.")
                    .setSeller(userRepository.findByUsername("user")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

            Offer offer2 = new Offer()
                    .setModel(modelRepository.findById(1L).orElse(null))
                    .setEngine(EngineType.DIESEL)
                    .setTransmission(TransmissionType.AUTOMATIC)
                    .setMileage(209000)
                    .setPrice(BigDecimal.valueOf(5500))
                    .setYear(2000)
                    .setDescription("After full maintenance, insurance, new tires...")
                    .setSeller(userRepository.findByUsername("admin")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://www.picclickimg.com/d/l400/pict/283362908243_/FORD-ESCORT-MK5-16L-DOHC-16v-ZETEC.jpg");

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }

    @Override
    public OfferSummaryView getOfferById(Long id) {
        Offer offer = offerRepository.findById(id).get();
        return map(offer);
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return this.offerRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel serviceModel) {
        Long modelId = serviceModel.getId();

        Offer offer = offerRepository.findById(modelId)
                .orElseThrow(() ->
                        new NotFoundObjectException("Offer with id " + modelId + " is not found."));

        offer = setOffer(offer, serviceModel);

        offerRepository.save(offer);
    }

    private Offer setOffer(Offer offer, OfferUpdateServiceModel serviceModel) {
        return (Offer) offer.setPrice(serviceModel.getPrice())
                .setDescription(serviceModel.getDescription())
                .setMileage(serviceModel.getMileage())
                .setEngine(serviceModel.getEngine())
                .setTransmission(serviceModel.getTransmission())
                .setYear(serviceModel.getYear())
                .setImageUrl(serviceModel.getImageUrl())
                .setModified(Instant.now());
    }

    private OfferSummaryView map(Offer offer) {
        return mapper.map(offer, OfferSummaryView.class)
                .setModel(offer.getModel().getName())
                .setBrand(offer.getModel().getBrand().getName())
                .setSeller(offer.getSeller().getFirstName() + " " + offer.getSeller().getLastName());
    }
}
