package com.example.mobilelele.model.mapper;

import com.example.mobilelele.model.dto.offer.OfferDetailDTO;
import com.example.mobilelele.model.dto.offer.OfferUpdateOrAddBindingModel;
import com.example.mobilelele.model.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    Offer offerUpdateOrAddBindingModelToOffer(OfferUpdateOrAddBindingModel addOfferDTO);

    OfferUpdateOrAddBindingModel offerToOfferUpdateOrAddBindingModel(Offer offer);


    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName", target = "sellerFirstName")
    @Mapping(source = "seller.lastName", target = "sellerLastName")
    OfferDetailDTO offerToOfferDetailDTO(Offer offerEntity);
}
