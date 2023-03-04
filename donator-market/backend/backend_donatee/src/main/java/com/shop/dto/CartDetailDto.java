package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemNm; //상품명


    private int count; //수량

    private String imgUrl; //상품 이미지 경로

    private String itemSeller;
    public CartDetailDto(Long cartItemId, String itemNm, int count, String imgUrl, String itemSeller){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.count = count;
        this.imgUrl = imgUrl;
        this.itemSeller = itemSeller;
    }

}