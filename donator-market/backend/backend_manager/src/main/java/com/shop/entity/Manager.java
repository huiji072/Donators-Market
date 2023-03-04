package com.shop.entity;

import com.shop.constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "manager")
@Getter
@Setter
public class Manager {

    @Id
    @Column(name="manager_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="member_id")
    private Long memberid;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid", insertable = false, updatable = false)
    private Member member;

    public static Manager createManager() {

        Manager manager = new Manager();
        manager.setRole(Role.MANAGER);
        return manager;
    }
}


