package com.shop.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.dto.*;
import com.shop.entity.Item;
import com.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.ui.Model;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private boolean isLoginUser(Principal principal){
        return principal != null;
    }

    //    카트 등록하기
    @PostMapping(value = "/cart/new")
    public String order(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult
            , Principal principal, HttpServletRequest request, HttpServletResponse response){

        JSONObject result = new JSONObject();
        boolean problem = false;
        String email;
        Long cartItemId = null;

        if(isLoginUser(principal)){
            try {
//            화면으로부터 넘어온 장바구니에 담을 상품 정보와 현재 로그인한 회원의 이메일 정보를
//            이용하여 장바구니에 상품을 담는 로직을 호출
                email = principal.getName();
                cartItemId = cartService.addCart(cartItemDto, email);
            } catch(Exception e){
                problem = true;
                log.error(e.getMessage());
            }
        }else{

            try {
                cartItemId = cartService.addCartNonMember(cartItemDto, response, request);
            } catch (ParseException e) {
                problem = true;
                log.error(e.getMessage());
            }
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

    //    카트 리스트 보내기
    @GetMapping(value = "/carts")
    public String orderHist(HttpServletRequest request, Principal principal, Model model, String cookies) {

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            if(isLoginUser(principal)){
                List<CartDetailDto> cartDetailList = cartService.getCartList(principal.getName());
                model.addAttribute("cartItems", cartDetailList);

                JSONArray cartJsonArray = new JSONArray();

                for(CartDetailDto c : cartDetailList) {
                    JSONObject cartObj = new JSONObject();
                    cartObj.put("itemId", c.getCartItemId());
                    cartObj.put("itemNm", c.getItemNm());
                    cartObj.put("count", c.getCount());
                    cartObj.put("itemSeller", c.getItemSeller());
                    cartObj.put("imgUrl", c.getImgUrl());
                    cartJsonArray.put(cartObj);
                }
                result.put("cartItem", cartJsonArray);

            }else{
                List<CartDetailNonMemberDto> cartDetailNonMemberDtoList = cartService.getCartListNonMember(request);
                model.addAttribute("cartItems", cartDetailNonMemberDtoList);

                JSONArray cartJsonArray = new JSONArray();

                for(CartDetailNonMemberDto c : cartDetailNonMemberDtoList) {
                    JSONObject cartObj = new JSONObject();
                    cartObj.put("itemId", c.getItemId());
                    cartObj.put("itemNm", c.getItemNm());
                    cartObj.put("count", c.getCount());
                    cartObj.put("itemSeller", c.getItemSeller());
                    cartObj.put("imgUrl", c.getImgUrl());
                    cartJsonArray.put(cartObj);
                }
                result.put("cartItem", cartJsonArray);
            }
        } catch (Exception e) {
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);

    }


    @PatchMapping(value = "/cartItem/{cartItemId}")
    public String updateCartItem(@PathVariable("cartItemId") Long cartItemId, int count, Principal principal){
        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            cartService.updateCartItemCount(cartItemId, count);
            if(count <= 0){
                problem = true;
                log.error("최소 1개 이상 담아주세요");
            }
        } catch (Exception e) {
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

    @DeleteMapping(value = "/cartItem/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Long cartItemId, Principal principal){
        JSONObject result = new JSONObject();
        boolean problem = false;
        try{
            cartService.deleteCartItem(cartItemId);
        } catch (Exception e) {
            problem = true;
            log.error(e.getMessage());
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }


    @PostMapping(value = "/cart/orders")
    public String orderCartItem(@RequestPart(value = "data", required = false) CartOrderDto cartOrderDto, Principal principal,
                                @RequestPart(value = "data2", required = false)CartOfferDto cartOfferDto){
        JSONObject result = new JSONObject();
        boolean problem = false;

        System.out.println("cartOrderDto: " + cartOrderDto);
        System.out.println(cartOfferDto);

        try {
            List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();
            List<CartOfferDto> cartOfferDtoList = cartOfferDto.getCartOfferDtoList();
            System.out.println(cartOfferDtoList.get(0).getCartItemId());
            if(cartOrderDtoList == null || cartOrderDtoList.size() == 0){
                problem = true;
                log.error("최소 1개 이상은 담아주세요");
            }

            cartService.orderCartItem(cartOrderDtoList, principal.getName());
            cartService.offerCartItem(cartOfferDtoList, principal.getName());
        } catch (Exception e) {
            problem = true;
            log.error(e.getMessage());
            e.printStackTrace();
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }


}