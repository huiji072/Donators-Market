package com.shop.dto;

import com.shop.entity.Item;
import com.shop.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem, String imgUrl, String oriImgName){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.imgUrl = imgUrl;
        this.itemSeller = orderItem.getItem().getCreatedBy();
        this.itemBuyer = orderItem.getCreatedBy();
        this.oriImgName = oriImgName;
    }

    private String itemNm; //상품명
    private int count; //주문 수량

    private String imgUrl; //상품 이미지 경로

    private String oriImgName;

    private String itemSeller;
    private String itemBuyer;

}