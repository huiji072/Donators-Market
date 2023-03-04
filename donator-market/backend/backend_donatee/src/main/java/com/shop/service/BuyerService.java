package com.shop.service;

import com.shop.entity.Buyer;
import com.shop.entity.Seller;
import com.shop.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백
@RequiredArgsConstructor //final이나 @NotNull에 붙은 필드에 생성자를 생성
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public List<Buyer> findById(String id) {
        return buyerRepository.findById(id);
    }

    public Buyer findByMemberid(Long memberid) {
        return buyerRepository.findByMemberid(memberid);
    }

}
