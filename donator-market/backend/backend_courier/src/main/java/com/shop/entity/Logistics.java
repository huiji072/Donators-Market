package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
//@Table(name = "logistics")
@Document(collection = "logistics")
public class Logistics{

    @Transient
    public static final String SEQUENCE_NAME = "logistic_sequence";

    @Id
    @Column(name="logistics_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //    주문상태 - 배송준비중, 배송중, 배송완료
    private String status;

    //    시간
    private String date;

    //    송장번호
    private String logistic;

    private Long orderIdNum;

}

