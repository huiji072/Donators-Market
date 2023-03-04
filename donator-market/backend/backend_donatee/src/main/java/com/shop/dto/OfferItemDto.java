package com.shop.dto;

import com.shop.entity.OfferItem;
import com.shop.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferItemDto {

    public OfferItemDto(OfferItem offerItem, String imgUrl){
        this.itemNm = offerItem.getItem().getItemNm();
        this.count = offerItem.getCount();
        this.imgUrl = imgUrl;
        this.itemSeller = offerItem.getItem().getCreatedBy();
        this.itemBuyer = offerItem.getCreatedBy();
    }

    private String itemNm; //상품명
    private int count; //주문 수량

    private String imgUrl; //상품 이미지 경로

    private String itemSeller;
    private String itemBuyer;

}
