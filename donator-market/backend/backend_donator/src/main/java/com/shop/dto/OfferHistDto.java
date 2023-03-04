package com.shop.dto;

import com.shop.constant.OfferStatus;
import com.shop.constant.OrderStatus;
import com.shop.entity.Offer;
import com.shop.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OfferHistDto {

    public OfferHistDto(Offer offer){
        this.offerId = offer.getId();
        this.offerDate = offer.getOfferDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.offerStatus = offer.getOfferStatus();
        this.offerLogistic = offer.getLogistics();
    }

    private Long offerId; //주문아이디
    private String offerDate; //주문날짜
    private OfferStatus offerStatus; //주문 상태
    private String offerLogistic; //배송번호

    private List<OfferItemDto> offerItemDtoList = new ArrayList<>();

    public void addOfferItemDto(OfferItemDto offerItemDto){
        offerItemDtoList.add(offerItemDto);
    }
}
