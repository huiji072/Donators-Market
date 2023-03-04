package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOfferDto {
    private Long cartItemId;
    private List<CartOfferDto> cartOfferDtoList;
}
