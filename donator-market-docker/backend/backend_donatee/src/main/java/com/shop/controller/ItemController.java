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

    
//    질문등록
    @PostMapping("/item/registerQuestion")
    public @ResponseBody String registerQuestion(@RequestBody String questionInput, Model model, Principal principal) {

        boolean problem = false;
        JSONObject jsonObject = new JSONObject(questionInput);


        try {
            String questionContent = jsonObject.getString("question");
            String itemId = String.valueOf(jsonObject.get("itemId"));

            Item item = new Item();
            item.setId(Long.valueOf(itemId));

            Answer answer = new Answer();
            answer.setContent(questionContent);
            answer.setDepth(0);
            answer.setGroupId(answer.getId()); //질문의 groupId 값은 해당 id 값이다.
            answer.setItem(item);
            answerService.addAnswer(answer);
            answer.setGroupId(answer.getId()); //질문의 groupId 값은 해당 id 값이다.
            answerService.addAnswer(answer);

            jsonObject.put("questionInput", questionContent);
            jsonObject.put("questionEmail", principal.getName());
            jsonObject.put("regTime", answer.getRegTime());
            jsonObject.put("questionId", answer.getId());
            jsonObject.put("questionDepth", answer.getDepth());
            jsonObject.put("groupId", answer.getGroupId());
        } catch (Exception e){
            problem = true;
            log.error(e.getMessage(), e);
        }

        jsonObject.put("problem", problem);
        return String.valueOf(jsonObject);
    }

}
