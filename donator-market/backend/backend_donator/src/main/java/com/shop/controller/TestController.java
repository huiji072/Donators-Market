package com.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping(value = "/naver")
    public String moveToNaver() {

        log.info("=============send================");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param", "param");

        HttpHeaders headers = new HttpHeaders();
        headers.add("test", "test");
        headers.add("data", "data");
        headers.add("test2", "test2");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                "http://172.30.1.251:8081/test", //{요청할 서버 주소}
                HttpMethod.GET, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class
        );
        return "test";

    }
}
