package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.offer.OfferDetailDTO;
import com.example.mobilelele.model.dto.offer.OfferUpdateOrAddBindingModel;
import com.example.mobilelele.model.userdetails.MobileleUserDetails;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<OfferDetailDTO> getAllOffers();

    void initializeOffers();

    Optional<OfferDetailDTO> getOfferById(Long id);

    void deleteOffer(Long id);

    void addOffer(OfferUpdateOrAddBindingModel offerModel, MobileleUserDetails userDetails);

    Optional<OfferUpdateOrAddBindingModel> getOfferEditDetails(Long id);
}
