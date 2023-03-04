package com.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shop.dto.LogisticsDto;
import com.shop.dto.OrderDto;
import com.shop.service.OrderService;
import com.shop.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RabbitMQControlller {

    private static final String EXCHANGE_NAME = "shop.exchange";
    private final OrderService orderService;
    private final RabbitMQService rabbitMQService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

//    송장번호 달라고 요청 주문번호를 날려야함 offeritem의 id
    @PostMapping("/queue/invoice/order")
    public String sendInvoiceMessage(@RequestBody @Valid OrderDto orderDto, Principal principal) {
        String email = principal.getName();
        Long orderId = orderService.order(orderDto, email);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "shop.invoice.order.key", orderId);
        log.info("========================================" + orderId);
        return "invoice message sending";
    }

//    송장번호를 받음
    @RabbitListener(queues = "shop.queue.logistics.order")
    public void invoiceReceiver(String message) throws JsonProcessingException {

        try {
            LogisticsDto logistics = objectMapper.registerModule(new JavaTimeModule()).readValue(message, LogisticsDto.class);
            rabbitMQService.orderAddLogistics(logistics.getOrderIdNum(), logistics.getLogistic());
            log.info("===================logistic number message=========== : " + message);
            log.info("order Id : " + logistics.getOrderIdNum());
            log.info("logistic number : " + logistics.getLogistic());
        } catch (Exception e){
            log.error(e.toString());
        }

    }

//  화면에서 입력한 배송번호를 큐로 logositc에 전송
    @PostMapping("/logistics/search")
    public String searchLogistics(@RequestParam(value = "searchName", required = false)String searchName) {
        log.info("========search name sending================");
        //return 값이 logistics/searchName으로 반환
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "search.name.key", searchName);
        return "searchName";
    }

    @GetMapping("/logistics/show")
    public String showLogistics() throws JsonProcessingException {

        RestTemplate restTemplate = restTemplateBuilder.build();

        String url = System.getenv("logistic_search_ip");
        String port = System.getenv("logistic_search_port");

        HttpHeaders headers = new HttpHeaders();
        headers.set("shop-1", "data-1");
        headers.set("shop-2", "data-2");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://"+url+":"+port+"/logistics/searchName",
                HttpMethod.GET,
                request,
                String.class
        );

        log.info("request header : " + request.getHeaders());

        log.info("response body : " + response.getBody());

//        String getLogistics = restTemplate.getForObject
//                ("http://"+url+":8081/logistics/searchName", String.class);
//        log.info("getLogistics no header: " + getLogistics);

        List<LogisticsDto> logistic = objectMapper.registerModule(new JavaTimeModule()).readValue(response.getBody(), new TypeReference<List<LogisticsDto>>(){});
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(LogisticsDto l : logistic) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("logistic", l.getLogistic());
            jsonObject.put("status", l.getStatus());
            jsonObject.put("date", l.getDate());
            jsonArray.put(jsonObject);
        }
        result.put("logistic", jsonArray);

        return result.toString();
    }

}
