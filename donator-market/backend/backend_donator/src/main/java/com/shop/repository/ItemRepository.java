package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.CartDetailNonMemberDto;
import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>,
QuerydslPredicateExecutor<Item>, ItemRepositoryCustom{

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    @Query(("select i.createdBy from Item i " +
            "where i.id = :itemId"))
    String findByCreatedBy(@Param("itemId") Long itemId);

    Optional<Item> findById(Long id);

    List<Item> findAll();

    List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);

    @Query("select count(*) " +
    "from Item i " +
    "where i.createdBy = :email")
    Long countBy(@Param("email") String email);

}