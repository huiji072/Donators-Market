package com.shop.dto;

import com.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CartDetailNonMemberDto {
    private Long itemId;

    private String itemNm; //상품명

    private int count; //수량

    private String imgUrl; //상품 이미지 경로

    private String itemSeller;

    private String oriImgName;

    public CartDetailNonMemberDto(Long itemId, String itemNm, int count, String imgUrl, String itemSeller,
                                  String oriImgName){
        this.itemId = itemId;
        this.itemNm = itemNm;
        this.count = count;
        this.imgUrl = imgUrl;
        this.itemSeller = itemSeller;
        this.oriImgName = oriImgName;
    }

}
