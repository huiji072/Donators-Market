package com.shop.repository;

import com.shop.entity.Logistics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogisticsRepository  extends MongoRepository<Logistics, String> {

    List<Logistics> findByLogistic(String logistic);
    Logistics findByOrderIdNum(Long orderIdNum);

}
