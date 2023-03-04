package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "seller")
@Getter
@Setter
@ToString
public class Seller extends BaseEntity{

    @Id
    @Column(name="seller_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="member_id")
    private Long memberid;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid", insertable = false, updatable = false)
    private Member member;


    public static Seller createSeller() {

        Seller seller = new Seller();
        seller.setRole(Role.SELLER);
        return seller;
    }

}
