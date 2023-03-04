package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogisticsDto {
    private String id;

    //    주문상태 - 배송준비중, 배송중, 배송완료
    private String status;

    //    시간
    private String date;

    //    송장번호
    private String logistic;

    private Long orderIdNum;
}
