//package com.shop.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter @Setter
//@Entity
//@Table(name = "question")
//public class Question extends BaseEntity{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(columnDefinition = "TEXT")
//    private String content; //질문내용
//
//    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
//
//    private Integer depth;
//
//}
