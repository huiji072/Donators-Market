package com.shop.controller;

import com.shop.constant.Role;
import com.shop.dto.LoginFormDto;
import com.shop.dto.MemberFormDto;
import com.shop.entity.Buyer;
import com.shop.entity.Member;
import com.shop.entity.Seller;
import com.shop.service.BuyerService;
import com.shop.service.MemberService;
import com.shop.service.SellerService;
import com.shop.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Slf4j
@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController{

    private final MemberService memberService;

    @PostMapping("/loginInfo")
    public String retrieveTodoList(
            @AuthenticationPrincipal String userEmail, Principal principal) {
        JSONObject jsonObject = new JSONObject();
        if (!Objects.equals(userEmail, "anonymousUser")) {
            Optional<Member> member = memberService.loginInfo(userEmail);
            jsonObject.put("email", member.get().getEmail());
            jsonObject.put("name", member.get().getName());
            jsonObject.put("role", member.get().getRole());

            log.debug("auth: " +principal);

            return String.valueOf(jsonObject);
        } else {
            return "anonymousUser";
        }

    }

}
