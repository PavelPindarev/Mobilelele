package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.offer.OfferUpdateOrAddBindingModel;
import com.example.mobilelele.model.dto.offer.OfferDetailDTO;
import com.example.mobilelele.model.userdetails.MobileleUserDetails;
import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final BrandService brandService;

    @Autowired
    public OfferController(OfferService offerService,
                           BrandService brandService,
                           ModelMapper mapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    //    ALL
    @GetMapping("/all")
    public String getAllOffersView(
            Model model,
            @PageableDefault(
                    sort = "price",
                    direction = Sort.Direction.ASC,
                    page = 0, size = 5) Pageable pageable) {
        model.addAttribute("offers", offerService.getAllOffers(pageable));
        return "offers";
    }

    //    ADD
    @GetMapping("/add")
    public String getAddOffersView(Model model) {
        if (!model.containsAttribute("offerModel")) {
            model.addAttribute("offerModel", new OfferUpdateOrAddBindingModel());
        }
        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferUpdateOrAddBindingModel offerModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal MobileleUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.addOffer(offerModel, userDetails);
        return "redirect:/offers/all";
    }


    //    GET DETAILS
    @GetMapping("/{id}")
    private String getOfferDetails(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("offer")) {
            OfferDetailDTO offerView = offerService.getOfferById(id).orElse(null);
            model.addAttribute("offer", offerView);
        }
        return "details";
    }

    //DELETE
    @DeleteMapping("/{id}")
    private String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    //UPDATE
    @GetMapping("/{id}/edit")
    public String updateOffer(@PathVariable Long id, Model model) {

        var offer = offerService.getOfferEditDetails(id).
                orElseThrow(() -> new NotFoundObjectException("Offer with ID " + id + "not found"));

        model.addAttribute("offerModel", offer);
        return "update";
    }

    @GetMapping("/{id}/edit/errors")
    public String updateOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("bad_credentials", true);
        return "update";
    }

    @PatchMapping("/{id}/edit")
    public String updateOffer(@PathVariable Long id,
                              @Valid OfferUpdateOrAddBindingModel offerModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal MobileleUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }
        offerService.addOffer(offerModel, userDetails);

        return "redirect:/offers/" + id;
    }


}
