package com.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shop.entity.Logistics;
import com.shop.service.LogisticsService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RabbitMQController {

    private static final String EXCHANGE_NAME = "shop.exchange";
    private final LogisticsService logisticsService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(RabbitMQController.class);
    static List<Long> orderId = new ArrayList<>();

    static String searchNameData;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

//    송장번호 보내달라는 요청을 받고 출력
    @RabbitListener(queues = "shop.queue.invoice.order")
    public void invoiceReceiver(Long message) {
        log.info("order id : " + message.toString());
        orderId.add(message);
    }

//    송장번호를 MQ에 전송
    @Scheduled(cron = "0 30 2 * * *") //한시간마다 요청
    @GetMapping("/queue/send/logistics")
    public String sendInvoiceMessage_insu() throws JsonProcessingException {
        for(int i = 0 ; i < orderId.size() ; i++) {
            Logistics logistics = new Logistics();
            logisticsService.saveLogistics(logistics, orderId.get(i));
            String jsonLogistic = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(logistics);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "shop.queue.logistics.order.key", jsonLogistic);
            log.info(logistics.getLogistic());
        }

        return "logistics message sending";
    }

    @Scheduled(cron = "0 40 2 * * *") //한시간마다 요청
    @GetMapping("/queue/send/logistics2")
    public String sendInvoiceMessage2() throws JsonProcessingException {
        for(int i = 0 ; i < orderId.size() ; i++) {
            Logistics logistics = new Logistics();
            logisticsService.saveLogistics_delivery_ready(orderId.get(i));

            String jsonLogistic = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(logistics);

            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "shop.queue.logistics.order.key", jsonLogistic);
        }

        return "logistics message sending";
    }

    @Scheduled(cron = "0 50 2 * * *") //한시간마다 요청
    @GetMapping("/queue/send/logistics3")
    public String sendInvoiceMessage3() throws JsonProcessingException {

        for(int i = 0 ; i < orderId.size() ; i++) {
            Logistics logistics = new Logistics();
            logisticsService.saveLogistics_delivery_ing(orderId.get(i));

            String jsonLogistic = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(logistics);

            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "shop.queue.logistics.order.key", jsonLogistic);
        }

        return "logistics message sending";
    }

    @Scheduled(cron = "0 0 3 * * *") //한시간마다 요청
    @GetMapping("/queue/send/logistics4")
    public String sendInvoiceMessage4() throws JsonProcessingException {

        for(int i = 0 ; i < orderId.size() ; i++) {
            Logistics logistics = new Logistics();
            logisticsService.saveLogistics_delivery_fin(orderId.get(i));

            String jsonLogistic = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(logistics);

            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "shop.queue.logistics.order.key", jsonLogistic);
        }
        return "logistics message sending";
    }


//큐로 logistic을 받고 searchNameData에 값 저장
    @RabbitListener(queues = "logistic.searchName")
    public void getSearchName(String searchName) throws JsonProcessingException{

//        RestTemplate restTemplate = restTemplateBuilder.build();

        log.info("sended searchName - queue : " + searchName);
        searchNameData = searchName;

    }

//   return 값을 donatee에서 restTemplete을 이용하여 값을 가져감
    @GetMapping("/logistics/searchName")
    public String searchLogistics() throws JsonProcessingException {
        List<Logistics> logisticsList = logisticsService.findByLogistic(searchNameData);
        String jsonLogistic = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(logisticsList);
        log.info("jsonLogistic: " + jsonLogistic);
        return jsonLogistic;
    }

}
