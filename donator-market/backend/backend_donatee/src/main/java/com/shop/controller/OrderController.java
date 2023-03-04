package com.shop.controller;

import com.shop.dto.*;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @PostMapping(value = "/order/new")
//    public String order(@RequestBody @Valid OrderDto orderDto
//            , Principal principal){
//
//        JSONObject result = new JSONObject();
//        boolean problem = false;
//
//        try {
//            String email = principal.getName();
//            orderService.order(orderDto, email);
//        } catch(Exception e){
//            problem = true;
//            log.error(e.getMessage());
//        }
//        result.put("problem", problem);
//        return String.valueOf(result);
//    }

    @GetMapping(value = {"/orders"})
    public String orderHist( @RequestParam(value = "pageNum", required = false) int pageNum, Principal principal){
        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            Long orderCount = orderService.orderCount(principal.getName());
            result.put("orderCount", orderCount);
            Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

            Pageable pageable = PageRequest.of(pageNum, 5);
            Page<OrderHistDto> ordersHistDtoList = orderService.getOrderList(auth.getName(), pageable);

            JSONArray orderJsonArray = new JSONArray();

            for(OrderHistDto o : ordersHistDtoList) {
                JSONObject orderObj = new JSONObject();
                orderObj.put("id", o.getOrderId());
                orderObj.put("orderStatus", o.getOrderStatus());
                orderObj.put("orderDate", o.getOrderDate());
                orderObj.put("logistic", o.getLogistic());
                orderObj.put("itemDtoList", o.getOrderItemDtoList());
                orderJsonArray.put(orderObj);
            }
            result.put("orderItem", orderJsonArray);
            result.put("pageNum", pageNum);
        } catch(Exception e){
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }


    @PostMapping("/order/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId , Principal principal){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            orderService.cancelOrder(orderId);
        } catch(Exception e){
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

}