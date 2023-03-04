package com.shop.service;

import com.shop.entity.Offer;
import com.shop.entity.Order;
import com.shop.repository.OfferRepository;
import com.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RabbitMQService {

    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;

//    public Offer offerAddLogistics(Long offerIdNum, String logistic) {
//
//        Optional<Offer> offer = offerRepository.findById(offerIdNum);
//        offer.get().setLogistics(logistic);
//
//        return offer.get();
//    }

    public Order orderAddLogistics(Long orderIdNum, String logistic) {
        Optional<Order> order = orderRepository.findById(orderIdNum);
        order.get().setLogistics(logistic);

        return order.get();
    }


}
