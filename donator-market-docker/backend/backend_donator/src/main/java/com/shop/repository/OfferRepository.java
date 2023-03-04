package com.shop.repository;

import com.shop.entity.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o from Offer o " +
            "where o.member.email = :email " +
            "order by o.offerDate desc"
    )
    List<Offer> findOffers(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Offer o " +
            "where o.member.email = :email"
    )
    Long countOffer(@Param("email") String email);

}
