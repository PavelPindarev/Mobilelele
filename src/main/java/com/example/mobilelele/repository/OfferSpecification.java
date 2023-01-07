package com.example.mobilelele.repository;

import com.example.mobilelele.model.dto.offer.OfferSearchDTO;
import com.example.mobilelele.model.entity.Offer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OfferSpecification implements Specification<Offer> {

    private final OfferSearchDTO offerSearchDTO;

    public OfferSpecification(OfferSearchDTO offerSearchDTO) {
        this.offerSearchDTO = offerSearchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Offer> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (offerSearchDTO.getModel() != null && !offerSearchDTO.getModel().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.join("model").get("name"), offerSearchDTO.getModel()))
            );
        }

        if (offerSearchDTO.getMinPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("price"), offerSearchDTO.getMinPrice()))
            );
        }

        if (offerSearchDTO.getMaxPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("price"), offerSearchDTO.getMaxPrice()))
            );
        }


        return p;
    }
}
