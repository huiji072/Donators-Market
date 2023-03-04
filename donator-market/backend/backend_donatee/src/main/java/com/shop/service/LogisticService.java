//package com.shop.service;
//
//import com.shop.entity.Logistics;
//import com.shop.repository.LogisticRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class LogisticService {
//
//    private final LogisticRepository logisticRepository;
//
//    public List<Logistics> findByLogistic(String logistic) {
//        List<Logistics> logistics = logisticRepository.findByLogistic(logistic);
//        return logistics;
//    }
//
//}
