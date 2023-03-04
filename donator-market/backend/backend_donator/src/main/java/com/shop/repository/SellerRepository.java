package com.shop.repository;

import com.shop.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    List<Seller> findById(String id);
    Seller findByMemberid(Long memberid);
}
