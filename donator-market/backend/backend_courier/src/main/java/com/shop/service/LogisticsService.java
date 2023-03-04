package com.shop.service;

import com.shop.entity.Logistics;
import com.shop.repository.LogisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogisticsService {

    private final LogisticsRepository logisticsRepository;


    public Logistics saveLogistics(Logistics logistics, Long orderId) {

        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmssSS"));
        logistics.setLogistic(formatDate);
        logistics.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        logistics.setStatus("인수 중");
        logistics.setOrderIdNum(orderId);
//        logistics.setOffer(offer.get());


        return logisticsRepository.save(logistics);
    }

    public Logistics saveLogistics_delivery_ready(Long orderId) {

        Logistics logistics = logisticsRepository.findByOrderIdNum(orderId);

        Logistics logistics_new = new Logistics();
        logistics_new.setLogistic(logistics.getLogistic());
        logistics_new.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        logistics_new.setStatus("배송 준비 중");

        return logisticsRepository.save(logistics_new);

    }
    public Logistics saveLogistics_delivery_ing(Long orderId) {

        Logistics logistics = logisticsRepository.findByOrderIdNum(orderId);

        Logistics logistics_new = new Logistics();
        logistics_new.setLogistic(logistics.getLogistic());
        logistics_new.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        logistics_new.setStatus("배송 중");

        return logisticsRepository.save(logistics_new);

    }

    public Logistics saveLogistics_delivery_fin(Long orderId) {

        Logistics logistics = logisticsRepository.findByOrderIdNum(orderId);

        Logistics logistics_new = new Logistics();
        logistics_new.setLogistic(logistics.getLogistic());
        logistics_new.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        logistics_new.setStatus("배송 완료");

        return logisticsRepository.save(logistics_new);

    }


    public List<Logistics> findByLogistic(String logistic) {
        List<Logistics> logistics = logisticsRepository.findByLogistic(logistic);
        return logistics;
    }

}
