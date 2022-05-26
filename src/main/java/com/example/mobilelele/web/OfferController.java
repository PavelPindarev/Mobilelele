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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @GetMapping("/{id}/update/errors")
    public String updateOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("engines", EngineType.values())
                .addAttribute("transmissions", TransmissionType.values());
        return "update";
    }

    @PatchMapping("/{id}/update")
    public String updateOffer(@PathVariable Long id,
                              @Valid OfferUpdateBindingModel offerModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/update/errors";
        }
        offerService.updateOffer(mapper.map(offerModel, OfferUpdateServiceModel.class));

        return "redirect:/offers/" + id + "/details";
    }


}
