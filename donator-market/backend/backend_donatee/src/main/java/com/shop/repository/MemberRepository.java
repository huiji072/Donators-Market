package com.shop.repository;

import com.shop.constant.Role;
import com.shop.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Page<Member> findAll(Pageable pageable);

    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByPassword(String password);

    Optional<Member> findByEmailAndRoleAndName(String email, Role role, String name);

}
