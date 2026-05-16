package com.app.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecom.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
