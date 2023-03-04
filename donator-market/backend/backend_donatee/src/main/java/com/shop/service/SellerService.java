package com.shop.service;

import com.shop.entity.Seller;
import com.shop.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백
@RequiredArgsConstructor //final이나 @NotNull에 붙은 필드에 생성자를 생성
public class SellerService {

    private final SellerRepository sellerRepository;

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }


    public List<Seller> findById(String id) {
        return sellerRepository.findById(id);
    }

    public Seller findByMemberid(Long memberid) {
        return sellerRepository.findByMemberid(memberid);
    }


}
