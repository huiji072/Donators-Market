package com.shop.controller;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemImgDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Answer;
import com.shop.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import com.shop.dto.ItemFormDto;

import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.EntityNotFoundException;

import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final AnswerService answerService;

//    @CrossOrigin(origins = "*")
    @PostMapping(value = "/admin/item/new")
    public String itemNew(@RequestPart(value = "data", required = false) ItemFormDto itemFormDto,
                          @RequestPart(value = "imgFile", required = false) List<MultipartFile> imgFile,
                          Principal principal){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            itemService.saveItem(itemFormDto, imgFile);
        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }

        result.put("problem", problem);
        return String.valueOf(result);
    }


    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            ItemFormDto itemFormDto2 = itemService.getItemDtl(itemId);

            JSONArray itemJsonArray = new JSONArray();

            JSONObject itemObj = new JSONObject();
            itemObj.put("id", itemFormDto2.getId());
            itemObj.put("itemNm", itemFormDto2.getItemNm());
            itemObj.put("itemDetail", itemFormDto2.getItemDetail());
            itemObj.put("stockNumber", itemFormDto2.getStockNumber());
            itemObj.put("itemSellStatus", itemFormDto2.getItemSellStatus());
            itemObj.put("itemDtoList", itemFormDto2.getItemImgDtoList());
            itemObj.put("itemImgIds", itemFormDto2.getItemImgIds());
            itemJsonArray.put(itemObj);

            result.put("item", itemJsonArray);
        }catch (Exception e) {
            problem = true;
            log.error(e.getMessage(), e);
        }

        result.put("problem", problem);
        return result.toString();
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate( @Valid @RequestPart(value = "data", required = false) ItemFormDto itemFormDto,
                             Model model, @RequestPart(value = "imgFile", required = false) List<MultipartFile> imgFile){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            itemService.updateItem(itemFormDto, imgFile);
        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }
        result.put("problem", problem);
        return result.toString();
    }


    @GetMapping(value = {"/item/itemMng"})
    public String itemManage(ItemSearchDto itemSearchDto, Principal principal,
                             @RequestParam(value = "pageNum", required = false)int pageNum){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            Long itemMngCount = itemService.itemMngCount(principal.getName());
            result.put("itemMngCount", itemMngCount);

            JPAUpdateClause update = itemService.getUpdateItemPage();

            Pageable pageable = PageRequest.of(pageNum, 10);
            Page<Item> items = itemService.getUserItemPage(itemSearchDto, pageable);

            JSONArray itemJsonArray = new JSONArray();

            for(Item i : items) {
                JSONObject itemObj = new JSONObject();
                itemObj.put("id", i.getId());
                itemObj.put("name", i.getItemNm());
                itemObj.put("stockNumber", i.getStockNumber());
                itemObj.put("sellStatus", i.getItemSellStatus());
                itemObj.put("createdBy", i.getCreatedBy());
                itemObj.put("regTime", i.getRegTime());
                if (principal.getName().equals(i.getCreatedBy())) {
                    itemJsonArray.put(itemObj);
                }

            }

            result.put("item", itemJsonArray);
            result.put("pageNum", pageNum);
        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

//상품관리 - 검색
    @GetMapping(value = {"/item/search"})
    public String itemSearch(@RequestParam(value = "searchName", required = false)String searchName,
                             @RequestParam(value = "searchStatus", required = false) ItemSellStatus searchStatus){
        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            if(searchStatus == null || searchStatus == ItemSellStatus.ALL) {
                List<Item> itemList = itemService.findByItemNm(searchName);
                JSONArray itemJsonArray = new JSONArray();

                for(Item i : itemList) {
                    JSONObject itemObj = new JSONObject();
                    itemObj.put("id", i.getId());
                    itemObj.put("name", i.getItemNm());
                    itemObj.put("stockNumber", i.getStockNumber());
                    itemObj.put("sellStatus", i.getItemSellStatus());
                    itemObj.put("createdBy", i.getCreatedBy());
                    itemObj.put("regTime", i.getRegTime());
                    itemJsonArray.put(itemObj);
                }

                result.put("item", itemJsonArray);
            } else {
                List<Item> itemList = itemService.findByItemNmAndItemSellStatus(searchName, searchStatus);
                JSONArray itemJsonArray = new JSONArray();

                for(Item i : itemList) {
                    JSONObject itemObj = new JSONObject();
                    itemObj.put("id", i.getId());
                    itemObj.put("name", i.getItemNm());
                    itemObj.put("stockNumber", i.getStockNumber());
                    itemObj.put("sellStatus", i.getItemSellStatus());
                    itemObj.put("createdBy", i.getCreatedBy());
                    itemObj.put("regTime", i.getRegTime());
                    itemJsonArray.put(itemObj);
                }

                result.put("item", itemJsonArray);
        }

        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

    @GetMapping(value = "/admin/item/itemMngOrderByItemNmAsc")
    public String itemManageOrderByItemNmAsc(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page) {
        JPAUpdateClause update = itemService.getUpdateItemPage();
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Item> items = itemService.getUserItemPageOrderByItemNmAsc(itemSearchDto, pageable);

        return "item/itemMngOrderByItemNmAsc";
    }


//    질문답변
    @PostMapping("/item/registerAnswer")
    public @ResponseBody String registerAnswer(@RequestBody String answerInput, Principal principal) {

        boolean problem = false;
        JSONObject jsonObject = new JSONObject(answerInput);

        try {
            String answerContent = jsonObject.getString("answerInput");
            String questionId = String.valueOf(jsonObject.get("questionId"));
            String itemId = String.valueOf(jsonObject.get("itemId"));

            //해당 아이템을 업로드한 판매자만 답글을 달수 있다.
            String itemCreatedBy = itemService.findByCreatedByWithItemid(Long.valueOf(itemId));
            if(!Objects.equals(principal.getName(), itemCreatedBy)) {
                problem = true;
            } else {
                Item item = new Item();
                item.setId(Long.valueOf(itemId));

                Answer answer = new Answer();
                answer.setContent(answerContent);
                answer.setDepth(1);
                answer.setGroupId(Integer.valueOf(questionId));
                answer.setItem(item);
                answerService.addAnswer(answer);

                jsonObject.put("answerInput", answerContent);
                jsonObject.put("answerEmail", principal.getName());
                jsonObject.put("regTime", answer.getRegTime());
                jsonObject.put("questionId", questionId);
                jsonObject.put("answerDepth", answer.getDepth());
                jsonObject.put("groupId", answer.getGroupId());
            }

        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }

        jsonObject.put("problem", problem);
        return String.valueOf(jsonObject);
    }
}
