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
    private final static String NON_LOGIN_USER_COOKIE_NAME = "cart_cookie";

    //회원일 때 장바구니에 담기
    public Long addCart(CartItemDto cartItemDto, String email){

        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        Optional<Member> member = memberRepository.findByEmail(email);
//        Optional<Item> item1 = itemRepository.findById(id);

        Cart cart = cartRepository.findByMemberId(member.get().getId());

        //상품을 처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티를 생성한다.
        if(cart == null){
            cart = Cart.createCart(member.get());
            cartRepository.save(cart);
        }

        //현재 상품이 장바구니에 이미 있는지 조회
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null){
            //장바구니에 있으면 수량을 더해준다.
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            //장바구니에 들어갈 상품 저장
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    //비회원일 때 장바구니에 담기
    public Long addCartNonMember(CartItemDto cartItemDto, HttpServletResponse response, HttpServletRequest request) throws ParseException {

        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        Cookie[] cookies = request.getCookies();
        String cookieJsonString = "";
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(NON_LOGIN_USER_COOKIE_NAME)){
                cookieJsonString = cookie.getValue();
                break;
            }
        }

        String jsonString = "";
        //cookiesjsonString이 없으면 item을 json으로 변환, count = 1
        if(cookieJsonString.length() == 0) {
            jsonString = createNewCart(item.getId());
        }else{
            //cookiejsonString이 존재하면 count++을 해줌
            jsonString = addOrUpdateCart(cookieJsonString, item.getId());
        }

        log.info(jsonString);
//       json형식의 쿠키를 Encoding해서 cartCookie에 담음
        Cookie cartCookie = new Cookie(NON_LOGIN_USER_COOKIE_NAME, URLEncoder.encode(jsonString, Charset.defaultCharset()));
        cartCookie.setMaxAge(3600);
        cartCookie.setPath("/");
        response.addCookie(cartCookie);


        return Long.valueOf(item.getId());
    }

    //cookie를 json 형식으로 만들어서 String으로 반환
    private String createNewCart(Long itemId){
        JSONObject item = new JSONObject();
        item.put("item", itemId);
        item.put("count", 1);

        JSONArray items = new JSONArray();
        items.put(item);

        JSONObject cart = new JSONObject();
        cart.put("cart", items);

        return cart.toString();
    }

    private String addOrUpdateCart(String jsonString, Long itemId) {

//       json형식의 쿠키를 decoding해서 decodeJsonString에 담음
        String decodeJsonString = URLDecoder.decode(jsonString, Charset.defaultCharset());

        JSONObject cart = new JSONObject(decodeJsonString);

        JSONArray items = cart.getJSONArray("cart");
        boolean isExist = false;
        long cartCount = 0L;
        long cartId = 0L;
        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            long cartItemId = item.getLong("item");
            log.info(item.toString());
            //cart에 이미 같은 itemId가 존재하면
            if(itemId == cartItemId){
                isExist = true;

//                쿠키에서 itemId와 count를 갖고온다.
                cartId = item.getLong("item");
                cartCount = item.getLong("count");
//                같은 id가 존재할 수 없으니 items를 지워준다.
                items.remove(i);

//                count++한 cartItem을 새롭게 생성해준다.
                JSONObject cartItem = new JSONObject();
                cartItem.put("item", cartId);
                cartItem.put("count", ++cartCount);
                items.put(cartItem);
                break;
            }
        }
//        존재하지 않는다면 item을 새롭게 만들어서 cart에 담아준다.
        if(!isExist) {
            JSONObject item = new JSONObject();

            item.put("item", itemId);
            item.put("count", 1);
            items.put(item);
        }
        cart.put("cart", items);

        return cart.toString();
    }


    //회원일 때 장바구니 목록
    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Optional<Member> member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.get().getId());
        if(cart == null){
            return cartDetailDtoList;
        }

        log.info(cart.getId() + "cartId");
        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());

        for(CartDetailDto c : cartDetailDtoList) {
            log.info("cartDetailDto : " + c.getItemNm());
        }

        return cartDetailDtoList;
    }

    //비회원일 때 장바구니 목록
    @Transactional(readOnly = true)
    public List<CartDetailNonMemberDto> getCartListNonMember(HttpServletRequest request) {

        List<CartDetailNonMemberDto> cartDetailNonMemberDtoList = new ArrayList<>();
        String cookieJsonString = "";

        //쿠키명이 같을 경우 cookieValue에 값 넣음
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(NON_LOGIN_USER_COOKIE_NAME)) {
                cookieJsonString = cookie.getValue();
                break;
            }
        }


        if (cookieJsonString.length() > 0) {
            String decodeJsonString = URLDecoder.decode(cookieJsonString, Charset.defaultCharset());

//             {"cart":[{"item":193,"count":1},{"item":253,"count":2}]}
            log.info("cookieJsonString : " + decodeJsonString);

            JSONObject cart = new JSONObject(decodeJsonString);
            JSONArray items = cart.getJSONArray("cart");

            for(int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                long cartItemId = item.getLong("item");
                long cartCount = item.getLong("count");
                log.info("item Value : " + cartItemId);
                log.info("count Value : " + cartCount);

                CartDetailNonMemberDto dto = cartItemRepository.findCartDetailNonMemberDto(cartItemId);
                dto.setCount((int) cartCount);
                cartDetailNonMemberDtoList.add(dto);
            }


        }


        return cartDetailNonMemberDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email){
        Optional<Member> curMember = memberRepository.findByEmail(email);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();

        if(!StringUtils.equals(curMember.get().getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

}