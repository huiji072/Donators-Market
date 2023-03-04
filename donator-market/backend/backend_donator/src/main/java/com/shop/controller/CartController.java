package com.shop.controller;
import com.shop.dto.*;
import com.shop.entity.Item;
import com.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.ui.Model;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/cart/offers")
    public  String offerCartItem(@RequestBody CartOfferDto cartOfferDto, Principal principal){
        JSONObject result = new JSONObject();
        boolean problem = false;

        System.out.println("cartOfferDto " + cartOfferDto);

        try {
            List<CartOfferDto> cartOfferDtoList = cartOfferDto.getCartOfferDtoList();
            cartService.offerCartItem(cartOfferDtoList, principal.getName());
        } catch (Exception e) {
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

}