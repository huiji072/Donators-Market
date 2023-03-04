package com.shop.controller;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.service.ItemService;
import com.shop.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;
    private final SellerService sellerService;

    @GetMapping(value = "/main")
    public String main(ItemSearchDto itemSearchDto, @RequestParam(value = "pageNum", required = false) int pageNum){

        JSONObject itemJsonObject = new JSONObject();
        boolean problem = false;

        try{
            Long itemCount = itemService.itemCount();
            itemJsonObject.put("itemCount", itemCount);

            Pageable pageable = PageRequest.of(pageNum, 9);
            Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

            JSONArray itemJsonArray = new JSONArray();

            for(MainItemDto i : items) {
                JSONObject itemObj = new JSONObject();
                itemObj.put("id", i.getId());
                itemObj.put("name", i.getItemNm());
                itemObj.put("dtl", i.getItemDetail());
                itemObj.put("stockNumber", i.getStockNumber());
                itemObj.put("imgUrl", i.getImgUrl());
                itemObj.put("oriImgName", i.getOriImgName());
                itemJsonArray.put(itemObj);
            }
            itemJsonObject.put("items", itemJsonArray);

//        items
            JSONArray itemsJsonArray = new JSONArray();
            JSONObject itemsObj = new JSONObject();
            itemsObj.put("itemsNumber", items.getNumber());
            itemsObj.put("itemsTotalPages", items.getTotalPages());
            itemsObj.put("pageNum", pageNum);
            itemsJsonArray.put(itemsObj);
            itemJsonObject.put("itemsObj", itemsJsonArray);


            //itemSearchDto
            JSONArray itemSearchDtoJsonArray = new JSONArray();
            JSONObject itemSearchDtoJsonObj = new JSONObject();
            itemSearchDtoJsonObj.put("searchBy", itemSearchDto.getSearchBy());
            itemSearchDtoJsonObj.put("searchQuery", itemSearchDto.getSearchQuery());
            itemSearchDtoJsonObj.put("searchDateType", itemSearchDto.getSearchDateType());
            itemSearchDtoJsonObj.put("searchSellStatus", itemSearchDto.getSearchSellStatus());
            itemSearchDtoJsonArray.put(itemSearchDtoJsonObj);
            itemJsonObject.put("itemSearchDto", itemSearchDtoJsonArray);

//        maxPage
            JSONArray pageJsonArray = new JSONArray();
            JSONObject pageJsonObj = new JSONObject();
            pageJsonObj.put("maxPage", 5);
            pageJsonArray.put(pageJsonObj);
            itemJsonObject.put("maxPage", pageJsonArray);

        }catch (Exception e){
            problem = true;
            log.error(e.getMessage());
        }

        itemJsonObject.put("problem", problem);

       return itemJsonObject.toString();
    }

}
