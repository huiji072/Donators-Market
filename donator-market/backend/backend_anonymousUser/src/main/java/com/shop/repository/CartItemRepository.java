package com.shop.repository;

import com.shop.dto.CartDetailNonMemberDto;
import com.shop.dto.CartItemDto;
import com.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.dto.CartDetailDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem save(CartItemDto cart);

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemNm, ci.count, im.imgUrl, i.createdBy, im.oriImgName) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc")
    List<CartDetailDto> findCartDetailDtoList(@Param("cartId") Long cartId);

    @Query("select new com.shop.dto.CartDetailNonMemberDto(i.id, i.itemNm, i.stockNumber, im.imgUrl, i.createdBy, im.oriImgName) " +
            "from ItemImg im, Item i " +
            "where i.id = :itemId " +
            "and im.repimgYn = 'Y' " +
            "and i.id = im.item.id"
    )
    CartDetailNonMemberDto findCartDetailNonMemberDto(@Param("itemId") Long itemId);





}