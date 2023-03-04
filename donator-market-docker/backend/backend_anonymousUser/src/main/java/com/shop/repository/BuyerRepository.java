package com.shop.repository;

import com.shop.entity.Buyer;
import com.shop.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    List<Buyer> findById(String id);
    Buyer findByMemberid(Long memberid);

}
