package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.dto.service.OfferUpdateServiceModel;
import com.example.mobilelele.model.dto.view.OfferSummaryView;
import com.example.mobilelele.model.entity.enums.EngineType;
import com.example.mobilelele.model.entity.enums.TransmissionType;
import com.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper mapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper mapper) {
        this.offerService = offerService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public String getAllOffersView(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/add")
    public String getAddOffersView() {
        return "offer-add";
    }

    @GetMapping("/{id}/details")
    private String getOfferDetails(@PathVariable Long id, Model model) {
        OfferSummaryView offerView = offerService.getOfferById(id);
        model.addAttribute("offer", offerView);
        return "details";
    }

    //DELETE
    @DeleteMapping("/{id}")
    private String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    //UPDATE
    @GetMapping("/{id}/update")
    public String updateOffer(@PathVariable Long id, Model model) {
        OfferUpdateBindingModel updateBindingModel = mapper
                .map(offerService.getOfferById(id), OfferUpdateBindingModel.class);

        model.addAttribute("offerModel", updateBindingModel)
                .addAttribute("engines", EngineType.values())
                .addAttribute("transmissions", TransmissionType.values());
        return "update";
    }

    @PatchMapping("/{id}/update")
    public String updateOffer(@PathVariable Long id,
                              OfferUpdateBindingModel bindingModel) {

        offerService.updateOffer(mapper.map(bindingModel, OfferUpdateServiceModel.class));

        return "redirect:/offers/" + id + "/details";
    }


}
