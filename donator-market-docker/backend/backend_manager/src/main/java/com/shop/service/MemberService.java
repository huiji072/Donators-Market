package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Properties;

@Slf4j
@Service
@Transactional //로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백
@RequiredArgsConstructor //final이나 @NotNull에 붙은 필드에 생성자를 생성
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    //이메일 중복 처리
    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


    public Member login(String email, String password) {

        Optional<Member> member = memberRepository.findByEmail(email);

        if(passwordEncoder.matches(password, member.get().getPassword())) {
            return memberRepository.findByEmailAndPassword(email, member.get().getPassword())
                    .orElse(null);
        }else{
            return null;
        }

    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);

        if(!member.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.get().getEmail())
                .password(member.get().getPassword())
                .roles(member.get().getRole().toString())
                .build();
    }

    public Optional<Member> loginInfo(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        return memberRepository.findByEmailAndRoleAndName(email, member.get().getRole(), member.get().getName());
    }

    //    soft delete

    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public void remove(Long id){
        memberRepository.deleteById(id);
    }

    public Iterable<Member> findAll(){
        return memberRepository.findAll();
    }


    public Page<Member> getMemberManagement(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    Properties properties = new Properties();



}
