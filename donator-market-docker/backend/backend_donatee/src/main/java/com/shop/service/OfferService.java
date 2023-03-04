package com.shop.service;

import com.shop.dto.*;
import com.shop.entity.*;
import com.shop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {

    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;

    private final OfferRepository offerRepository;

    private final ItemImgRepository itemImgRepository;

    public Long offer(OfferDto offerDto, String email){

        Optional<Member> memberRole = memberRepository.findByEmail(email);

        System.out.println("!!!");
        System.out.println(memberRole.get().getRole());

        if(memberRole.get().getRole().equals("ROLE_ADMIN") || memberRole.get().getRole().equals("ROLE_BUYER")) {
            Item item = itemRepository.findById(offerDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);

            System.out.println(item.getCreatedBy());
            Optional<Member> member = memberRepository.findByEmail(item.getCreatedBy());

            List<OfferItem> offerItemList = new ArrayList<>();
            OfferItem offerItem = OfferItem.createOfferItem(item, offerDto.getCount());
            offerItemList.add(offerItem);
            Offer offer = Offer.createOffer(member.get(), offerItemList);
            offerRepository.save(offer);

            return offer.getId();
        } else{
            return null;
        }


    }

    @Transactional(readOnly = true)
    public Page<OfferHistDto> getOfferList(String email, Pageable pageable) {

        List<Offer> offers = offerRepository.findOffers(email, pageable);
        Long totalCount = offerRepository.countOffer(email);
        List<OfferHistDto> offerHistDtos = new ArrayList<>();

        for (Offer offer : offers) {
            OfferHistDto offerHistDto = new OfferHistDto(offer);
            List<OfferItem> offerItems = offer.getOfferItems();
            for (OfferItem offerItem : offerItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn
                        (offerItem.getItem().getId(), "Y");
                OfferItemDto offerItemDto =
                        new OfferItemDto(offerItem, itemImg.getImgUrl());
                offerHistDto.addOfferItemDto(offerItemDto);
            }

            offerHistDtos.add(offerHistDto);
        }

        return new PageImpl<OfferHistDto>(offerHistDtos, pageable, totalCount);
    }

    @Transactional(readOnly = true)
    public boolean validateOffer(Long offerId, String email){
        Optional<Member> curMember = memberRepository.findByEmail(email);
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = offer.getMember();

        if(!StringUtils.equals(curMember.get().getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    public void cancelOffer(Long offerId){
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(EntityNotFoundException::new);
        offer.cancelOffer();
    }



    public Long offers(List<OfferDto> offerDtoList, String email){

        Optional<Member> member = memberRepository.findByEmail(email);
        List<OfferItem> offerItemList = new ArrayList<>();

        for (OfferDto offerDto : offerDtoList) {
            Item item = itemRepository.findById(offerDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OfferItem offerItem = OfferItem.createOfferItem(item, offerDto.getCount());
            offerItemList.add(offerItem);
        }
//        OfferDto offerDto = new OfferDto();
//
//        Item item = itemRepository.findById(offerDto.getItemId())
//                .orElseThrow(EntityNotFoundException::new);

        Offer offer = Offer.createOffer(member.get(), offerItemList);
        offerRepository.save(offer);

        return offer.getId();
    }

}
