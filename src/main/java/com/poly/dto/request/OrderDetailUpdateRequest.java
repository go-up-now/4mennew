package com.poly.dto.request;

import com.poly.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailUpdateRequest {
    int id;
    int price;
    int discount;
    int num;
    int totalPrice;
    Product product;

}
