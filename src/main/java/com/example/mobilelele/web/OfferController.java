package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.view.OfferSummaryView;
import com.example.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
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

    @DeleteMapping("/{id}")
    private String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
