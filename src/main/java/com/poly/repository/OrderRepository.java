package com.poly.repository;

import com.poly.entity.Order;
import com.poly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository  extends JpaRepository<Order,Integer> {
    void deleteByUser(User user);

    Optional<Order> findByUser(User user);
}
