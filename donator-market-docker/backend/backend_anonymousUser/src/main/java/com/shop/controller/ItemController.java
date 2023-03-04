package com.shop.controller;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.shop.entity.Answer;
import com.shop.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import com.shop.dto.ItemFormDto;

import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final AnswerService answerService;


//    상품 상세
    @GetMapping(value = "/itemDtl/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId,
                          @RequestParam(value = "pageNum", required = false)int pageNum
                          ,@PageableDefault Pageable pageable){

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            //        상품 상세
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("item", itemFormDto);
            JPAUpdateClause update =  itemService.getUpdateItemPage();

            JSONArray itemJsonArray = new JSONArray();

            JSONObject itemObj = new JSONObject();
            itemObj.put("id", itemFormDto.getId());
            itemObj.put("name", itemFormDto.getItemNm());
            itemObj.put("dtl", itemFormDto.getItemDetail());
            itemObj.put("imgId", itemFormDto.getItemImgIds());
            itemObj.put("sellStatus", itemFormDto.getItemSellStatus());
            itemObj.put("imgDtoList", itemFormDto.getItemImgDtoList());
            itemObj.put("stockNumber", itemFormDto.getStockNumber());
            itemJsonArray.put(itemObj);

            result.put("item", itemJsonArray);

//        질문과 답변

            Long commentCount = answerService.commentCount(itemId);
            result.put("commentCount", commentCount);

            pageable = PageRequest.of(pageNum, 5);
            Page<Answer> answerPageList = answerService.getAnswerPage(pageable, itemId);

            JSONArray commentJsonArray = new JSONArray();
            for(Answer answer : answerPageList) {
                JSONObject commentObj = new JSONObject();
                commentObj.put("answerId", answer.getId());
                commentObj.put("answerEmail", answer.getCreatedBy());
                commentObj.put("answerContent", answer.getContent());
                commentObj.put("answerRegTime", answer.getRegTime());
                commentObj.put("answerDepth", answer.getDepth());
                commentObj.put("groupId", answer.getGroupId());
                commentJsonArray.put(commentObj);
            }
            result.put("comment", commentJsonArray);

//        페이징
            int startPage = Math.max(1, answerPageList.getPageable().getPageNumber() - 4);
            int endPage = Math.min(answerPageList.getTotalPages(), answerPageList.getPageable().getPageNumber() + 4);
            result.put("pageNum", pageNum);
        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }
        result.put("problem", problem);
        return String.valueOf(result);
    }

}
