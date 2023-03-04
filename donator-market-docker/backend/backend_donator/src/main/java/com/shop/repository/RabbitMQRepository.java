package com.shop.repository;

import com.shop.entity.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RabbitMQRepository extends JpaRepository<Logistics, Long> {

}
