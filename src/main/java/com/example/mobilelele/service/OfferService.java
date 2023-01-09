package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.offer.OfferDetailDTO;
import com.example.mobilelele.model.dto.offer.OfferSearchDTO;
import com.example.mobilelele.model.dto.offer.OfferUpdateOrAddBindingModel;
import com.example.mobilelele.model.userdetails.MobileleUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    Page<OfferDetailDTO> getAllOffers(Pageable pageable);

    void initializeOffers();

    Optional<OfferDetailDTO> getOfferById(Long id);

    void deleteOffer(Long id);

    void addOffer(OfferUpdateOrAddBindingModel offerModel, MobileleUserDetails userDetails);

    Optional<OfferUpdateOrAddBindingModel> getOfferEditDetails(Long id);

    List<OfferDetailDTO> searchOffer(OfferSearchDTO offerSearchDTO);

    boolean isOwner(String userName, Long offerId);
}
