package com.shop.repository;

import com.shop.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer>,
        PagingAndSortingRepository<Answer, Integer> {


    @Query(
            "select a " +
            "from Answer a inner join a.item i " +
            "where a.item.id = :itemId " +
            "order by a.groupId, a.depth "
             )
    Page<Answer> findAll(Pageable pageable, @Param("itemId") Long itemId);

}
