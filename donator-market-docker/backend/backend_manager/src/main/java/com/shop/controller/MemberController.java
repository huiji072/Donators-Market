package com.shop.controller;

import com.shop.entity.Member;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController{

    private final MemberService memberService;
    static final List AUTHORITIES = new ArrayList();

    @GetMapping("/managements")
    public String memberManagement(@RequestParam(value = "pageNum", required = false)int pageNum) {

        JSONObject result = new JSONObject();
        boolean problem = false;

        try {
            Pageable pageable = PageRequest.of(pageNum, 10);
            Page<Member> memberList = memberService.getMemberManagement(pageable);

            JSONArray memberJsonArray = new JSONArray();
            for(Member m : memberList) {
                JSONObject memberObj = new JSONObject();
                memberObj.put("id", m.getId());
                memberObj.put("name", m.getName());
                memberObj.put("email", m.getEmail());
                memberObj.put("role", m.getRole());
                memberObj.put("address", m.getAddress());
                memberJsonArray.put(memberObj);
            }
            result.put("memberManagement", memberJsonArray);
        }catch (Exception e) {
            problem = true;
            log.error(e.getMessage(), e);
        }
        result.put("problem", problem);
        return result.toString();
    }

}
