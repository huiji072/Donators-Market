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
    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final TokenProvider tokenProvider;
    static final List AUTHORITIES = new ArrayList();

    @GetMapping(value="/new")
    public MemberFormDto memberForm(@RequestBody MemberFormDto memberFormDto) {
        return new MemberFormDto();
    }


    @PostMapping(value = "/new")
    public  String memberForm(@Valid @RequestBody MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model,
                             @RequestParam(value="checkSeller", required = false) String sellerid,
                             @RequestParam(value="checkBuyer", required = false) String buyerid
    ) {
        JSONObject result = new JSONObject();
        boolean problem = false;

        try{

            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);

            //기부자
            if(Objects.equals(sellerid, "chkseller") && !Objects.equals(buyerid, "chkbuyer")) { //seller
                Seller seller = new Seller();
                seller.setMemberid(member.getId());
                member.setRole(Role.SELLER);

                sellerService.saveSeller(seller);
                //피기부 기관
            }else if(!Objects.equals(sellerid, "chkseller") && Objects.equals(buyerid, "chkbuyer")) { //buyer
                //회원가입시 seller의 role을 갖고있는 회원 이메일로 메일을 발송한다.
                MimeMessage m = mailSender.createMimeMessage();
                MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");

                h.setFrom("gmlwl0720@naver.com");
                h.setTo(member.getEmail());
                h.setSubject("[Donators Market] 회원 관련 안내드립니다.");
                h.setText(member.getEmail() + "님의 Donator Market 회원가입이 승인되었습니다.");

                mailSender.send(m);

                //회원가입 승인 메일 보내기가 성공하면 confirm y로 변경
                member.setConfirnyn("y");

                Buyer buyer = new Buyer();
                buyer.setMemberid(member.getId());
                member.setRole(Role.BUYER);
                member.setConfirnyn("y");
                buyerService.saveBuyer(buyer);

            }else if(Objects.equals(sellerid, "chkseller") && Objects.equals(buyerid, "chkbuyer")) { //seller, buyer

                //회원가입시 seller의 role을 갖고있는 회원 이메일로 메일을 발송한다.
                MimeMessage m = mailSender.createMimeMessage();
                MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");

                h.setFrom("gmlwl0720@naver.com");
                h.setTo(member.getEmail());
                h.setSubject("[Donators Market] 회원 관련 안내드립니다.");
                h.setText(member.getEmail() + "님의 Donator Market 회원가입이 승인되었습니다.");

                mailSender.send(m);

                //회원가입 승인 메일 보내기가 성공하면 confirm y로 변경
                member.setConfirnyn("y");
                member.setRole(Role.ADMIN);

                Seller seller = new Seller();
                seller.setMemberid(member.getId());
                sellerService.saveSeller(seller);

                Buyer buyer = new Buyer();
                buyer.setMemberid(member.getId());
                buyerService.saveBuyer(buyer);
            }

        }catch(Exception e) {
            problem = true;
            log.error(e.getMessage(), e);
        }

        result.put("problem", problem);
        return String.valueOf(result);
    }


//    로그인
    @PostMapping("/login/token")
    public String loginMembergetToken(@RequestBody LoginFormDto loginFormDto) {
        Member member = memberService.login(loginFormDto.getEmail(), loginFormDto.getPassword());
        JSONObject jsonObject = new JSONObject();
        if(member != null) {
            final String token = tokenProvider.create(member);
            final LoginFormDto responseLoginFormDto = LoginFormDto.builder()
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .token(token)
                    .build();
            jsonObject.put("email", responseLoginFormDto.getEmail());
            jsonObject.put("token", responseLoginFormDto.getToken());
            return String.valueOf(jsonObject);
        }else {
            log.error("Login failed");
        }
        return String.valueOf(loginFormDto);
    }


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
