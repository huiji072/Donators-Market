package com.shop.controller;

import com.shop.dto.*;
import com.shop.service.OfferService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping(value = "/offer/new")
    public String offer(@RequestBody @Valid OfferDto offerDto
            ,Principal principal){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            String email = principal.getName();
            offerService.offer(offerDto, email);
        } catch(Exception e){
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

    @GetMapping(value = {"/offers"})
    public String offerHist(@RequestParam(value = "pageNum", required = false) int pageNum, Principal principal){
        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            Long offerCount = offerService.countOffer(principal.getName());
            result.put("offerCount", offerCount);

            Pageable pageable = PageRequest.of(pageNum, 5);
            Page<OfferHistDto> offersHistDtoList = offerService.getOfferList(principal.getName(), pageable);
            JSONArray offerJsonArray = new JSONArray();

            for(OfferHistDto o : offersHistDtoList) {
                JSONObject offerObj = new JSONObject();
                offerObj.put("id", o.getOfferId());
                offerObj.put("status", o.getOfferStatus());
                offerObj.put("date", o.getOfferDate());
                offerObj.put("logistic", o.getOfferLogistic());
                offerObj.put("itemDtoList", o.getOfferItemDtoList());
                offerJsonArray.put(offerObj);
            }
            result.put("offerItem", offerJsonArray);
            result.put("pageNum", pageNum);
        } catch(Exception e){
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);

    }

    @PostMapping("/offer/{offerId}/cancel")
    public String cancelOffer(@PathVariable("offerId") Long offerId , Principal principal){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            offerService.cancelOffer(offerId);
        } catch(Exception e){
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

}
