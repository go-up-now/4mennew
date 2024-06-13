package com.poly.dto.request;

import com.poly.entity.Order;
import com.poly.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreateRequest {
    int id;
    int price;
    int discount;
    int num;
    int totalPrice;
    Product product;
    LocalDate dateOrder;

}
