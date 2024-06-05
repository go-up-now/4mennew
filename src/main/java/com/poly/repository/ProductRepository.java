package com.poly.repository;

import com.poly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT " +
            "new com.poly.report.ReportProductByQuantity(p.category, sum(p.price * p.quantityInStock), sum(p.quantityInStock)) " +
            "FROM Product p GROUP BY p.category")
    List<Product> getProductsByQuantity();
}
