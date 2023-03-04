package com.shop.entity;

import com.shop.constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "buyer")
@Getter
@Setter
@ToString
public class Buyer extends BaseEntity{

    @Id
    @Column(name="buyer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="member_id")
    private Long memberid;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid", insertable = false, updatable = false)
    private Member member;


    public static Buyer createBuyer() {

        Buyer buyer = new Buyer();
        buyer.setRole(Role.BUYER);
        return buyer;
    }

}
