package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    List<OfferSummaryView> getAllOffers();

    void initializeOffers();

    OfferSummaryView getOfferById(Long id);

    void deleteOffer(Long id);
}
