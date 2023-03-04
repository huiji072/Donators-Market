package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;

    private String itemNm;

    private String itemDetail;

    private Integer stockNumber;

    private String imgUrl;


    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetail, Integer stockNumber, String imgUrl){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.imgUrl = imgUrl;
    }

}