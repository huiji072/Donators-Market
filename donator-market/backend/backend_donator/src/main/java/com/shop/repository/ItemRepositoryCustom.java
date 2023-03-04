package com.shop.repository;

import com.querydsl.jpa.impl.JPAUpdateClause;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<Item> getUserItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    JPAUpdateClause getUpdateItemPage();

    Page<Item> getUserItemPageOrderByItemNmAsc(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);



}