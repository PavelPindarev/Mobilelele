package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.dto.offer.OfferDetailDTO;
import com.example.mobilelele.model.dto.offer.OfferSearchDTO;
import com.example.mobilelele.model.dto.offer.OfferUpdateOrAddBindingModel;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.enums.EngineType;
import com.example.mobilelele.model.enums.RoleType;
import com.example.mobilelele.model.enums.TransmissionType;
import com.example.mobilelele.model.mapper.OfferMapper;
import com.example.mobilelele.model.userdetails.MobileleUserDetails;
import com.example.mobilelele.repository.ModelRepository;
import com.example.mobilelele.repository.OfferRepository;
import com.example.mobilelele.repository.OfferSpecification;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    private final OfferMapper offerMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            UserRepository userRepository,
                            ModelRepository modelRepository,
                            OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
    }

    @Override
    public Optional<OfferDetailDTO> getOfferById(Long id) {
        return offerRepository.
                findById(id).
                map(offerMapper::offerToOfferDetailDTO);
    }

    @Override
    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(offerMapper::offerToOfferDetailDTO);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }


    @Override
    public void addOffer(OfferUpdateOrAddBindingModel offerModel, MobileleUserDetails userDetails) {

        if (offerRepository.existsById(offerModel.getId())) {
            //UPDATE
            Offer oldOffer = offerRepository.findById(offerModel.getId()).get();

            Offer newOffer = (Offer)
                    oldOffer.setPrice(offerModel.getPrice())
                            .setImageUrl(offerModel.getImageUrl())
                            .setYear(offerModel.getYear())
                            .setMileage(offerModel.getMileage())
                            .setDescription(offerModel.getDescription())
                            .setEngine(offerModel.getEngine())
                            .setTransmission(offerModel.getTransmission())
                            .setModified(Instant.now());

            offerRepository.save(newOffer);
        } else {
            //CREATE
            Offer newOffer =
                    offerMapper.offerUpdateOrAddBindingModelToOffer(offerModel);

            newOffer.setCreated(Instant.now());

            User seller =
                    userRepository.findByEmail(userDetails.getUsername())
                            .orElseThrow();

            Model model = modelRepository.findById(offerModel.getId())
                    .orElseThrow();

            newOffer.setModel(model);
            newOffer.setSeller(seller);
            newOffer.setModified(Instant.now());

            offerRepository.save(newOffer);
        }
    }

    @Override
    public Optional<OfferUpdateOrAddBindingModel> getOfferEditDetails(Long id) {
        return offerRepository.
                findById(id).
                map(offerMapper::offerToOfferUpdateOrAddBindingModel);
    }

    @Override
    public List<OfferDetailDTO> searchOffer(OfferSearchDTO offerSearchDTO) {
        return offerRepository.findAll(new OfferSpecification(offerSearchDTO))
                .stream()
                .map(offerMapper::offerToOfferDetailDTO)
                .toList();
    }

    @Override
    public boolean isOwner(String userName, Long offerId) {

        boolean isOwner = offerRepository.findById(offerId)
                .filter(o -> o.getSeller().getEmail().equals(userName))
                .isPresent();

        if (isOwner) return true;

        return userRepository.findByEmail(userName)
                .filter(this::isAdmin)
                .isPresent();
    }

    private boolean isAdmin(User user) {
        return user.getRoles().stream()
                .anyMatch(userRole -> userRole.getRole().equals(RoleType.ADMIN));
    }


    //INITIALIZATIONS
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
                    .setSeller(userRepository.findByEmail("user@example.com")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

            Offer offer2 = new Offer()
                    .setModel(modelRepository.findById(2L).orElse(null))
                    .setEngine(EngineType.DIESEL)
                    .setTransmission(TransmissionType.AUTOMATIC)
                    .setMileage(209000)
                    .setPrice(BigDecimal.valueOf(5500))
                    .setYear(2000)
                    .setDescription("After full maintenance, insurance, new tires...")
                    .setSeller(userRepository.findByEmail("admin@example.com")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg");

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }

}
