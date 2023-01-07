package com.example.mobilelele.repository;

import com.example.mobilelele.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long>, JpaSpecificationExecutor<Offer> {

}
