package com.shop.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.shop.dto.*;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;

import org.thymeleaf.util.StringUtils;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OfferService offerService;
    private final static String NON_LOGIN_USER_COOKIE_NAME = "cart_cookie";


    public Long offerCartItem(List<CartOfferDto> cartOfferDtoList, String email){
        List<OfferDto> offerDtoList = new ArrayList<>();

        for (CartOfferDto cartOfferDto : cartOfferDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOfferDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OfferDto offerDto = new OfferDto();
            offerDto.setItemId(cartItem.getItem().getId());
            offerDto.setCount(cartItem.getCount());
            offerDtoList.add(offerDto);
        }

        Long offerId = offerService.offers(offerDtoList, email);
        for (CartOfferDto cartOfferDto : cartOfferDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOfferDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return offerId;
    }

}